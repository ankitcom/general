package com.ques;

public class ReformatNumber {

	public static void main(String[] args) {
		System.out.println(new ReformatNumber().solution("0000000000"));
	}
	
	public String solution(String S) {
		S=S.replace("-", "").replace(" ", "");
		int length=S.length();
		StringBuffer sb=new StringBuffer(S);
		
		int itrLen=length;
		if(length%3==2&&length>2){
			sb.replace(length-2, length-2, "-");
			itrLen=length-2;
		}else if(length%3==1){
			sb.replace(length-2, length-2, "-");
			if(length>4) sb.replace(length-4, length-4, "-");
			itrLen=length-4;
		}
		for(int i=3;i<itrLen;i=i+3){
			sb.replace(i, i, "-");
			//to accomodate the added character
			i++;
			itrLen++;
		}
		
		return sb.toString();
    }
}
