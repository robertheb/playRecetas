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
		System.out.println("entra aqui");
		System.out.println(request().toString());
		JsonNode body = request().body().asJson();
		System.out.println(body);
		
		Recipe recipe = new Recipe();
		Ingredient newIngredient = new Ingredient();
		
		recipe.setTitle(body.get("title").asText());
		recipe.setDescription(body.get("description").asText());
		recipe.setPreparationTime(body.get("preparedTime").asText());
		
		
		ArrayNode array = (ArrayNode) body.get("ingredients");

		
		for(JsonNode node1 : array)
		{
			newIngredient = new Ingredient();
			newIngredient.setName(node1.get("name").asText());
			newIngredient.setDescription(node1.get("description").asText());
			newIngredient.save();
			recipe.addIngredient(newIngredient);
		}
		
		recipe.save();
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
