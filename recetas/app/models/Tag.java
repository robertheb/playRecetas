package models;

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
public class Tag extends Model
{

	@Id
	private Long id;
	
	@Required
	private String name;
	
	
	@ManyToMany(mappedBy = "tags")
	@JsonIgnore
    public Set<Recipe> recipes;
	
	
	private static final Find<Long,Tag> find =new Find<Long,Tag>(){};
	

	public static List<Tag> getByName(String tagName){
		
		 return find.where().eq("name",tagName ).findList();
		 
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
	public void addRecipe(Recipe recipe){
		this.recipes.add(recipe);
	}

	@Override
	public String toString() {
		return "Tag [name=" + name + "]";
	}
	
	
	
	
	
	
}
