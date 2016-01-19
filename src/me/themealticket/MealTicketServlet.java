package me.themealticket;


import java.io.IOException;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.netazoic.ent.NetRoute;
import com.netazoic.ent.ServENT;


public class MealTicketServlet extends ServENT{
	
	NetRoute homeHdlr = new Home();
	
	public enum HOME_ROUTE{
		home
	}
	
	//creating enum HOME TemPLate?
	public  enum HOME_TPL{
		Home("/HOME/Home.html","MealTicket home page"),
		;
		// ^^^Why is there a comma, paragraph, then semicolon?
		// Why all this whitespace?
	
		//Why store template path and description into variables?
		String tplPath;
		String desc;
		HOME_TPL(String t, String d){
			tplPath = t;
			desc = d;
		}
	}
	
	
	//Clearly I've missed the point of Routes...
		//Trying to convert EDAPI_ROUTE to HOME_ROUTE without knowing why
		//Where is ServletConfig, config, and Serv..Exception used?
	
	public void init(ServletConfig config) throws javax.servlet.ServletException {
			super.init(config);

			defaultRoute = HOME_ROUTE.home.name();

			routeMap.put(HOME_ROUTE.home.name(), homeHdlr);
	}
		
	public class Home extends RouteEO{

			@Override
			public void routeAction(HttpServletRequest request,
				HttpServletResponse response, Connection con, HttpSession session)
							throws IOException, Exception {
				String tPath = HOME_TPL.Home.tplPath;
				Map<String,Object> map = new HashMap<String,Object>();
				parser.parseOutput(map, tPath, response.getWriter());
			}	
	}
}
