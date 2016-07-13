package com.ques;

public class Heap {

	public static void main(String[] args) {
		int numOfItems=25;
		int height=(int)(Math.log(numOfItems)/Math.log(2));
		System.out.println((int)Math.pow(2, height));
	}
}
