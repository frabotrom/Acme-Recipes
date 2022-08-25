package acme.features.epicure.finedish;

import java.util.Collection;
import java.util.Date;

import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.finedish.FineDish;
import acme.entities.finedish.Status;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractCreateService;
import acme.roles.Chef;
import acme.roles.Epicure;
import spamDetector.SpamDetector;

@Service
public class FineDishEpicureCreateService implements AbstractCreateService<Epicure,FineDish>{
	
	@Autowired
	protected FineDishEpicureRepository repository;

	
	@Override
	public boolean authorise(final Request<FineDish> request) {
		
		assert request != null;
		 
		return true;
	}
	
	@Override
	public FineDish instantiate(final Request<FineDish> request) {
	
		assert request != null;
		
		FineDish result;
		final Epicure epicure = this.repository.findEpicureById(request.getPrincipal().getActiveRoleId());
		
		final Date creationMoment = new Date(System.currentTimeMillis()-1);
		
		result = new FineDish();
		result.setCreationMoment(creationMoment);
		result.setRequest("");
		result.setMoreInfo("");
		result.setEpicure(epicure);
		result.setPublished(false);
		return result;
	}
	
	@Override
	public void bind(final Request<FineDish> request, final FineDish entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		final Date moment = new Date(System.currentTimeMillis() -1);
		
		entity.setCreationMoment(moment);
		entity.setStatus(Status.PROPOSED);
		entity.setPublished(false);
		entity.setChef(this.repository.findChefById(Integer.valueOf(request.getModel().getAttribute("chefId").toString())));

		request.bind(entity, errors, "code", "request", "budget", "startDate", "endDate", "moreInfo");

	}
	
	
	@Override
	public void validate(final Request<FineDish> request, final FineDish entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		 if(!errors.hasErrors("code")) {
	        	
	        	final FineDish fineDishByCode =  this.repository.findFineDishByCode(entity.getCode());
	        	if(fineDishByCode != null) {
	        		errors.state(request, fineDishByCode.getId() == entity.getId(), "code", "epicure.fine-dish.form.error.code-exists");
	        	}
	        	
	        }
        
		if (!errors.hasErrors("budget")) {
			
			final String[] acceptedCurrencies = this.repository.findSystemConfiguration().getAcceptedCurrencies().split(",");
            boolean accepted = false;
            
            for(int i=0 ; i < acceptedCurrencies.length ; i++) {
            	
                if(entity.getBudget().getCurrency().equals(acceptedCurrencies[i].trim())) {
                    accepted = true;
                }
            }
			
			errors.state(request, accepted, "budget", "epicure.fine-dish.form.error.budget-currency");
			errors.state(request, entity.getBudget().getAmount() > 0, "budget", "epicure.fine-dish.form.error.budget-amount");
		}


		 if(!errors.hasErrors("startDate")) {
				
				final Date startDateMin = DateUtils.addMonths(entity.getCreationMoment(), 1);
				
				errors.state(request, entity.getStartDate().after(startDateMin), "startDate", "epicure.fine-dish.form.error.start-date");			
			}
			
			if(!errors.hasErrors("endDate")&& entity.getStartDate() !=null) {
				
				final Date periodEndDate = DateUtils.addMonths(entity.getStartDate(), 1);
				final Date moment = entity.getEndDate();
				
				errors.state(request, moment.after(periodEndDate) , "endDate", "epicure.fine-dish.form.error.end-date");
				
			}
		
			if(!errors.hasErrors("request")) {
				final boolean isReportSpam = SpamDetector.isSpam(entity.getRequest(), this.repository.systemConfiguration());
				errors.state(request, !isReportSpam, "request", "epicure.fine-dish.form.error.request");
			}
		
	}	
	
	@Override
	public void unbind(final Request<FineDish> request, final FineDish entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model,  "code", "request", "budget", "startDate", "endDate", "moreInfo", "chef.id");	
		final Collection<Chef> chefs = this.repository.findChefs();
		model.setAttribute("chefs", chefs);

	}
	
	
	@Override
	public void create(final Request<FineDish> request, final FineDish entity) {
		assert request != null;
		assert entity != null;

		this.repository.save(entity);
	}

	

}
