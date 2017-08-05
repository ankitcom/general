package com.ques;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class CardsWhiteBlack {

	public static String combinationOfCards(int input1,int input2, String input3, String input4){
		StringBuilder result=new StringBuilder();
		
		TreeSet<String> set = new TreeSet<>();
		
		char[] ini=new char[input1];
		for(int i=0;i<input1;i++){
			ini[i]='1';
		}

		int white1=0,white2=0,black1=0,black2=0;
		
		int commaIndex=input3.indexOf(",");
		if(input3.equals("-1")){
			
		}else if(commaIndex<0){
			white1=Integer.parseInt(input3);
		}else{
			white1=Integer.parseInt(input3.substring(0, commaIndex));
			white2=Integer.parseInt(input3.substring(commaIndex+1));
		}
		
		commaIndex=input4.indexOf(",");
		if(input4.equals("-1")){
			
		}else if(commaIndex<0){
			black1=Integer.parseInt(input4);
		}else{
			black1=Integer.parseInt(input4.substring(0, commaIndex));
			black2=Integer.parseInt(input4.substring(commaIndex+1));
		}
		
		List<HashSet<String>> doneSets = new ArrayList<>(input2+1);
		for(int i=0;i<input2+1;i++) doneSets.add(new HashSet<String>());
		loadSet(ini, input2, white1, white2, black1, black2, set, doneSets);
		
		Iterator<String> iter=set.iterator();
		while(iter.hasNext()){
			result.append(iter.next()).append("#");
		}
		
		return result.substring(0, result.length()-1).toString();
	}

	private static void loadSet(char[] state, int chances, int white1, int white2, int black1, int black2, TreeSet<String> result, List<HashSet<String>> doneSets) {
		
		Set<String> doneSet=doneSets.get(chances);
		String key=String.valueOf(state);
		if(doneSet.contains(key)){
			return;
		}else{
			doneSet.add(key);
		}
		
//		System.out.println("chances:"+chances);
//		System.out.println(String.valueOf(state));
		if(chances==0){
			if(check(state, white1, '1') && check(state, white2, '1') && check(state, black1, '0') && check(state, black2, '0')){
//				System.out.println("True check:"+String.valueOf(state));
				result.add(String.valueOf(state));
			}else{
				
			}
			return;
		}
		
		loadSet(flipAll(Arrays.copyOf(state, state.length)), chances-1, white1, white2, black1, black2, result, doneSets);
		loadSet(flipOdd(Arrays.copyOf(state, state.length)), chances-1, white1, white2, black1, black2, result, doneSets);
		loadSet(flipEven(Arrays.copyOf(state, state.length)), chances-1, white1, white2, black1, black2, result, doneSets);
		loadSet(flip3k(Arrays.copyOf(state, state.length)), chances-1, white1, white2, black1, black2, result, doneSets);
	}

	private static boolean check(char[] state, int idx, char c) {
		if(idx==0){
			return true;
		}else{
			return state[idx-1]==c;
		}
	}

	private static char[] flip3k(char[] state) {
		for(int i=0;i<state.length;i=i+3){
			if(state[i]=='0'){
				state[i]='1';
			}else{
				state[i]='0';
			}
		}
		return state;
	}

	private static char[] flipOdd(char[] state) {
		for(int i=0;i<state.length;i=i+2){
			if(state[i]=='0'){
				state[i]='1';
			}else{
				state[i]='0';
			}
		}
		return state;
	}

	private static char[] flipEven(char[] state) {
		for(int i=1;i<state.length;i=i+2){
			if(state[i]=='0'){
				state[i]='1';
			}else{
				state[i]='0';
			}
		}
		return state;
	}

	private static char[] flipAll(char[] state) {
		for(int i=0;i<state.length;i++){
			if(state[i]=='0'){
				state[i]='1';
			}else{
				state[i]='0';
			}
		}
		return state;
	}
	
	public static void main(String[] args) {
//		String result=combinationOfCards(10,1,"-1","7");
		String result=combinationOfCards(40,3,"16","2,25");
		System.out.println(result);
	}
}
