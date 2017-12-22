package co.com.soinsoftware.hotelero.util;

import java.text.SimpleDateFormat;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import com.soinsoftware.hotelero.persistence.entity.InvoiceItem;
import com.soinsoftware.hotelero.persistence.entity.Service;

/**
 * @author Carlos Rodriguez
 * @since 23/07/2016
 * @version 1.0
 */
public class InvoiceItemTableModel extends AbstractTableModel {

	private static final long serialVersionUID = 8408209589620109955L;

	private static final String[] COLUMN_NAMES = { "Fecha", "Servicio", "Cantidad", "Precio", "Total", "Eliminar" };

	private final List<InvoiceItem> invoiceItemList;

	private Object[][] data;

	public InvoiceItemTableModel(final List<InvoiceItem> invoiceItemList) {
		super();
		this.invoiceItemList = invoiceItemList;
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
		final InvoiceItem invoiceItem = this.invoiceItemList.get(row);
		invoiceItem.setDelete((Boolean) value);
		data[row][col] = value;
		fireTableCellUpdated(row, col);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public Class getColumnClass(final int col) {
		return getValueAt(0, col).getClass();
	}

	public List<InvoiceItem> getInvoiceItemList() {
		return this.invoiceItemList;
	}

	private void buildData() {
		final SimpleDateFormat format = new SimpleDateFormat("dd-MMM-yyyy");
		final int rowSize = this.getRowSizeToBuild();
		data = new Object[rowSize][6];
		if (this.invoiceItemList != null) {
			int index = 0;
			for (final InvoiceItem invoiceItem : this.invoiceItemList) {
				final Service service = invoiceItem.getService();
				data[index][0] = format.format(invoiceItem.getInvoiceitemdate());
				data[index][1] = service.getName();
				data[index][2] = String.valueOf(invoiceItem.getQuantity());
				data[index][3] = String.valueOf(invoiceItem.getUnitvalue());
				data[index][4] = String.valueOf(invoiceItem.getValue());
				data[index][5] = new Boolean(false);
				index++;
			}
		}
	}

	private int getRowSizeToBuild() {
		int rowSize = 0;
		if (this.invoiceItemList != null) {
			rowSize = this.invoiceItemList.size();
		}
		return rowSize;
	}
}