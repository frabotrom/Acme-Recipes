package acme.features.administrator.bulletin;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.systemConfiguration.SystemConfiguration;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AdministratorBulletinRepository extends AbstractRepository {
	
	@Query("SELECT c FROM SystemConfiguration c")
	SystemConfiguration getSystemConfiguration();

}
