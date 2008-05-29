package timeMachine.ui.graph;

import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;

import timeMachine.modele.Task;
import timeMachine.modele.TaskType;

public class XlsExtractor{
	private static final DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	private Collection<Task> tasks ;
	private String path;
	
	private Map<Date, Short> colDays = new HashMap<Date, Short>();
	private Map<Short, Date> daysCols = new HashMap<Short, Date>();
	private Map<TaskType, HSSFRow> rowTaskType =  new HashMap<TaskType, HSSFRow>();

	
	public XlsExtractor(Collection<Task> tasks, String path) {
		super();
		this.tasks = tasks;
		this.path = path;
	}
	
	
	public void extract() throws IOException{
			HSSFWorkbook extract = new HSSFWorkbook();
			HSSFSheet sheet = extract.createSheet();
			
			HSSFCellStyle cellStyleH = extract.createCellStyle();
			cellStyleH.setAlignment(HSSFCellStyle.ALIGN_CENTER);
			HSSFFont font = extract.createFont();
			font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
			cellStyleH.setFont(font);
			
			HSSFCellStyle cellStyleH1 = extract.createCellStyle();
			cellStyleH1.setAlignment(HSSFCellStyle.ALIGN_CENTER);
			font = extract.createFont();
			font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
			cellStyleH1.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);
			cellStyleH1.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
			cellStyleH1.setFont(font);
			
			HSSFCellStyle cellStyleH2 = extract.createCellStyle();
			cellStyleH2.setAlignment(HSSFCellStyle.ALIGN_CENTER);
			font = extract.createFont();
			font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
			cellStyleH2.setFillForegroundColor(HSSFColor.LIGHT_CORNFLOWER_BLUE.index);
			cellStyleH2.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
			cellStyleH2.setFont(font);
			
			HSSFCellStyle cellStyle1 = extract.createCellStyle();
			cellStyle1.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);
			cellStyle1.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
			
			HSSFCellStyle cellStyle2 = extract.createCellStyle();
			cellStyle2.setFillForegroundColor(HSSFColor.LIGHT_CORNFLOWER_BLUE.index);
			cellStyle2.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
			
			
			// On créer le header
			extractHeader(sheet,cellStyleH,cellStyleH1,cellStyleH2);
			
			// On crée le contenu
			extractContent(sheet);
			
			// On reformate le tableau
			short nc = sheet.getRow(0).getLastCellNum();
			for (short i = 0 ; i <= nc ; i++ ){
				sheet.autoSizeColumn(i);
				
				if (i >= 3){
					for (int rn = 1; rn <= rowTaskType.size(); rn ++){
						HSSFRow row = sheet.getRow(rn);
						HSSFCell cell = row.getCell(i);
						if (cell == null)
							cell = row.createCell(i);
						
						Date date = daysCols.get(i);
						if (isColor(date))
							cell.setCellStyle(cellStyle1);
						else 
							cell.setCellStyle(cellStyle2);
						
					}
				}
				

			}
			
			
			FileOutputStream os = new FileOutputStream(path);
			extract.write(os);
			os.close();
		

	}


	private void extractContent(HSSFSheet sheet) {
		for(Task task: tasks){
			HSSFRow row = rowTaskType.get(task.getTaskType());
			if (row == null){
				row = sheet.createRow(rowTaskType.size()+1);
				row.createCell((short)0).setCellValue(new HSSFRichTextString(task.getTaskType().getName()));
				row.createCell((short)1).setCellValue(new HSSFRichTextString(task.getTaskType().getCategorie().getName()));
				row.createCell((short)2).setCellValue(new HSSFRichTextString(task.getTaskType().getTheme().getName()));
				rowTaskType.put(task.getTaskType(), row);
			}
			HSSFCell cell = row.createCell(colDays.get(task.getDay()));
			cell.setCellValue(task.getTimeTakenInMinute());
		}
	}


	private void extractHeader(HSSFSheet sheet, HSSFCellStyle cellStyleH, HSSFCellStyle cellStyleH1, HSSFCellStyle cellStyleH2) {
		Date first = tasks.iterator().next().getDay();
		Date last = tasks.iterator().next().getDay();
		for(Task task : tasks){
			if (task.getDay().before(first))
				first = task.getDay();
			if (task.getDay().after(last))
				last = task.getDay();
		}
		
		HSSFRow header = sheet.createRow(0);
		HSSFCell cell;
		cell = header.createCell((short)0);
		cell.setCellValue(new HSSFRichTextString("tache"));
		cell.setCellStyle(cellStyleH);
		cell = header.createCell((short)1);
		cell.setCellValue(new HSSFRichTextString("categorie"));
		cell.setCellStyle(cellStyleH);
		cell = header.createCell((short)2);
		cell.setCellValue(new HSSFRichTextString("theme"));
		cell.setCellStyle(cellStyleH);
		
		Date currentDate = first;
		short nbday = 0; 
		while (!currentDate.after(last)){
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(currentDate);
			int d = calendar.get(Calendar.DAY_OF_WEEK);
			if (d != Calendar.SUNDAY && d!= Calendar.SATURDAY){
				nbday ++;
				cell = header.createCell((short)(2 + nbday));
				cell.setCellValue(new HSSFRichTextString( dateFormat.format(currentDate)));
				if (isColor(currentDate))
					cell.setCellStyle(cellStyleH1);
				else 
					cell.setCellStyle(cellStyleH2);
				
				colDays.put(currentDate, (short)(2 + nbday));
				daysCols.put( (short)(2 + nbday),currentDate);
			}
			
			currentDate = addOneDay(currentDate);
		}
	}


	private boolean isColor(Date currentDate) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(currentDate);
		int i = calendar.get(Calendar.WEEK_OF_YEAR) % 2;
		boolean color = (i == 0);
		return color;
	}


	private Date addOneDay(Date currentDate) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(currentDate);
		calendar.add(Calendar.DAY_OF_MONTH, 1);
		return calendar.getTime();
	}
	
	
	
	
}