package com.ques;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Stack;
import java.util.stream.Collectors;

class FizzBuzzAndDirectoryPath {
	
	static String[] JudgeSort(String judgesInput) {

		String[] inputs=judgesInput.split(",");
		for(int i=0;i<inputs.length;i++){
//			boolean isValid=false;
			String numS=inputs[i];
			try{
				int numI=Integer.parseInt(numS);
				if(numI>=-10&&numI<=10){
//					isValid=true;
					//new Score(numI,numS);
				}
			}catch(Exception e){
				
			}
		}
		return inputs;

    }
	
	class Score implements Comparable<Score>{
		Integer numI;
		String numS;
		
		public Score(Integer numI, String numS){
			this.numI=numI;
			this.numS=numS;
		}

		@Override
		public int compareTo(Score o) {
			return numI.compareTo(o.numI);
		}
		
	}
	
	
    public static void main(String args[] ) throws Exception {
    	
    	/* Directory Path calculations
    	System.out.println(createPath(new String[]{"foo","bar","..","bob"}));
    	*/
    	
    	/*Fizz Buzz divider 3 and divider 5
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();
        int cases = Integer.parseInt(line);
        String numbersStr=br.readLine();
        String[] numbers=numbersStr.split(" ");
        
        StringBuffer sb=new StringBuffer();
        for (int i = 0; i < cases; i++) {
            for(int j=1;j<=Integer.parseInt(numbers[i]);j++){
            	
            	if(j%3==0) sb.append("Fizz");
            	if(j%5==0) sb.append("Buzz");
            	if(j%3!=0&&j%5!=0) sb.append(j);
            	sb.append("\n");
            	
            }
        }

        System.out.println(sb.toString());
        br.close();
        
        final Collection<? extends Number> foo= new ArrayList<Number>();
        foo.add(new Integer(4));
        foo.add(new Object());
        foo.addAll(null);
        foo=null;
        */
    }
    
    static String createPath(String[] pathSegments) {
    	Stack<String> stack=new Stack<String>();
    	
    	Iterator<String> itr=Arrays.asList(pathSegments).iterator();
    	while(itr.hasNext()){
    		String ent=itr.next();
    		if("..".equals(ent)){
    			stack.pop();
    		}else if(".".equals(ent)){
    			//do nothing
    		}else if("/".equals(ent)){
    			stack.removeAllElements();
    		}else{
    			String[] entin=ent.split("/");
    			for(int i=0;i<entin.length;i++){
    				if("..".equals(entin[i])){
    	    			stack.pop();
    	    		}else if(".".equals(entin[i])){
    	    			//do nothing
    	    		}else if("/".equals(entin[i])){
    	    			stack.removeAllElements();
    	    		}else{
    	    			stack.push(entin[i]);
    	    		}
    			}
    		}
    	}
    	
        return '/' + stack.stream().collect(Collectors.joining("/"));
    }
    
}
