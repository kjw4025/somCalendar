package model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.User;

public class UserDAO {
	private JDBCUtil jdbcUtil = null;

	public UserDAO() {
		jdbcUtil = new JDBCUtil();
	}

	public int create(User user) throws SQLException {
		String sql = "INSERT INTO USERINFO VALUES (?, ?, ?, ?, ?, ?)";
		Object[] param = new Object[] { user.getUserId(), user.getPassword(), user.getName(), user.getEmail(),
				user.getPhone(), user.getMainUser() };
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

	public int update(User user) throws SQLException {
		String sql = "UPDATE USERINFO " + "SET password=?, name=?, email=?, phone=? " + "WHERE userId=?";
		Object[] param = new Object[] { user.getPassword(), user.getName(), user.getEmail(), user.getPhone(),
				user.getUserId() };
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

	public int remove(String userId) throws SQLException {
		String sql = "DELETE FROM USERINFO WHERE userid=?";
		jdbcUtil.setSqlAndParameters(sql, new Object[] { userId });

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

	public boolean isMain(String userId, String currentId) throws SQLException {
		String sql = "SELECT mainUser FROM USERINFO WHERE userId=? ";
		jdbcUtil.setSqlAndParameters(sql, new Object[] { userId });

		String result;

		try {
			ResultSet rs = jdbcUtil.executeQuery();
			if (rs.next()) {
				result = rs.getString("mainUser");
				System.out.println("isMain 쿼리 결과 : " + result + ", " + "currentId : " + currentId);
				if (result.equals(currentId))
					return true;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();
		}
		return false;
	}

	public int removeShare(String userId) throws SQLException {
		String sql = "UPDATE USERINFO SET mainUser=? WHERE userId=? ";
		Object[] param = new Object[] { userId, userId };
		jdbcUtil.setSqlAndParameters(sql, param);

		try {
			int result = jdbcUtil.executeUpdate();
			System.out.print("removeShare 결과: " + result);
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

	public String findpassword(String userId) throws SQLException {
		String sql = "SELECT password " + "FROM USERINFO " + "WHERE userId=? ";
		jdbcUtil.setSqlAndParameters(sql, new Object[] { userId });
		User user = null;
		try {
			ResultSet rs = jdbcUtil.executeQuery();
			if (rs.next()) {
				rs.getString("password");
				System.out.println("userDao의 findUser에서 받은 password : " + rs.getString("password"));
				return rs.getString("password");
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();
		}
		return null;
	}

	public User findUser(String userId) throws SQLException {
		String sql = "SELECT password, name, email, phone, mainUser " + "FROM USERINFO " + "WHERE userId=? ";
		jdbcUtil.setSqlAndParameters(sql, new Object[] { userId });
		User user = null;
		try {
			ResultSet rs = jdbcUtil.executeQuery();
			if (rs.next()) {
				user = new User(userId, rs.getString("password"), rs.getString("name"), rs.getString("email"),
						rs.getString("phone"), rs.getString("mainUser"));
				System.out.println("userDao의 findUser에서 받은 password : " + rs.getString("password"));
				return user;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();
		}
		return null;
	}

	public List<String> findShareUser(String userId) throws SQLException {
		String sql = "SELECT userId FROM USERINFO WHERE mainUser = (SELECT u1.mainUser FROM USERINFO u1, USERINFO u2 WHERE u1.userId = u2.userId and u1.userId=?) ";
		jdbcUtil.setSqlAndParameters(sql, new Object[] { userId });

		List<String> shareList = new ArrayList<String>();

		try {
			ResultSet rs = jdbcUtil.executeQuery();
			while (rs.next()) {
				System.out.println("받아온 메인 유저" + rs.getString("userId"));
				shareList.add(rs.getString("userId"));
			}
			return shareList;
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();
		}

		return null;
	}

	public boolean existingUser(String userId) throws SQLException {
		String sql = "SELECT count(*) FROM USERINFO WHERE userid=?";
		jdbcUtil.setSqlAndParameters(sql, new Object[] { userId });

		try {
			ResultSet rs = jdbcUtil.executeQuery();
			if (rs.next()) {
				int count = rs.getInt(1);
				return (count == 1 ? true : false);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();
		}
		return false;
	}

	public boolean existingPet(String userId) throws SQLException {
		String sql = "SELECT p.petId FROM USERINFO u, PET p WHERE u.userId = p.userId and u.userId = ?";
		jdbcUtil.setSqlAndParameters(sql, new Object[] { userId });
		System.out.println("existingPet 실행중");
		try {
			ResultSet rs = jdbcUtil.executeQuery();
			if (rs.next()) {
				System.out.println("existingPet result: " + rs.getString("petId"));
				System.out.println("existing 결과:" + rs != null ? true : false);
				return (rs.getString("petId") != null ? true : false);
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();
		}
		return false;
	}

	public String findmainUser(String userId) throws SQLException {
		String sql = "SELECT mainUser " + "FROM USERINFO " + "WHERE userId=? ";
		jdbcUtil.setSqlAndParameters(sql, new Object[] { userId });
		User user = null;
		try {
			ResultSet rs = jdbcUtil.executeQuery();
			if (rs.next()) {
				rs.getString("mainUser");
				System.out.println("userDao의 findUser에서 받은 mainUser : " + rs.getString("mainUser"));
				return rs.getString("mainUser");
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();
		}
		return null;
	}

}
