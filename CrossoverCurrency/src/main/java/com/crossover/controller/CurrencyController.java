package com.crossover.controller;

import java.util.concurrent.Callable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.crossover.data.DataAccess;
import com.crossover.data.RestAccess;
import com.crossover.model.CurrencyRate;
import com.crossover.model.GeneralResponse;

/**
 * You are an architect in an organization that provides easy to scale and modern solutions, you are required to architect, design and implement a client server application, the client is either a modern web client or a desktop application with a simple user interface, the server is a virtual currency exchange rate publishing server and the communication channel is TCP or HTTP.

The server would expose different services for the client application to consume, list of exchange rates for different currencies, search for the exchange rate for a specific currency and a currency converter to convert a specific amount of money from a currency to another.

Use one of the public currency converter API(https://openexchangerates.org/signup/free, https://currencylayer.com/documentation, etc... ) at server side to get latest conversion rates.


The client application should provide simple and clear interface to the end user to perform different functionalities exposed by the server, listing currencies with their exchange rates, search box for a specific currency, currency exchange box and a refresh button to refresh the rates from the server.

The client application should be responsive, any freezes in the client app is not acceptable, the network operations should be Non blocking operations, the user should be notified that there is a server call and get notified once response received and rendered without any hangs.

You should use modern Java technologies to architect and implement the application with the mentioned specifications.
 * @author maggy
 *
 */
@Controller
public class CurrencyController {

	@Value("${appId}")
	private String appId;
	@Value("${defaultBase}")
	private String defaultBase;
	
	private static Logger log = LoggerFactory.getLogger(CurrencyController.class);
	
	@RequestMapping(path="/currency", method = RequestMethod.GET)
	public @ResponseBody Callable<CurrencyRate> currencyRates() throws Exception{
		
		log.trace("in method: currencyRates");
		return new Callable<CurrencyRate>() {
	        public CurrencyRate call() throws Exception {
	        	DataAccess dataAccess=getDataAccess();
	        	return dataAccess.getCurrencyRates(appId,defaultBase);
	        }
	    };
	}
	
	@RequestMapping(path="/currency/{base}", method = RequestMethod.GET)
	public @ResponseBody Callable<CurrencyRate> currencyRates(@PathVariable("base") final String base) throws Exception{
		
		log.trace("in method: currencyRates");
		return new Callable<CurrencyRate>() {
	        public CurrencyRate call() throws Exception {
	        	DataAccess dataAccess=getDataAccess();
	        	return dataAccess.getCurrencyRates(appId, base.toUpperCase());
	        }
	    };
	}
	
	@RequestMapping(path="/currency/{from}/{to}/{amount}", method = RequestMethod.GET)
	public @ResponseBody Callable<GeneralResponse> currencyConvertor(@PathVariable("from") final String from, 
			@PathVariable("to") final String to, @PathVariable("amount") final float amount) throws Exception{
		
		log.trace("in method: currencyConvertor");
		return new Callable<GeneralResponse>() {
	        public GeneralResponse call() throws Exception {
	        	DataAccess dataAccess=getDataAccess();
	        	return dataAccess.convert(appId, from.toUpperCase(), to.toUpperCase(), amount);
	        }
	    };
	}
	
	private DataAccess getDataAccess() throws Exception{
		DataAccess dataAccess=new RestAccess();
		return dataAccess;
	}
	
	@ExceptionHandler(Exception.class)
    public ResponseEntity<GeneralResponse> handleException(Exception ex) {
		log.error("Exception occured:",ex);
		GeneralResponse ge=new GeneralResponse(666,ex.getMessage(),false);
        return new ResponseEntity<GeneralResponse>(ge,new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }
}
