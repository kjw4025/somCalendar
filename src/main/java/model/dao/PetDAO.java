package model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Pet;

public class PetDAO {
	private JDBCUtil jdbcUtil = null;

	public PetDAO() {
		jdbcUtil = new JDBCUtil();
	}

	public int create(Pet pet) throws SQLException {
		String sql = "INSERT INTO PET VALUES (sqpet.nextval, ?, ?, ?, ?, ?)";
		Object[] param = new Object[] { pet.getName(), pet.getWeight(), pet.getSex(), pet.getSpecies(),
				pet.getUserId() };
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

	public int update(Pet pet) throws SQLException {
		String sql = "UPDATE PET " + "SET name=?, weight=?, sex=?, species=?, userId=? " + "WHERE petId=?";

		Object[] param = new Object[] { pet.getName(), pet.getWeight(), pet.getSex(), pet.getSpecies(), pet.getUserId(),
				pet.getPetId() };
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

	public int remove(int petId) throws SQLException {
		String sql = "DELETE FROM PET WHERE petId=?";
		jdbcUtil.setSqlAndParameters(sql, new Object[] { petId });

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

	public Pet findPet(int petId) throws SQLException {
		String sql = "SELECT p.name, weight, sex, species, p.userId " + "FROM USERINFO u, PET p  "
				+ "WHERE u.userId = p.userId and p.petId = ?";
		jdbcUtil.setSqlAndParameters(sql, new Object[] { petId });
		Pet pet = null;
		try {
			ResultSet rs = jdbcUtil.executeQuery();
			if (rs.next()) {
				pet = new Pet(petId, rs.getString("name"), rs.getInt("weight"), rs.getString("sex"),
						rs.getString("species"), rs.getString("userId"));
				return pet;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();
		}
		return null;
	}

	public String findPetName(int petId) throws SQLException {
		String sql = "SELECT name " + "FROM PET " + "WHERE petId = ?";
		jdbcUtil.setSqlAndParameters(sql, new Object[] { petId });
		String petName = null;
		try {
			ResultSet rs = jdbcUtil.executeQuery();
			if (rs.next()) {
				petName = rs.getString("name");

				return petName;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();
		}
		return null;
	}

	public Pet findPetbyUser(String mainUser) throws SQLException {
		String sql = "SELECT p.petId as petId, p.name as name, p.weight as weight, p.sex as sex, p.species as species, p.userId as userId "
				+ "FROM USERINFO u, PET p  " + "WHERE u.mainUser = p.userId and u.mainUser = ?";
		jdbcUtil.setSqlAndParameters(sql, new Object[] { mainUser });
		Pet pet = null;
		try {
			ResultSet rs = jdbcUtil.executeQuery();
			while (rs.next()) {
				pet = new Pet(rs.getInt("petId"), rs.getString("name"), rs.getInt("weight"), rs.getString("sex"),
						rs.getString("species"), mainUser);
				System.out.println("PetDao의 findPetbyUserId에서 받은 petId : " + rs.getInt("petId"));
			}
			return pet;
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();
		}
		return null;
	}

	public List<Pet> findPetbyUserId(String userId) throws SQLException {
		String sql = "SELECT p.petId as petId, p.name as name, p.weight as weight, p.sex as sex, p.species as species, p.userId as userId "
				+ "FROM USERINFO u, PET p  " + "WHERE u.userId = p.userId and u.userId = ?";
		jdbcUtil.setSqlAndParameters(sql, new Object[] { userId });
		Pet pet = null;
		try {
			ResultSet rs = jdbcUtil.executeQuery();
			List<Pet> petList = new ArrayList<Pet>();
			while (rs.next()) {
				pet = new Pet(rs.getInt("petId"), rs.getString("name"), rs.getInt("weight"), rs.getString("sex"),
						rs.getString("species"), userId);
				petList.add(pet);
				System.out.println("PetDao의 findPetbyUserId에서 받은 petId : " + rs.getInt("petId"));
			}
			return petList;
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();
		}
		return null;
	}

	public boolean existingPet(int i) throws SQLException {
		String sql = "SELECT count(*) FROM PET WHERE petId=?";
		jdbcUtil.setSqlAndParameters(sql, new Object[] { i });

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

}
