package com.crossover.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.crossover.data.DataAccess;
import com.crossover.data.MongoAccess;
import com.crossover.model.AppLog;
import com.crossover.model.GeneralResponse;
import com.crossover.util.LoggerUtil;

@Controller
public class LogController {

	private static Logger log = LoggerFactory.getLogger(LogController.class);
	
	private DataAccess getDataAccess() throws Exception{
		DataAccess dataAccess=new MongoAccess();
		return dataAccess;
	}
	
	@RequestMapping(path="/applog", method = RequestMethod.POST)
	public ResponseEntity<GeneralResponse> appLog(@RequestBody AppLog appLog) throws Exception{
		log.trace("App Log Request Processing started");
		LoggerUtil.log(appLog);
		log.debug("LoggerUtil has finished logging");
		GeneralResponse ge=new GeneralResponse(701,"App Logging Successful",true);
		log.info("App Logging successful. Sending Response");
		return new ResponseEntity<GeneralResponse>(ge,new HttpHeaders(),HttpStatus.OK);
	}
	
	@RequestMapping(path="/userlog/{userId}", method = RequestMethod.POST)
	public ResponseEntity<GeneralResponse> userLog(@RequestBody String userLog, @PathVariable("userId") String userId) throws Exception{
		log.trace("User Log Request Processing started");
		DataAccess dataAccess=getDataAccess();
		dataAccess.storeLog(userId, userLog);
		log.debug("DataAccess has finished logging");
		GeneralResponse ge=new GeneralResponse(702,"User Logging Successful",true);
		log.info("User Logging successful. Sending Response");
		return new ResponseEntity<GeneralResponse>(ge,new HttpHeaders(),HttpStatus.OK);
	}
	
	@RequestMapping(path="/userlog", method = RequestMethod.GET)
	public @ResponseBody List<String> userLogGET() throws Exception{
		DataAccess dataAccess=getDataAccess();
		return dataAccess.getUserLogs();
	}
	
	@ExceptionHandler(Exception.class)
    public ResponseEntity<GeneralResponse> handleException(Exception ex) {
		log.error("Exception occured:",ex);
		GeneralResponse ge=new GeneralResponse(666,ex.getMessage(),false);
        return new ResponseEntity<GeneralResponse>(ge,new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }
}
