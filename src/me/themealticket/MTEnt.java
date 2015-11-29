package me.themealticket;

import java.sql.Connection;
import java.sql.SQLException;

public abstract class MTEnt {
	
	Connection con;

	public abstract void CreateEnt() throws SQLException;
	
	public abstract void RetrieveEnt();
	
	public abstract void UpdateEnt();
	
	public abstract void DeleteEnt();
}
