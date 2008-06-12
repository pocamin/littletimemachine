package org.littleTeamMachine.ui.graph;

import java.util.Collection;
import java.util.HashMap;

import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.littleTeamMachine.common.modele.Task;


public class TaskDataSetExtractor implements DataSetExtractor {

	public PieDataset getPieDataSet(Collection<Task> tasks) {
		
		DefaultPieDataset dataset = new DefaultPieDataset();
		HashMap<String , Double> map = new HashMap<String, Double>(); 
		

		for (Task task : tasks){

			Double value = map.get(task.getTaskType().getName());
			if (value == null) 
				value = new Double("0");
			
			value += task.getTimeTakenInMinute();
			
			map.put(task.getTaskType().getName(), value);
		}
		
		for( String name : map.keySet() ){
			dataset.setValue(name, map.get(name));
		}
		
		return dataset;
	}

}
