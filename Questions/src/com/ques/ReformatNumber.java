package com.ques;

public class ReformatNumber {

	public static void main(String[] args) {
		System.out.println(new ReformatNumber().solution("- 123456789-345-0222 07-0--  "));
	}
	
	public String solution(String S) {
		StringBuffer cleanNumbers=cleanNumbers(S);
		
		int length=cleanNumbers.length();
		
		int itLen=length;
		if(length%3==2&&length>2){
			itLen=length-2;
			cleanNumbers.replace(length-2, length-2, "-");
			length++;
		}else if(length%3==1){
			itLen=length-4;
			cleanNumbers.replace(length-2, length-2, "-");
			if(length>4){
				cleanNumbers.replace(length-4, length-4, "-");
				length++;
			}
			length++;
		}
		
		int resultLength=length;
		if(itLen>3){
			resultLength=length+(itLen/3-1);
			char[] result=new char[resultLength];
			int i=0,j=0;
			for(;i<resultLength-(length-itLen+3);i++){
				if(i%4==3){
					result[i]='-';
				}else{
					result[i]=cleanNumbers.charAt(j);
					j++;
				}
			}
			for(;i<resultLength;i++,j++){
				result[i]=cleanNumbers.charAt(j);
			}
			return String.valueOf(result);
		}else{
			return cleanNumbers.toString();
		}
		
    }

	private StringBuffer cleanNumbers(String S) {
		StringBuffer cleanNumbers=new StringBuffer();

		for(int i=0;i<S.length();i++){
			if(S.charAt(i)!='-' && S.charAt(i)!=' '){
				cleanNumbers.append(S.charAt(i));
			}
		}
		return cleanNumbers;
	}
}
