package timeMachine.ui.graph;

import java.util.Collection;

import org.jfree.data.general.PieDataset;

import timeMachine.modele.Task;

public interface DataSetExtractor {
	
	public PieDataset getPieDataSet(Collection<Task> tasks);

}
