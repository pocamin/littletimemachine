package timeMachine.ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

import timeMachine.controller.Controller;
import timeMachine.modele.Categorie;
import timeMachine.modele.TaskType;
import timeMachine.modele.Theme;

public class CreateTaskUI extends JDialog {

	private static final DateFormat dateFormat = new SimpleDateFormat(
			"dd/MM/yy");

	private static final long serialVersionUID = 1L;
	private Controller controller;

	private JComboBox name = new JComboBox();

	private JTextField startDate = new JTextField(dateFormat.format(new Date()));
	private JTextField endDate = new JTextField(dateFormat.format(new Date()));
	private JComboBox categories = new JComboBox();
	private JComboBox themes = new JComboBox();
	private JTextField timeInMinute = new JTextField("60");
	private JButton ok = new JButton("OK");
	private JCheckBox forcasted = new JCheckBox();

	private JCheckBox dayL = new JCheckBox("L");
	private JCheckBox dayMa = new JCheckBox("M");
	private JCheckBox dayMe = new JCheckBox("M");
	private JCheckBox dayJ = new JCheckBox("J");
	private JCheckBox dayV = new JCheckBox("V");

	{
		dayL.setSelected(true);
		dayMa.setSelected(true);
		dayMe.setSelected(true);
		dayJ.setSelected(true);
		dayV.setSelected(true);
	}

	private CreateTaskUI createTaskUI = this;

	private void build() {
		setTitle("Little Time Machine - create task");
		this.setSize(700, 300);

		JPanel panel = new JPanel();
		JPanel dayPanel = new JPanel(new FlowLayout());
		dayPanel.add(dayL);
		dayPanel.add(dayMa);
		dayPanel.add(dayMe);
		dayPanel.add(dayJ);
		dayPanel.add(dayV);

		this.setLayout(new BorderLayout());

		panel.setLayout(new SpringLayout());
		panel.add(new JLabel("Nom"));
		initTaskTypes();
		
		panel.add(name);

		panel.add(new JLabel("Début"));
		panel.add(startDate);

		panel.add(new JLabel("Fin"));
		panel.add(endDate);

		panel.add(new JLabel("Jours"));
		panel.add(dayPanel);

		panel.add(new JLabel("Categorie"));
		initCategories();
		panel.add(categories);

		panel.add(new JLabel("theme"));
		initThemes();
		panel.add(themes);

		panel.add(new JLabel("prévue"));
		panel.add(forcasted);

		panel.add(new JLabel("durée"));
		panel.add(timeInMinute);

		this.add(panel, BorderLayout.CENTER);

		this.add(ok, BorderLayout.SOUTH);

		SpringUtilities.makeCompactGrid(panel, 8, 2, //rows, cols
				6, 6, //initX, initY
				6, 6); //xPad, yPad

		updateTaskType();

		ok.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {

				try {

					TaskType taskType = null;
					if (name.getSelectedItem() instanceof TaskType) {
						taskType = (TaskType) name.getSelectedItem();
					} else {
						taskType = new TaskType();
						taskType.setFinished(false);
						// Categorie
						Categorie categorieO = null;
						if (categories.getSelectedItem() instanceof Categorie) {
							categorieO = (Categorie) categories
									.getSelectedItem();
						} else {
							categorieO = new Categorie();
							categorieO.setName(categories.getSelectedItem()
									.toString());
						}
						taskType.setCategorie(categorieO);

						// Theme
						Theme themeO = null;
						if (themes.getSelectedItem() instanceof Theme) {
							themeO = (Theme) themes.getSelectedItem();
						} else {
							themeO = new Theme();
							themeO.setName(themes.getSelectedItem().toString());
						}
						taskType.setTheme(themeO);

						// TaskType
						taskType.setName(name.getSelectedItem().toString());
					}

					controller.createTasks(taskType, dateFormat.parse(startDate
							.getText()), dateFormat.parse(endDate.getText()),
							forcasted.isSelected(), dayL.isSelected(), dayMa
									.isSelected(), dayMe.isSelected(), dayJ
									.isSelected(), dayV.isSelected(), Long
									.parseLong(timeInMinute.getText())

					);

					createTaskUI.dispose();
				} catch (NumberFormatException e) {
				} catch (ParseException e) {
				}

			}

		});

		startDate.addFocusListener(new FocusListener() {

			public void focusLost(FocusEvent arg0) {
				if (endDate.getText().equals(""))
					endDate.setText(startDate.getText());
			}

			public void focusGained(FocusEvent arg0) {

			}
		});

		name.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				updateTaskType();
			}

		});

	}

	private void updateTaskType() {
		if (!(name.getSelectedItem() instanceof  TaskType)){
			for (int i = 0 ; i < name.getItemCount() ; i++){
				if (name.getSelectedItem().toString().toUpperCase().equals(name.getItemAt(i).toString().toUpperCase())){
					name.setSelectedItem(name.getItemAt(i));
					break;
				}
			}
		}
		
		if (name.getSelectedItem() instanceof TaskType) {
			TaskType taskType = (TaskType) name.getSelectedItem();
			categories.setSelectedItem(taskType.getCategorie());
			categories.setEnabled(false);
			themes.setSelectedItem(taskType.getTheme());
			themes.setEnabled(false);
		} else {
			themes.setEnabled(true);
			categories.setEnabled(true);
		}
	}

	private void initThemes() {
		themes.setEditable(true);
		Collection<Theme> themesC = controller.getAvailableThemes();
		for (Theme theme : themesC) {
			themes.addItem(theme);
		}
	}

	private void initCategories() {
		categories.setEditable(true);
		Collection<Categorie> categorieC = controller.getAvailableCategories();
		for (Categorie categorie : categorieC) {
			this.categories.addItem(categorie);
		}
	}

	private void initTaskTypes() {
		name.setEditable(true);
		Collection<TaskType> taskTypes = controller.getAvailableTaskTypes();
		for (TaskType taskType : taskTypes) {
			name.addItem(taskType);
		}
	}

	public CreateTaskUI(Controller controller, MainUI mainUI) {
		super(mainUI, true);
		setLocationRelativeTo(mainUI);
		this.controller = controller;
		build();
	}

}
