package co.com.soinsoftware.hotelero.util;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import co.com.soinsoftware.hotelero.entity.Company;

/**
 * @author Carlos Rodriguez
 * @since 23/07/2016
 * @version 1.0
 */
public class CompanyTableModel extends AbstractTableModel {

	private static final long serialVersionUID = 8408209589620109955L;

	private static final String[] COLUMN_NAMES = { "Empresa", "NIT", "Eliminar" };

	private final List<Company> companyList;

	private Object[][] data;

	public CompanyTableModel(final List<Company> companyList) {
		super();
		this.companyList = companyList;
		this.buildData();
	}

	@Override
	public int getColumnCount() {
		return COLUMN_NAMES.length;
	}

	@Override
	public int getRowCount() {
		return this.data.length;
	}

	@Override
	public Object getValueAt(final int row, final int col) {
		return this.data[row][col];
	}

	@Override
	public String getColumnName(final int col) {
		return COLUMN_NAMES[col];
	}

	@Override
	public boolean isCellEditable(final int row, final int col) {
		return true;
	}

	@Override
	public void setValueAt(final Object value, final int row, final int col) {
		final Company company = this.companyList.get(row);
		if (col == 0) {
			company.setNewName((String) value);
		} else if (col == 1) {
			company.setNewNit((String) value);
		} else {
			company.setDelete((Boolean) value);
		}
		data[row][col] = value;
		fireTableCellUpdated(row, col);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public Class getColumnClass(final int col) {
		return getValueAt(0, col).getClass();
	}

	public List<Company> getCompanyList() {
		return this.companyList;
	}

	private void buildData() {
		final int rowSize = this.getRowSizeToBuild();
		data = new Object[rowSize][3];
		if (this.companyList != null) {
			int index = 0;
			for (final Company company : this.companyList) {
				data[index][0] = company.getName();
				data[index][1] = company.getNit();
				data[index][2] = new Boolean(false);
				index++;
			}
		}
	}

	private int getRowSizeToBuild() {
		int rowSize = 0;
		if (this.companyList != null) {
			rowSize = this.companyList.size();
		}
		return rowSize;
	}
}