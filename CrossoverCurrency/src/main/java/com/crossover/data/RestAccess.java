package com.crossover.data;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.crossover.model.CurrencyRate;
import com.crossover.model.GeneralResponse;

public class RestAccess implements DataAccess{
	
	private static Logger log = LoggerFactory.getLogger(RestAccess.class);
	
	private String url="https://openexchangerates.org/api/latest.json";

	public CurrencyRate getCurrencyRates(String appId, String baseCurrency) throws Exception {
		
		log.trace("in method: getCurrencyRates");
		CurrencyRate currRate = getLatestRates(appId);
		
		//Base currency details can be obtained directly from api but in a paid version.
		//Since not purchasing a paid version right now, doing the conversion at our end.
		Map<String, Float> rates=currRate.getRates();
		float baseCurrencyValue=rates.get(baseCurrency);
		Iterator<String> currItr=rates.keySet().iterator();
		while(currItr.hasNext()){
			String curr=currItr.next();
			rates.put(curr, rates.get(curr)/baseCurrencyValue);
		}
		currRate.setBase(baseCurrency);
		
		return currRate;
	}
	
	public GeneralResponse convert(String appId, String from, String to, float amount) throws Exception {
		
		log.trace("in method: convert");
		CurrencyRate currRate = getLatestRates(appId);
		float fromR=currRate.getRates().get(from);
		float toR=currRate.getRates().get(to);
		return new GeneralResponse(200,String.valueOf(toR/fromR * amount),true);
	}

	private CurrencyRate getLatestRates(String appId) throws Exception{
		
		log.trace("in method: getLatestRates");
		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url)
		        .queryParam("app_id", appId);
		        //.queryParam("base", baseCurrency);
		
	    RestTemplate restTemplate = new RestTemplate();
	    restTemplate.setMessageConverters(getMessageConverters());
	 
	    HttpEntity<String> entity = new HttpEntity<String>() {};
		
		ResponseEntity<CurrencyRate> response = 
			      restTemplate.exchange(builder.build().encode().toUri(), HttpMethod.GET, entity, CurrencyRate.class);
	    
		return response.getBody();
	}
	
	private List<HttpMessageConverter<?>> getMessageConverters() {
	    List<HttpMessageConverter<?>> converters = new ArrayList<HttpMessageConverter<?>>();
	    converters.add(new MappingJackson2HttpMessageConverter());
	    return converters;
	}

}
