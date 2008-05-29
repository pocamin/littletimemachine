package timeMachine.runner;

import javax.swing.SwingUtilities;

import timeMachine.controller.Controller;
import timeMachine.dao.hibernate.SessionFactoryProvider;
import timeMachine.ui.MainUI;

public class Runner {
	
	public static void main(String[] args) {
		
		SessionFactoryProvider.getInstance().getCurrentSession();
		
		SwingUtilities.invokeLater(new Runnable(){
			public void run(){
				//On crée une nouvelle instance de notre JDialog
				final MainUI mainUI = new MainUI(new Controller());
				mainUI.setVisible(true);//On la rend visible
				new Thread(new Runnable(){

					public void run() {
						while(true)
						{
						  //mes actions
						  try
						  { 
							mainUI.refresh();
						    Thread.sleep(5000);

						  }catch(InterruptedException e)
						  {
						    System.out.println("interrupted exception : "+e.getMessage());
						    e.printStackTrace();
						  }
						}						
					}}).start();
				
				
			}
		});
		
		while(true)
		{
		  //mes actions
		  try
		  { 
			
		    Thread.sleep(5000);

		  }catch(InterruptedException e)
		  {
		    System.out.println("interrupted exception : "+e.getMessage());
		    e.printStackTrace();
		  }
		}

		
	}
	

}
