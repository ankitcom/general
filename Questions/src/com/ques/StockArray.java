package com.ques;

public class StockArray {

	public static void main(String[] args) throws Exception{
		int[] stockArray={3,5,9,2,5,90,32,25,343,2,55,221,67,9,10};
		System.out.println(getMaxProfit(stockArray));
	}

	private static int getMaxProfit(int[] stockArray) throws Exception{
		
		int profit=0;
		if(stockArray.length==0 || stockArray.length==1) return 0;
		int stIn=stockArray[0],endIn=stockArray[1];
		if(stIn>=endIn) stIn=endIn;
		if(stockArray.length==2) profit=endIn-stIn;
		
		for(int i=2;i<stockArray.length;i++){
			System.out.println(i+" :: "+stockArray[i]);
			Thread.sleep(2000l);
			if(stIn<endIn && endIn>stockArray[i] ){
				System.out.println("1:"+endIn+"-"+stIn);
				profit=profit+endIn-stIn;
				if( (i+1) < stockArray.length){
					endIn=stockArray[i+1];
					stIn=stockArray[i];
					if(stIn>=endIn) stIn=endIn;
					i+=1;
					if( i==stockArray.length-1 ){
						if(stIn>=endIn){
							stIn=endIn;
						}else{
							System.out.println("2:"+endIn+"-"+stIn);
							profit=profit+endIn-stIn;
						}
					}
				}else{
					break;
				}
			}else if(stIn<endIn && endIn<stockArray[i] ){
				endIn=stockArray[i];
				if(i==stockArray.length-1){
					System.out.println("3:"+endIn+"-"+stIn);
					profit=profit+endIn-stIn;
				}
			}else if(stIn>=endIn && endIn>stockArray[i] ){
				stIn=endIn=stockArray[i];
			}else if(stIn>=endIn && endIn<stockArray[i] ){
				endIn=stockArray[i];
				if(i==stockArray.length-1){
					System.out.println("4:"+endIn+"-"+stIn);
					profit=profit+endIn-stIn;
				}
			}
			
		}
		
		return profit;
	}
}
