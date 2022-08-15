package acme.features.epicure.finedish;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.finedish.FineDish;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractUpdateService;
import acme.roles.Epicure;

@Service
public class FineDishEpicureUpdateService implements AbstractUpdateService<Epicure,FineDish>{
	

	@Autowired
	protected FineDishEpicureRepository repository;


	@Override
	public boolean authorise(final Request<FineDish> request) {
		assert request != null;

		boolean result;
		int fineDishId;
		FineDish fineDish;
		
		fineDishId = request.getModel().getInteger("id");
		fineDish = this.repository.findOneFineDishById(fineDishId);

		result = request.isPrincipal(fineDish.getEpicure()) && !fineDish.isPublished();
		

		return result;
	}

	@Override
	public FineDish findOne(final Request<FineDish> request) {
		assert request != null;

		FineDish result;
		int fineDishId;
		fineDishId = request.getModel().getInteger("id");

		result = this.repository.findOneFineDishById(fineDishId);

		
		return result;
	}

	@Override
	public void bind(final Request<FineDish> request, final FineDish entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		request.bind(entity, errors, "code","status","creationMoment", "request", "budget", "startDate", "endDate", "moreInfo");
	}

	@Override
	public void validate(final Request<FineDish> request, final FineDish entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		
		
		/*if(!errors.hasErrors("code")) {
			Patronage existingPatronage;
			existingPatronage = this.repository.findPatronageByCode(entity.getCode());
			
				errors.state(request, existingPatronage == null || existingPatronage.getId() == entity.getId(), "code", "patron.patronage.form.error.code-exists");
			
		}
        
		if (!errors.hasErrors("budget")) {
			
			final String[] acceptedCurrencies = this.repository.findSystemConfiguration().getAcceptedCurrencies().split(",");
            boolean accepted = false;
            
            for(int i=0 ; i < acceptedCurrencies.length ; i++) {
            	
                if(entity.getBudget().getCurrency().equals(acceptedCurrencies[i].trim())) {
                    accepted = true;
                }
            }
			
			errors.state(request, accepted, "budget", "patron.patronage.form.error.budget-currency");
			errors.state(request, entity.getBudget().getAmount() > 0, "budget", "patron.patronage.form.error.budget-amount");
		}


		if(!errors.hasErrors("startDate")) {
			final Date startDateMin = DateUtils.addMonths(entity.getCreationMoment(), 1);
	
			errors.state(request, entity.getStartDate().after(startDateMin), "startDate", "patron.patronage.form.error.start-date");			
		}
					
		
		
		if(!errors.hasErrors("endDate") && entity.getStartDate() !=null) {
				final Date periodEndDate = DateUtils.addMonths(entity.getStartDate(), 1);
				final Date moment = entity.getEndDate();
				errors.state(request, moment.after(periodEndDate) , "endDate", "patron.patronage.form.error.end-date");
						
			
		}*/
		

	}

	@Override
	public void unbind(final Request<FineDish> request, final FineDish entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		

		request.unbind(entity, model,"code","status","creationMoment", "request", "budget", "startDate", "endDate", "moreInfo","published");

		model.setAttribute("chefs", this.repository.findChefs());
		model.setAttribute("chefId", entity.getChef().getId());
		model.setAttribute("epicureId", entity.getEpicure().getId());
		model.setAttribute("chefOrganisation", entity.getChef().getOrganisation());
		model.setAttribute("chefAssertion", entity.getChef().getAssertion());
		model.setAttribute("chefFullName", entity.getChef().getIdentity().getFullName());
		model.setAttribute("chefEmail", entity.getChef().getIdentity().getEmail());
		model.setAttribute("chefInfo", entity.getChef().getInfo());
	}

	@Override
	public void update(final Request<FineDish> request, final FineDish entity) {
		assert request != null;
		assert entity != null;

		
		this.repository.save(entity);

	}


}
