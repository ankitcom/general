package com.ques;

import java.util.Stack;

public class BaseballTotalScore {

	public static final String X="X";
	public static final String P="+";
	public static final String Z="Z";
//	public static final String E="E";
	
	public static int totalScore(String[] blocks, int n){
		int result=0;
		if(n==0) return 0;
		
		Stack<Integer> scores=new Stack<>();
		
		int prev=0,prev2=0;
		
		for(int i=0;i<n;i++){
			int score=0;
			String block=blocks[i];
			switch(block){
			case X:
				score=prev*2;
				break;
			case P:
				score=prev+prev2;
				break;
			case Z:
				if(!scores.isEmpty()){
					int pop=scores.pop();
					result-=pop;
					if(!scores.isEmpty()){
						prev=scores.pop();
						prev2=scores.isEmpty()?0:scores.peek();
						scores.push(prev);
					}else{
						prev=0;prev2=0;
					}
				}else{
					prev=0;prev2=0;
				}
				break;
			default:
				try{
					score=Integer.parseInt(block);
				}catch(Exception e){
					return 0;
				}
				break;
			}
			
			if(!Z.equals(block)){
				prev2=prev;
				prev=score;
				scores.push(score);
				result+=score;
			}
			
		}
		
		return result;
    }
	
	public static void main(String[] args) {
		System.out.println(totalScore(new String[]{"5","-2","4","Z","Z","X","9","+","+"},9));
	}

//	private void removeZ(String[] blocks, int n) {
//		int empty=0;
//		for(int i=0;i<n;i++){
//			if(Z.equals(blocks[i])){
//				empty--;
//				if(empty>=0){
//					blocks[i-1]=E;
//					empty--;
//				}
//			}else{
//				empty=i;
//			}
//		}
//	}
}
