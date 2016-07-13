package com.ques;

import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Write a program that monitors the availability of a webserver. The program takes 3 command line parameters: the web address, 
 * the frequency of checking the availability, and the time it should wait for a response from the webserver.
 * If the webserver is not available it should print a message.
 * @author maggy
 *
 */
public class MonitorWebServer {

	public static void main(String[] args){
		
		//EXAMPLE of Program Arguments-->http://maggyreviews.com/ 2000 2000
		if(args.length!=3){
			System.out.println("Wrong number of arguments passed");
			System.exit(0);
		}
		String webAdd=args[0];
		//Assuming frequency value in miliseconds
		long freq=Long.parseLong(args[1]);
		//Assuming response timeout value in miliseconds
		int resTimeout=Integer.parseInt(args[2]);
		
		boolean available = false;
		
		while(true){
			try{
				if(true){
			        HttpURLConnection connection = (HttpURLConnection) new URL(webAdd).openConnection();
			        connection.setConnectTimeout(resTimeout);
			        connection.setReadTimeout(resTimeout);
			        int responseCode = connection.getResponseCode();
			        if(200 <= responseCode && responseCode <= 399) available=true;
				}
			}catch (Exception e) {
		    	//e.printStackTrace();
			}finally{
				if(!available){
					System.out.println("Web Address is NOT available");
				}else{
					System.out.println("AVAILABLE");
				}
				try {
					Thread.sleep(freq);
				} catch (InterruptedException e) {}
			}
		}
	}
}
