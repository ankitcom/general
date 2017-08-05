package com.ques;

import java.io.IOException;
import java.util.Scanner;
public class AddCharsForPalindrome {
	
	public static int openingRightDoor(int input1, String input2){
		
		int[][] store=new int[input1][input1];
		return getSorted(input2,0,input1-1,store);
    }
	
	public static int getSorted(String input2, int start, int end, int[][] store){
		
		if(store[start][end]!=0) return store[start][end];
		
		if(start>=end) return 0;
		
		if(input2.charAt(start)==input2.charAt(end)){
			int result=getSorted(input2, start+1, end-1, store);
			if(store[start+1][end-1]==0) store[start+1][end-1]=result;
			return result;
		}else if(start==end-1){
			return 1;
		}else{
			int result1=getSorted(input2, start+1,end,store);
			if(store[start+1][end]==0) store[start+1][end]=result1;
			int result2=getSorted(input2, start,end-1,store);
			if(store[start][end-1]==0) store[start][end-1]=result2;
			
			int result=Math.min(result1, result2)+1;
			
			return result;
		}
	}
	
	public static void main(String[] args) throws IOException{
		System.out.println("Give input");
        Scanner in = new Scanner(System.in);
        int output = 0;
//        int ip1 = Integer.parseInt(in.nextLine().trim());
        String ip2 = in.nextLine().trim();
        output = openingRightDoor(ip2.length(),ip2);
        System.out.println(String.valueOf(output));
        in.close();
    }
}