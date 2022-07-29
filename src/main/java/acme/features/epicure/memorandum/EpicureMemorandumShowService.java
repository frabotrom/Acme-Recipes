package acme.features.epicure.memorandum;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.finedish.FineDish;
import acme.entities.memorandum.Memorandum;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractShowService;
import acme.roles.Epicure;

@Service
public class EpicureMemorandumShowService implements AbstractShowService<Epicure, Memorandum> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected EpicureMemorandumRepository repository;

	// AbstractShowService<Employer, Duty> interface ---------------------------


	@Override
	public boolean authorise(final Request<Memorandum> request) {
		assert request != null;

		boolean result;
		int memorandumId;
		FineDish fineDish;

		memorandumId = request.getModel().getInteger("id");
		fineDish = this.repository.findOneFineDishByMemorandumId(memorandumId);
		result = (fineDish != null && (!fineDish.isPublished() || request.isPrincipal(fineDish.getEpicure())));

		return result;
	}

	@Override
	public Memorandum findOne(final Request<Memorandum> request) {
		assert request != null;

		Memorandum result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findOneMemorandumById(id);

		return result;
	}

	@Override
	public void unbind(final Request<Memorandum> request, final Memorandum entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "serialNumber", "instantiationMoment","report","info");
		model.setAttribute("masterId", entity.getFineDish().getId());
	}

}
