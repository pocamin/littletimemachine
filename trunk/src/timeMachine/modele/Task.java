package timeMachine.modele;

import static timeMachine.tools.DateTool.normalizeDate;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Index;



@Entity
public class Task extends Root {

	@ManyToOne(fetch=FetchType.EAGER)
	private TaskType taskType;

	@Temporal(TemporalType.DATE)
	@Index(name="day_idx")
	private Date day;
	
	private Boolean forcasted; 
	
	private Long timeTakenInMinute = new Long(0);
	
	private Long forecastTimeInMinute;
	
	public Task(){
		
	}
	
	@Override
	public String toString() {
		return timeTakenInMinute + "/" + forecastTimeInMinute;
	}
	
	public TaskType getTaskType() {
		return taskType;
	}

	public void setTaskType(TaskType taskType) {
		this.taskType = taskType;
	}

	public Date getDay() {
		return day;
	}

	public void setDay(Date day) {
		this.day = normalizeDate(day);
	}

	public Long getTimeTakenInMinute() {
		return timeTakenInMinute;
	}

	public void setTimeTakenInMinute(Long timeTakenInMinute) {
		this.timeTakenInMinute = timeTakenInMinute;
	}


	public Boolean getForcasted() {
		return forcasted;
	}

	public void setForcasted(Boolean forcasted) {
		this.forcasted = forcasted;
	}

	public Long getForecastTimeInMinute() {
		return forecastTimeInMinute;
	}

	public void setForecastTimeInMinute(Long forecastTimeInMinute) {
		this.forecastTimeInMinute = forecastTimeInMinute;
	}
	

}
