package me.themealticket.db;



public class MTSchedule {
	

	
	MTScheduleMeal[] Meals;
	
	
	class MTScheduleMeal{
		Meal mealOfTheDay;
		Integer dayOfWeek;    //1-7  Sunday = 1
		MealTime mealTime;
	}

}
