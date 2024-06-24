package model;

import java.util.Date;
import java.util.List;

public class Pet {
	private int petId;
	private String name;
	private float weight;
	private String sex;
	private String species;
	private String userId;

	public Pet() {
	}

	public Pet(int petId, String name, float weight, String sex, String species, String userId) {
		this.petId = petId;
		this.name = name;
		this.weight = weight;
		this.sex = sex;
		this.species = species;
		this.userId = userId;
	}

	public Pet(String name, float weight, String sex, String species, String userId) {
		this.name = name;
		this.weight = weight;
		this.sex = sex;
		this.species = species;
		this.userId = userId;
	}

	public int getPetId() {
		return petId;
	}

	public void setPetId(int petId) {
		this.petId = petId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public float getWeight() {
		return weight;
	}

	public void setWeight(float weight) {
		this.weight = weight;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getSpecies() {
		return species;
	}

	public void setSpecies(String species) {
		this.species = species;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

}
