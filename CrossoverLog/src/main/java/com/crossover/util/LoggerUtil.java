package com.crossover.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.crossover.model.AppLog;


public class LoggerUtil {

	private static Logger logThis = LoggerFactory.getLogger(LoggerUtil.class);
    
	public static void log(AppLog appLog) throws Exception{
		logThis.trace("in logging util");
		Logger logger = LoggerFactory.getLogger(appLog.getLogName());
		logThis.debug("Got Logger. Loggin for application now.");
		switch(appLog.getLogLevel()){
		case ERROR:
			logger.error(appLog.getMessage(),appLog.getMessageObjects());
			break;
		case WARN:
			logger.warn(appLog.getMessage(),appLog.getMessageObjects());
			break;
		case INFO:
			logger.info(appLog.getMessage(),appLog.getMessageObjects());
			break;
		case DEBUG:
			logger.debug(appLog.getMessage(),appLog.getMessageObjects());
			break;
		case TRACE:
			logger.trace(appLog.getMessage(),appLog.getMessageObjects());
			break;
		}
	}
	
}
