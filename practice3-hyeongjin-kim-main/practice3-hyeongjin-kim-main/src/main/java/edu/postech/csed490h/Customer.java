package edu.postech.csed490h;

import java.util.Enumeration;
import java.util.Vector;

class Customer extends DomainObject
{
    public Customer(String customer_name) {
        _name = customer_name;
    }
    public String Compute_Total_Amount() {
        double totalAmount = 0;
        int frequentRenterPoints = 0;
        Enumeration rentals = _rentals.elements();
        String anouncement = "Rental Record for " + name() + "\n";
        while (rentals.hasMoreElements()) {
            Rental each = (Rental) rentals.nextElement();

            //determine amounts for each line
            double thisAmount = Compute_Amount(each);
            totalAmount += thisAmount;

            frequentRenterPoints += compute_rental_point(each);

            //show figures for this rental
            anouncement += "\t" + each.tape().movie().name()+ "\t" + String.valueOf(thisAmount) + "\n";

        }
        //add footer lines
        anouncement +=  "Amount owed is " + String.valueOf(totalAmount) + "\n";
        anouncement += "You earned " + String.valueOf(frequentRenterPoints) + " frequent renter points";
        return anouncement;

    }
    public void addRental(Rental arg) {
        _rentals.addElement(arg);
    }
    public static Customer get(String name) {
        return (Customer) Registrar.get("Customers", name);
    }
    public void persist() {
        Registrar.add("Customers", this);
    }
    private Vector _rentals = new Vector();

    //determine amounts for each line
    private double Compute_Amount(Rental each){
        double Amount = 0;
        switch (each.tape().movie().priceCode()) {
            case Movie.REGULAR:
                Amount += 2;
                if (each.daysRented() > 2)
                    Amount += (each.daysRented() - 2) * 1.5;
                break;
            case Movie.NEW_RELEASE:
                Amount += each.daysRented() * 3;
                break;
            case Movie.CHILDRENS:
                Amount += 1.5;
                if (each.daysRented() > 3)
                    Amount += (each.daysRented() - 3) * 1.5;
                break;
        }
        return Amount;
    }

    // add frequent renter points
    private int compute_rental_point(Rental each){
        int RenterPoints = 1;
        // add bonus for a two day new release rental
        if ((each.tape().movie().priceCode() == Movie.NEW_RELEASE) && each.daysRented() > 1) RenterPoints ++;
        return RenterPoints;
    }

}