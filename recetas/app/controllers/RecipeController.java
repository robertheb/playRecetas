package controllers;

import com.fasterxml.jackson.databind.JsonNode;

import play.mvc.Controller;
import play.mvc.Result;

public class RecipeController extends Controller{

	public Result create() 
	{
	
		JsonNode body = request().body().asJson();
		String titulo = body.get("titulo").asText();
		String description = body.get("description").asText();
		String preparedTime = body.get("preparedTime").asText();
		
		for(JsonNode node : body.get("ingredients"))
		{
			
			String ingredientName = node.get("name").asText();
			String descriptionName = node.get("description").asText();
			String cuantity = node.get("cuantity").asText(); 
			
			
		}
		
		
		
		return ok("created");
		
		
		
		
		
	}
	
	public Result retrieve(Long id) {
		return ok("recipe");
	}
	
	public Result remove(long id) {
		return ok("removed");
	}
	
	public Result listRecipes() {	
		return ok("ok");
	}
	
	public Result update(String id) {

    	return ok("No existe ese usuario");
    	
    }
}
