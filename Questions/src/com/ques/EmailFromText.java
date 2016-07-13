package com.ques;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Write a program that takes a text file as input and finds all email addresses within the text and then prints all email addresses sorted 
 * and alphabetically ordered by domain name.
 * @author maggy
 *
 */
public class EmailFromText implements Comparable<EmailFromText>{

	public String email;
	public String domain;
	
	public EmailFromText(String email, String domain){
		this.email=email;
		this.domain=domain;
	}
	
	public static void main(String[] args){
		
		if(args.length!=1){
			System.out.println("Wrong number of arguments passed. Please pass just one argument as a text file path");
			System.exit(0);
		}
		
		String content=null;
		try {
			content = new String(Files.readAllBytes(Paths.get(args[0])));
		} catch (IOException e) {
			e.printStackTrace();
		}
		List<EmailFromText> emails=new ArrayList<EmailFromText>();
		//Splitting by whitespace characters including spaces, tabs, newlines etc.
		String[] inputs=content.split("\\s+");
		for(int i=0;i<inputs.length;i++){
			String inp=inputs[i];
			//Email check can be done using InternetAddress class but here I'm doing just a basic email address check
			if( inp.endsWith(".com") && inp.indexOf("@")>0 && inp.indexOf("@")+1<inp.indexOf(".com") && 
					inp.indexOf("@")==inp.lastIndexOf("@") && inp.indexOf(".com")==inp.lastIndexOf(".com")){
				
				emails.add(new EmailFromText(inp,inp.substring(inp.indexOf("@")+1)));
			}
		}
		
		//sorting the email list
		Collections.sort(emails);
		for(EmailFromText e:emails){
			System.out.println(e.email);
		}
	}

	@Override
	public int compareTo(EmailFromText o) {
		//Comparing the objects on the basis of domain
		return domain.compareTo(o.domain);
	}
}