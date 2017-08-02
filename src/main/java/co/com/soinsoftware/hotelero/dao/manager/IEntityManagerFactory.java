package co.com.soinsoftware.hotelero.dao.manager;

import javax.persistence.EntityManager;

/**
 * Interface for creating entity manager factory that will help to create DAO
 * objects.
 * 
 * @author Carlos Rodriguez
 * @since 1.0.1
 *
 */
public interface IEntityManagerFactory {

	/**
	 * Called when entity manager factory will help to create DAO objects.
	 * 
	 * @return
	 */
	EntityManager createEntityManager();
}
