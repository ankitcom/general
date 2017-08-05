package com.ques;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;
import java.util.TreeSet;

public class LaunchingSatellites {

	public static int launchingSatellites(int input1,int input2,String[] input3){
		
		Map<Integer,TreeSet<Integer>> sortedInput= new TreeMap<>();
		int total=input3.length;
		
		for(int i=0;i<total;i++){
			int hashIdx=input3[i].indexOf("#");
			int x=Integer.parseInt(input3[i].substring(0,hashIdx));
			int y=Integer.parseInt(input3[i].substring(hashIdx+1));
			if(sortedInput.containsKey(x)){
				sortedInput.get(x).add(y);
			}else{
				TreeSet<Integer> ySet=new TreeSet<>();
				ySet.add(y);
				sortedInput.put(x, ySet);
			}
		}
		
		int x[] = new int[total];
		int y[] = new int[total];
		
		int idx=0;
		for(Map.Entry<Integer, TreeSet<Integer>> entry: sortedInput.entrySet()){
			int xVal=entry.getKey();
			Iterator<Integer> itr=entry.getValue().iterator();
			while(itr.hasNext()){
				int yVal=itr.next();
				x[idx]=xVal;
				y[idx]=yVal;
				idx++;
			}
		}
		
		return getMaxCount(total, x, y);
    }

	private static int getMaxCount(int total, int[] x, int[] y) {
		
		int count=0;
		Map<Integer,Map<Integer,Map<Double,Integer>>> doneSlopes = new HashMap<>();
		
		for(int i=0;i<total;i++){
			
			Map<Integer, Map<Integer, Map<Double,Integer>>> currDoneSlopes=new HashMap<>();
			
			//int array attr:
			//0. current count of satellites in this slope
			//1. x distance
			//2. y distance
			Map<Integer, Map<Integer, int[]>> slopes=new HashMap<>();
			
			for(int j=i+1; j<total; j++){
				int xDiff=x[j]-x[i];
				int yDiff=y[j]-y[i];
				int gcd=gcd(xDiff,yDiff);
				int xKey=xDiff/gcd;
				int yKey=yDiff/gcd;
				double cons=(xKey==0)?x[i]:(y[i]-((double)yKey/xKey)*x[i]);
				if(!checkDoneSlopes(doneSlopes,xKey,yKey,cons)){
					if(slopes.containsKey(xKey)){
						Map<Integer,int[]> nMap=slopes.get(xKey);
						if(nMap.containsKey(yKey)){
							int[] attr=nMap.get(yKey);
							int curCount=attr[0];
							if(curCount!=0){
								if(xDiff==attr[1]*curCount && yDiff==attr[2]*curCount){
									attr[0]++;
								}else{
									attr[0]=0;
								}
								updateCurrDoneSlopes(currDoneSlopes, xKey, yKey, cons, attr[0]);
							}
						}else{
							nMap.put(yKey, getNewAttr(xDiff,yDiff));
						}
					}else{
						Map<Integer,int[]> nMap=new HashMap<>();
						nMap.put(yKey, getNewAttr(xDiff,yDiff));
						slopes.put(xKey, nMap);
					}
				}
			}
			
			updateDoneSlopes(doneSlopes, currDoneSlopes);
		}
		
		for(Map<Integer,Map<Double,Integer>> yVal:doneSlopes.values()){
			for(Map<Double,Integer> consMap:yVal.values()){
				count=Math.max(count,consMap.values().stream().mapToInt(Integer::intValue).max().getAsInt());
			}
		}
		
		return count;
	}
	
	private static int[] getNewAttr(int xDiff, int yDiff) {
		int[] attr=new int[3];
		attr[0]=2;
		attr[1]=xDiff;
		attr[2]=yDiff;
		return attr;
	}

	private static boolean checkDoneSlopes(Map<Integer, Map<Integer, Map<Double,Integer>>> doneSlopes, int xKey, int yKey, double cons) {
		if(doneSlopes.containsKey(xKey)){
			if(doneSlopes.get(xKey).containsKey(yKey)){
				if(doneSlopes.get(xKey).get(yKey).containsKey(cons)) return true;
				else return false;
			}else{
				return false;
			}
		}else{
			return false;
		}
	}

	private static void updateDoneSlopes(Map<Integer, Map<Integer, Map<Double,Integer>>> doneSlopes,
			Map<Integer, Map<Integer, Map<Double,Integer>>> currDoneSlopes) {
		
		for(Map.Entry<Integer, Map<Integer,Map<Double,Integer>>> entry: currDoneSlopes.entrySet()){
			int xKey=entry.getKey();
			Map<Integer, Map<Double,Integer>> yMap=entry.getValue();
			if(doneSlopes.containsKey(xKey)){
				for(Map.Entry<Integer, Map<Double,Integer>> entry2:yMap.entrySet()){
					int yKey=entry2.getKey();
					Map<Double,Integer> consMap=entry2.getValue();
					if(doneSlopes.get(xKey).containsKey(yKey)){
						doneSlopes.get(xKey).get(yKey).putAll(consMap);
					}else{
						doneSlopes.get(xKey).put(yKey, consMap);
					}
				}
			}else{
				doneSlopes.put(xKey, yMap);
			}
		}
	}

	private static void updateCurrDoneSlopes(Map<Integer, Map<Integer, Map<Double,Integer>>> currDoneSlopes, 
			int xKey, int yKey, double cons, int count) {
		
		if(currDoneSlopes.containsKey(xKey)){
			if(currDoneSlopes.get(xKey).containsKey(yKey)){
				currDoneSlopes.get(xKey).get(yKey).put(cons, count);
			}else{
				currDoneSlopes.get(xKey).put(yKey, getConsMap(cons, count));
			}
		}else{
			currDoneSlopes.put(xKey, getDroppedYMap(yKey,cons, count));
		}
	}

	private static Map<Integer, Map<Double,Integer>> getDroppedYMap(int yKey, double cons, int count) {
		Map<Integer, Map<Double,Integer>> yMap=new HashMap<>();
		yMap.put(yKey, getConsMap(cons, count));
		return yMap;
	}

	private static Map<Double,Integer> getConsMap(double cons, int count) {
		Map<Double,Integer> consMap=new HashMap<>();
		consMap.put(cons, count);
		return consMap;
	}

	private static int gcd(int num1, int num2){
		if (num1 == 0) return num2;
        if (num2 == 0) return num1;
        if (num1 < 0) return gcd(-1 * num1, num2);
        if (num2 < 0) return gcd(num1, -1 * num2);
        if (num1 > num2) return gcd(num2, num1);
        return gcd(num2%num1, num1);
	}
	
	public static void main(String[] args) {
		String[] str=new String[]{"2#1","6#6","4#2","2#5","2#6","2#7","3#4","6#1","6#2","2#3","6#3","6#4","6#5","6#7"};
//		String[] str=new String[]{"2#1","6#6","4#2","2#5","2#6","2#7","3#4","6#1","6#2","2#3","6#3","6#4","6#5","6#7","2#8","3#8","4#8","5#8","6#8","7#8","8#8","9#8","10#8"};
//		String[] str=new String[]{"1#1","1#100","100#1"};
		System.out.println(launchingSatellites(0, 0, str));
	}
}
