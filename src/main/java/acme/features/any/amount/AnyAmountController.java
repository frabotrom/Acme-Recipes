package acme.features.any.amount;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import acme.entities.amount.Amount;
import acme.framework.controllers.AbstractController;
import acme.framework.roles.Any;

@Controller
public class AnyAmountController extends AbstractController<Any, Amount> {

	@Autowired
	protected AnyAmountListService listService;
	
	@Autowired
	protected AnyAmountShowService showService;

	@PostConstruct
	protected void initialise() {
		super.addCommand("list", this.listService);
		super.addCommand("show", this.showService);

		
	}
}
