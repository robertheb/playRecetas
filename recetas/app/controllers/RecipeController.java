package controllers;

import play.mvc.Controller;
import play.mvc.Result;

public class RecipeController extends Controller{

	public Result create() {
		return ok("created");
	}
	
	public Result retrieve(Long id) {
		return ok("recipe");
	}
	
	public Result remove(long id) {
		return ok("removed");
	}
	
	public Result listRecipes() {	
		return ok("ok");
	}
	
	public Result update(String id) {

    	return ok("No existe ese usuario");
    	
    }
}
