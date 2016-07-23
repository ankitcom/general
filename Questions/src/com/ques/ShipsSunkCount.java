package com.ques;

import java.util.HashMap;
import java.util.Map;

class ShipsSunkCount {
    
	public static void main(String[] args) {
		System.out.println(new ShipsSunkCount().solution(3,"1B 2C,2D 4D","2B 2D 3D 4D 4A"));
	}
	
	public String solution(int N, String S, String T) {
        int shipsSunk=0,shipsHitButNotSunk=0;
        
        String[] ships=S.split(",");
        int shipSize=ships.length;
        
        int[] shipsCellCount=new int[shipSize];
        boolean[] shipHit= new boolean[shipSize];
        Map<String, Integer> cellToShip= new HashMap<String, Integer>();
        
        for(int i=0;i<shipSize;i++){
        	String ship=ships[i];
        	String[] cell=ship.split(" ");
        	int c0i=Integer.parseInt(cell[0].substring(0, cell[0].length()-1));
        	char c0c=cell[0].substring(cell[0].length()-1).charAt(0);
        	int c1i=Integer.parseInt(cell[1].substring(0, cell[1].length()-1));
        	char c1c=cell[1].substring(cell[1].length()-1).charAt(0);
        	if(c0i==c1i){
        		shipsCellCount[i]=c1c-c0c+1;
        		for(int j=0;j<shipsCellCount[i];j++){
        			cellToShip.put(c0i+String.valueOf((char)(c0c+j)),i);
//        			System.out.println("A:putting in map:"+c0i+String.valueOf((char)(c0c+j))+" at index:"+i);
        		}
        	}else if(c0c==c1c){
        		shipsCellCount[i]=c1i-c0i+1;
        		for(int j=0;j<shipsCellCount[i];j++){
        			cellToShip.put( (c0i+j)+String.valueOf(c0c) , i);
//        			System.out.println("B:putting in map:"+(c0i+j)+String.valueOf(c0c)+" at index:"+i);
        		}
        	}else{
        		shipsCellCount[i]=4;
        		cellToShip.put(cell[0], i);
        		cellToShip.put(cell[1], i);
        		cellToShip.put(c0i+String.valueOf(c1c), i);
        		cellToShip.put(c1i+String.valueOf(c0c), i);
//        		System.out.println("C:putting in map:"+cell[0]+" at index:"+i);
//        		System.out.println("C:putting in map:"+cell[1]+" at index:"+i);
//        		System.out.println("C:putting in map:"+c0i+String.valueOf(c1c)+" at index:"+i);
//        		System.out.println("C:putting in map:"+c1i+String.valueOf(c0c)+" at index:"+i);
        	}
        }
        
        String[] hits=T.split(" ");
        for(int i=0;i<hits.length;i++){
        	if(cellToShip.containsKey(hits[i])){
        		int shipNum=cellToShip.get(hits[i]);
        		shipsCellCount[shipNum] = shipsCellCount[shipNum]-1;
        		shipHit[shipNum]=true;
        	}
        }
        
        for(int i=0;i<shipSize;i++){
        	if(shipsCellCount[i]==0){
        		shipsSunk++;
        	}
        }
        
        int shipsHit=0;
        for(int i=0;i<shipSize;i++){
        	if(shipHit[i]==true){
        		shipsHit++;
        	}
        }
        shipsHitButNotSunk=shipsHit-shipsSunk;
        
        return shipsSunk+","+shipsHitButNotSunk;
    }
}