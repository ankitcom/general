package com.ques;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class CommandoCatchLocation {

	
	public static int maxPossibleWait(int input1,int input2,String[] input3) throws Exception{
		int result=-1;
		int N=input3.length;
		char [][] inp=new char[N+1][N];
		
		List<Queue<Cell>> lQs=new LinkedList<>();
		Queue<Cell> mQ = new LinkedList<>();
		Cell s=new Cell(0,0);
		List<Cell> prevCells=new LinkedList<>();
		
		for(int i=1;i<=N;i++){
			for(int j=0;j<N;j++){
				char cc=input3[i-1].charAt(j);
				inp[i][j]=cc;
				if(cc=='L'){
					Queue<Cell> lQ=new LinkedList<>();
					lQ.add(new Cell(i,j));
					lQ.add(null);
					lQs.add(lQ);
				}else if(cc=='M'){
					mQ.add(new Cell(i,j));
					mQ.add(null);
				}
			}
		}
		
		boolean reachedSafe=false;
		while(!reachedSafe && !mQ.isEmpty()){
			Iterator<Queue<Cell>> lQitr=lQs.iterator();
			while(lQitr.hasNext()){
				Queue<Cell> lQ=lQitr.next();
				if(lQ.isEmpty()){
					lQitr.remove();
				}else{
					while(true){
						Cell cell=lQ.poll();
						if(cell==null){
							lQ.add(null);
							break;
						}
						addNextCells(lQ, cell, N, inp);
					}
				}
			}
			
			while(true){
				Cell cell=mQ.poll();
				if(cell==null){
					mQ.add(null);
					break;
				}
				boolean reachedSafeCur=addNextCells(mQ,cell,N,input2,inp,s,prevCells);
				if(reachedSafeCur) reachedSafe=true;
			}
		}
		
		if(reachedSafe){
			for(Cell prev:prevCells){
				int currResult=1;
				Queue<Cell> sQ = new LinkedList<>();
				sQ.add(prev);
				sQ.add(null);
				boolean gotResult=false;
				while(!sQ.isEmpty() && !gotResult){
					while(true){
						Cell cell=sQ.poll();
						if(cell==null){
							sQ.add(null);
							currResult++;
							break;
						}
						if(addNextCellsForS(sQ, cell, N, inp)){
							gotResult=true;
							break;
						}
					}
				}
				result=Math.max(result,currResult);
			}
		}
		
		return result;
    }
	
	private static boolean addNextCellsForS(Queue<Cell> sQ, Cell cell, int N, char[][] inp) {
		if(isValidAndHasL(cell.x+1,cell.y,N,inp)){
			return true;
		}else{
			addCellForS(sQ, cell.x+1, cell.y, N,inp);
		}
		if(isValidAndHasL(cell.x-1,cell.y,N,inp)){
			return true;
		}else{
			addCellForS(sQ, cell.x-1, cell.y, N,inp);
		}
		if(isValidAndHasL(cell.x,cell.y+1,N,inp)){
			return true;
		}else{
			addCellForS(sQ, cell.x, cell.y+1, N,inp);
		}
		if(isValidAndHasL(cell.x,cell.y-1,N,inp)){
			return true;
		}else{
			addCellForS(sQ, cell.x, cell.y-1, N,inp);
		}
		
		return false;
	}
	
	private static boolean isValidAndHasL(int x, int y, int N, char[][] inp) {
		if(x<=N && x>0 && y<N && y>=0){
			if(inp[x][y]=='L') return true;
		}
		return false;
	}
	
	private static boolean addCellForS(Queue<Cell> q, int x, int y, int N, char[][] inp) {
		if(isValidForS(x,y,N,inp)){
			q.add(new Cell(x,y));
			return true;
		}
		return false;
	}

	private static boolean isValidForS(int x, int y, int N, char[][] inp) {
		if(x<=N && x>0 && y<N && y>=0){
			char c=inp[x][y];
			if(c=='O' || c=='M'){
				return true;
			}
		}
		return false;
	}
	
	private static boolean addNextCells(Queue<Cell> mQ, Cell cell, int N, int steps, char[][] inp, Cell s, List<Cell> prevCells) {
		for(int i=1;i<=steps;i++){
			if(!addCell(mQ, cell.x+i, cell.y, N,inp,true,s)) break;
		}
		if(s.x==0){
			for(int i=1;i<=steps;i++){
				if(!addCell(mQ, cell.x-i, cell.y, N,inp,true,s)) break;
			}
		}
		if(s.x==0){
			for(int i=1;i<=steps;i++){
				if(!addCell(mQ, cell.x, cell.y+i, N,inp,true,s)) break;
			}		
		}
		if(s.x==0){
			for(int i=1;i<=steps;i++){
				if(!addCell(mQ, cell.x, cell.y-i, N,inp,true,s)) break;
			}
		}
		
		if(s.x!=0){
			prevCells.add(new Cell(cell.x,cell.y));
			return true;
		}
		
		return false;
	}
	
	private static void addNextCells(Queue<Cell> lQ, Cell cell, int N, char[][] inp) {
		addCell(lQ, cell.x+1, cell.y, N,inp,false,null);
		addCell(lQ, cell.x-1, cell.y, N,inp,false,null);
		addCell(lQ, cell.x, cell.y+1, N,inp,false,null);
		addCell(lQ, cell.x, cell.y-1, N,inp,false,null);
	}

	private static boolean addCell(Queue<Cell> q, int x, int y, int N, char[][] inp, boolean forM, Cell s) {
		if(isValid(x,y,N,inp,forM,s)){
			q.add(new Cell(x,y));
			if(forM){
				inp[x][y]='M';
			}else{
				inp[x][y]='L';
			}
			return true;
		}
		return false;
	}

	private static boolean isValid(int x, int y, int N, char[][] inp, boolean forM, Cell s) {
		if(x<=N && x>0 && y<N && y>=0){
			char c=inp[x][y];
			if(c=='O'){
				return true;
			}else if(!forM && c=='M'){
				return true;
			}else if(forM && c=='S'){
				s.x=x;s.y=y;
				return false;
			}
		}
		return false;
	}

	static class Cell{
		int x;
		int y;
		
		public Cell(int x, int y){
			this.x=x;
			this.y=y;
		}

//		@Override
//		public String toString() {
//			return "Cell [x=" + x + ", y=" + y + "]";
//		}
		
	}
	
	public static void main(String[] args) throws Exception{
//		String[] input3=new String[]{"OMOOS","OOOOO","OOOOO","OOOOO","OOOOL"};
		String[] input3=new String[]{"OOOOS","LOOHO","OOMOO","OOOOO","OOOOO"};
		System.out.println(maxPossibleWait(5,2,input3));
	}
	/*
	private static void print(char[][] inp, int N) {
		for(int i=1;i<=N;i++){
			for(int j=0;j<N;j++){
				System.out.print(inp[i][j]+",");
			}
			System.out.println();
		}
	}
	*/
}
