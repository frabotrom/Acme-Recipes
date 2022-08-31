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
	
	@Autowired
	protected ChefThingCreateService chefThingCreateService;
	
	@Autowired
	protected ChefThingUpdateService chefThingUpdateService;
	
	@Autowired
	protected ChefThingDeleteService chefThingDeleteService;
	
	@Autowired
	protected ChefThingPublishService chefThingPublishService;
		

	//Constructors ----------------------------------------------------

	@PostConstruct
	protected void initialise() {
		super.addCommand("show", this.chefThingShowService);
		super.addCommand("list-own-kitchen-utensils","list", this.chefKitchenUtensilListService);
		super.addCommand("list-own-ingredients","list", this.chefIngredientListService);
		super.addCommand("create", this.chefThingCreateService);
		super.addCommand("update", this.chefThingUpdateService);
		super.addCommand("delete", this.chefThingDeleteService);
		super.addCommand("publish", "update", this.chefThingPublishService);
	}
}
