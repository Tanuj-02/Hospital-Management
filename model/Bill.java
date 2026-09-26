package model;

public class Bill {

    private int billId;
    private Patient patient;
    private Doctor doctor;
    private double consultationFee;
    private boolean paid;


    public Bill(int billId, Patient patient, Doctor doctor, double consultationFee){

        this.billId = billId;
        this.patient = patient;
        this.doctor = doctor;
        this.consultationFee = consultationFee;
        this.paid = false;

    }


    public int getBillId(){
        return billId;
    }


    public Patient getPatient(){
        return patient;
    }


    public Doctor getDoctor(){
        return doctor;
    }


    public double getConsultationFee(){
        return consultationFee;
    }


    public boolean isPaid(){
        return paid;
    }


    public void makePayment(){

        paid = true;

    }


    public void displayBill(){

        System.out.println("--------- BILL ---------");
        System.out.println("Bill ID : " + billId);
        System.out.println("Patient : " + patient.getName());
        System.out.println("Doctor : " + doctor.getName());
        System.out.println("Amount : " + consultationFee);
        System.out.println("Status : " + 
                (paid ? "PAID" : "PENDING"));

    }

}
