package acme.features.chef.finedish;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import acme.entities.finedish.FineDish;
import acme.framework.controllers.AbstractController;
import acme.roles.Chef;

@Controller
public class FineDishChefController extends AbstractController<Chef,FineDish>{
	
	@Autowired
	protected FineDishChefListService listService;
	
	@Autowired
	protected FineDishChefShowService showService;
	@Autowired
	protected FineDishChefStatusUpdateService updateService;
	
	
	@PostConstruct
	protected void initialise() {
		super.addCommand("list", this.listService);
		super.addCommand("show", this.showService);
		super.addCommand("update-status","update", this.updateService);
		
	}

}
