package timeMachine.ui.graph;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.PieDataset;


import timeMachine.controller.Controller;
import timeMachine.modele.Categorie;
import timeMachine.modele.Task;
import timeMachine.modele.Theme;


public class GraphUI extends JFrame {
	
	private static final long serialVersionUID = 1L;

	private static final DateFormat dateFormat = new SimpleDateFormat("dd/MM/yy");
	
	BufferedImage image;
	JLabel labelChrt = new JLabel();
	JComboBox voir = new JComboBox(new String[]{"prevue/imprévue","hors temps","categorie","theme","tache"});
	JComboBox filtreCategorie = new JComboBox();
	JComboBox filtreTheme = new JComboBox();
	JTextField dateFrom = new JTextField("25/08/1980"); 
	JTextField dateTo = new JTextField(dateFormat.format(new Date()));
	JButton btnOk = new JButton("Fitrer");
	JButton btnExtract = new JButton("XLS");
	
	private Controller controller;
	

	
	public GraphUI(Controller controller) {
		this.controller = controller;
		initThemes();
		initCategories();
		build();
		setTitle("Time Machine - stats");
		JPanel panelHaut = new JPanel(new GridLayout(3,4));
		panelHaut.add(new JLabel("Voir :"));
		panelHaut.add(voir);
		panelHaut.add(new JLabel("Categorie :"));
		panelHaut.add(filtreCategorie);
		panelHaut.add(new JLabel("theme :"));
		panelHaut.add(filtreTheme);
		panelHaut.add(new JLabel("de :"));
		panelHaut.add(dateFrom);		
		panelHaut.add(new JLabel("a :"));
		panelHaut.add(dateTo);
		panelHaut.add(btnOk);
		panelHaut.add(btnExtract);
		setLayout(new BorderLayout());
		setSize(500, 650);
		add(panelHaut,BorderLayout.NORTH);
		add(labelChrt,BorderLayout.CENTER);
		setLocationRelativeTo(null);
		setResizable(false);
		setVisible(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		btnOk.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent arg0) {
				build();
			}
			
		});
		
		btnExtract.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent arg0) {
				try {
					extract();
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}
			
		});

		
	}

	private void build() {
		extractData();
		labelChrt.setIcon(new ImageIcon(image));
	}
	
	private void extract() throws ParseException {
		try {
			Collection<Task> tasks = getTasks();
			tasks = filterCategory(tasks);
			tasks = filterTheme(tasks);
			String file = "c:/timeMachine.xls";
			XlsExtractor extractor = new XlsExtractor(tasks,file);
			extractor.extract();
			JOptionPane.showMessageDialog(this,"le fichier " + file + " a été créé","Information",JOptionPane.INFORMATION_MESSAGE);  
		} catch (Throwable t){
			JOptionPane.showMessageDialog(this,"Erreur inconue : le fichier n'a pas été créé" ,"Error",JOptionPane.ERROR_MESSAGE);  
		}
		
	}

	private void extractData() {
		try {
			Collection<Task> tasks = getTasks();
			tasks = filterCategory(tasks);
			tasks = filterTheme(tasks);
			
			PieDataset dataSet = getDataSet(tasks);
			JFreeChart chart = ChartFactory.createPieChart(null, dataSet, true, false, false);
			image = chart.createBufferedImage(500, 500);
			
			
		} catch (ParseException e) {
			e.printStackTrace();
		}
		

	}

	private PieDataset getDataSet(Collection<Task> tasks) {
		PieDataset dataset = null;
		DataSetExtractor dataSetExtractor = DataSetFactory.getInstance().create(voir.getSelectedIndex()); 
		
		dataset = dataSetExtractor.getPieDataSet(tasks);
		return dataset;
	}

	private Collection<Task> getTasks() throws ParseException {
		Date from = dateFormat.parse(dateFrom.getText());
		Date to = dateFormat.parse(dateTo.getText());
		Collection<Task> tasks = controller.getTasks(from,to);
		return tasks;
	}

	

	private Collection<Task> filterTheme(Collection<Task> tasks) {
		if (filtreTheme.getSelectedItem().equals("tous")){
			return tasks;
		} else {
			Collection<Task> toReturn = new ArrayList<Task>();
			for(Task task: tasks){
				if (task.getTaskType().getTheme().equals(filtreTheme.getSelectedItem())){
					toReturn.add(task);
				}
			}
			return toReturn;
		}
	}

	private Collection<Task> filterCategory(Collection<Task> tasks) {
		if (filtreCategorie.getSelectedItem().equals("toutes")){
			return tasks;
		} else {
			Collection<Task> toReturn = new ArrayList<Task>();
			for(Task task: tasks){
				if (task.getTaskType().getCategorie().equals(filtreCategorie.getSelectedItem())){
					toReturn.add(task);
				}
			}
			return toReturn;
		}
	}

	private void initThemes() {
		filtreTheme.setEditable(true);
		Collection<Theme> themesC = controller.getAvailableThemes();
		filtreTheme.addItem("tous");
		for (Theme theme : themesC) {
			filtreTheme.addItem(theme);
		}
	}

	private void initCategories() {
		filtreCategorie.setEditable(true);
		Collection<Categorie> categorieC = controller.getAvailableCategories();
		filtreCategorie.addItem("toutes");		
		for (Categorie categorie : categorieC) {
			filtreCategorie.addItem(categorie);
		}
	}
	
}
