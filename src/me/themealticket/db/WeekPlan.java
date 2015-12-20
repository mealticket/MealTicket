package me.themealticket.db;

import me.themealticket.db.Meal.MealDay;
import me.themealticket.db.Meal.MealTime;

public class WeekPlan {
 public Long WeekPlanID;
 public Long userID;
 MealDay[] daysInPlan;
 MealTime[] mealTimes;
 Meal[] meals;
}
