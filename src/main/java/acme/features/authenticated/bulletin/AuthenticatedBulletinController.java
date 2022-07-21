package acme.features.authenticated.bulletin;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import acme.entities.bulletin.Bulletin;
import acme.framework.controllers.AbstractController;
import acme.framework.roles.Authenticated;

@Controller
public class AuthenticatedBulletinController extends AbstractController<Authenticated, Bulletin>{
	
	//Internal state ---------------------------------------------------------
	
		@Autowired
		protected AuthenticatedBulletinShowService showService;
	
		@Autowired
		protected AuthenticatedBulletinListRecentService listRecentService;
	
	//Constructors ---------------------------------------------------------
	
		@PostConstruct
		protected void initialise() {
			super.addCommand("show", this.showService);
			super.addCommand("list", this.listRecentService);
			
		}

}
