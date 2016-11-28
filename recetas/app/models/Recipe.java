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

import play.data.validation.Constraints.Required;


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



	@Override
	public String toString() {
		return "Recipe [id=" + id + ", title=" + title + ", preparationTime=" + preparationTime + ", description="
				+ description + ", ingredients=" + ingredients + "]";
	}
	

}
