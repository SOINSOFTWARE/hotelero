package co.com.soinsoftware.hotelero.bll;

import java.util.Set;

import co.com.soinsoftware.hotelero.dao.CompanyDAO;
import co.com.soinsoftware.hotelero.entity.Company;

/**
 * @author Carlos Rodriguez
 * @since 23/07/2016
 * @version 1.0
 */
public class CompanyBLL {

	private static CompanyBLL instance;

	private final CompanyDAO dao;

	public static CompanyBLL getInstance() {
		if (instance == null) {
			instance = new CompanyBLL();
		}
		return instance;
	}

	public Set<Company> select() {
		return this.dao.select();
	}

	public Company select(final String name) {
		return this.dao.select(name);
	}

	public void save(final Company company) {
		this.dao.save(company);
	}

	private CompanyBLL() {
		super();
		this.dao = new CompanyDAO();
	}
}