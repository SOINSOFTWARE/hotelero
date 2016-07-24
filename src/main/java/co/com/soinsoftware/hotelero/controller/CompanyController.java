package co.com.soinsoftware.hotelero.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Set;

import co.com.soinsoftware.hotelero.bll.CompanyBLL;
import co.com.soinsoftware.hotelero.entity.Company;

/**
 * @author Carlos Rodriguez
 * @since 23/07/2016
 * @version 1.0
 */
public class CompanyController {

	private final CompanyBLL companyBLL;

	public CompanyController() {
		super();
		this.companyBLL = CompanyBLL.getInstance();
	}

	public List<Company> selectCompanies() {
		List<Company> companyList = new ArrayList<>();
		final Set<Company> companySet = this.companyBLL.select();
		if (companySet != null) {
			companyList = new ArrayList<>(companySet);
			Collections.sort(companyList);
		}
		return companyList;
	}

	public void saveCompany(final String name, final String nit) {
		final Date currentDate = new Date();
		final Company serviceType = new Company(name, nit, currentDate,
				currentDate, true);
		this.saveCompany(serviceType);
	}

	public void saveCompany(final Company company) {
		this.companyBLL.save(company);
	}
}