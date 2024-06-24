package model;

import java.util.Date;
import java.util.List;

public class Task {
	private int taskId;
	private String name;
	private String startdate;
	private String enddate;
	private String place;
	private int hour;
	private int minute;
	private String memo;
	private int petId;

	public Task(String name) {
		super();
		this.name = name;
	}

	public Task(String name, String startdate, String enddate, String place, int hour, int minute, String memo,
			int petId) {
		super();
		this.name = name;
		this.startdate = startdate;
		this.enddate = enddate;
		this.place = place;
		this.hour = hour;
		this.minute = minute;
		this.memo = memo;
		this.petId = petId;
	}

	public Task(int taskId, String name, String startdate, String enddate, String place, int hour, int minute,
			String memo, int petId) {
		super();
		this.taskId = taskId;
		this.name = name;
		this.startdate = startdate;
		this.enddate = enddate;
		this.place = place;
		this.hour = hour;
		this.minute = minute;
		this.memo = memo;
		this.petId = petId;
	}

	public int getTaskId() {
		return taskId;
	}

	public void setTaskId(int taskId) {
		this.taskId = taskId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStartdate() {
		return startdate;
	}

	public void setStartdate(String startdate) {
		this.startdate = startdate;
	}

	public String getEnddate() {
		return enddate;
	}

	public void setEnddate(String enddate) {
		this.enddate = enddate;
	}

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public int getHour() {
		return hour;
	}

	public void setHour(int hour) {
		this.hour = hour;
	}

	public int getMinute() {
		return minute;
	}

	public void setMinute(int minute) {
		this.minute = minute;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public int getPetId() {
		return petId;
	}

	public void setPetId(int petId) {
		this.petId = petId;
	}

}
