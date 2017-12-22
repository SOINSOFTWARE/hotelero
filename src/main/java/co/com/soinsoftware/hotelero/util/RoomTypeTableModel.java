package co.com.soinsoftware.hotelero.util;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import com.soinsoftware.hotelero.persistence.entity.RoomType;

/**
 * @author Carlos Rodriguez
 * @since 02/11/2017
 * @version 1.0.1
 */
public class RoomTypeTableModel extends AbstractTableModel {

	private static final long serialVersionUID = 8408209589620109955L;

	private static final String[] COLUMN_NAMES = { "Código", "Nombre", "Eliminar" };

	private final List<RoomType> roomTypeList;

	private Object[][] data;

	public RoomTypeTableModel(final List<RoomType> roomTypeList) {
		super();
		this.roomTypeList = roomTypeList;
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
		final RoomType roomType = this.roomTypeList.get(row);
		if (col == 0) {
			roomType.setNewCode((String) value);
		} else if (col == 1) {
			roomType.setNewName((String) value);
		} else {
			roomType.setDelete((Boolean) value);
		}
		data[row][col] = value;
		fireTableCellUpdated(row, col);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public Class getColumnClass(final int col) {
		return getValueAt(0, col).getClass();
	}

	public List<RoomType> getRoomTypeList() {
		return this.roomTypeList;
	}

	private void buildData() {
		final int rowSize = this.getRowSizeToBuild();
		data = new Object[rowSize][3];
		if (this.roomTypeList != null) {
			int index = 0;
			for (final RoomType roomType : this.roomTypeList) {
				data[index][0] = roomType.getCode();
				data[index][1] = roomType.getName();
				data[index][2] = new Boolean(false);
				index++;
			}
		}
	}

	private int getRowSizeToBuild() {
		int rowSize = 0;
		if (this.roomTypeList != null) {
			rowSize = this.roomTypeList.size();
		}
		return rowSize;
	}
}