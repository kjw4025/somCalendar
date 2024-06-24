package model.service;

import java.sql.SQLException;
import java.util.List;

import model.Pet;
import model.dao.PetDAO;

public class PetManager {
	private static PetManager petMan = new PetManager();
	private PetDAO petDAO;

	private PetManager() {
		try {
			petDAO = new PetDAO();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static PetManager getInstance() {
		return petMan;
	}

	public int create(Pet pet) throws SQLException, ExistingException {
		if (petDAO.existingPet(pet.getPetId()) == true) {
			throw new ExistingException(pet.getPetId() + "�� ��ϵ� �ݷ������Դϴ�.");
		}
		return petDAO.create(pet);
	}

	public int update(Pet pet) throws SQLException, UserNotFoundException {
		return petDAO.update(pet);
	}

	public int remove(int petId) throws SQLException, UserNotFoundException {
		return petDAO.remove(petId);
	}

	public Pet findPet(int petId) throws SQLException, UserNotFoundException {
		Pet pet = petDAO.findPet(petId);

		if (pet == null) {
			throw new UserNotFoundException(petId + "�� ��ϵ��� ���� �ݷ������Դϴ�.");
		}
		return pet;
	}

	public List<Pet> findPetbyUserId(String mainUser) throws SQLException, UserNotFoundException {
		List<Pet> pet = petDAO.findPetbyUserId(mainUser);

		if (pet == null) {
			throw new UserNotFoundException(mainUser + "�� ��ϵ� �ݷ������� �������� �ʽ��ϴ�.");
		}
		return pet;
	}

	public PetDAO getPetDAO() {
		return this.petDAO;
	}

	public String findPetName(int petId) throws SQLException, UserNotFoundException {
		String pet = petDAO.findPetName(petId);

		if (pet == null) {
			throw new UserNotFoundException(petId + "�� ���� �ݷ������Դϴ�. ");
		}
		return pet;
	}
}
