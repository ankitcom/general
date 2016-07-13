package com.tpir.controller;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.tpir.model.GeneralResponse;
import com.tpir.model.Payload;
import com.tpir.model.Product;
import com.tpir.model.Subscribe;
import com.tpir.model.Subscribes;
import com.tpir.model.Unsubscribe;
import com.tpir.model.Unsubscribes;
import com.tpir.model.When;

@Controller
public class TpirController {

	/*
	 * Keeps the current minimum value in the Product object.
	 * Key is the product Id.
	 */
	private static Map<String,Product> prodMap = new HashMap<String,Product>();
	
	/*
	 * Keeps all the subscritions.
	 * Key is the product id and the When Enumeration. Value is the set of user ids that are subscribed.
	 */
	private static Map<String,Set<String>> subsMap = new HashMap<String, Set<String>>();
	
	/*
	 * Used to send final notifications.
	 * Key is the user id and the When Enumeration. Value is the payload object that has to be send to the user.
	 */
	private static Map<String, Payload> notifMap = new HashMap<String, Payload>();
	
	private static Logger log = LoggerFactory.getLogger(TpirController.class);
	
	@RequestMapping(path="/priceDataPoint", method = RequestMethod.POST)
	public ResponseEntity<GeneralResponse> priceDataPoint(@RequestBody Product prod) throws Exception{
		log.trace("At Method:priceDataPoint");
		if(prodMap.containsKey(prod.getProduct_id())){
			Product currProd=prodMap.get(prod.getProduct_id());
			prod.setMinPrice(currProd.getPrice());
			
			if(prod.getPrice()<currProd.getMinPrice()){
				prod.setMinPrice(prod.getPrice());
				String keySubs=prod.getProduct_id()+When.ALL_TIME_LOW;
				Iterator<String> itr = subsMap.get(keySubs).iterator();
				while(itr.hasNext()){
					String userId=itr.next();
					String keyNotif=userId+When.ALL_TIME_LOW;
					notifMap.put(keyNotif, new Payload(prod,When.ALL_TIME_LOW));
				}
			}
			if(prod.getPrice()<currProd.getPrice()*0.9){
				String keySubs=prod.getProduct_id()+When.MORE_THAN_10;
				Iterator<String> itr = subsMap.get(keySubs).iterator();
				while(itr.hasNext()){
					String userId=itr.next();
					String keyNotif=userId+When.MORE_THAN_10;
					notifMap.put(keyNotif, new Payload(prod,When.MORE_THAN_10));
				}
			}
			if(prod.getPrice()<currProd.getPrice()){
				String keySubs=prod.getProduct_id()+When.ALWAYS;
				Iterator<String> itr = subsMap.get(keySubs).iterator();
				while(itr.hasNext()){
					String userId=itr.next();
					String keyNotif=userId+When.ALWAYS;
					notifMap.put(keyNotif, new Payload(prod,When.ALWAYS));
				}
			}else{
				prod.setPrice(currProd.getPrice());
			}
		}else{
			prod.setMinPrice(prod.getPrice());
		}
		
		prodMap.put(prod.getProduct_id(), prod);
		
		GeneralResponse ge=new GeneralResponse(701,"Storing price data point successful",true);
		log.info("Storing price data point successful. Sending Response");
		return new ResponseEntity<GeneralResponse>(ge,new HttpHeaders(),HttpStatus.OK);
	}
	
	@RequestMapping(path="/subscribe", method = RequestMethod.POST)
	public ResponseEntity<GeneralResponse> subscribe(@RequestBody Subscribes subs) throws Exception{
		log.trace("At Method:subscribe");
		
		Iterator<Subscribe> itr=subs.getSubscribe().iterator();
		while(itr.hasNext()){
			Subscribe sub=itr.next();
			String key=sub.getProduct_id()+sub.getWhen();
			if(subsMap.containsKey(key)){
				subsMap.get(key).add(subs.getUserId());
			}else{
				Set<String> set=new HashSet<String>();
				set.add(subs.getUserId());
				subsMap.put(key,set);
			}
		}
		
		GeneralResponse ge=new GeneralResponse(701,"Subscribed",true);
		log.info("Subscription successful. Sending Response");
		return new ResponseEntity<GeneralResponse>(ge,new HttpHeaders(),HttpStatus.OK);
	}
	
	@RequestMapping(path="/unsubscribe", method = RequestMethod.POST)
	public ResponseEntity<GeneralResponse> unsubscribe(@RequestBody Unsubscribes unsubs) throws Exception{
		log.trace("At Method:unsubscribe");
		
		Iterator<Unsubscribe> itr=unsubs.getUnsubscribe().iterator();
		while(itr.hasNext()){
			Unsubscribe unsub=itr.next();
			When[] whenVal=When.values();
			for(int i=0;i<whenVal.length;i++){
				String key=unsub.getProduct_id()+whenVal[i];
				if(subsMap.containsKey(key)){
					subsMap.get(key).remove(unsubs.getUser_id());
				}
			}
		}
		
		GeneralResponse ge=new GeneralResponse(701,"Unsubscribed",true);
		log.info("Unsubscription successful. Sending Response");
		return new ResponseEntity<GeneralResponse>(ge,new HttpHeaders(),HttpStatus.OK);
	}
	
	//Cron that runs every 30 min to send the latest updated notifications to the subscribed users.
	@Scheduled(fixedDelay = 30*60*1000)
	public void notificationCron(){
		Iterator<String> itr=notifMap.keySet().iterator();
		while(itr.hasNext()){
			String key=itr.next();
			String userId=null;
			When[] whenVal=When.values();
			for(int i=0;i<whenVal.length;i++){
				if(key.indexOf(whenVal[i].toString())>0){
					userId=key.substring(0, key.indexOf(whenVal[i].toString()));
					break;
				}
			}
			Payload payload=notifMap.get(key);
			{
				//Using this payload object and userId value. Send the notif via any means (email etc)
				log.info("Sending notification to user:"+userId);
				log.info("Sending notification with the following payload:"+payload);
			}
		}
	}
	
	@ExceptionHandler(Exception.class)
    public ResponseEntity<GeneralResponse> handleException(Exception ex) {
		log.error("Exception occured:",ex);
		GeneralResponse ge=new GeneralResponse(666,ex.getMessage(),false);
        return new ResponseEntity<GeneralResponse>(ge,new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }
}
