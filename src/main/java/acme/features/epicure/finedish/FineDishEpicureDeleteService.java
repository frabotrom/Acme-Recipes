package acme.features.epicure.finedish;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.finedish.FineDish;
import acme.entities.memorandum.Memorandum;
import acme.features.chef.memorandum.ChefMemorandumRepository;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractDeleteService;
import acme.roles.Epicure;

@Service
public class FineDishEpicureDeleteService implements AbstractDeleteService<Epicure,FineDish>{
	
	@Autowired
	protected FineDishEpicureRepository finedishRepository;
	
	@Autowired
	protected ChefMemorandumRepository memorandumRepository;
	
	@Override
	public boolean authorise(final Request<FineDish> request) {
		assert request != null;

		boolean result;
		final int fineDishId = request.getModel().getInteger("id");
		final FineDish fineDish = this.finedishRepository.findOneFineDishById(fineDishId);

		result = request.isPrincipal(fineDish.getEpicure()) && !fineDish.isPublished();

		return result;
	}


	@Override
	public void bind(final Request<FineDish> request, final FineDish entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors, "code", "request", "budget", "startDate", "endDate", "moreInfo");
	}

	@Override
	public void unbind(final Request<FineDish> request, final FineDish entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "code", "request", "budget", "startDate", "endDate", "moreInfo","published");
	}
	

	@Override
	public FineDish findOne(final Request<FineDish> request) {
		assert request != null;

		FineDish result;
		final int fineDishId = request.getModel().getInteger("id");

		result = this.finedishRepository.findOneFineDishById(fineDishId);

		return result;
	}

	@Override
	public void validate(final Request<FineDish> request, final FineDish entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
	}

	@Override
	public void delete(final Request<FineDish> request, final FineDish entity) {
		assert request != null;
		assert entity != null;
		
		Collection<Memorandum> fineDishMemorandum;
		fineDishMemorandum = this.finedishRepository.findAllmemorandumsOfFineDish(entity.getId());
		for (final Memorandum m : fineDishMemorandum) {
			this.memorandumRepository.delete(m);
		}

		this.finedishRepository.delete(entity);
	}

}
