package me.themealticket.db;

import java.sql.Connection;
import java.sql.SQLException;

public abstract class MTEnt {
	
	protected Connection con;

	public abstract void CreateEnt() throws SQLException;
	
	public abstract void RetrieveEnt();
	
	public abstract void UpdateEnt();
	
	public abstract void DeleteEnt();

}
