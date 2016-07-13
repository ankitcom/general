package com.ques;

/**
 * Write a method that takes two dates as input (each date specified as month and day) and calculates and returns the number of days in between. 
 * Assume the dates are in the same year but also take into account that the year can be a leap year (option when calling the method) 
 * then February has 29 days instead of 28. Don't use the Date or Calendar class or any other existing class to compute this task, 
 * the purpose is to program the calculation yourself.
 * Handle invalid input appropriately.
 * @author maggy
 *
 */
public class DateDiff {

	private static int[] monthDays={31,28,31,30,31,30,31,31,30,31,30,31};
	public static void main(String[] args){
		
		int day1=21, month1=1, day2=28, month2=3;
		boolean isLeap=true;
		try{
			int days=getBetweenDays(day1,month1,day2,month2,isLeap);
			//print the answer in console
			System.out.println(days);
		}catch(Exception e){
			System.out.println(e);
		}
		
	}

	private static int getBetweenDays(int day1, int month1, int day2, int month2, boolean isLeap) throws Exception{
		int days=0;
		if(isLeap) monthDays[1]=29;
		
		//Valid input check
		if(month1<1 || month2<1 || month1>12 || month2>12 || day1>monthDays[month1-1] || day2>monthDays[month2-1] || day1<1 || day2<1){
			throw new Exception("Invalid input");
		}
		//modify variables so that difference calculation assumes date2 always greater than date1.
		//This is done if date2 passed is less than date1. We are assuming dates belong to same year
		if(month2<month1 || (month2==month1&&day2<day1)){
			int month3=month1;
			month1=month2;month2=month3;
			int day3=day1;
			day1=day2;day2=day3;
		}
		
		//Calculating date difference now
		if(month2==month1){
			days=day2-day1;
		}else{
			days=monthDays[month1-1]-day1;
			int diff=month2-month1;
			while(diff>1){
				days=days+monthDays[month1-1+(--diff)];
			}
			days=days+day2;
		}
		
		return days;
	}
}