package me.themealticket.db;


public class Dish {
	
	public enum FoodType{
		Drink, Protein, Carb, Vegetable, Fruit, Desert, Soup, Sandwich, Salad 
	}
	Long dishID;
	String dishName;
	FoodType foodType;
}


