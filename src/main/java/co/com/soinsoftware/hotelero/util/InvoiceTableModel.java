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
public class InvoiceTableModel extends AbstractTableModel {

	private static final long serialVersionUID = 8408209589620109955L;

	private static final String[] COLUMN_NAMES = { "Habitación", "Nombre",
			"Llegada", "Salida", "Estado", "Total" };

	private Object[][] data;

	public InvoiceTableModel(final List<Invoice> invoiceList) {
		super();
		this.buildData(invoiceList);
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

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public Class getColumnClass(final int col) {
		return getValueAt(0, col).getClass();
	}

	private void buildData(final List<Invoice> invoiceList) {
		final SimpleDateFormat format = new SimpleDateFormat("dd-MMM-yyyy");
		final int rowSize = this.getRowSizeToBuild(invoiceList);
		data = new Object[rowSize][6];
		if (invoiceList != null) {
			int index = 0;
			for (final Invoice invoice : invoiceList) {
				data[index][0] = invoice.getRoom().getName();
				data[index][1] = invoice.getUser().getName();
				data[index][2] = format.format(invoice.getInitialdate());
				data[index][3] = format.format(invoice.getFinaldate());
				data[index][4] = invoice.getInvoicestatus().getName();
				data[index][5] = String.valueOf(invoice.getValue());
				index++;
			}
		}
	}

	private int getRowSizeToBuild(final List<Invoice> invoiceList) {
		int rowSize = 0;
		if (invoiceList != null) {
			rowSize = invoiceList.size();
		}
		return rowSize;
	}
}