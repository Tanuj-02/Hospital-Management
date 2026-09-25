package hospitalManagement.model;

import hospitalManagement.enums.Gender;

public class Patient extends Person{
	
	private String disease;

	public Patient(int id, String name, int age, Gender gender, String diesease) {
		super(id, name, age, gender);
		this.disease = diesease;
	}

	@Override
	public void displayDetails() {
		System.out.println("Patient Id: " + getId());
		System.out.println("Patient Name: " + getName());
		System.out.println("Patient Age: " + getAge());
		System.out.println("Patient Gender: " + getGender());
		System.out.println("Patient Disease: " + disease);
	}
}