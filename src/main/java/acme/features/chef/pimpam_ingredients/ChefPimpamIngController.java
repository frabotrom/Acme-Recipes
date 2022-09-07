package acme.features.chef.pimpam_ingredients;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import acme.entities.pimpam.Pimpam;
import acme.framework.controllers.AbstractController;
import acme.roles.Chef;

@Controller
public class ChefPimpamIngController extends AbstractController<Chef,Pimpam> {

	// Internal state ---------------------------------------------------------

		@Autowired
		protected ChefPimpamIngListService	listService;

		@Autowired
		protected ChefPimpamIngShowService	showService;
		
		@Autowired
		protected ChefPimpamIngUpdateService updateService;
		
		@Autowired
		protected ChefPimpamIngDeleteService deleteService;
		
		@Autowired
		protected ChefPimpamIngCreateService createService;

		// Constructors -----------------------------------------------------------


		@PostConstruct
		protected void initialise() {
			super.addCommand("list", this.listService);
			super.addCommand("show", this.showService);
			super.addCommand("update", this.updateService);
			super.addCommand("delete", this.deleteService);
			super.addCommand("create", this.createService);


		}
}
