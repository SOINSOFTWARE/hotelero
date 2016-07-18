package co.com.soinsoftware.hotelero.bll;

import co.com.soinsoftware.hotelero.dao.UserDAO;
import co.com.soinsoftware.hotelero.entity.User;

/**
 * @author Carlos Rodriguez
 * @since 18/07/2016
 * @version 1.0
 */
public class UserBLL {
	
	private static UserBLL instance; 
	
	private final UserDAO dao;
	
	public static UserBLL getInstance() {
		if (instance == null) {
			instance = new UserBLL();
		}
		return instance;
	}
	
	public User select(final String login, final String password) {
		return this.dao.select(login, password);
	}
	
	public User select(final long identification) {
		return this.dao.select(identification);
	}
	
	public void save(final User user) {
		this.dao.save(user);
	}
	
	private UserBLL() {
		super();
		this.dao = new UserDAO();
	}
}