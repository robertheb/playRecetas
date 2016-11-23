package controllers;

import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;

import models.Ingredient;
import models.Recipe;
import play.mvc.Controller;
import play.mvc.Result;

public class RecipeController extends Controller{

	public Result create() 
	{
	
		JsonNode body = request().body().asJson();
		Recipe recipe = new Recipe();
		recipe.setTitle(body.get("title").asText());
		recipe.setDescription(body.get("description").asText());
		recipe.setPreparationTime(body.get("preparedTime").asText());
		recipe.save();
		
		ArrayNode array = (ArrayNode) body.get("ingredients");

		
		for(JsonNode node1 : array)
		{
			
			Ingredient newIngredient = new Ingredient();
			
			newIngredient = new Ingredient();
			newIngredient.setName(node1.get("name").asText());
			newIngredient.setDescription(node1.get("description").asText());
			newIngredient.addRecipe(recipe);
			newIngredient.save();
			recipe.addIngredient(newIngredient);
			
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
		List<Recipe> recipes = Recipe.findAll();
		System.out.println(recipes);
		return ok("ok");
	}
	
	public Result update(String id) {

    	return ok("No existe ese usuario");
    	
    }
}
