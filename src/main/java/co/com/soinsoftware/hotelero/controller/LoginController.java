package co.com.soinsoftware.hotelero.controller;

import co.com.soinsoftware.hotelero.bll.UserBLL;
import co.com.soinsoftware.hotelero.entity.User;

/**
 * @author Carlos Rodriguez
 * @since 31/05/2016
 * @version 1.0
 */
public class LoginController {
	
	private final UserBLL userBLL;
	
	public LoginController() {
		super();
		this.userBLL = UserBLL.getInstance();
	}

	
	public User selectUser(final String login, final char[] password) {
		String strPassword = String.valueOf(password);
		return this.userBLL.select(login, strPassword);
	}
}