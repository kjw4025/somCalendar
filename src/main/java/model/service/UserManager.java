package model.service;

import java.sql.SQLException;
import java.util.List;

import model.User;
import model.dao.UserDAO;

public class UserManager {
	private static UserManager userMan = new UserManager();
	private UserDAO userDAO;

	private UserManager() {
		try {
			userDAO = new UserDAO();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static UserManager getInstance() {
		return userMan;
	}

	public int create(User user) throws SQLException, ExistingException {
		if (userDAO.existingUser(user.getUserId()) == true) {
			throw new ExistingException(user.getUserId() + "�� �����ϴ� ���̵��Դϴ�.");
		}
		if (userDAO.existingUser(user.getMainUser()) == false) {
			if (user.getMainUser().equals(user.getUserId()))
				return userDAO.create(user);
			throw new ExistingException(user.getMainUser() + "�� �������� �ʴ� ���̵��Դϴ�.");
		}
		return userDAO.create(user);
	}

	public int update(User user) throws SQLException, UserNotFoundException {
		return userDAO.update(user);
	}

	public int remove(String userId) throws SQLException, UserNotFoundException {
		return userDAO.remove(userId);
	}

	public int removeShare(String userId, String currentId) throws SQLException, UserNotFoundException {
		System.out.println("UserManager�� removeShare ����");

		if (userDAO.isMain(userId, currentId)) {
			System.out.println("if�� ����");
			return userDAO.removeShare(userId);
		}

		return 0;
	}

	public String findpassword(String userId) throws SQLException, UserNotFoundException {
		String password = userDAO.findpassword(userId);

		if (userId == null) {
			throw new UserNotFoundException(userId + "�� �������� �ʴ� ���̵��Դϴ�.");
		}
		return password;
	}

	public String findmainUser(String userId) throws SQLException, UserNotFoundException {
		String mainUser = userDAO.findmainUser(userId);

		if (userId == null) {
			throw new UserNotFoundException(userId + "�� �������� �ʴ� ���̵��Դϴ�.");
		}
		return mainUser;
	}

	public User findUser(String userId) throws SQLException, UserNotFoundException {
		User user = userDAO.findUser(userId);

		if (user == null) {
			throw new UserNotFoundException(userId + "�� �������� �ʴ� ���̵��Դϴ�.");
		}
		return user;
	}

	public boolean checkUser(String userId) throws SQLException, UserNotFoundException, ExistingException {
		if (userDAO.existingUser(userId) == true) {
			throw new ExistingException(userId + "�� �����ϴ� ���̵��Դϴ�.");
		}
		if (userDAO.existingUser(userId) == false) {
			throw new ExistingException(userId + "�� ��밡���� ���̵��Դϴ�.");
		}
		return true;
	}

	public boolean login(String userId, String password)
			throws SQLException, UserNotFoundException, PasswordMismatchException {
		User user = findUser(userId);

		if (!user.matchPassword(password)) {
			throw new PasswordMismatchException("��й�ȣ�� ��ġ���� �ʽ��ϴ�.");
		}
		return true;
	}

	public boolean isPet(String userId) throws SQLException, UserNotFoundException {
		System.out.println("isPet �����");
		boolean petid = userDAO.existingPet(userId);
		System.out.println("petid: " + petid);
		return petid;
	}

	public List<String> findShareUser(String userId) throws SQLException, UserNotFoundException {
		List<String> share = userDAO.findShareUser(userId);

		if (share == null) {
			throw new UserNotFoundException(userId + "�� ��ϵ� ���� ������ �������� �ʽ��ϴ�.");
		}
		return share;
	}

	public UserDAO getUserDAO() {
		return this.userDAO;
	}

}
