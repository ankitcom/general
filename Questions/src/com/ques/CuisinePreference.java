package com.ques;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class CuisinePreference {

	public String[][] matchLunches(String[][] lunchMenuPairs, 
            String[][] teamCuisinePreference){
		
		List<String> resultList=new ArrayList<>();
		
		Map<String,List<String>> cuisOptMap=new HashMap<>();
		
		for(int i=0;i<lunchMenuPairs.length;i++){
			String opt=lunchMenuPairs[i][0];
			String cuisine=lunchMenuPairs[i][1];
			if(cuisOptMap.containsKey(cuisine)){
				cuisOptMap.get(cuisine).add(opt);
			}else{
				List<String> optList=new LinkedList<>();
				optList.add(opt);
				cuisOptMap.put(cuisine, optList);
			}
		}
		
		for(int i=0;i<teamCuisinePreference.length;i++){
			String name=teamCuisinePreference[i][0];
			String cuisine=teamCuisinePreference[i][1];
			if(cuisine.equals("*")){
				Iterator<List<String>> listItr=cuisOptMap.values().iterator();
				while(listItr.hasNext()){
					Iterator<String> itr=listItr.next().iterator();
					while(itr.hasNext()){
						resultList.add(name);
						resultList.add(itr.next());
					}
				}
			}else{
				if(cuisOptMap.containsKey(cuisine)){
					Iterator<String> itr=cuisOptMap.get(cuisine).iterator();
					while(itr.hasNext()){
						resultList.add(name);
						resultList.add(itr.next());
					}
				}
				
			}
			
		}
		
		if(resultList.size()>0){
			String[][] result=new String[resultList.size()/2][2];
			for(int i=0;i<resultList.size();i=i+2){
				result[i/2][0]=resultList.get(i);
				result[i/2][1]=resultList.get(i+1);
			}
			return result;
		}else{
			return new String[0][0];
		}
	}
}
