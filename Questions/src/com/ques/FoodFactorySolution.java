package com.ques;

public class FoodFactorySolution {

	public static void main(String[] argh) throws Exception{
		foodFactory myFoods = new foodFactory();
		Food food1 = myFoods.getFood("com.ques.Fastfood");
		Food food2 = myFoods.getFood("com.ques.Fruits");	
		System.out.println("My name is: " + food1.getClass().getName());
		System.out.println("My name is: " + food2.getClass().getName());
		System.out.println("Our superclass is: " + food1.getClass().getSuperclass().getName());
		food1.serveFood();
		food2.serveFood();
	}
}

//Write your code here
class foodFactory extends FoodFactorySolution {
  public Food getFood(String food){
      return Food.getFood(food);
  }
}

abstract class Food extends FoodFactorySolution {
  
  
  
  public static Food getFood(String foodType) {

      Food f=null;
      try{
          f=(Food)Class.forName(foodType).newInstance();
      }catch(ClassNotFoundException ce){
          //Catch and throw valid exception according to the application
      }catch(InstantiationException ie){
          
      }catch(IllegalAccessException  iae){
          
      }
      return f;
  }
  
  public void serveFood(){
      System.out.println("I'm serving "+this.getClass().getName());
  }
}

class Fastfood extends Food {
  public Fastfood(){
      
  }
}

class Fruits extends Food {
  public Fruits(){
      
  }
}