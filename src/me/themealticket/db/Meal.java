package me.themealticket.db;

import java.sql.SQLException;
import java.sql.Statement;

import me.themealticket.MTEnt;

public class Meal extends MTEnt {
	
	Long mealID;
	String mealName;

	
	Recipe[] dishes;   //or dishes, or recipes


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
