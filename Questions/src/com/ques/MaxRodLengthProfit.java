package com.ques;

import java.util.Arrays;

public class MaxRodLengthProfit {

	static int maxProfit(int cost_per_cut, int metal_price, int[] lengths) {
		
		int maxProfit=0;
		Arrays.sort(lengths);
		
		int maxLength=lengths[lengths.length-1];
		for(int i=1;i<maxLength;i++){
			int profit=getProfit(i, lengths, cost_per_cut, metal_price);
			if(profit>maxProfit){
				maxProfit=profit;
			}
		}
		
		return maxProfit;
    }

	private static int getProfit(int rodLength, int[] lengths, int cost_per_cut, int metal_price) {
		int profit=0;
		
		for(int i=0;i<lengths.length;i++){
			profit+=getCost(rodLength,lengths[i], cost_per_cut, metal_price);
		}
		if(rodLength==51) System.out.println(profit);
		return profit;
	}

	private static int getCost(int rodLength, int inputRod, int cost_per_cut, int metal_price) {
		int maxCost=0;
		if(rodLength==51) System.out.println("inputRod:"+inputRod);
		if(rodLength<inputRod){
			int rods=inputRod/rodLength;
			int rem=inputRod%rodLength;
			if(rodLength==51) System.out.println("rods:"+rods);
			int maxCuts=rods;
			if(rem==0) maxCuts=rods-1;
			if(rodLength==51) System.out.println("maxCuts:"+maxCuts);
			maxCost=rods*rodLength*metal_price-maxCuts*cost_per_cut;
			if(rodLength==51) System.out.println("first maxCost:"+maxCost);
			for(int cuts=1;cuts<maxCuts;cuts++){
				int cost=cuts*(rodLength*metal_price-cost_per_cut);
				if(cost>maxCost) maxCost=cost;
				if(rodLength==51) System.out.println("cost:"+cost);
			}
			
		}else if(rodLength==inputRod){
			maxCost=rodLength*metal_price;
		}
		maxCost=maxCost>=0?maxCost:0;
		if(rodLength==51) System.out.println("maxCost:"+maxCost);
		return maxCost;
	}
	
	public static void main(String[] args) {
		System.out.println(maxProfit(100,10, new int[]{26,103,59}));
	}
}
