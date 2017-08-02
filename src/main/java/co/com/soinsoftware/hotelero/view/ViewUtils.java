package co.com.soinsoftware.hotelero.view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.text.DecimalFormat;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.ListSelectionModel;
import javax.swing.text.NumberFormatter;

/**
 * @author Carlos Rodriguez
 * @since 30/05/2016
 * @version 1.0
 */
public class ViewUtils {
	
	protected static final String TITLE_DATABASE_ERROR = "Error en el software Hotelero!";
	
	protected static final String TITLE_REQUIRED_FIELDS = "Campos requeridos";
	
	protected static final String TITLE_SAVED = "Guardar";
	
	protected static final String MSG_DATABASE_CONNECTION_ERROR = "Error conectando con la base de datos, contacte a soporte técnico";
	
	protected static final String MSG_DELETE_QUESTION = "¿Está seguro que desea eliminar los datos seleccionados?";
	
	protected static final String MSG_DELETED = "¡Datos eliminados con exito!";
	
	protected static final String MSG_SAVE_QUESTION = "¿Está seguro que desea guardar los datos?";

	protected static final String MSG_SAVED = "¡Datos guardados con exito!";
	
	protected static final String MSG_UNEDITED = "No hay datos editados";
	
	protected static final String MSG_UNSELECTED = "No hay datos seleccionados";
	
	protected static final String MSG_UPDATE_QUESTION = "¿Está seguro que desea actualizar los datos?";
	
	protected static final String MSG_UPDATED = "¡Datos actualizados con exito!";

	protected static final Color GREY = new Color(249, 249, 249);

	protected static final Color BLUE = new Color(16, 135, 221);

	protected static final Font VERDANA_BOLD = new Font("Verdana", Font.BOLD,
			12);

	protected static final Font VERDANA_PLAIN = new Font("Verdana", Font.PLAIN,
			12);

	private ViewUtils() {
		super();
	}

	protected static void showMessage(final Component component,
			final Object message, final String title, final int type) {
		JOptionPane.showMessageDialog(component, message, title, type);
	}

	protected static int showConfirmDialog(final Component component,
			final Object message, final String title) {
		return JOptionPane.showConfirmDialog(component, message, title,
				JOptionPane.OK_CANCEL_OPTION);
	}

	protected static JLabel createJLabel(final String label, final int x,
			final int y) {
		final JLabel jlabel = new JLabel(label);
		jlabel.setForeground(Color.BLACK);
		jlabel.setFont(ViewUtils.VERDANA_BOLD);
		jlabel.setBounds(x, y, 300, 20);
		return jlabel;
	}

	protected static JTextField createJTextField(final String toolTip,
			final int x, final int y) {
		final JTextField textField = new JTextField();
		if (toolTip != null) {
			textField.setToolTipText(toolTip);
		}
		textField.setFont(ViewUtils.VERDANA_PLAIN);
		textField.setColumns(10);
		textField.setBounds(x, y, 186, 20);
		return textField;
	}

	protected static JFormattedTextField createJFormatedTextField(
			final String toolTip, final int x, final int y) {
		final NumberFormatter formatter = ViewUtils.getNumberFormatter();
		final JFormattedTextField textField = new JFormattedTextField(formatter);
		if (toolTip != null) {
			textField.setToolTipText(toolTip);
		}
		textField.setFont(ViewUtils.VERDANA_PLAIN);
		textField.setColumns(10);
		textField.setBounds(x, y, 186, 20);
		return textField;
	}

	protected static JPasswordField createJPasswordField(final String toolTip,
			final int x, final int y) {
		final JPasswordField password = new JPasswordField();
		if (toolTip != null) {
			password.setToolTipText(toolTip);
		}
		password.setFont(ViewUtils.VERDANA_PLAIN);
		password.setBounds(x, y, 186, 20);
		return password;
	}

	protected static JButton createJButton(final String label, final int key,
			final int x, final int y) {
		final JButton button = new JButton(label);
		button.setMnemonic(key);
		button.setForeground(Color.WHITE);
		button.setBackground(ViewUtils.BLUE);
		button.setFont(ViewUtils.VERDANA_BOLD);
		button.setBounds(x, y, 89, 23);
		return button;
	}

	protected static JList<String> createJList(final String toolTip,
			final String[] data, final int x, final int y) {
		final JList<String> jlst = new JList<>(data);
		jlst.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		if (toolTip != null) {
			jlst.setToolTipText(toolTip);
		}
		return jlst;
	}

	protected static JMenuItem createJMenuItem(final String label,
			final int key, final KeyStroke keyStroke) {
		final JMenuItem menuItem = new JMenuItem(label, key);
		menuItem.setAccelerator(keyStroke);
		return menuItem;
	}

	protected static JRadioButton createRadioButton(final String label,
			final int x, final int y) {
		final JRadioButton radioButton = new JRadioButton(label);
		radioButton.setBackground(ViewUtils.GREY);
		radioButton.setBounds(x, y, 100, 23);
		return radioButton;
	}

	protected static ButtonGroup createButtonGroup(
			final JRadioButton... radioButtons) {
		final ButtonGroup buttonGroup = new ButtonGroup();
		if (radioButtons != null) {
			for (int i = 0; i < radioButtons.length; i++) {
				buttonGroup.add(radioButtons[i]);
			}
		}
		return buttonGroup;
	}

	protected static void buildSoinSoftwareLabel(Dimension dimFrame,
			final JPanel panel) {
		int additionalY = 0;
		if (dimFrame.getHeight() == 0 || dimFrame.getWidth() == 0) {
			dimFrame = Toolkit.getDefaultToolkit().getScreenSize();
			additionalY = 60;
		}
		final int x = (int) dimFrame.getWidth() - 200;
		final int y = (int) dimFrame.getHeight() - (50 + additionalY);
		final JLabel soinLabel = ViewUtils.createJLabel(
				"SOIN SOFTWARE SAS, 2016", x, y);
		panel.add(soinLabel);
	}

	private static NumberFormatter getNumberFormatter() {
		final DecimalFormat format = new DecimalFormat("####0");
		format.setGroupingUsed(true);
		format.setGroupingSize(3);
		format.setMinimumFractionDigits(0);
		format.setMaximumFractionDigits(0);
		format.setParseIntegerOnly(true);
		format.setDecimalSeparatorAlwaysShown(false);
		NumberFormatter formatter = new NumberFormatter(format);
		return formatter;
	}
}