# Hospital Management System

A console-based Hospital Management System developed using Java. The application manages patient records, doctor information, and appointments while demonstrating core Object-Oriented Programming (OOP) concepts, including abstract classes, interfaces, inheritance, and custom exception handling.

## System Architecture

The architecture of the Hospital Management System is illustrated in the diagram below.

🔗 **[View Hospital Management System Architecture on Excalidraw](https://excalidraw.com/#room=d519fcc169461035825b,oKd82eFVuc_hltip0szBXg)**

Join the Excalidraw room to view and collaborate on the system architecture.

## Features

### 1. Patient Management
- Register new patients with their ID, name, age, gender, and disease.
- Search patients by their ID.
- Search patients by name.
- Delete existing patient records.
- Categorize patients based on their disease or treatment type.
- Prevent duplicate patient records.

### 2. Doctor Management
- Search doctors by their ID.
- Search doctors based on their medical specialization.
- View all available doctors.
- Display doctor details, including their specialization.

**Supported Specializations:**
- Orthopedic
- Dermatologist
- Cardiologist
- Neurologist
- Rheumatologist

### 3. Appointment Management
- Create appointments for registered patients.
- Associate appointments with doctors.
- Schedule appointments using a specified date and time.
- Search appointments using their appointment ID.
- Update appointment status.
- Cancel scheduled appointments.
- Mark appointments as completed.
- View the complete appointment history of a patient.

**Appointment Statuses:**
- `SCHEDULED`
- `CANCELLED`
- `COMPLETED`

### 4. Exception Handling
- Custom exceptions for invalid appointment operations.
- Custom exceptions for duplicate records.
- Handle invalid date and time formats.
- Validate user input and handle invalid menu choices.
- Display appropriate error messages when records are not found.

## Technologies Used

- **Programming Language:** Java
- **Date and Time API:** `java.time`
- **Collections Framework:** Java Collections
- **Input Handling:** `java.util.Scanner`
- **OOP Concepts:** Abstraction, Inheritance, Encapsulation, and Polymorphism
- **Exception Handling:** Custom and built-in exceptions

## Project Structure

```text
Hospital-Management-System/
│
├── Hospital.java
│
├── model/
│   ├── Patient.java
│   ├── Doctor.java
│   └── Appointment.java
│
├── services/
│   ├── PatientService.java
│   ├── DoctorService.java
│   └── AppointmentService.java
│
├── enums/
│   ├── Gender.java
│   └── Status.java
│
└── exceptions/
    ├── HospitalException.java
    └── InvalidAppointmentException.java
