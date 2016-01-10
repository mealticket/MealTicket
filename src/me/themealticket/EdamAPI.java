package me.themealticket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.netazoic.ent.NetRoute;
import com.netazoic.ent.ServENT;
import com.netazoic.util.JSONUtil;

/*
 * This class is to interface with the Edamam food database API
 * 
 * See: https://developer.edamam.com/recipe-docs
 */
public class EdamAPI extends ServENT{

	NetRoute homeHdlr = new Home();
	NetRoute recipeSearchHdlr = new RecipeSearch();
	private static final String API_BASE_URL = "https://api.edamam.com/";


	public enum EDAPI_ROUTE{
		home,recipe_search
	}

	public enum API_ROUTE{
		search
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

	public enum EDAPI_Param{
		searchKey, dumpAll
	}


    @JsonIgnoreProperties(ignoreUnknown=true)
	public static class SearchResults{
		//Field	Type	Description
		public String	q;			//	string	Query text, as submitted
		public int		from;		//	integer	First result index, as submitted
		public int		to;			//	integer	Last result index, as submitted
		//public String[][]params;	//	String[][]	Effective parameters
		@JsonIgnore
		public String[]	params;		//	String[][]	Effective parameters
		public int		count;		//	integer	Number of results found
		public boolean	more;		//	boolean	More than the maximum allowed number of results found
		public RecipeWrapper[]	hits;		//	Recipe[]	Matching results (Recipe objects)
	}
	
    @JsonIgnoreProperties(ignoreUnknown=true)
	public static class RecipeWrapper{
		public Recipe recipe;
		public boolean bookmarked;
	}

	 @JsonIgnoreProperties(ignoreUnknown=true)
	public static class Recipe{
		//Field	Type	Description
		public String	uri;		// Ontology identifier
		public String   label;		// Recipe title
		public String	image;		// Image URL
		public String   source;		// Source site identifier
		public String	url	;		// string	Original recipe URL
		public int		yield;		// integer	Number of servings
		public String	level;		//enum	Difficulty level	easy,medium,hard

		public String 	summary;		//	string	Short description
		public Float	calories;		//	float	Total energy, kcal
		public Float	totalWeight;	//	float	Total weight, g
		public Ingredient[]	ingredients;	//	Ingredient[]	Ingredients list
		@JsonIgnore
		public NutrientInfo[]	totalNutrients;	//	NutrientInfo[]	Total nutrients per serving
		@JsonIgnore
		public NutrientInfo[]	totalDaily;		//	NutrientInfo[]	% daily value per serving
		@JsonIgnore
		public String[]	dietLabels;		//	enum[]	Diet labels: “balanced”, “high-protein”, “high-fiber”, “low-fat”, “low-carb”, “low-sodium”
		@JsonIgnore
		public String[]	healthLabels;	//	enum[]	Health labels: “vegan”, “vegetarian”, “paleo”, “dairy-free”, “gluten-free”, “wheat-free”, “fat-free”, “low-sugar”, “egg-free”, “peanut-free”, “tree-nut-free”, “soy-free”, “fish-free”, “shellfish-free”

		//NOT LISTED IN PUBLIC API
		public String 	sourceIcon;		// url for icon for source site?
		public String 	shareAs;		// ??
		@JsonIgnore
		public String   cautions;		//??

	}


	public static class Ingredient{
		//Field	Type	Description
		public String	uri;		//	string	Ontology identifier
		public Float	quantity;	//	float	Quantity of specified measure
		public Measure	measure;	//	Measure	Measure
		public Float	weight;		//	float	Total weight, g
		public Food		food;		//	Food	Food
		public String	text;		// ??  NOT INCLUDED IN PUBLIC API DESCRIPTION
	}

	public static class NutrientInfo{
		//Field	Type	Description
		public String	uri;		//	string	Ontology identifier
		public String 	label;		//	string	Display label
		public Float	quantity;	//	float	Quantity of specified units
		public String	unit;		//	string	Units

	}

	public static class Measure{
		//Field	Type	Description
		public Measure(String lbl){
			this.label = lbl;
		}
		public String	uri;	//	string	Ontology identifier
		public String	label;	//	string	Common name
	}

	public static class Food{
		//Field	Type	Description
		public String	uri;	//	string	Ontology identifier
		public String	label;	//	string	Common name
		
		public Food(String lbl){
			this.label = lbl;
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

		private class RecipeSummary{
			public String recipeName;
			public String image;
			public String uri;
			public String url;
			public String source;
			public String sourceIcon;
			public String shareAs;
			
			RecipeSummary (Recipe recipe){
				recipeName = recipe.label;
				image = recipe.image;
				uri = recipe.uri;
				url = recipe.url;
				source = recipe.source;
				shareAs = recipe.shareAs;
				sourceIcon = recipe.sourceIcon;
				
			}
	
		}
		@Override
		public void routeAction(HttpServletRequest request,
				HttpServletResponse response, Connection con, HttpSession session)
						throws IOException, Exception {

			String charset = java.nio.charset.StandardCharsets.UTF_8.name();

			String applicationID = "e9741903";
			String applicationKey  = "244a06ec7b50379730a16ad9c5718d05";
			String baseURL =  API_BASE_URL;
			String routeURL = "search";
			String url = baseURL + routeURL;
			String searchKey = (String) request.getAttribute(EDAPI_Param.searchKey.name());
			String query = String.format("q=%s&app_id=%s&app_key=%s", 
					URLEncoder.encode(searchKey,charset),
					URLEncoder.encode(applicationID, charset), 
					URLEncoder.encode(applicationKey, charset));
			HttpURLConnection connection = 
					(HttpURLConnection) new URL(url + "?" + query).openConnection();
			connection.setRequestProperty("Accept-Charset", charset);
			InputStream remote = connection.getInputStream();
			String jsonResults = "";
			String dumpAll = (String) request.getAttribute(EDAPI_Param.dumpAll.name());
			boolean flgDumpAll = false;
			if(dumpAll!=null) flgDumpAll = Boolean.parseBoolean(dumpAll);

			int status = connection.getResponseCode();

			String contentType = connection.getHeaderField("Content-Type");

			for (String param : contentType.replace(" ", "").split(";")) {
				if (param.startsWith("charset=")) {
					charset = param.split("=", 2)[1];
					break;
				}
			}

			if (charset != null) {
				try (BufferedReader reader = new BufferedReader(new InputStreamReader(remote, charset))) {
					for (String line; (line = reader.readLine()) != null;) {
						System.out.println(line);
						jsonResults += line;
					}
				}
			}

			if(flgDumpAll) ajaxResponse(jsonResults,response);
			else{
				try{
				String strResponse = "";
				//parse the jsonResults into a SearchResults object
				ObjectMapper mapper = new ObjectMapper();
				//JSON from file to Object
				SearchResults searchResults = mapper.readValue(jsonResults, SearchResults.class);
				RecipeSummary[] summaryResults = new RecipeSummary[searchResults.hits.length];
				int idx = 0;
				for(RecipeWrapper hit : searchResults.hits){
					RecipeSummary summary = new RecipeSummary(hit.recipe);
					summaryResults[idx] = summary;
					idx++;
				}
				String jsonSummary = JSONUtil.toJSON(summaryResults);
				jsonSummary = "{\"hits\":" + jsonSummary + "}";
				ajaxResponse(jsonSummary,response);
				}catch(JsonMappingException ex){
					throw new ServletException(ex);
				}
			}
			//ajaxResponse("{\"good\":\"job\"}",response);

		}	

	}
}
