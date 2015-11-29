package me.themealticket.db;


public class Recipe {
	
	public enum RecipeType{
		Drink, Protein, Carb, Vegetable, Fruit, Desert, Snack
	}
	
	Long recipeID;
	String recipeName;
	RecipeType recipeType;
	Ingredient[] ingredients;

}


