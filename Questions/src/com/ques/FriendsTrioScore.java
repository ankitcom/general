package com.ques;

public class FriendsTrioScore {

	static int bestTrio(int friends_nodes, int[] friends_from, int[] friends_to) {
		int result=-1;
		
		int[] friendsCount=new int[friends_nodes+1];
		boolean[][] friendMap=new boolean[friends_nodes+1][friends_nodes+1];
		
		for(int i=0;i<friends_from.length;i++){
			friendMap[friends_from[i]][friends_to[i]]=true;
			friendMap[friends_to[i]][friends_from[i]]=true;
			friendsCount[friends_from[i]]++;
			friendsCount[friends_to[i]]++;
		}
		
		for(int i=1;i<=friends_nodes;i++){
			for(int j=i+1;j<=friends_nodes;j++){
				if(friendMap[i][j]==true){
					for(int k=j+1;k<=friends_nodes;k++){
						if(friendMap[j][k]==true && friendMap[k][i]==true){
							//trio formed
							int trioScore=friendsCount[i]+friendsCount[j]+friendsCount[k]-6;
							if(result==-1) result=trioScore;
							else result=Math.min(result, trioScore);
						}
					}
					
				}
			}
		}
		
		return result;
    }
}
