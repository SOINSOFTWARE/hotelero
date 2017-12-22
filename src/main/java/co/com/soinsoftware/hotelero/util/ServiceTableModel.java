package co.com.soinsoftware.hotelero.util;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import com.soinsoftware.hotelero.persistence.entity.Service;

/**
 * @author Carlos Rodriguez
 * @since 23/07/2016
 * @version 1.0
 */
public class ServiceTableModel extends AbstractTableModel {

	private static final long serialVersionUID = 8408209589620109955L;

	private static final String[] COLUMN_NAMES = { "Tipo de consumo", "Servicio", "Valor", "Eliminar" };

	private final List<Service> serviceList;

	private Object[][] data;

	public ServiceTableModel(final List<Service> serviceList) {
		super();
		this.serviceList = serviceList;
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
		return (col == 0) ? false : true;
	}

	@Override
	public void setValueAt(final Object value, final int row, final int col) {
		final Service service = this.serviceList.get(row);
		if (col == 1) {
			service.setNewName((String) value);
		} else if (col == 2) {
			service.setNewValue((Long) value);
		} else if (col == 3) {
			service.setDelete((Boolean) value);
		}
		data[row][col] = value;
		fireTableCellUpdated(row, col);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public Class getColumnClass(final int col) {
		return getValueAt(0, col).getClass();
	}

	public List<Service> getServiceList() {
		return this.serviceList;
	}

	private void buildData() {
		final int rowSize = this.getRowSizeToBuild();
		data = new Object[rowSize][4];
		if (this.serviceList != null) {
			int index = 0;
			for (final Service service : this.serviceList) {
				service.setNewValue(service.getValue());
				data[index][0] = service.getServiceType().getName();
				data[index][1] = service.getName();
				data[index][2] = service.getValue();
				data[index][3] = new Boolean(false);
				index++;
			}
		}
	}

	private int getRowSizeToBuild() {
		int rowSize = 0;
		if (this.serviceList != null) {
			rowSize = this.serviceList.size();
		}
		return rowSize;
	}
}