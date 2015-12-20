package me.themealticket.db.archive;

import me.themealticket.db.Meal;
import me.themealticket.db.Meal.MealTime;



public class MTSchedule {
	

	
	MTScheduleMeal[] Meals;
	
	
	class MTScheduleMeal{
		Meal mealOfTheDay;
		Integer dayOfWeek;    //1-7  Sunday = 1
		MealTime mealTime;
	}

}
