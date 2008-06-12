package org.littleTeamMachine.ui.graph;

import java.util.Collection;

import org.jfree.data.general.PieDataset;
import org.littleTeamMachine.common.modele.Task;



public interface DataSetExtractor {
	
	public PieDataset getPieDataSet(Collection<Task> tasks);

}
