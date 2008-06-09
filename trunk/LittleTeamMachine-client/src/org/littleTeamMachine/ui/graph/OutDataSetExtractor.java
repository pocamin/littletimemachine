package org.littleTeamMachine.ui.graph;

import java.util.Collection;

import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.littleTeamMachine.common.modele.Task;

public class OutDataSetExtractor implements DataSetExtractor {

	public PieDataset getPieDataSet(Collection<Task> tasks) {
		
		DefaultPieDataset dataset = new DefaultPieDataset();
		
		double timeOut = 0; 
		double timeOk = 0;
		
		
		for (Task task : tasks){
			if (task.getTimeTakenInMinute() > 0){
				if (task.getTimeTakenInMinute() > task.getForecastTimeInMinute()){
					timeOut += 1;
				} else timeOk += 1;
			}
		}
		
		if (timeOk != 0)
			dataset.setValue("ok", timeOk);
		if (timeOut != 0)
			dataset.setValue("out", timeOut);
		
		return dataset;
	}

}
