package org.littleTeamMachine.ui;


import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import static java.awt.event.KeyEvent.*;
import javax.swing.JComboBox;

public class maxLengthInputListener implements KeyListener {

	int length; 
	JComboBox comboBox ;
	
	public maxLengthInputListener(int length, JComboBox comboBox) {
		super();
		this.length = length;
		this.comboBox = comboBox;
	}

	public void keyPressed(KeyEvent e) {
		
		String value = comboBox.getSelectedItem().toString();

		if (e.getKeyCode() != VK_BACK_SPACE && e.getKeyCode() != VK_DELETE && value.length() >= length){
			e.consume();
		}
	}

	public void keyReleased(KeyEvent e) {
	}

	public void keyTyped(KeyEvent e) {
	}

	
	
	

}
