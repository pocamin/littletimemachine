package timeMachine.ui.calendar;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.TableColumnModel;

import timeMachine.ui.calendar.components.Refreshable;

public class CalendarTableMouseListener extends MouseAdapter {
	private JTable table;
	private CalendarUI calendarUI;

	public CalendarTableMouseListener(JTable table,CalendarUI calendarUI) {
		this.table = table;
		this.calendarUI = calendarUI;
	}

	private void forwardEventToButton(MouseEvent e) {
		TableColumnModel columnModel = table.getColumnModel();
		int column = columnModel.getColumnIndexAtX(e.getX());
		int row = e.getY() / table.getRowHeight();
		Object value;
		JButton button;

		if (row >= table.getRowCount() || row < 0
				|| column >= table.getColumnCount() || column < 0)
			return;

		value = table.getValueAt(row, column);

		if (!(value instanceof JButton))
			return;

		button = (JButton) value;

		button.doClick();
		
		if (button instanceof Refreshable) 
			((Refreshable) button).refresh();
		
		calendarUI.repaint();
	}

	public void mouseClicked(MouseEvent e) {
		forwardEventToButton(e);
	}


}
