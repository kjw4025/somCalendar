package model;

import java.util.Date;
import java.util.List;

public class Calendar {
	private int calendar_id;
	private int user_id;
	private int pet_id;

	public Calendar() {
	} // 기본 생성자

	public Calendar(int calendar_id, int user_id, int pet_id) {
		super();
		this.calendar_id = calendar_id;
		this.user_id = user_id;
		this.pet_id = pet_id;
	}

	public int getCalendar_id() {
		return calendar_id;
	}

	public void setCalendar_id(int calendar_id) {
		this.calendar_id = calendar_id;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public int getPet_id() {
		return pet_id;
	}

	public void setPet_id(int pet_id) {
		this.pet_id = pet_id;
	}

}
