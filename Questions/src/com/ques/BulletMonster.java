package com.ques;

public class BulletMonster {

	public static void main(String[] args) {
		Point[] monster=new Point[]{new Point(1,1),new Point(2,1.5f),new Point(2,2.5f),new Point(1.5f,2),new Point(1,2)};
		System.out.println(isMonsterKilled(monster, new Point(1.5f,1.5f)));
	}
	
	public static boolean isMonsterKilled(Point[] monster, Point bullet){
		
		boolean isMonsterKilled=false;
		
		//create an outside point away from the highest x axis point
		float xMax=monster[0].x;
		for(int i=1;i<monster.length;i++){
			if(xMax<monster[i].x) xMax=monster[i].x;
		}
		Point endPath=new Point(xMax+1f,bullet.y);
		
		//checking the intersection with each segment
		//if number of intersections are odd, that means bullet did hit the monster
		for(int i=0;i<monster.length;i++){
			Point endPoint;
			if(i==monster.length-1) endPoint=monster[0];
			else endPoint=monster[i+1];
			
			if(isSegmentIntersecting(monster[i], endPoint, bullet, endPath)){
				isMonsterKilled=!isMonsterKilled;
				if(endPoint.y==bullet.y){
					Point endPlusPoint;
					if(i==monster.length-2){
						endPlusPoint=monster[0];
					}else if(i==monster.length-1){
						endPlusPoint=monster[1];
					}else{
						endPlusPoint=monster[i+2];
					}
					if( (monster[i].y>bullet.y?endPlusPoint.y<bullet.y:endPlusPoint.y>bullet.y) ){
						isMonsterKilled=!isMonsterKilled;
					}
				}
			}
		}
		
		return isMonsterKilled;
	}
	
	public static boolean isSegmentIntersecting(Point p1, Point q1, Point p2, Point q2){
		
		boolean isSegmentIntersecting=false;
		
		int o1 = orientation(p1, q1, p2);
		int o2 = orientation(p1, q1, q2);
		int o3 = orientation(p2, q2, p1);
		int o4 = orientation(p2, q2, q1);
		
		if(o1 != o2 && o3 != o4) isSegmentIntersecting=true;
		//System.out.println(isSegmentIntersecting);
		return isSegmentIntersecting;
	}
	
	public static int orientation(Point p, Point q, Point r){
		
		float val = (q.y - p.y) * (r.x - q.x) - (q.x - p.x) * (r.y - q.y);
		if(val == 0) return 0; //collinear
		return (val>0) ? 1:2; //clockwise:counterclockwise
	}
}

class Point{
	float x;
	float y;
	
	Point(float x, float y){
		this.x=x;
		this.y=y;
	}
}