package timeMachine.ui.graph;

import java.util.Collection;
import java.util.HashMap;

import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;

import timeMachine.modele.Categorie;
import timeMachine.modele.Task;

public class CategorieDataSetExtractor implements DataSetExtractor {

	public PieDataset getPieDataSet(Collection<Task> tasks) {
		
		DefaultPieDataset dataset = new DefaultPieDataset();
		HashMap<Categorie , Double> map = new HashMap<Categorie, Double>(); 
		

		for (Task task : tasks){

			Double value = map.get(task.getTaskType().getCategorie());
			if (value == null) 
				value = new Double("0");
			
			value += task.getTimeTakenInMinute();
			
			map.put(task.getTaskType().getCategorie(), value);
		}
		
		for( Categorie categorie : map.keySet() ){
			dataset.setValue(categorie.getName(), map.get(categorie));
		}
		
		return dataset;
	}

}
