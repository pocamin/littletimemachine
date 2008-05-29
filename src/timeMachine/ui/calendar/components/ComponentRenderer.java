package timeMachine.ui.calendar.components;

import java.awt.Component;

import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

public class ComponentRenderer implements TableCellRenderer {

	public Component getTableCellRendererComponent(JTable table, Object arg1,
			boolean isSelected, boolean hasFocus, int row, int column) {
		
		if(arg1 instanceof Component)
			return (Component)arg1;
		else return null ;
	}

}
