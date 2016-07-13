package com.ques;

class SwapOneFromIntArrayMakeNonDescending {
    static public boolean solution(int[] A) {
    	boolean result =true;
    	
    	int toChange=0;
    	int changeWith=0;
    	boolean isSwapped=false;
    	for(int i=1;i<A.length;i++){
    		if(isSwapped){
    			if(A[i]<A[i-1]){
    				result=false;
    				break;
    			}
    		}else{
    			if(toChange>0){
        			if(A[i]<A[i-1]){
        				toChange=i;
        				swap(A,changeWith,toChange);
        				isSwapped=true;
        			}else if(A[i]==A[i-1]){
        				if(toChange!=Integer.MAX_VALUE) toChange=i;
        			}else if(A[i]>A[i-1] && A[i]>A[changeWith] ){
        				if(toChange!=Integer.MAX_VALUE){
        					swap(A,changeWith,toChange);
            				isSwapped=true;
            				if(A[i]<A[i-1]){
            					result=false;
            					break;
            				}
        				}else{
        					result=false;
        					break;
        				}
        				
        			}else if(A[i]>A[i-1] && A[i]<=A[changeWith] ){
        				toChange=Integer.MAX_VALUE;
        			}
        		}else if(A[i]<A[i-1]){
        			toChange=i;
        			changeWith=i-1;
        		}
    		}
    	}
    	
    	if(toChange>0 && isSwapped==false){
    		if(toChange!=Integer.MAX_VALUE){
    			swap(A,changeWith,toChange);
    			isSwapped=true;
    		}else{
    			result=false;
    		}
    	}
    	
    	return result;
    }
    
    static public void swap(int[] A, int changeWith, int toChange){
    	int reserve=A[changeWith];
    	A[changeWith]=A[toChange];
    	A[toChange]=reserve;
    }
    
    public static void main(String[] args) {
//		int[] A = new int[]{5,3,3,4,2,7,8};
//		int[] A = new int[]{5,3,3,4,7,8};
//		int[] A = new int[]{5,3,3,4,4,4};
//		int[] A = new int[]{5,3,3,5,2,7,8};
//		int[] A = new int[]{5,3,3,4,5,2,7,8};
//		int[] A = new int[]{5,3,3,4,6,2,7,8};
//		int[] A = new int[]{5,3,3,4,4,5,3,6,7,8};
		int[] A = new int[]{6,3,3,4,5,4,5,3,6,7,8};
		System.out.println(solution(A));
	}
}