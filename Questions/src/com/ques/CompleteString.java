package com.ques;

import java.io.BufferedReader;
import java.io.InputStreamReader;

class CompleteString {

	public static boolean[] ascii=new boolean[26];
	
	public static void main(String args[] ) throws Exception {
				
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int strNum = Integer.parseInt(br.readLine());
        String[] inputs=new String[strNum];
        for (int i = 0; i < strNum; i++) {
            inputs[i]=br.readLine();
        }
        br.close();
        
        for (int i = 0; i < strNum; i++) {
        	for(int j=0;j<26;j++){
            	ascii[j]=false;
            }
            String input=inputs[i];
            char[] chars=input.toCharArray();
            for(int j=0;j<chars.length;j++){
            	int index=Integer.valueOf(chars[j])-Integer.valueOf('a');
            	if(index<26&&index>=0){
            		ascii[index]=true;
            	}
            }
            boolean isComplete=true;
            for(int j=0;j<26;j++){
            	if(!ascii[j]){
            		isComplete=false;
            		break;
            	}
            }
            
            if(isComplete) System.out.println("YES");
            else System.out.println("NO");
            
        }
    }
}
