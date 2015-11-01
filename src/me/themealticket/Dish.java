package me.themealticket;

public class Dish {
	
	public enum DishType{
		Drink, Protein, Carb, Vegetable, Fruit, Desert, Snack
	}
	
	Long dishID;
	String dishName;
	DishType dishType;
	Recipe dishRecipe;

}
