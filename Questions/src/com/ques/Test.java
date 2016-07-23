package com.ques;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Test {

	public static void main(String[] args) {
		List<TT> l1=new ArrayList<TT>();
		TT a=new TT(1,"a");
		TT b=new TT(2,"b");
		TT c=new TT(3,"c");
		l1.add(a);l1.add(c);l1.add(b);
		
		List<TT> l2=new ArrayList<TT>();
		l2.addAll(l1);
		Collections.sort(l2);
		System.out.println(l1);
		System.out.println(l2);
	}
}

class TT implements Comparable<TT>{
	int a;
	String b;
	public TT(int a,String b){
		this.a=a;
		this.b=b;
	}
	
	@Override
	public String toString(){
		return a+b;
	}

	@Override
	public int compareTo(TT o) {
		return this.a-o.a;
	}
}