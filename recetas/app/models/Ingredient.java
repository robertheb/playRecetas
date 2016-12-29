package models;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import com.avaje.ebean.Model;
import com.avaje.ebean.Model.Find;
import com.fasterxml.jackson.annotation.JsonIgnore;

import play.data.validation.Constraints.Required;


@Entity
public class Ingredient extends Model
{
	@Id
	private Long id;
	
	@Required
	private String name;
	
	@Required
	private String description;
	
	@ManyToMany(mappedBy = "ingredients")
	@JsonIgnore
    public Set<Recipe> recipes;
	//public List<Recipe> recipes = new ArrayList<>();
	
	private static final Find<Long,Ingredient> find =new Find<Long,Ingredient>(){};
	
	public static List<Ingredient> getByName(String ingredient){
		
		 return find.where().eq("name",ingredient ).findList();
		 
	}
	
	
	public Long getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	public void addRecipe(Recipe recipe){
		this.recipes.add(recipe);
	}

	@Override
	public String toString() {
		return "Ingredient [id=" + id + ", name=" + name + ", description=" + description + "]";
	}
	
	
	

}
