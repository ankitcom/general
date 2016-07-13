package com.ques;

/**
 * A zero-indexed array A consisting of N integers is given. An equilibrium index of this array is any integer P such that 0 ≤ P < N 
 * and the sum of elements of lower indices is equal to the sum of elements of higher indices, i.e.
 *  A[0] + A[1] + ... + A[P−1] = A[P+1] + ... + A[N−2] + A[N−1].
 *  Sum of zero elements is assumed to be equal to 0. This can happen if P = 0 or if P = N−1.
 * @author maggy
 */
class LargeIntEquillibrium {
	
	public static boolean willAdditionOverflow(int left, int right) {
        if (right < 0 && right != Integer.MIN_VALUE) {
            return willSubtractionOverflow(left, -right);
        } else {
            return (~(left ^ right) & (left ^ (left + right))) < 0;
        }
    }

    public static boolean willSubtractionOverflow(int left, int right) {
        if (right < 0) {
            return willAdditionOverflow(left, -right);
        } else {
            return ((left ^ right) & (left ^ (left - right))) < 0;
        }
    }
	
	public static void main(String[] args) {
		int[] A={0, -2147483648, -2147483648, -2147483648};
		System.out.println(solution(A));
	}
    public static int solution(int[] A) {
        int eq=-1;
//        BigInteger sumS=new BigInteger("0");
//        BigInteger sumE=new BigInteger("0");
        long sumS=0;
        long sumE=0;
        //int sumS=0,sumE=0;
        
        for(int i=1;i<A.length;i++){
//        	sumE=sumE.add(new BigInteger(String.valueOf(A[i])));
        	sumE=sumE+A[i];
        }
        
        for(int i=0;i<A.length;i++){
        	if(i!=0){
//        		sumS=sumS.add(new BigInteger(String.valueOf(A[i-1])));
//        		sumE=sumE.subtract(new BigInteger(String.valueOf(A[i])));
        		sumS=sumS+A[i-1];
        		sumE=sumE-A[i];
        	}
        	
//        	if(sumS.equals(sumE)) return i;
        	if(sumS==sumE) return i;
        }
        
        return eq;
    }
    
}