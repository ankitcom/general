package com.ques;

import java.util.Scanner;

public class CountOfSumCombinationsIncIntegers {
    public static void main(String args[] ) throws Exception {

        //Scanner
        Scanner s = new Scanner(System.in);
        int t = s.nextInt();

        int[] N=new int[t];
        for (int i = 0; i < t; i++) {
            N[i]=s.nextInt();
        }
        s.close();
        
        int numbersAllowed=4;
        int minInt=1;
        for (int i = 0; i < t; i++) {
        	int output=getCombinations(N[i], numbersAllowed, minInt);
        	System.out.println(output);
        }

    }

	private static int getCombinations(int N, int num, int min) {
		if(N<num) return 0;
		if(N<min) return 0;
		if (num==1) return 1;
		int combinations=0;
		
		for(;min<=(N-(num-1));min++){
			combinations+=getCombinations(N-min,num-1,min);
		}
		return combinations;
	}
}
