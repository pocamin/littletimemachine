package org.littleTeamMachine.ui.graph;

import java.util.Collection;

import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.littleTeamMachine.common.modele.Task;


public class ForcastDataSetExtractor implements DataSetExtractor {

	public PieDataset getPieDataSet(Collection<Task> tasks) {
		
		DefaultPieDataset dataset = new DefaultPieDataset();
		
		double timeF = 0; 
		double timeNF = 0;
		
		for (Task task : tasks){
			if (task.getForcasted() ){
				timeF += task.getTimeTakenInMinute(); 
			} else {
				timeNF += task.getTimeTakenInMinute();
			}
		}
		
		if (timeF != 0)
			dataset.setValue("prévu", timeF);
		if (timeNF != 0)		
			dataset.setValue("imprévu", timeNF);
		
		return dataset;
	}

}
