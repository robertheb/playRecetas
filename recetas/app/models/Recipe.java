package models;

import javax.persistence.Id;

import com.avaje.ebean.Model;

public class Recipe extends Model{
	
	@Id
	private Long id;
	
	private String title;
	
	private String preparationTime;
	
	private String description;

}
