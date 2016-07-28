package co.com.soinsoftware.hotelero.util;

import java.text.SimpleDateFormat;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import co.com.soinsoftware.hotelero.entity.Invoice;

/**
 * @author Carlos Rodriguez
 * @since 23/07/2016
 * @version 1.0
 */
public class InvoiceBookedTableModel extends AbstractTableModel {

	private static final long serialVersionUID = 8408209589620109955L;

	private static final String[] COLUMN_NAMES = { "Cedula", "Nombre",
			"Habitación", "Llegada", "Salida", "Eliminar" };

	private final List<Invoice> invoiceList;

	private Object[][] data;

	public InvoiceBookedTableModel(final List<Invoice> invoiceList) {
		super();
		this.invoiceList = invoiceList;
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
		return (col == 5) ? true : false;
	}

	@Override
	public void setValueAt(final Object value, final int row, final int col) {
		final Invoice invoice = this.invoiceList.get(row);
		invoice.setDelete((Boolean) value);
		data[row][col] = value;
		fireTableCellUpdated(row, col);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public Class getColumnClass(final int col) {
		return getValueAt(0, col).getClass();
	}

	public List<Invoice> getInvoiceList() {
		return this.invoiceList;
	}

	private void buildData() {
		final SimpleDateFormat format = new SimpleDateFormat("dd-MMM-yyyy");
		final int rowSize = this.getRowSizeToBuild();
		data = new Object[rowSize][6];
		if (this.invoiceList != null) {
			int index = 0;
			for (final Invoice invoice : this.invoiceList) {
				data[index][0] = String.valueOf(invoice.getUser()
						.getIdentification());
				data[index][1] = invoice.getUser().getName();
				data[index][2] = invoice.getRoom().getName();
				data[index][3] = format.format(invoice.getInitialdate());
				data[index][4] = format.format(invoice.getFinaldate());
				data[index][5] = new Boolean(false);
				index++;
			}
		}
	}

	private int getRowSizeToBuild() {
		int rowSize = 0;
		if (this.invoiceList != null) {
			rowSize = this.invoiceList.size();
		}
		return rowSize;
	}
}