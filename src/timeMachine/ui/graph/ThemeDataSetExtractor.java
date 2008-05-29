package timeMachine.ui.graph;

import java.util.Collection;
import java.util.HashMap;

import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;

import timeMachine.modele.Task;
import timeMachine.modele.Theme;

public class ThemeDataSetExtractor implements DataSetExtractor {

	public PieDataset getPieDataSet(Collection<Task> tasks) {
		
		DefaultPieDataset dataset = new DefaultPieDataset();
		HashMap<Theme , Double> map = new HashMap<Theme, Double>(); 
		

		for (Task task : tasks){

			Double value = map.get(task.getTaskType().getTheme());
			if (value == null) 
				value = new Double("0");
			
			value += task.getTimeTakenInMinute();
			
			map.put(task.getTaskType().getTheme(), value);
		}
		
		for( Theme theme : map.keySet() ){
			dataset.setValue(theme.getName(), map.get(theme));
		}
		
		return dataset;
	}

}
