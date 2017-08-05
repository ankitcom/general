package com.ques;

import java.util.LinkedList;
import java.util.Queue;

public class ChessKnightMinSteps {

	public static int getStepCount(int input1,int input2,int input3,int input4){
		
		boolean[][] visit=new boolean[9][9];
		visit[input1][input2]=true;
		
		Queue<Cell> q = new LinkedList<>();
		q.add(new Cell(input1,input2,0));
		
		int[] px=new int[]{-2,-2,-1,-1,1,1,2,2};
		int[] py=new int[]{-1,1,-2,2,-2,2,-1,1};
		
		while(!q.isEmpty()){
			Cell cell=q.poll();
			visit[cell.x][cell.y]=true;
			
			if(cell.x==input3 && cell.y==input4) return cell.steps;
			
			for(int i=0;i<8;i++){
				int x=cell.x+px[i];
				int y=cell.y+py[i];
				
				if(inLimits(x,y) && !visit[x][y]){
					q.add(new Cell(x,y,cell.steps+1));
				}
			}
		}
		
		return 0;
    }
	
	public static boolean inLimits(int x, int y){
	    if (x >= 1 && x <= 8 && y >= 1 && y <= 8) return true;
	    return false;
	}
	
	static class Cell{
		int x;
		int y;
		int steps;
		
		public Cell(int x, int y, int steps){
			this.x=x;
			this.y=y;
			this.steps=steps;
		}
	}
}
