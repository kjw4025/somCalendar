package model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import model.Pet;
import model.Task;
import model.User;
import model.dao.JDBCUtil;

public class TaskDAO {
	private JDBCUtil jdbcUtil = null;

	public TaskDAO() {
		jdbcUtil = new JDBCUtil();
	}

	public int create(Task task) throws SQLException {
		String sql = "INSERT INTO Task VALUES (sqtask.nextval,?,?,?,?,?,?,?,?)";
		Object[] param = new Object[] { task.getName(), task.getStartdate(), task.getEnddate(), task.getPlace(),
				task.getHour(), task.getMinute(), task.getMemo(), task.getPetId() };
		jdbcUtil.setSqlAndParameters(sql, param);

		try {
			int result = jdbcUtil.executeUpdate();
			return result;
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		} finally {
			jdbcUtil.commit();
			jdbcUtil.close();
		}
		return 0;
	}

	public int update(Task task) throws SQLException {
		String sql = "UPDATE Task " + "SET name=?, startdate=?, enddate=?, place=?, hour=?, minute=?, memo=?, petId=? "
				+ "WHERE taskId=?";

		Object[] param = new Object[] { task.getName(), task.getStartdate(), task.getEnddate(), task.getPlace(),
				task.getHour(), task.getMinute(), task.getMemo(), task.getPetId(), task.getTaskId() };
		jdbcUtil.setSqlAndParameters(sql, param);

		try {

			int result = jdbcUtil.executeUpdate();
			return result;
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		} finally {
			jdbcUtil.commit();
			jdbcUtil.close();
		}
		return 0;
	}

	public Task findTask(int taskId) throws SQLException {
		String sql = "SELECT name, startdate, enddate, place, hour, minute, memo, petId " + "FROM Task "
				+ "WHERE taskId=? ";
		jdbcUtil.setSqlAndParameters(sql, new Object[] { taskId });
		Task task = null;
		try {
			ResultSet rs = jdbcUtil.executeQuery();
			while (rs.next()) {
				task = new Task(taskId, rs.getString("name"), rs.getString("startdate"), rs.getString("enddate"),
						rs.getString("place"), rs.getInt("hour"), rs.getInt("minute"), rs.getString("memo"),
						rs.getInt("petId"));
				return task;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();
		}
		return null;
	}

	public List<Task> findTaskbyPetId(int petId) throws SQLException {
		String sql = "select t.taskid taskid, t.name name, t.startdate startdate, t.enddate enddate, t.place place, t.hour hour, t.minute minute, t.memo memo, t.petid petid "
				+ "from task t, pet p " + "where t.petid = p.petid and p.petid = ?";
		jdbcUtil.setSqlAndParameters(sql, new Object[] { petId });

		try {
			ResultSet rs = jdbcUtil.executeQuery();
			List<Task> taskList = new ArrayList<Task>();
			while (rs.next()) {
				Task task = new Task(rs.getInt("taskid"), rs.getString("name"), rs.getString("startdate"),
						rs.getString("enddate"), rs.getString("place"), rs.getInt("hour"), rs.getInt("minute"),
						rs.getString("memo"), rs.getInt("petId"));
				taskList.add(task);
				System.out.println("TaskDao의 findTaskbyPetId에서 받은 taskId : " + rs.getInt("taskid"));
			}
			return taskList;
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();
		}
		return null;
	}

	/**
	 * 주어진 taskID에 해당하는 task 삭제.
	 */
	public int remove(int task_id) throws SQLException {
		String sql = "DELETE FROM Task WHERE taskId=?";
		jdbcUtil.setSqlAndParameters(sql, new Object[] { task_id });

		try {
			int result = jdbcUtil.executeUpdate();
			return result;
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		} finally {
			jdbcUtil.commit();
			jdbcUtil.close();
		}
		return 0;
	}

	/**
	 * 주어진 taskID에 해당하는 커뮤니티 정보를 데이터베이스에서 찾아 Task 도메인 클래스에 저장하여 반환. 상세페이지에서 보여줄 리스트
	 */
	public List<Task> findTaskList(String userId, int petId, String date) throws SQLException {
		String sql = "select t.taskId as taskId, t.name as name, t.startdate as startdate, t.enddate as enddate, t.place as place, t.hour as hour, t.minute as minute, t.memo as memo, t.petId as petId "
				+ "from pet p, task t, userInfo u " + "where p.petid = t.petid and u.userid = p.userid "
				+ "and u.userid = ? and t.petid = ? " + "and TO_DATE(?, 'YYYYMMDD') <= TO_DATE(t.enddate, 'YYYYMMDD') "
				+ "and TO_DATE(?, 'YYYYMMDD') >= TO_DATE(t.startdate, 'YYYYMMDD')";
		Object[] param = new Object[] { userId, petId, date, date };
		jdbcUtil.setSqlAndParameters(sql, param);

		try {
			ResultSet rs = jdbcUtil.executeQuery();
			List<Task> taskList = new ArrayList<Task>();
			while (rs.next()) {
				Task task = new Task(rs.getInt("taskId"), rs.getString("name"), rs.getString("startdate"),
						rs.getString("enddate"), rs.getString("place"), rs.getInt("hour"), rs.getInt("minute"),
						rs.getString("memo"), rs.getInt("petId"));
				taskList.add(task);
			}
			return taskList;

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();
		}
		return null;
	}

	public List<Task> findTaskList(int year, int month, int day) throws SQLException {
		String sql = "SELECT taskId, Name, startdate, enddate, place, hour, minute, memo, petId" + "FROM Task";
		jdbcUtil.setSqlAndParameters(sql, null);

		try {
			ResultSet rs = jdbcUtil.executeQuery();
			List<Task> taskList = new ArrayList<Task>();
			while (rs.next()) {
				Task task = new Task(rs.getInt("taskId"), rs.getString("Name"), rs.getString("startdate"),
						rs.getString("enddate"), rs.getString("place"), rs.getInt("hour"), rs.getInt("minute"),
						rs.getString("memo"), rs.getInt("petId"));
				taskList.add(task);
			}
			return taskList;

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();
		}
		return null;
	}

	public List<Task> findAllTask(String userId, String startDate, String endDate) throws SQLException {
		String sql = "select t.taskId as taskId, t.name as name, t.startdate as startdate, t.enddate as enddate, t.place as place, t.hour as hour, t.minute as minute, t.memo as memo, t.petId as petId "
				+ "from pet p, task t, userInfo u " + "where p.petid = t.petid and u.userid = p.userid "
				+ "and u.userid = ? " + "and TO_DATE(?, 'YYYYMMDD') <= TO_DATE(t.enddate, 'YYYYMMDD') "
				+ "and TO_DATE(?, 'YYYYMMDD') >= TO_DATE(t.startdate, 'YYYYMMDD')";

		Object[] param = new Object[] { userId, startDate, endDate };
		jdbcUtil.setSqlAndParameters(sql, param);
		System.out.println("taskDao의 findAllTask 의 userId: " + userId);
		System.out.println("taskDao의 findAllTask 의 startDate: " + startDate);
		System.out.println("taskDao의 findAllTask 의 enddate: " + endDate);
		try {
			ResultSet rs = jdbcUtil.executeQuery();
			List<Task> taskList = new ArrayList<Task>();
			while (rs.next()) {
				Task task = new Task(rs.getInt("taskId"), rs.getString("name"), rs.getString("startdate"),
						rs.getString("enddate"), rs.getString("place"), rs.getInt("hour"), rs.getInt("minute"),
						rs.getString("memo"), rs.getInt("petId"));
				taskList.add(task);
				System.out.println("taskDao의 findAllTask : " + rs.getInt("taskId"));
			}

			return taskList;

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();
		}
		return null;
	}

}
