package models;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.Id;

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
	
	@Required
	private Map<Long, String> ingredients = new HashMap<>();

	
	
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

	public Map<Long, String> getIngredients() {
		return ingredients;
	}

	public void setIngredients(Long id, String quantity ) 
	{
	
		//Buscar en la Base de datos si tenemos o no el ingrediente
		
	}



	@Override
	public String toString() {
		return "Recipe [id=" + id + ", title=" + title + ", preparationTime=" + preparationTime + ", description="
				+ description + ", ingredients=" + ingredients + "]";
	}
	
	
	
	
	
	
	
	

}
