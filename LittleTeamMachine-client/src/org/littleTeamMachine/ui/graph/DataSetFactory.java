package org.littleTeamMachine.ui.graph;

public class DataSetFactory {
	
	private static DataSetFactory factory; 
	
	private DataSetFactory(){
		
	}
	
	public static DataSetFactory getInstance(){
		if (factory == null)
			factory = new DataSetFactory(); 
		return factory;
	}
	
	public DataSetExtractor create(int type){
		
		switch (type){
			case 0 : return new ForcastDataSetExtractor(); 
			case 1 : return new OutDataSetExtractor();
			case 2 : return new CategorieDataSetExtractor();
			case 3 : return new ThemeDataSetExtractor();
			case 4 : return new TaskDataSetExtractor();
		}
		
		return null;
		
		
		
		
		
	}
	
	
	

}
