package models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import com.avaje.ebean.Model;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import play.data.validation.Constraints.Required;
import play.libs.Json;


@Entity
public class Recipe extends Model{
	
	@Id
	private Long id;
	
	@Required
	private String title;
	
	@Required
	private String preparationTime;
	
	@Required
	private String description;
	
	@ManyToMany(cascade = CascadeType.ALL)
	private List<Ingredient> ingredients = new ArrayList<>();
	

	private static final Find<Long, Recipe> find = new Find<Long, Recipe>() {
	};
	
	public static List<Recipe> findAll() {
		return find.all();
	}
	
	public static Recipe getById(Long id){
		 return find.byId(id);
	}
	
	public static List<Recipe> getByName(String recipeName){
		
		 return find.where().eq("title",recipeName ).findList();
		 
	}
	
	public Long getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getPreparationTime() {
		return preparationTime;
	}

	public void setPreparationTime(String preparationTime) {
		this.preparationTime = preparationTime;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public void addIngredient(Ingredient ingredient){
		this.ingredients.add(ingredient);
		ingredient.recipes.add(this);
	}
	
	
	public void setIngredients(List<Ingredient> ingredients) {
		this.ingredients = ingredients;
	}

	public List<Ingredient> getIngredients() {
		return ingredients;
	}

	

	public JsonNode toJson() 
	{
		return Json.toJson(this);
	}
	
	public JsonNode toJsonList() 
	{
		
		ObjectNode node = play.libs.Json.newObject();
		node.put("id", this.id);
		node.put("title", this.title);
		node.put("description", this.description);
		return node;
		
	}
	
	
	


	@Override
	public String toString() {
		return "Recipe [id=" + id + ", title=" + title + ", preparationTime=" + preparationTime + ", description="
				+ description + ", ingredients=" + ingredients + "]";
	}
	

}
