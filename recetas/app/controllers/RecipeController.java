package controllers;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import models.Ingredient;
import models.Recipe;
import models.Tag;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Results;

public class RecipeController extends Controller{

	public Result create() 
	{
		System.out.println("entra aqui");
		System.out.println(request().toString());
		JsonNode body = request().body().asJson();
		System.out.println(body);
		
		if (body.isNull()){
			
			
			return Results.badRequest();
			
			
		}
		
		List<Recipe> listRecipes = Recipe.getByName(body.get("title").asText());
		if (!listRecipes.isEmpty()){
			
			return Results.status(CONFLICT);
			
		}	
		
		Recipe recipe = new Recipe();
		Ingredient newIngredient = new Ingredient();
		
		recipe.setTitle(body.get("title").asText());
		recipe.setDescription(body.get("description").asText());
		recipe.setPreparationTime(body.get("preparedTime").asText());
		
		
		ArrayNode arrayTags = (ArrayNode) body.get("tags");
		

		for(JsonNode node2 : arrayTags)
		{
			
			
			List<Tag> tagsList = Tag.getByName(node2.asText().trim().toLowerCase());
			if (tagsList.isEmpty())
			{
				System.out.println("Ingredient añadido a la db");
				Tag tag = new Tag();
				tag.setName(node2.asText().trim().toLowerCase());
				tag.save();
				recipe.addTag(tag);
			}else
			{
				System.out.println("Ingredient ya en la db");
				recipe.addTag(tagsList.get(0));
			}
			
			
		}
		
	
		
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
		else{
			
			return Results.status(406);
			
		}
		
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
			
			}else{
				
				return Results.status(406);
				
			}
		}
		return Results.notFound();
	}
	
	public Result remove(Long id) 
	{
		//Obtenemos la recipe con esa id
		Recipe recipe = Recipe.getById(id);
		
		List<Ingredient> ingredientsList = recipe.getIngredients();
		
		if (recipe != null)// Existe la receta
		{
			if (recipe.delete()){
				return ok();
			}
			else{
				
				return internalServerError();
				
			}
		}
		
		
		return Results.notFound();
				
		
		
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
		else{
			
			return Results.status(406);
			
		}
		
		
	}
	
	public Result update(Long id) {

		Recipe recipe = Recipe.getById(id);
		JsonNode body = request().body().asJson();
		
		
		if (recipe == null){
			
			return Results.notFound();
			
		}
		
	
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
			
			return ok ();
    	
    }
	
	
	public Result getByName(String name){
		
		System.out.println(name);
		List<Recipe> listRecipes = Recipe.getByName(name);
		
		
		if(listRecipes.isEmpty()) // No hemos encontrado la receta
		{
		
			return Results.notFound();
			
			
		}else{ //Receta encontrada
			
			Recipe recipe = listRecipes.get(0);
			
			if (request().accepts("application/json"))
			{
				return ok (recipe.toJsonList());
				
				
			}else if(request().accepts("application/xml"))
			{	
				return ok(views.xml.recipe.render(recipe));
			}
			else{
				
				return Results.status(406);
				
			}
			
			
		}
		
	}
	
	public Result getRecipesByTag(String tagName){
		
		List<Tag> tag = Tag.getByName(tagName);
		
		if (tag.isEmpty()){
			
			return Results.notFound();
			
		}else{
		
			
			List<Recipe> recipes = Recipe.findRecipesByTag(tag.get(0));
			
			
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
			else{
				
				return Results.status(406);
				
			}
			
			
			
		}
		
		
	}
	
}
