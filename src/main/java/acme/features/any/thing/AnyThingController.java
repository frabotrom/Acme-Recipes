package acme.features.any.thing;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import acme.entities.thing.Thing;
import acme.features.any.recipe.AnyRecipeThingsService;
import acme.framework.controllers.AbstractController;
import acme.framework.roles.Any;

@Controller
public class AnyThingController extends AbstractController<Any, Thing>{
	
	
	//Internal state --------------------------------------------------
	@Autowired	
	protected AnyIngredientListService anyIngredientListService;
	
	@Autowired
	protected AnyKitchenUtensilListService anyKitchenUtensilListService;
	
	@Autowired
	protected AnyThingShowService thingShowService;
	
	@Autowired
	protected AnyRecipeThingsService anyRecipeThingsService;
	

	
	//Constructors ----------------------------------------------------

	@PostConstruct
	protected void initialise() {
		super.addCommand("show", this.thingShowService);
		super.addCommand("list-published-kitchen-utensils","list", this.anyKitchenUtensilListService);
		super.addCommand("list-published-ingredients","list", this.anyIngredientListService);
		super.addCommand("list-recipe-things","list", this.anyRecipeThingsService);
	}
}

