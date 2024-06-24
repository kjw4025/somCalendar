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
			throw new ExistingException(user.getUserId() + "는 존재하는 아이디입니다.");
		}
		if (userDAO.existingUser(user.getMainUser()) == false) {
			if (user.getMainUser().equals(user.getUserId()))
				return userDAO.create(user);
			throw new ExistingException(user.getMainUser() + "는 존재하지 않는 아이디입니다.");
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
		System.out.println("UserManager의 removeShare 들어옴");

		if (userDAO.isMain(userId, currentId)) {
			System.out.println("if문 들어옴");
			return userDAO.removeShare(userId);
		}

		return 0;
	}

	public String findpassword(String userId) throws SQLException, UserNotFoundException {
		String password = userDAO.findpassword(userId);

		if (userId == null) {
			throw new UserNotFoundException(userId + "는 존재하지 않는 아이디입니다.");
		}
		return password;
	}

	public String findmainUser(String userId) throws SQLException, UserNotFoundException {
		String mainUser = userDAO.findmainUser(userId);

		if (userId == null) {
			throw new UserNotFoundException(userId + "는 존재하지 않는 아이디입니다.");
		}
		return mainUser;
	}

	public User findUser(String userId) throws SQLException, UserNotFoundException {
		User user = userDAO.findUser(userId);

		if (user == null) {
			throw new UserNotFoundException(userId + "는 존재하지 않는 아이디입니다.");
		}
		return user;
	}

	public boolean checkUser(String userId) throws SQLException, UserNotFoundException, ExistingException {
		if (userDAO.existingUser(userId) == true) {
			throw new ExistingException(userId + "는 존재하는 아이디입니다.");
		}
		if (userDAO.existingUser(userId) == false) {
			throw new ExistingException(userId + "는 사용가능한 아이디입니다.");
		}
		return true;
	}

	public boolean login(String userId, String password)
			throws SQLException, UserNotFoundException, PasswordMismatchException {
		User user = findUser(userId);

		if (!user.matchPassword(password)) {
			throw new PasswordMismatchException("비밀번호가 일치하지 않습니다.");
		}
		return true;
	}

	public boolean isPet(String userId) throws SQLException, UserNotFoundException {
		System.out.println("isPet 실행됨");
		boolean petid = userDAO.existingPet(userId);
		System.out.println("petid: " + petid);
		return petid;
	}

	public List<String> findShareUser(String userId) throws SQLException, UserNotFoundException {
		List<String> share = userDAO.findShareUser(userId);

		if (share == null) {
			throw new UserNotFoundException(userId + "에 등록된 메인 유저가 존재하지 않습니다.");
		}
		return share;
	}

	public UserDAO getUserDAO() {
		return this.userDAO;
	}

}
