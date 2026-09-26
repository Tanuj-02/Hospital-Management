package hospitalManagement.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import hospitalManagement.model.Patient;

public class PatientService {
	private HashMap<Integer, Patient> patients = new HashMap<>();
	
	public void addPatient(Patient patient) {
		patients.put(patient.getId(), patient);
		System.out.println("Patient added successfully");
	}
	
	public List<Patient> searchPatient(String name) {
		List<Patient> list = new ArrayList<>();
		
		for(Patient patient : patients.values()) {
			if(patient.getName().equalsIgnoreCase(name)){
				list.add(patient);
			}
		}
		
		return list;
	}
	
	public Patient searchPatient(int id) {
		return patients.get(id);
	}
	
	public void displayDetails(int id) {
		Patient patient = patients.get(id);
		
		patient.displayDetails();
	}
	
	public void deletePatient(int id) {
		patients.remove(id);
		System.out.println("Patient Deleted successfully");
	}
}
