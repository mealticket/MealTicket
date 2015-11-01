package me.themealticket;

public class Meal {
	
	public enum MealTime{
		Breakfast,Lunch,Dinner,Snack
	}
	
	Long mealID;
	String mealName;
	Integer dayOfWeek;    //1-7
	MealTime mealTime;
	
	Dish[] dishes;   //or dishes, or recipes

}
