package com.ques;

public class ConsecutiveNumSumCount {

	static int consecutive(long num) {
		int result=0;
		if(num==1 || num==2) return 0;
		int len=0;
		if(num<(long)Integer.MAX_VALUE) len=(int)num;
		else len=Integer.MAX_VALUE;
		
		for(int i=2;i<len;i=i+1){
			if(i%2==0){
				if( (num%i)*2==i ){
					result++;
					if(num/i<i) break;
				}
			}else{
				if(num%i==0){
					result++;
					if(num/i<i) break;
				}
			}
		}
		
		return result;
    }
}
