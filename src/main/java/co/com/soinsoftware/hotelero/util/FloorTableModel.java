package co.com.soinsoftware.hotelero.util;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import com.soinsoftware.hotelero.persistence.entity.Floor;

/**
 * @author Carlos Rodriguez
 * @since 23/09/2017
 * @version 1.0.1
 */
public class FloorTableModel extends AbstractTableModel {

	private static final long serialVersionUID = 8408209589620109955L;

	private static final String[] COLUMN_NAMES = { "Código", "Nombre", "Eliminar" };

	private final List<Floor> floorList;

	private Object[][] data;

	public FloorTableModel(final List<Floor> floorList) {
		super();
		this.floorList = floorList;
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
		final Floor floor = this.floorList.get(row);
		if (col == 0) {
			floor.setNewCode((String) value);
		} else if (col == 1) {
			floor.setNewName((String) value);
		} else {
			floor.setDelete((Boolean) value);
		}
		data[row][col] = value;
		fireTableCellUpdated(row, col);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public Class getColumnClass(final int col) {
		return getValueAt(0, col).getClass();
	}

	public List<Floor> getFloorList() {
		return this.floorList;
	}

	private void buildData() {
		final int rowSize = this.getRowSizeToBuild();
		data = new Object[rowSize][3];
		if (this.floorList != null) {
			int index = 0;
			for (final Floor floor : this.floorList) {
				data[index][0] = floor.getCode();
				data[index][1] = floor.getName();
				data[index][2] = new Boolean(false);
				index++;
			}
		}
	}

	private int getRowSizeToBuild() {
		int rowSize = 0;
		if (this.floorList != null) {
			rowSize = this.floorList.size();
		}
		return rowSize;
	}
}