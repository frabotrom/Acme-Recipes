package acme.features.chef.pimpam_ingredients;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.pimpam.Pimpam;
import acme.entities.thing.Thing;
import acme.entities.thing.ThingType;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractCreateService;
import acme.roles.Chef;

@Service
public class ChefPimpamIngCreateService implements AbstractCreateService<Chef, Pimpam> {

	// Internal state -------------------------------------------------------------

	@Autowired
	protected ChefPimpamIngRepository repository;

	// AbstractCreateService<Administrator, Announcement> interface ---------------

	@Override
	public boolean authorise(final Request<Pimpam> request) {
		
		assert request != null;
		
		
		boolean result;
		final Thing thing;
		
		int thingId;		
		thingId = request.getModel().getInteger("thingId");
		thing = this.repository.findThingByThingId(thingId);
		
		result = thing!= null  && thing.getPimpam() == null && thing.getThingType()==ThingType.INGREDIENT && !thing.isPublished();
		
		return result;
	}

	@Override
	public void bind(final Request<Pimpam> request, final Pimpam entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors, "code", "title", "description", "budget", "startDate", "endDate", "link");
		
		
		//final Date moment;
		//moment = new Date(System.currentTimeMillis() - 1);
		entity.setInstantiationMoment(new Date());
		
		
	}

	@Override
	public void unbind(final Request<Pimpam> request, final Pimpam entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "code", "title", "description", "budget", "startDate", "endDate", "link");
		int thingId;
		thingId = request.getModel().getInteger("thingId");
		model.setAttribute("thingId", thingId);
	}

	@Override
	public Pimpam instantiate(final Request<Pimpam> request) {
		assert request != null;

		Pimpam result;

		result = new Pimpam();
		
		return result;
	}

	@Override
	public void validate(final Request<Pimpam> request, final Pimpam entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		if (!errors.hasErrors("code")) {
			//Comprobando formato, en este caso, que el código contenga yy-mm-dd
			final LocalDate now = LocalDate.now();
			final String year = Integer.toString(now.getYear()).substring(2,4);
			String month = Integer.toString(now.getMonthValue());
			String day = Integer.toString(now.getDayOfMonth());
			
			if (month.length()==1) {
				month="0"+month;
			}
			if (day.length()==1) {
				day="0"+day;
			}
			final String newCode=year+"-"+month+"-"+day;
			errors.state(request, entity.getCode().contains(newCode), "code", "inventor.chimpum.form.error.code-format");

			//Comprobando código duplicado
			final Boolean codeExisting = this.repository.findPimpamByCode(newCode) == null;
			errors.state(request, codeExisting, "code", "inventor.chimpum.form.error.code-existing");

		}
		
		
		if (!errors.hasErrors("budget")) {
			errors.state(request, entity.getBudget().getAmount()>=0, "budget", "inventor.chimpum.form.error.budget");
		}
		
		if (!errors.hasErrors("endDate")) {	
			System.out.println(entity.period());
			errors.state(request, entity.period()>=7, "endDate", "inventor.chimpum.form.error.period");
			errors.state(request, entity.getEndDate().after(entity.getStartDate()), "endDate", "inventor.chimpum.form.error.finalDate");
		}
		
		if (!errors.hasErrors("startDate")) {
			
			Calendar calendar;
			Date deadline;

			calendar = Calendar.getInstance();
			calendar.setTime(entity.getStartDate());
			calendar.add(Calendar.MONTH, -1);
			deadline = calendar.getTime();

			
			errors.state(request, deadline.after(entity.getInstantiationMoment()), "startDate", "inventor.chimpum.form.error.creationMoment");
		}
		
	}

	@Override
	public void create(final Request<Pimpam> request, final Pimpam entity) {
		assert request != null;
		assert entity != null;
		
		Thing thing;
		int thingId;
		thingId = request.getModel().getInteger("thingId");
		
		// Para asegurar que es tipo Tool
		thing = this.repository.findThingByThingId(thingId);
		thing.setPimpam(entity);

		this.repository.save(entity);
	}



}
