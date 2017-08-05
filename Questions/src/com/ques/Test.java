package com.ques;

public class Test {

	public static void main(String[] args) {
		
	}
	
	private static void printLinkedList(Nod node) {
		if(node==null) return;
		
		do{
			System.out.println(node.val);
			node=node.next;
		}while(node!=null);
	}

	public static Nod reverse(Nod n){
		Nod r=n;
		while(r.next!=null){
			Nod m=n;
			n=r.next;
			Nod p = n.next;
			n.next=m;
			r.next=p;
		}
		return n;
	}
}

class Nod{
	Nod next;
	String val;
	
	Nod(String val){
		this.val=val;
	}
}