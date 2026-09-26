package hospitalManagement;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;

import hospitalManagement.enums.Gender;
import hospitalManagement.enums.Status;
import hospitalManagement.model.Appointment;
import hospitalManagement.model.Doctor;
import hospitalManagement.model.Patient;
import hospitalManagement.services.AppointmentService;
import hospitalManagement.services.DoctorService;
import hospitalManagement.services.PatientService;

public class Hospital {

    private static final Scanner sc = new Scanner(System.in);

    private static final DoctorService doctorService = new DoctorService();
    private static final PatientService patientService = new PatientService();
    private static final AppointmentService appointmentService = new AppointmentService();

    private static final DateTimeFormatter formatter =
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public static void main(String[] args) {

        int choice;

        do {
            System.out.println("\n===== HOSPITAL MANAGEMENT SYSTEM =====");
            System.out.println("1. Book Appointment");
            System.out.println("2. Patient Management");
            System.out.println("3. Doctor Management");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");

            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    bookAppointment();
                    break;
                case 2:
                    patientMenu();
                    break;
                case 3:
                    doctorMenu();
                    break;
                case 4:
                    System.out.println("Exiting Hospital Management System.");
                    break;
                default:
                    System.out.println("Invalid choice.");
                    break;
            }

        } while (choice != 4);
    }

    private static void bookAppointment() {

        int choice;

        do {
            System.out.println("\n===== APPOINTMENT MENU =====");
            System.out.println("1. Create Appointment");
            System.out.println("2. Update Appointment Status");
            System.out.println("3. Search Appointment");
            System.out.println("4. View Patient Appointment History");
            System.out.println("5. Back");
            System.out.print("Enter your choice: ");

            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    createAppointment();
                    break;
                case 2:
                    updateAppointmentStatus();
                    break;
                case 3:
                    searchAppointment();
                    break;
                case 4:
                    viewPatientAppointmentHistory();
                    break;
                case 5:
                    break;
                default:
                    System.out.println("Invalid choice.");
                    break;
            }

        } while (choice != 5);
    }

    private static void createAppointment() {

        System.out.println("\n===== CREATE APPOINTMENT =====");

        System.out.print("Enter Patient ID: ");
        int patientId = sc.nextInt();
        sc.nextLine();

        Patient patient = patientService.searchPatient(patientId);

        if (patient == null) {
            System.out.println("Patient not found. Please register the patient first.");
            return;
        }

        System.out.print(
                "Enter appointment date and time (yyyy-MM-dd HH:mm): ");
        String input = sc.nextLine();

        LocalDateTime appointmentTime;

        try {
            appointmentTime = LocalDateTime.parse(input, formatter);
        } catch (DateTimeParseException e) {
            System.out.println("Invalid date format.");
            return;
        }

        if (appointmentTime.isBefore(LocalDateTime.now())) {
            System.out.println("Appointment date and time cannot be in the past.");
            return;
        }

        if (!isValidDisease(patient.getDisease())) {
            System.out.println(
                    "No matching doctor found for the patient's disease.");
            return;
        }

        appointmentService.createAppointment(patient, appointmentTime);

        System.out.println("Appointment created successfully.");
        System.out.println("Patient: " + patient.getName());
        System.out.println("Date and Time: "
                + appointmentTime.format(formatter));
    }

    private static boolean isValidDisease(String disease) {

        switch (disease) {
            case "Joint Pain":
            case "Fracture":
            case "Acne":
            case "Skin Allergy":
            case "Heart":
            case "Migraine":
            case "Inflamation":
                return true;
            default:
                return false;
        }
    }

    private static void updateAppointmentStatus() {

        System.out.println("\n===== UPDATE APPOINTMENT STATUS =====");

        System.out.print("Enter Appointment ID: ");
        int appointmentId = sc.nextInt();
        sc.nextLine();

        Appointment appointment =
                appointmentService.getAppointmentById(appointmentId);

        if (appointment == null) {
            System.out.println("Appointment not found.");
            return;
        }

        System.out.println("1. Scheduled");
        System.out.println("2. Cancelled");
        System.out.println("3. Completed");
        System.out.print("Select new status: ");

        int choice = sc.nextInt();
        sc.nextLine();

        Status status;

        switch (choice) {
            case 1:
                status = Status.SCHEDULED;
                break;
            case 2:
                status = Status.CANCELLED;
                break;
            case 3:
                status = Status.COMPLETED;
                break;
            default:
                System.out.println("Invalid status choice.");
                return;
        }

        appointmentService.updateStatus(appointmentId, status);

        System.out.println("Appointment status updated successfully.");
    }

    private static void searchAppointment() {

        System.out.println("\n===== SEARCH APPOINTMENT =====");

        System.out.print("Enter Appointment ID: ");
        int appointmentId = sc.nextInt();
        sc.nextLine();

        Appointment appointment =
                appointmentService.getAppointmentById(appointmentId);

        if (appointment == null) {
            System.out.println("Appointment not found.");
            return;
        }

        displayAppointment(appointment);
    }

    private static void viewPatientAppointmentHistory() {

        System.out.println("\n===== PATIENT APPOINTMENT HISTORY =====");

        System.out.print("Enter Patient ID: ");
        int patientId = sc.nextInt();
        sc.nextLine();

        Patient patient = patientService.searchPatient(patientId);

        if (patient == null) {
            System.out.println("Patient not found.");
            return;
        }

        List<Appointment> appointments =
                appointmentService.getPatientAppointments(patientId);

        if (appointments.isEmpty()) {
            System.out.println("No appointments found for this patient.");
            return;
        }

        System.out.println("\nPatient Name: " + patient.getName());
        System.out.println("Patient ID: " + patient.getId());
        System.out.println("\n===== APPOINTMENT HISTORY =====");

        for (Appointment appointment : appointments) {
            displayAppointment(appointment);
            System.out.println("------------------------------");
        }
    }

    private static void displayAppointment(Appointment appointment) {

        System.out.println("Appointment ID: " + appointment.getId());
        System.out.println("Patient: "
                + appointment.getPatient().getName());
        System.out.println("Doctor: "
                + appointment.getDoctor().getName());
        System.out.println("Specialty: "
                + appointment.getDoctor().getSpecialty());
        System.out.println("Date and Time: "
                + appointment.getTime().format(formatter));
        System.out.println("Status: " + appointment.getStatus());
    }

    private static void patientMenu() {

        int choice;

        do {
            System.out.println("\n===== PATIENT MANAGEMENT =====");
            System.out.println("1. Search Patient by ID");
            System.out.println("2. Search Patient by Name");
            System.out.println("3. Register Patient");
            System.out.println("4. Delete Patient");
            System.out.println("5. View Patient Appointment History");
            System.out.println("6. Back");
            System.out.print("Enter your choice: ");

            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    searchPatientById();
                    break;
                case 2:
                    searchPatientByName();
                    break;
                case 3:
                    registerPatient();
                    break;
                case 4:
                    deletePatient();
                    break;
                case 5:
                    viewPatientAppointmentHistory();
                    break;
                case 6:
                    break;
                default:
                    System.out.println("Invalid choice.");
                    break;
            }

        } while (choice != 6);
    }

    private static void registerPatient() {

        System.out.println("\n===== REGISTER PATIENT =====");

        System.out.print("Enter Patient ID: ");
        int id = sc.nextInt();
        sc.nextLine();

        if (patientService.searchPatient(id) != null) {
            System.out.println("Patient ID already exists.");
            return;
        }

        System.out.print("Enter Patient Name: ");
        String name = sc.nextLine();

        System.out.print("Enter Patient Age: ");
        int age = sc.nextInt();
        sc.nextLine();

        Gender gender = selectGender();

        if (gender == null) {
            return;
        }

        String disease = selectDisease();

        if (disease == null) {
            return;
        }

        Patient patient =
                new Patient(id, name, age, gender, disease);

        patientService.addPatient(patient);
    }

    private static Gender selectGender() {

        System.out.println("\n===== SELECT GENDER =====");
        System.out.println("1. Male");
        System.out.println("2. Female");
        System.out.print("Enter your choice: ");

        int choice = sc.nextInt();
        sc.nextLine();

        switch (choice) {
            case 1:
                return Gender.MALE;
            case 2:
                return Gender.FEMALE;
            default:
                System.out.println("Invalid gender choice.");
                return null;
        }
    }

    private static String selectDisease() {

        System.out.println("\n===== SELECT DISEASE =====");
        System.out.println("1. Joint Pain");
        System.out.println("2. Fracture");
        System.out.println("3. Acne");
        System.out.println("4. Skin Allergy");
        System.out.println("5. Heart");
        System.out.println("6. Migraine");
        System.out.println("7. Inflamation");
        System.out.print("Enter your choice: ");

        int choice = sc.nextInt();
        sc.nextLine();

        switch (choice) {
            case 1:
                return "Joint Pain";
            case 2:
                return "Fracture";
            case 3:
                return "Acne";
            case 4:
                return "Skin Allergy";
            case 5:
                return "Heart";
            case 6:
                return "Migraine";
            case 7:
                return "Inflamation";
            default:
                System.out.println("Invalid disease choice.");
                return null;
        }
    }

    private static void searchPatientById() {

        System.out.println("\n===== SEARCH PATIENT BY ID =====");

        System.out.print("Enter Patient ID: ");
        int id = sc.nextInt();
        sc.nextLine();

        Patient patient = patientService.searchPatient(id);

        if (patient == null) {
            System.out.println("Patient not found.");
            return;
        }

        patient.displayDetails();
    }

    private static void searchPatientByName() {

        System.out.println("\n===== SEARCH PATIENT BY NAME =====");

        System.out.print("Enter Patient Name: ");
        String name = sc.nextLine();

        List<Patient> patients =
                patientService.searchPatient(name);

        if (patients.isEmpty()) {
            System.out.println("No patient found with this name.");
            return;
        }

        for (Patient patient : patients) {
            patient.displayDetails();
            System.out.println("------------------------------");
        }
    }

    private static void deletePatient() {

        System.out.println("\n===== DELETE PATIENT =====");

        System.out.print("Enter Patient ID: ");
        int id = sc.nextInt();
        sc.nextLine();

        Patient patient = patientService.searchPatient(id);

        if (patient == null) {
            System.out.println("Patient not found.");
            return;
        }

        patientService.deletePatient(id);
    }

    private static void doctorMenu() {

        int choice;

        do {
            System.out.println("\n===== DOCTOR MANAGEMENT =====");
            System.out.println("1. Search Doctor by ID");
            System.out.println("2. Search Doctor by Specialty");
            System.out.println("3. View All Doctors");
            System.out.println("4. Back");
            System.out.print("Enter your choice: ");

            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    searchDoctorById();
                    break;
                case 2:
                    searchDoctorBySpecialty();
                    break;
                case 3:
                    viewAllDoctors();
                    break;
                case 4:
                    break;
                default:
                    System.out.println("Invalid choice.");
                    break;
            }

        } while (choice != 4);
    }

    private static void searchDoctorById() {

        System.out.println("\n===== SEARCH DOCTOR BY ID =====");

        System.out.print("Enter Doctor ID: ");
        int id = sc.nextInt();
        sc.nextLine();

        Doctor doctor = doctorService.getById(id);

        if (doctor == null) {
            System.out.println("Doctor not found.");
            return;
        }

        doctor.displayDetails();
    }

    private static void searchDoctorBySpecialty() {

        System.out.println("\n===== SEARCH DOCTOR BY SPECIALTY =====");

        System.out.println("1. Orthopedic");
        System.out.println("2. Dermatologist");
        System.out.println("3. Cardiologist");
        System.out.println("4. Neurologist");
        System.out.println("5. Rheumatologist");
        System.out.print("Select specialty: ");

        int choice = sc.nextInt();
        sc.nextLine();

        String specialty;

        switch (choice) {
            case 1:
                specialty = "Orthopedic";
                break;
            case 2:
                specialty = "Dermatologist";
                break;
            case 3:
                specialty = "Cardiologist";
                break;
            case 4:
                specialty = "Neurologist";
                break;
            case 5:
                specialty = "Rheumatologist";
                break;
            default:
                System.out.println("Invalid specialty choice.");
                return;
        }

        List<Doctor> doctors =
                doctorService.searchDoctor(specialty);

        if (doctors.isEmpty()) {
            System.out.println("No doctor found for this specialty.");
            return;
        }

        for (Doctor doctor : doctors) {
            doctor.displayDetails();
            System.out.println("------------------------------");
        }
    }

    private static void viewAllDoctors() {

        System.out.println("\n===== ALL DOCTORS =====");

        for (int id = 101; id <= 105; id++) {
            Doctor doctor = doctorService.getById(id);

            if (doctor != null) {
                doctor.displayDetails();
                System.out.println("------------------------------");
            }
        }
    }
}