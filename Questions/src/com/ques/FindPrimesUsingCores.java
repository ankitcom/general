package com.ques;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Write a method that takes two numbers as input and prints all prime numbers in between those two numbers (including those numbers) 
 * in ascending order. Write a second function that does the same but takes an additional number specifying how many CPU cores to use to compute it.
 * @author maggy
 *
 */
public class FindPrimesUsingCores implements Runnable{

	int number;
	int startD,endD;
	
	public FindPrimesUsingCores(){}
	public FindPrimesUsingCores(int number){
		this.number=number;
	}
	public FindPrimesUsingCores(int startD, int endD){
		this.startD=startD;
		this.endD=endD;
	}
	
	public static void main(String[] args) {
		
		int start=3,end=100000000,cores=1;
		long l1=System.currentTimeMillis();
		
		//calling of SINGLE CORE method
		(new FindPrimesUsingCores()).printBwPrimes(start, end);
		long l2=System.currentTimeMillis();
		System.out.println("Time1:"+(l2-l1));//792,11656,229328-->100000000
		
		//calling of MULTIPLE CORES method
		printBwPrimes(start, end, cores);
		long l3=System.currentTimeMillis();
		System.out.println("Time2:"+(l3-l2));//880,9174,152535-->100000000
	}

	private void printBwPrimes(int start, int end) {
		
		System.out.println("Computing through single core:");
		if(start>end){
			int buff=start;
			start=end;end=buff;
		}
		
		if(start%2==0) start++;
		for(int i=start;i<=end;i=i+2){
			printIfPrime(i);
		}
	}
	
	private void printIfPrime(int number) {
		int j=2;
		boolean isPrime=true;
		while (j <= (int)Math.sqrt(number)){
	        if (number % j == 0){
	           isPrime=false;
	           break;
	        }
	        j++;
	    }
	    if (isPrime) System.out.println(number);
	}

	private static void printBwPrimes(int start, int end, int cores) {
		
		//check available cores and assign the maximum that we can using cores parameter
		int avCores=Runtime.getRuntime().availableProcessors();
		if(cores>avCores) cores=avCores;
		System.out.println("Computing through multiple cores:"+avCores);
		
		//ExecutorService executor=Executors.newFixedThreadPool(cores);
		ExecutorService executor=Executors.newWorkStealingPool(avCores);
		if(start>end){
			int buff=start;
			start=end;end=buff;
		}
		
		for(int i=1;i<=avCores;i++){
			Runnable thread=null;
			if(i==1){
				thread=new FindPrimesUsingCores(start,end/avCores);
			}else{
				thread=new FindPrimesUsingCores((i-1)*end/avCores +1,i*end/avCores);
			}
			executor.execute(thread);
		}

		executor.shutdown();
		while(!executor.isTerminated());
	}

	@Override
	public void run() {
		if(startD%2==0) startD++;
		for(int i=startD;i<=endD;i=i+2){
			printIfPrime(i);
		}
	}

}