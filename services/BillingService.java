package services;

import java.util.ArrayList;

import exceptions.PaymentException;
import model.*;

public class BillingService {


    private ArrayList<Bill> bills;


    public BillingService(){

        bills = new ArrayList<>();

    }


    public void generateBill(Bill bill){

        bills.add(bill);

        System.out.println(
        "Bill generated successfully");

    }


    public void showAllBills(){

        for(Bill bill : bills){

            bill.displayBill();

        }

    }
    public Bill getBillById(int billId) {

    for (Bill bill : bills) {

        if (bill.getBillId() == billId) {
            return bill;
        }
    }

    return null;
}


    public void payBill(Bill bill)
throws PaymentException{


    if(bill.isPaid()){

        throw new PaymentException(
        "Bill is already paid");

    }


    bill.makePayment();

    System.out.println(
    "Payment successful");

}

}
