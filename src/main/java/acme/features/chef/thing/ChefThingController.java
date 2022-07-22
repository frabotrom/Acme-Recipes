package acme.features.chef.thing;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import acme.entities.thing.Thing;
import acme.framework.controllers.AbstractController;
import acme.roles.Chef;

@Controller
public class ChefThingController extends AbstractController<Chef, Thing>{
	
	//Internal state --------------------------------------------------
	
	@Autowired	
	protected ChefIngredientListService chefIngredientListService;
		
	@Autowired
	protected ChefKitchenUtensilListService chefKitchenUtensilListService;
		
	@Autowired
	protected ChefThingShowService chefThingShowService;
		

	//Constructors ----------------------------------------------------

	@PostConstruct
	protected void initialise() {
		super.addCommand("show", this.chefThingShowService);
		super.addCommand("list-own-kitchen-utensils","list", this.chefKitchenUtensilListService);
		super.addCommand("list-own-ingredients","list", this.chefIngredientListService);
	}
}
