package co.com.soinsoftware.hotelero.util;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import com.soinsoftware.hotelero.persistence.entity.Room;

/**
 * @author Carlos Rodriguez
 * @since 23/07/2016
 * @version 1.0
 */
public class RoomTableModel extends AbstractTableModel {

	private static final long serialVersionUID = 8408209589620109955L;

	private static final String[] COLUMN_NAMES = { "Habitación", "Precio" };

	private final List<Room> roomList;

	private Object[][] data;

	public RoomTableModel(final List<Room> roomList) {
		super();
		this.roomList = roomList;
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
		data[row][col] = value;
		fireTableCellUpdated(row, col);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public Class getColumnClass(final int col) {
		return getValueAt(0, col).getClass();
	}

	public List<Room> getRoomList() {
		return this.roomList;
	}

	private void buildData() {
		final int rowSize = this.getRowSizeToBuild();
		data = new Object[rowSize][4];
		if (this.roomList != null) {
			int index = 0;
			for (final Room room : this.roomList) {
				data[index][0] = room.getName();
				index++;
			}
		}
	}

	private int getRowSizeToBuild() {
		int rowSize = 0;
		if (this.roomList != null) {
			rowSize = this.roomList.size();
		}
		return rowSize;
	}
}