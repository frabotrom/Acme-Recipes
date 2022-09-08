package acme.features.chef.pimpam_ingredients;

import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.pimpam.Pimpam;
import acme.entities.thing.Thing;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractUpdateService;
import acme.roles.Chef;

@Service
public class ChefPimpamIngUpdateService implements AbstractUpdateService<Chef,Pimpam>{
	// Internal state ---------------------------------------------------------

	@Autowired
	protected ChefPimpamIngRepository repository;

	// AbstractUpdateService<Administrator, SystemConfiguration> interface ----------------


	//Si es epicure en vez de chef hay que cambiar esto
	@Override
	public boolean authorise(final Request<Pimpam> request) {
		assert request != null;
		final int id = request.getModel().getInteger("id");
		final Thing thing = this.repository.findThingByPimpamId(id);
		final int chefId = request.getPrincipal().getActiveRoleId();
		
		return thing.getChef().getId() == chefId && !thing.isPublished();
	}
	
	@Override
	public void validate(final Request<Pimpam> request, final Pimpam entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		if (!errors.hasErrors("budget")) {
			errors.state(request, entity.getBudget().getAmount()>=0, "budget", "chef.pimpam.form.error.budget");
		}
		
		if (!errors.hasErrors("startDate")) {
			errors.state(request, entity.getStartDate().after(new Date()), "startDate", "chef.pimpam.form.error.startDate");
		}
		
		if (!errors.hasErrors("endDate")) {			
			errors.state(request, entity.period()>=7, "endDate", "chef.pimpam.form.error.period");
			errors.state(request, entity.getEndDate().after(entity.getStartDate()), "endDate", "chef.pimpam.form.error.finalDate");
		}
		
		if (!errors.hasErrors("initialDate")) {
			
			Calendar calendar;
			Date deadline;

			calendar = Calendar.getInstance();
			calendar.setTime(entity.getStartDate());
			calendar.add(Calendar.MONTH, -1);
			deadline = calendar.getTime();
			
			errors.state(request, deadline.after(entity.getInstantiationMoment()), "startDate", "chef.pimpam.form.error.instantiationMoment");
		}
		
	}
	
	@Override
	public void bind(final Request<Pimpam> request, final Pimpam entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		request.bind(entity, errors, "title", "description", "budget", "startDate", "endDate", "link");
		
		//Aqui no se modifica el creationMoment ni el code
	}


	@Override
	public void unbind(final Request<Pimpam> request, final Pimpam entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "code", "title", "description", "budget", "startDate", "endDate", "link", "instantiationMoment");
		model.setAttribute("period", entity.period());
		
		final Thing thing;
		int pimpamId;

		pimpamId = entity.getId();
		thing = this.repository.findThingByPimpamId(pimpamId);
		
		model.setAttribute("artefactName", thing.getName());
		model.setAttribute("artefactCode", thing.getCode());
		model.setAttribute("artefactDescription", thing.getDescription());
		model.setAttribute("artefactRetailPrice", thing.getRetailPrice());
		model.setAttribute("artefactLink", thing.getInfo());
		model.setAttribute("artefactType", thing.getThingType());
		model.setAttribute("artefactVisible", thing.isPublished());
	}
	
	@Override
	public Pimpam findOne(final Request<Pimpam> request) {
		assert request != null;

		final Pimpam pimpam;
		int pimpamId;

		pimpamId = request.getModel().getInteger("id");
		pimpam = this.repository.findPimpamById(pimpamId);

		return pimpam;
	}
	
	@Override
	public void update(final Request<Pimpam> request, final Pimpam entity) {
		assert request != null;
		assert entity != null;

		this.repository.save(entity);
	}
	
	


}
