package hospitalManagement.services;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import hospitalManagement.enums.Status;
import hospitalManagement.model.Appointment;
import hospitalManagement.model.Doctor;
import hospitalManagement.model.Patient;

public class AppointmentService {
	
	DoctorService doctorService = new DoctorService();
	Random random = new Random();
	
	private HashMap<Integer, Appointment> map = new HashMap<>();
	
	private HashMap<Integer, List<Appointment>> patientAppointments = new HashMap<>();
	
	public void createAppointment(Patient patient, LocalDateTime time) {
		String disease = patient.getDisease();
		
		String specialty = null;
		
		switch (disease) {
		case "Joint Pain", "Fracture":
			specialty = "Orthopedic";
			break;
		case "Acne", "Skin Allergy":
			specialty = "Dermatologist";
			break;
		case "Heart":
			specialty = "Cardiologist";
			break;
		case "Migraine":
			specialty = "Neurologist";
			break;
		case "Inflamation":
			specialty = "Rheumatologist";
			break;
		default:
			break;
		}
		
		List<Doctor> doctors = doctorService.searchDoctor(specialty);
		
		Doctor doctor = doctors.get(0);
		
		int id = random.nextInt();
		Appointment appointment = new Appointment(id, patient, doctor, time, Status.SCHEDULED);
		
		map.put(id, appointment);
		
		if(patientAppointments.containsKey(patient.getId())) {
			patientAppointments.get(patient.getId()).add(appointment);
		} else {
			List<Appointment> list = new ArrayList<>();
			list.add(appointment);
			patientAppointments.put(patient.getId(), list);
		}
	}
	
	public void updateStatus(int id, Status status) {
		if(!map.containsKey(id)) {
			System.out.println("No appointment present with given id");
			return;
		}
		Appointment appointment = map.get(id);
		appointment.setStatus(status);
		map.put(id, appointment);
		int patientId = appointment.getPatient().getId();
		
		List<Appointment> appointments = patientAppointments.get(patientId);
		
		for(Appointment appointment2 : appointments) {
			if(appointment2.getId() == id) {
				appointment2.setStatus(status);
			}
		}
	}
	
	public Appointment getAppointmentById(int id) {
		return map.get(id);
	}
	
	public List<Appointment> getPatientAppointments(int patientId) {
	    return patientAppointments.getOrDefault(patientId, new ArrayList<>());
	}
}
