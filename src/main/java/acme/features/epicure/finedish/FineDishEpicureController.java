package acme.features.epicure.finedish;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import acme.entities.finedish.FineDish;
import acme.framework.controllers.AbstractController;
import acme.roles.Epicure;

@Controller
public class FineDishEpicureController extends AbstractController<Epicure,FineDish>{
	
	@Autowired
	protected FineDishEpicureListService listService;
	
	@Autowired
	protected FineDishEpicureShowService showService;
	
	@Autowired
	protected FineDishEpicureCreateService createService;
	
	@Autowired
	protected FineDishEpicureUpdateService updateService;
	
	@Autowired
	protected FineDishEpicureDeleteService deleteService;
	
	@Autowired
	protected FineDishEpicurePublishedService publishedService;
	
	
	@PostConstruct
	protected void initialise() {
		super.addCommand("list", this.listService);
		super.addCommand("show", this.showService);
		super.addCommand("create", this.createService);
		super.addCommand("update", this.updateService);
		super.addCommand("delete", this.deleteService);
		super.addCommand("publish", "update", this.publishedService);
	}

}
