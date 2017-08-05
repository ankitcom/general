package com.ques;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FreightHub {

	
	public static List<String>[] getPolygonSets(String fileName){
		List<String>[] sets=new List[4];
		for(int i=0;i<sets.length;i++){
			sets[i]=new ArrayList<>();
		}
		
		try (Scanner scanner = new Scanner(new File(fileName))) {

			while (scanner.hasNext()){
				String polygon=scanner.nextLine();
				String[] sides=polygon.split(",");
				if(sides.length==3){
					sets[0].add(polygon);
				}else if(sides.length==4){
					//Since the problem says angles are irrelevant, I'm considering the polygon with 4 sides either as square or rectangle.
					//Assuming there can't be any other thing like rhombus.
					if(sides[0].equals(sides[1])){
						//It's a square as adjacent sides are equal. No more checks are required as we're assuming 90 degree angle always
						sets[2].add(polygon);
					}else{
						//Since adjacent sides aren't equal, it means it's a rectangle
						sets[1].add(polygon);
					}
				}else{
					sets[3].add(polygon);
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return sets;
	}
	
	public static int cumulativeTtl(Request[] requests){
		if(requests.length==0) return 0;
		long earliestStart=Long.MAX_VALUE;
		long lastFinish=0;
		for(int i=0;i<requests.length;i++){
			Request req=requests[i];
			earliestStart=earliestStart<req.startedAt?earliestStart:req.startedAt;
			lastFinish=lastFinish>(req.startedAt+req.ttl)?lastFinish:(req.startedAt+req.ttl);
		}
		
		return (int)(lastFinish-earliestStart);
	}
	
	public static class Request{
		String requestId;
		long startedAt;
		int ttl;
		
		public Request(String requestId, long startedAt, int ttl) {
			super();
			this.requestId = requestId;
			this.startedAt = startedAt;
			this.ttl = ttl;
		}
	}
	
	public static void main(String[] args) {
		
		print(getPolygonSets("/Users/ankit.bhatnagar/Desktop/polyFile.txt"));
		
		Request[] requests=new Request[4];
		requests[0]=new Request("poiax",1489744808,8);
		requests[1]=new Request("kdfhd",1489744803,3);
		requests[2]=new Request("uqwyet",1489744806,12);
		requests[3]=new Request("qewaz",1489744810,1);
		System.out.println(cumulativeTtl(requests));
	}

	private static void print(List<String>[] polygonSets) {
		for(int i=0;i<polygonSets.length;i++){
			switch(i){
			case 0:System.out.println("Triangles:");break;
			case 1:System.out.println("Rectangles:");break;
			case 2:System.out.println("Squares:");break;
			case 3:System.out.println("Others:");break;
			}
			polygonSets[i].stream().forEach(System.out::println);
		}
	}
}
