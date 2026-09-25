package hospitalManagement.model;

import hospitalManagement.enums.Gender;

public class Doctor extends Person{
	
	private String specitality;

	public Doctor(int id, String name, int age, Gender gender, String specitality) {
		super(id, name, age, gender);
		this.specitality = specitality;
	}

	@Override
	public void displayDetails() {
		System.out.println("Doctor Id: " + getId());
		System.out.println("Doctor Name: " + getName());
		System.out.println("Doctor Age: " + getAge());
		System.out.println("Doctor Gender: " + getGender());
		System.out.println("Doctor Disease: " + specitality);
	}
}
