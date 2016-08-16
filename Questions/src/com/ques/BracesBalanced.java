package com.ques;

import java.io.IOException;
import java.util.Stack;

public class BracesBalanced {
	/*
	 * Complete the function below.
	 */

	    static String[] braces(String[] values) {
	    	
	    	String[] result = new String[values.length];
	    	for(int i=0;i<values.length;i++){
	    		Stack<Character> braces=new Stack<Character>();
	    		String input=values[i];
	    		result[i]="YES";
	    		try{
		    		for(int j=0;j<input.length();j++){
		    			char brace=input.charAt(j);
		    			if(brace=='{'||brace=='('||brace=='['){
		    				braces.push(brace);
		    			}else if(brace=='}'){
		    				if(braces.pop()!='{'){
		    					result[i]="NO";
		    					break;
		    				}
		    			}else if(brace==')'){
		    				if(braces.pop()!='('){
		    					result[i]="NO";
		    					break;
		    				}
		    			}else if(brace==']'){
		    				if(braces.pop()!='['){
		    					result[i]="NO";
		    					break;
		    				}
		    			}else{
		    				result[i]="NO";
		    				break;
		    			}
		    		}
		    		if(!braces.isEmpty()) result[i]="NO";
	    		}catch(Exception e){
	    			result[i]="NO";
	    		}
	    	}
	    	
	    	return result;
	    }
	    
	    public static void main(String[] args) throws IOException{
	        String[] values=new String[]{"{}[]()","{[}]"};
	        String[] result=braces(values);
	        for(int i=0;i<result.length;i++){
	        	System.out.println(result[i]);
	        }
	    }
	}