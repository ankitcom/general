package com.ques;

public class LinkedListOps {

	public static void main(String[] args) {

		Node n=new Node(3);
		n.next=new Node(5);
		n.next.next=new Node(7);
		n.next.next.next=new Node(9);
		n.next.next.next.next=new Node(11);
		Node b=n;
		while(b!=null){
			System.out.print(b.value+"->");
			b=b.next;
		}
		System.out.println();
		Node back=reverseLinkedList(n);
		while(back!=null){
			System.out.print(back.value+"->");
			back=back.next;
		}
	}

	private static Node reverseLinkedList(Node n) {
		Node f=n;
		while(n.next!=null){
			Node s=f;
			f=n.next;
			Node p=f.next;
			f.next=s;
			n.next=p;
		}
		return f;
	}
}

class Node{
	int value;
	Node next;
	
	public Node(int value){
		this.value=value;
	}
}