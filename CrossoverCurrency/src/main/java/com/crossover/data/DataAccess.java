package com.crossover.data;

import com.crossover.model.CurrencyRate;
import com.crossover.model.GeneralResponse;

public interface DataAccess {

	public CurrencyRate getCurrencyRates(String appId, String baseCurrency) throws Exception;

	public GeneralResponse convert(String appId, String from, String to, float amount) throws Exception;
}
