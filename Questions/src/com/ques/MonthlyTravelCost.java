package com.ques;

public class MonthlyTravelCost {

	public int solution(int[] A) {
		int cost=0;
		
		StringBuffer pot7Indexes=new StringBuffer();
		int s7=0,e7=0;
		for(int i=0;i<A.length;i++){
			if( (A[i]-A[s7]) > 6 ){
				e7=i-1;
				if(e7-s7+1>3){
					int potDays=e7-s7+1;
					if(potDays<7){
						for(int j=s7+1;j<s7+4&&j<A.length;j++){
							for(int k=j+potDays;k<j+7&&potDays<7&&k<A.length;k++){
								if(A[k]-A[j]<=6){
									e7=k;s7=j;
									potDays=e7-s7+1;
								}
							}
						}
					}
					pot7Indexes.append(s7).append(e7);
					s7=e7+1;
					i=e7+1;
				}else{
					s7++;
				}
			}
		}
		
		for(int i=0;i<A.length;i++){
			if(pot7Indexes.length()>0){
				if(i<Integer.parseInt(pot7Indexes.substring(0, 1))){
					cost=cost+2;
				}else{
					i=Integer.parseInt(pot7Indexes.substring(1, 2))+1;
					cost=cost+7;
					pot7Indexes=new StringBuffer(pot7Indexes.substring(2));
				}
			}else{
				cost=cost+2;
			}
		}
		
		if(cost>25) cost=25;
		
		return cost;
    }
}
