package acme.features.authenticated.epicure;

import org.springframework.data.jpa.repository.Query;

import acme.entities.systemConfiguration.SystemConfiguration;
import acme.framework.entities.UserAccount;
import acme.framework.repositories.AbstractRepository;
import acme.roles.Epicure;

public interface AuthenticatedEpicureRepository extends AbstractRepository{
	
	@Query("select ua from UserAccount ua where ua.id = :id")
	UserAccount findOneUserAccountById(int id);

	@Query("select e from Epicure e where e.userAccount.id = :id")
	Epicure findOneEpicureByUserAccountId(int id);
	
	@Query("select systemConfiguration from SystemConfiguration systemConfiguration")
	SystemConfiguration systemConfiguration();

}
