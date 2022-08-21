package acme.features.any.peep;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import acme.entities.peep.Peep;
import acme.framework.controllers.AbstractController;
import acme.framework.roles.Any;

@Controller
public class AnyPeepController extends AbstractController<Any, Peep>{

	// Internal state ---------------------------------------------------------

	@Autowired
	protected AnyPeepListService listService;
	
	@Autowired
	protected AnyPeepCreateService createService;

	// Constructors -----------------------------------------------------------

	@PostConstruct
	protected void initialise() {
		super.addCommand("list", this.listService);
		super.addCommand("create", this.createService);
	}
}
