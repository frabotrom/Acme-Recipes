package acme.features.chef.amount;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import acme.entities.amount.Amount;
import acme.framework.controllers.AbstractController;
import acme.roles.Chef;

@Controller
public class ChefAmountController extends AbstractController<Chef, Amount> {

	@Autowired
	protected ChefAmountListService listService;
	
	@Autowired
	protected ChefAmountShowService showService;
	
	@Autowired
	protected ChefAmountCreateService createService;
	
	@Autowired
	protected ChefAmountUpdateService updateService;
	
	@Autowired
	protected ChefAmountDeleteService deleetService;

	@PostConstruct
	protected void initialise() {
		super.addCommand("list", this.listService);
		super.addCommand("show", this.showService);
		super.addCommand("create", this.createService);
		super.addCommand("update", this.updateService);
		super.addCommand("delete", this.deleetService);


		
	}
}
