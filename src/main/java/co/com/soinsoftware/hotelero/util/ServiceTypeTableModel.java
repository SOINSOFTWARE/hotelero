package co.com.soinsoftware.hotelero.util;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import co.com.soinsoftware.hotelero.entity.Servicetype;

/**
 * @author Carlos Rodriguez
 * @since 23/07/2016
 * @version 1.0
 */
public class ServiceTypeTableModel extends AbstractTableModel {

	private static final long serialVersionUID = 8408209589620109955L;

	private static final String[] COLUMN_NAMES = { "Tipo de consumo",
			"Eliminar" };

	private final List<Servicetype> serviceTypeList;

	private Object[][] data;

	public ServiceTypeTableModel(final List<Servicetype> serviceTypeList) {
		super();
		this.serviceTypeList = serviceTypeList;
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
		final Servicetype serviceType = this.serviceTypeList.get(row);
		if (col == 0) {
			serviceType.setNewName((String) value);
		} else {
			serviceType.setDelete((Boolean) value);
		}
		data[row][col] = value;
		fireTableCellUpdated(row, col);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public Class getColumnClass(final int col) {
		return getValueAt(0, col).getClass();
	}

	public List<Servicetype> getServiceTypeList() {
		return this.serviceTypeList;
	}

	private void buildData() {
		final int rowSize = this.getRowSizeToBuild();
		data = new Object[rowSize][2];
		if (this.serviceTypeList != null) {
			int index = 0;
			for (final Servicetype serviceType : this.serviceTypeList) {
				data[index][0] = serviceType.getName();
				data[index][1] = new Boolean(false);
				index++;
			}
		}
	}

	private int getRowSizeToBuild() {
		int rowSize = 0;
		if (this.serviceTypeList != null) {
			rowSize = this.serviceTypeList.size();
		}
		return rowSize;
	}
}