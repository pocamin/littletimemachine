package org.littleTeamMachine.ui.security;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.littleTeamMachine.common.modele.User;
import org.littleTeamMachine.common.services.Controller;
import org.littleTeamMachine.service.LTMContext;
import org.littleTeamMachine.service.jmsReceiver.INotificationReceiver;
import org.littleTeamMachine.ui.MainUI;
import org.littleTeamMachine.ui.SpringUtilities;

public class LoginUI extends JDialog{

	private static final long serialVersionUID = 1L;
	private static final Log log = LogFactory.getLog(LoginUI.class);
	
	private Controller controller;

	private JTextField login = new JTextField() ; 
	private JPasswordField password = new JPasswordField();
	private JButton btnOK = new JButton("Se connecter");
	private INotificationReceiver notificationReceiver;

	public LoginUI(Controller controller, INotificationReceiver notificationReceiver){
		this.notificationReceiver = notificationReceiver;
		this.controller = controller;
		build();
	}
	
	
	private void build() {
		setTitle("Team Machine - Login");
		this.setSize(200, 120);
		this.setLocationRelativeTo(null);
		
		
		JPanel panel = new JPanel();
		this.setLayout(new BorderLayout());
		panel.setLayout(new SpringLayout());
		
		panel.add(new JLabel("login"));
		panel.add(login);

		panel.add(new JLabel("password"));
		panel.add(password);

		this.add(panel,BorderLayout.CENTER);
		
		this.add(btnOK,BorderLayout.SOUTH);
		
		
		
		SpringUtilities.makeCompactGrid(panel, 2, 2, //rows, cols
			        6, 6, //initX, initY
			        6, 6); //xPad, yPad
		
		
		btnOK.addActionListener(new ActionListener(){
			
			public void actionPerformed(ActionEvent arg0) {
				User user = controller.getUser(login.getText(),new String(password.getPassword()));
				if (user != null){
					LTMContext.setUser(user);
					log.error(user.getGroup().getLinkedGroups().size());
					{
						MainUI mainUI = new MainUI(controller,notificationReceiver);
						mainUI.setVisible(true);
					}
					dispose();
				}
			}
			
		});
		

		
	}
	
	



}
