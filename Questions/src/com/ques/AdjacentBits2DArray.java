package com.ques;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class AdjacentBits2DArray {

	public static int strNum;
	public static int count=0;
	public static Set<Integer> ones=new HashSet<Integer>();
	
	public static void main(String args[] ) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        strNum = Integer.parseInt(br.readLine());
        String[] inputs=new String[strNum];
        for (int i = 0; i < strNum; i++) {
            inputs[i]=br.readLine();
        }
        br.close();
        
        for(int i=0;i<strNum;i++){
        	String input=inputs[i];
            char[] chars=input.toCharArray();
            for(int j=0;j<chars.length;j++){
            	if(chars[j]=='1'){
            		ones.add(i*strNum + j + 1);
            	}
            }
        }
        
        while(true){
        	Iterator<Integer> oneItr=ones.iterator();
        	if(oneItr.hasNext()){
        		int num=oneItr.next();
        		
        		checkAndRemove(num);
        		
        		count++;
        	}else{
        		break;
        	}
        }
        System.out.println(count);
    }
	
	public static void checkAndRemove(int num){
		ones.remove(num);
		int check[]=new int[8];
		if(num%strNum!=0){
			check[0]=num+1;
			check[1]=num+strNum+1;
			check[7]=num-strNum+1;
		}
		check[2]=num+strNum;
		if((num-1)%strNum!=0){
			check[3]=num+strNum-1;
			check[4]=num-1;
			check[5]=num-strNum-1;
		}
		check[6]=num-strNum;
		
		for(int i=0;i<8;i++){
			if(ones.contains(check[i])){
				checkAndRemove(check[i]);
			}
		}
		
	}
}