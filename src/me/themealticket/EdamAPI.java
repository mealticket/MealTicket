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
import com.netazoic.ent.ServENT.RouteEO;
import com.netazoic.util.ParseUtil;

/*
 * This class is to interface with the Edamam food database API
 */
public class EdamAPI extends ServENT{
	
	NetRoute homeHdlr = new Home();
	NetRoute recipeSearchHdlr = new RecipeSearch();
	
	public enum EDAPI_ROUTE{
		home,recipe_search
	}


	public  enum EDAPI_TPL{
		Home("/EDAPI/Home.html","Edamam API home page"),
		;
	
	
	
		String tplPath;
		String desc;
		EDAPI_TPL(String t, String d){
			tplPath = t;
			desc = d;
		}
	}
	
	public void init(ServletConfig config) throws javax.servlet.ServletException {
		super.init(config);
	
		defaultRoute = EDAPI_ROUTE.home.name();
	
		routeMap.put(EDAPI_ROUTE.home.name(), homeHdlr);
		routeMap.put(EDAPI_ROUTE.recipe_search.name(), recipeSearchHdlr);

	}
	
	public class Home extends RouteEO{
		
		@Override
		public void routeAction(HttpServletRequest request,
				HttpServletResponse response, Connection con, HttpSession session)
				throws IOException, Exception {
			String tPath = EDAPI_TPL.Home.tplPath;
			Map<String,Object> map = new HashMap<String,Object>();
			parser.parseOutput(map, tPath, response.getWriter());
		}	
		
	}
	
	public class RecipeSearch extends RouteEO{
		
		@Override
		public void routeAction(HttpServletRequest request,
				HttpServletResponse response, Connection con, HttpSession session)
				throws IOException, Exception {
			
			//TODO
		}	
		
	}
}
