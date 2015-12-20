package me.themealticket.db;

import java.sql.SQLException;
import java.sql.Statement;

public class Meal extends MTEnt {
	
	Long mealID;
	Long userID;
	String mealName;
	MealDay mealDay;
	MealTime mealTime;
	
	Dish[] dishes;   //or dishes, or recipes

	public enum MealDay {
		 MON,TUE,WED,THUR,FRI,SAT,SUN
		}
	
	public enum MealTime{
		Breakfast,Lunch,Dinner,Snack
	}

	@Override
	public void CreateEnt() throws SQLException {
		String q;
		Statement stat;
		
		stat = con.createStatement();
		
		q  = "INSERT INTO Meal (MealID,mealName) VALUES (" + mealID +","+ mealName+");";
		
		stat.execute(q);
		
		
	}


	@Override
	public void RetrieveEnt() {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void UpdateEnt() {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void DeleteEnt() {
		// TODO Auto-generated method stub
		
	}

}
