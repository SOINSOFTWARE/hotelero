package co.com.soinsoftware.hotelero.controller;

import java.util.Date;

import co.com.soinsoftware.hotelero.bll.UserBLL;
import co.com.soinsoftware.hotelero.entity.Company;
import co.com.soinsoftware.hotelero.entity.User;

/**
 * @author Carlos Rodriguez
 * @since 18/07/2016
 * @version 1.0
 */
public class UserController {

	private final UserBLL userBLL;

	public UserController() {
		super();
		this.userBLL = UserBLL.getInstance();
	}

	public boolean isExistingUser(final long identification) {
		return (this.selectUser(identification) != null);
	}

	public User selectUser(final long identification) {
		return this.userBLL.select(identification);
	}

	public User saveUser(final Company company, final long identification,
			final String name, final long phone, final String career) {
		User user = this.selectUser(identification);
		final Date currentDate = new Date();
		if (user == null) {
			user = new User(company, identification, name, null, null, phone,
					career, currentDate, currentDate, true, null);
		} else {
			user.setUpdated(currentDate);
		}
		this.saveUser(user);
		return user;
	}

	public void saveUser(final User user) {
		this.userBLL.save(user);
	}
}