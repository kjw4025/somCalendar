package model;

import java.util.Date;
import java.util.List;

public class DailyList {
	private Date date;
	private int calendar_id;
	private int pet_id;
	private List<User> memberList;

	public DailyList() {
	}

	public DailyList(Date date, int calendar_id, int pet_id) {
		super();
		this.date = date;
		this.calendar_id = calendar_id;
		this.pet_id = pet_id;
	}

	public Date getTask_id() {
		return date;
	}

	public void setDate_id(Date date) {
		this.date = date;
	}

	public int getCalendar_id() {
		return calendar_id;
	}

	public void setCalendar(int calendar_id) {
		this.calendar_id = calendar_id;
	}

	public int getPet_id() {
		return pet_id;
	}

	public void setPet_id(int pet_id) {
		this.pet_id = pet_id;
	}

	public List<User> getMemberList() {
		return memberList;
	}

	public void setMemberList(List<User> memberList) {
		this.memberList = memberList;
	}

}
