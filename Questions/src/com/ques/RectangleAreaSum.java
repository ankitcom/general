package com.ques;

/**
 * K,L lower left vertex and M,N right top vertex of first triangle
 * P,Q lower left vertex and R,S right top vertex of second triangle
 * Intersection shouldn't be calculated twice
 * @author maggy
 *
 */
class RectangleAreaSum {
    public int solution(int K, int L, int M, int N, int P, int Q, int R, int S) {
        int sol=0;
        long solL=0l;
        boolean vertex1=false,vertex2=false,vertex3=false,vertex4=false;
        boolean dealingWith1st=false,dealingWith2nd=false;
        
        long area1=((long)M-(long)K)*((long)N-(long)L);
        long area2=((long)R-(long)P)*((long)S-(long)Q);
        int inCount=0;
        
        if( K>P && K<R && N>Q && N<S ){
        	//vertex1 is inside
        	vertex1=true;
        	dealingWith1st=true;inCount++;
        }
        if( K>P && K<R && L>Q && L<S ){
        	//vertex2 is inside
        	vertex2=true;
        	dealingWith1st=true;inCount++;
        }
        if( M>P && M<R && L>Q && L<S ){
        	//vertex3 is inside
        	vertex3=true;
        	dealingWith1st=true;inCount++;
        }
        if( M>P && M<R && N>Q && N<S ){
        	//vertex4 is inside
        	vertex4=true;
        	dealingWith1st=true;inCount++;
        }
        
        if(!dealingWith1st){
        	if( P>K && P<M && S>L && S<N ){
            	//vertex1 is inside
            	vertex1=true;
            	dealingWith2nd=true;inCount++;
            }
        	if( P>K && P<M && Q>L && Q<N ){
            	//vertex2 is inside
            	vertex2=true;
            	dealingWith2nd=true;inCount++;
            }
        	if( R>K && R<M && Q>L && Q<N ){
            	//vertex3 is inside
            	vertex3=true;
            	dealingWith2nd=true;inCount++;
            }
        	if( R>K && R<M && S>L && S<N ){
            	//vertex4 is inside
            	vertex4=true;
            	dealingWith2nd=true;inCount++;
            }
        }
        
        if(dealingWith2nd){
        	long area3=area1;
        	area1=area2;area2=area3;
        	int a=K;K=P;P=a;
        	a=L;L=Q;Q=a;
        	a=M;M=R;R=a;
        	a=N;N=S;S=a;
        }
        
        if(!dealingWith1st && !dealingWith2nd){
        	solL=area1+area2;
        }else{
        	if(inCount==1){
        		if(vertex1){
        			solL=area1+area2-( (R-K)*(N-Q) );
        		}else if(vertex2){
        			solL=area1+area2-( (R-K)*(S-L) );
        		}else if(vertex3){
        			solL=area1+area2-( (M-P)*(S-L) );
        		}else if(vertex4){
        			solL=area1+area2-( (M-P)*(N-Q) );
        		}
        	}else if(inCount==4){
        		solL=area2-area1;
        	}else if(inCount==2){
        		if(vertex1&&vertex2){
        			solL=area1+area2-( (N-L)*(R-K) );
        		}else if(vertex2&&vertex3){
        			solL=area1+area2-( (M-K)*(S-L) );
        		}else if(vertex3&&vertex4){
        			solL=area1+area2-( (N-L)*(M-P) );
        		}else if(vertex4&&vertex1){
        			solL=area1+area2-( (M-K)*(N-Q) );
        		}
        	}
        }
        
        
        if(solL>2147483647l) sol=-1;
        else{
        	sol=(int)solL;
        }
        	
        return sol;
    }
}