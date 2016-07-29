package co.com.soinsoftware.hotelero.util;

import java.text.SimpleDateFormat;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import co.com.soinsoftware.hotelero.entity.Invoiceitem;
import co.com.soinsoftware.hotelero.entity.Service;

/**
 * @author Carlos Rodriguez
 * @since 29/07/2016
 * @version 1.0
 */
public class InvoiceItemNotEditableTableModel extends AbstractTableModel {

	private static final long serialVersionUID = 8408209589620109955L;

	private static final String[] COLUMN_NAMES = { "Fecha", "Servicio",
			"Cantidad", "Precio", "Total" };

	private Object[][] data;

	public InvoiceItemNotEditableTableModel(
			final List<Invoiceitem> invoiceItemList) {
		super();
		this.buildData(invoiceItemList);
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

	private void buildData(final List<Invoiceitem> invoiceItemList) {
		final SimpleDateFormat format = new SimpleDateFormat("dd-MMM-yyyy");
		final int rowSize = this.getRowSizeToBuild(invoiceItemList);
		data = new Object[rowSize][5];
		if (invoiceItemList != null) {
			int index = 0;
			for (final Invoiceitem invoiceItem : invoiceItemList) {
				final Service service = invoiceItem.getService();
				data[index][0] = format
						.format(invoiceItem.getInvoiceitemdate());
				data[index][1] = service.getName();
				data[index][2] = String.valueOf(invoiceItem.getQuantity());
				data[index][3] = String.valueOf(invoiceItem.getUnitvalue());
				data[index][4] = String.valueOf(invoiceItem.getValue());
				index++;
			}
		}
	}

	private int getRowSizeToBuild(final List<Invoiceitem> invoiceItemList) {
		int rowSize = 0;
		if (invoiceItemList != null) {
			rowSize = invoiceItemList.size();
		}
		return rowSize;
	}
}