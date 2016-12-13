package controllers;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

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
			List<Ingredient> ingredientList = Ingredient.getByName(node1.get("name").asText().trim().toLowerCase());
			if (ingredientList.isEmpty())
			{
				System.out.println("Ingredient añadido a la db");
				newIngredient = new Ingredient();
				newIngredient.setName(node1.get("name").asText().trim().toLowerCase());
				newIngredient.setDescription(node1.get("description").asText());
				newIngredient.save();
				
				recipe.addIngredient(newIngredient);
			}else
			{
				System.out.println("Ingredient ya en la db");
				recipe.addIngredient(ingredientList.get(0));
			}
			
			
		}
		
		recipe.save();
		
		if (request().accepts("application/json")){
		
			
			return ok(recipe.toJson());
			
		}
		else if(request().accepts("application/xml")){
			
			return ok(views.xml.recipe.render(recipe));
		
		}
		
		
		
		return ok("created");	
		
	}
	
	public Result retrieve(Long id) {
		
		Recipe recipe = Recipe.getById(id);
		if (recipe != null) // Recipe Exist
		{
			
			if (request().accepts("application/json")){
				
				
				return ok(recipe.toJson());
				
			}
			else if(request().accepts("application/xml")){
				
				return ok(views.xml.recipe.render(recipe));
			
			}
		}
		return ok("No se ha encontrado receta con ese id");
	}
	
	public Result remove(Long id) 
	{
		//Obtenemos la recipe con esa id
		Recipe recipe = Recipe.getById(id);
		
		List<Ingredient> ingredientsList = recipe.getIngredients();
		
		if (recipe != null)// Existe la receta
		{
			if (recipe.delete()){
				return ok("Receta eliminada con exito");
			}
			else{
				return ok("Error a la hora de eliminar la receta");
			}
		}
		
		
		return ok("Receta no encontrada");
				
		
		
	}
	
	public Result listRecipes() 
	{
		
		List<Recipe> recipes = Recipe.findAll();
		
		if (request().accepts("application/json"))
		{
			ArrayNode array = new play.libs.Json().newArray();
			
			
			for (Recipe recipe1 : recipes)
			{	
				array.add(recipe1.toJsonList());
			}
			
			return ok (array);
			
		}else if(request().accepts("application/xml"))
		{	
			return ok(views.xml.recipes.render(recipes));
		}
		
		return ok("Solo de admite Json o XML");
		
	}
	
	public Result update(Long id) {

		Recipe recipe = Recipe.getById(id);
		JsonNode body = request().body().asJson();
		
		
	
		if (body.has("title"))
		{
			recipe.setTitle(body.get("title").asText());
			
		}if (body.has("preparationTime"))
		{
			recipe.setPreparationTime(body.get("preparationTime").asText());
			
			
		}if (body.has("description"))
		{
			recipe.setDescription(body.get("description").asText());
			System.out.println("Description");
			
		}if (body.has("ingredients"))
		{
			
			System.out.println("Ingredients");
			ArrayNode array = (ArrayNode) body.get("ingredients");
			List<Ingredient> listIngredients = new LinkedList<>();
			for(JsonNode node1 : array)
			{
	
				List<Ingredient> ingredientList = Ingredient.getByName(node1.get("name").asText().trim().toLowerCase());
				if (ingredientList.isEmpty())
				{
					System.out.println("Ingredient añadido a la db");
					Ingredient newIngredient = new Ingredient();
					newIngredient.setName(node1.get("name").asText().trim().toLowerCase());
					newIngredient.setDescription(node1.get("description").asText());
					newIngredient.save();
					
					listIngredients.add(newIngredient);
					
				}else
				{
					System.out.println("Ingredient ya en la db");
					listIngredients.add(ingredientList.get(0));
					
				}
				
				
					
					
			}
			
			
			recipe.setIngredients(listIngredients);
			System.out.println(recipe.getIngredients().get(0));	
			recipe.save();
			
		}
			
			return ok ("Actualizada");
    	
    }
}
