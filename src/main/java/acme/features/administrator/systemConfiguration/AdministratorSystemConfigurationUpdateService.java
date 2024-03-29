package acme.features.administrator.systemConfiguration;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.systemConfiguration.SystemConfiguration;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.roles.Administrator;
import acme.framework.services.AbstractUpdateService;

@Service
public class AdministratorSystemConfigurationUpdateService implements AbstractUpdateService<Administrator, SystemConfiguration>{
	
	@Autowired
	protected AdministratorSystemConfigurationRepository repository;

	@Override
	public boolean authorise(final Request<SystemConfiguration> request) {
		assert request != null;
		return true;
	}

	@Override
	public SystemConfiguration findOne(final Request<SystemConfiguration> request) {
		
		assert request != null;
		
		SystemConfiguration res;
		res = this.repository.findSystemConfiguration();
		
		return res;
	}
	
	@Override
	public void bind(final Request<SystemConfiguration> request, final SystemConfiguration entity, final Errors errors) {
        assert request != null;
        assert entity != null;
        assert errors != null;

        request.bind(entity, errors, "systemCurrency", "acceptedCurrencies", "spamTermsEn", "spamTermsEs",
            "spamThreshold");

    }

	@Override
	public void unbind(final Request<SystemConfiguration> request, final SystemConfiguration entity, final Model model) {
        assert request != null;
        assert entity != null;
        assert model != null;

        request.unbind(entity, model, "systemCurrency", "acceptedCurrencies", "spamTermsEn", "spamTermsEs",
            "spamThreshold");
        model.setAttribute("confirmation", false);
    }
	
	@Override
	public void validate(final Request<SystemConfiguration> request, final SystemConfiguration entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		if(!errors.hasErrors("systemCurrency")) {
			errors.state(request, entity.getAcceptedCurrencies().contains(entity.getSystemCurrency()) , "systemCurrency", "administrator.systemconfiguration.form.error.systemCurrency");
		}
		
		if(!errors.hasErrors("acceptedCurrencies")) {
			final List<String> acceptedCurrencies = new ArrayList<>();
			final List<String> worldCurrenciesCodes = this.repository.findWorldCurrencies();
			
			for(final String s: entity.getAcceptedCurrencies().split(",")) {
				acceptedCurrencies.add(s);
			}
			
			for(final String ac: acceptedCurrencies) {
				if(!worldCurrenciesCodes.contains(ac)) {
					errors.state(request, worldCurrenciesCodes.contains(ac) , "acceptedCurrencies", "administrator.systemconfiguration.invalid-currency.error");
				}
			}
		}
	}
	
	@Override
	public void update(final Request<SystemConfiguration> request, final SystemConfiguration entity) {
		assert request != null;
		assert entity != null;

		this.repository.save(entity);

	}

}
