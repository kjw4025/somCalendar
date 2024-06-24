package model.service;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

import model.Task;
import model.User;
import model.dao.TaskDAO;

public class TaskManager {
	private static TaskManager taskMan = new TaskManager();
	private TaskDAO taskDAO;

	private TaskManager() {
		try {
			taskDAO = new TaskDAO();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static TaskManager getInstance() {
		return taskMan;
	}

	public int create(Task task) throws SQLException, ExistingException, ParseException {
		return taskDAO.create(task);
	}

	public int update(Task task) throws SQLException, UserNotFoundException {
		return taskDAO.update(task);
	}

	public int remove(int task_id) throws SQLException, UserNotFoundException {
		return taskDAO.remove(task_id);
	}

	public Task findTask(int task_id) throws SQLException, UserNotFoundException {
		Task task = taskDAO.findTask(task_id);

		if (task == null) {
			throw new UserNotFoundException(task + "는 존재하지 않는 일정입니다.");
		}
		return task;
	}

	public List<Task> findTaskbyPetId(int petId) throws SQLException, UserNotFoundException {
		List<Task> task = taskDAO.findTaskbyPetId(petId);

		if (task == null) {
			throw new UserNotFoundException(petId + "에는 일정이 없습니다.");
		}
		return task;
	}

	public TaskDAO getTaskDAO() {
		return this.taskDAO;
	}

	public List<Task> findTaskList(String userId, int petId, String date) throws SQLException, UserNotFoundException {
		List<Task> task = taskDAO.findTaskList(userId, petId, date);

		if (task == null) {
			throw new UserNotFoundException(petId + "에는 일정이 없습니다.");
		}
		return task;
	}

	public List<Task> findAllTask(String userId, String startDate, String endDate)
			throws SQLException, UserNotFoundException {
		List<Task> task = taskDAO.findAllTask(userId, startDate, endDate);
		System.out.println("taskManager의 findAllTask : " + userId);
		if (task == null) {
			throw new UserNotFoundException("일정이 없습니다.");
		}
		return task;
	}
}
