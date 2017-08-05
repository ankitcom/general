package com.ques;

import java.util.ArrayList;
import java.util.List;

public class RectanglePairMinHubs {

	public static int homesteadThatDefinesANewLivingPlanet(int input1,int input2,int input3,int input4,int[][] input5){
		int[][] hubs = new int[input1+1][input2+1];
		
		for(int i=0;i<input3;i++){
			hubs[input5[i][0]][input5[i][1]]++;
		}
//		print(hubs, input1+1,input2+1);
		
//		int[][] preSums = getPreSumsArray(hubs, input1, input2);
//		print(preSums, input1+1, input2+1);
		
		List<Rectangle> recs=new ArrayList<>();
		
		for(int dep=1;dep<=input2;dep++){
			for(int ty=dep;ty<=input2;ty++){
				int by=ty-dep;
				int tx=getNonEmptyIndex(0,ty,dep,input1,hubs);
				if(tx==-1) continue;
				int bx=tx+1;
				boolean addLastBxCol=true;
				int count=0;
				while(tx<input1 && bx<=input1){
					if(addLastBxCol) count+=getColCount(bx-1,ty,dep,hubs);
					if(count>=input4){
						if(count==input4){
							addRectangle(recs,tx,ty,bx,by,hubs);
							bx++;
							addLastBxCol=true;
						}else{
							addLastBxCol=false;
						}
						count-=getColCount(tx,ty,dep,hubs);
						tx++;
						tx=getNonEmptyIndex(tx,ty,dep,input1,hubs);
						if(tx==-1) break;
						if(bx<=tx){
							addLastBxCol=true;
							bx=tx+1;
						}
					}else{//count<input4
						addLastBxCol=true;
						bx++;
					}
					
					/*
					//check if hubs are there in the first column
					int colCount=0;
					for(int i=0;i<dep;i++){
						colCount+=hubs[tx+1][ty-i];
					}
					if(colCount==0) continue;
					
					if(colCount>=input4){
						addRectangle(colCount,input4,recs,tx,ty,tx+1,by,hubs);
						continue;
					}
					for(int bx=tx+2;bx<=input1;bx++){
						for(int i=0;i<dep;i++){
							colCount+=hubs[bx][ty-i];
						}
						if(colCount>=input4){
							addRectangle(colCount,input4,recs,tx,ty,bx,by,hubs);
							break;
						}
					}
					//*/
					
				}
			}
		}
		
		print(recs);
		
		int perimeter=Integer.MAX_VALUE;
		for(int i=0;i<recs.size();i++){
			for(int j=i+1;j<recs.size();j++){
				if(!isOverlaping(recs.get(i), recs.get(j))){
					System.out.println("overlap:false");
					System.out.println(recs.get(i));
					System.out.println(recs.get(j));
					int locPer=recs.get(i).per+recs.get(j).per;
					if(locPer<perimeter) perimeter=locPer;
				}
			}
		}
		if(perimeter==Integer.MAX_VALUE) return 0;
		
		return perimeter;
    }
	
	private static int getNonEmptyIndex(int idx, int ty, int dep, int xSize, int[][] hubs) {
		while(idx<xSize){
			if(getColCount(idx,ty,dep,hubs)>0) return idx;
			idx++;
		}
		return -1;
	}

	private static int getColCount(int idx, int ty, int dep, int[][] hubs) {
		int count=0;
		for(int i=0;i<dep;i++){
			count+=hubs[idx+1][ty-i];
		}
		return count;
	}

	/*
	private static int[][] getPreSumsArray(int[][] hubs, int input1, int input2) {
		int[][] preSums = new int[input1+1][input2+1];
		for(int j=1;j<=input2;j++){
			int sum=0;
			for(int i=1;i<=input1;i++){
				sum+=hubs[i][j];
				preSums[i][j]=sum;
			}
		}
		
		return preSums;
	}
*/
	
	private static void addRectangle(List<Rectangle> recs, int tlx, int tly, int brx, int bry, int[][] hubs) {
		boolean toAdd1=false, toAdd2=false;
		for(int i=tlx+1;i<=brx;i++){
			if(hubs[i][tly]!=0){
				toAdd1=true;
			}
			if(hubs[i][bry+1]!=0){
				toAdd2=true;
			}
			if(toAdd1&&toAdd2) break;
		}
		if(toAdd1&&toAdd2) recs.add(new Rectangle(tlx,tly,brx,bry));
	}

	/*
	private static void addRectangle(int colCount, int input4, List<Rectangle> recs, int tlx, int tly, int brx, int bry, int[][] hubs) {
		if(colCount==input4){
			boolean toAdd1=false, toAdd2=false;
			for(int i=tlx+1;i<=brx;i++){
				if(hubs[i][tly]!=0){
					toAdd1=true;
				}
				if(hubs[i][bry+1]!=0){
					toAdd2=true;
				}
				if(toAdd1&&toAdd2) break;
			}
			if(toAdd1&&toAdd2) recs.add(new Rectangle(tlx,tly,brx,bry));
		}
	}
	*/
	
	public static boolean isOverlaping(Rectangle rec1, Rectangle rec2){
	    if (rec1.tlx >= rec2.brx || rec2.tlx >= rec1.brx)
	        return false;
	 
	    if (rec1.tly <= rec2.bry || rec2.tly <= rec1.bry)
	        return false;
	 
	    return true;
	}

	static class Rectangle{
		int tlx;
		int tly;
		int brx;
		int bry;
		int per;
		
		public Rectangle(int tlx,int tly, int brx, int bry){
			this.tlx=tlx;
			this.tly=tly;
			this.brx=brx;
			this.bry=bry;
			this.per=2*(brx-tlx+tly-bry);
		}

		@Override
		public String toString() {
			return "Rectangle [tlx=" + tlx + ", tly=" + tly + ", brx=" + brx + ", bry=" + bry + ", per=" + per + "]";
		}
		
	}
	
	
	
	static void print(List<Rectangle> recs){
		System.out.println("--");
		for(Rectangle rec:recs){
			System.out.println(rec);
		}
		System.out.println("--");
	}
	
	static void print(int[][] arr, int xSize, int ySize){
		for(int j=1;j<ySize;j++){
			for(int i=1;i<xSize;i++){
				System.out.print(arr[i][j] + ",");
			}
			System.out.println();
		}
		System.out.println("--");
	}
	
	public static void main(String[] args) {
		int input1=6;
		int input2=5;
		int input3=7;
		int input4=3;
		int[][] input5=new int[input3][2];
		input5[0][0]=3;input5[0][1]=4;
		input5[1][0]=3;input5[1][1]=3;
		input5[2][0]=6;input5[2][1]=1;
		input5[3][0]=1;input5[3][1]=1;
		input5[4][0]=5;input5[4][1]=5;
		input5[5][0]=5;input5[5][1]=5;
		input5[6][0]=3;input5[6][1]=1;
		
//		input1=6;
//		input2=5;
//		input3=5;
//		input4=2;
//		input5=new int[input3][2];
//		input5[0][0]=1;input5[0][1]=1;
//		input5[1][0]=1;input5[1][1]=5;
//		input5[2][0]=6;input5[2][1]=1;
//		input5[3][0]=6;input5[3][1]=5;
//		input5[4][0]=2;input5[4][1]=1;
		System.out.println(homesteadThatDefinesANewLivingPlanet(input1,input2,input3,input4,input5));
	}
}
