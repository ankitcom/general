package com.ques;

public class NumberOfHolidays {

	public static void main(String[] args) {
		System.out.println(new NumberOfHolidays().solution(new int[]{7,3,1,1,7,1,7,7,3}));
	}
	
	public int solutionNew(int[] A) {
		
		int result=0;
		int len=A.length;
		int start=0,end=len-1;
		
		return result;
    }

	public int solution(int[] A) {
		
		int len=A.length;
		int start=0,end=len-1;
		//array to keep location count
		int[] loc=new int[len];
		
		for(int i=0;i<len;i++){
			loc[A[i]]++;
		}
		
		for(int i=0;i<len;i++){
			loc[A[i]]--;
			if(loc[A[i]]==0){
				start=i;
				break;
			}
		}
		
		for(int i=len-1;i>=0;i--){
			loc[A[i]]--;
			if(loc[A[i]]==0){
				end=i;
				break;
			}
		}
		
		int result1=end-start+1;
		
		
		return result1;
    }
}
