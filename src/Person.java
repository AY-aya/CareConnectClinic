
package com.mycompany.careconnectclinic;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;

public abstract class Person {
    private String name;
    private String password;
    private String email;
    
    public Person(){};
    public Person(String name, String password, String email) {
        this.name = name;
        this.password = password;
        this.email = email;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    public void view_schedule(String doctorID, Connection c){
        Schedule.viewSchedule(doctorID, c);
    }
    
    public void editReservationTypeFee(String type, int newFee, Connection connection){
        
        new Report(connection).editReservationTypeFee(type, newFee);
    }
     
   public static void generateMonthlyReport(ArrayList<Reservation> reservations, int month, int year,Connection connection) {
        HashMap<String, Integer> incomeMap = new Report(connection).prepareMonthlyReport(month, year);
        
        // Print the generated report
        System.out.println("Monthly Report for " + month + "/" + year + ":");
        for (HashMap.Entry<String, Integer> entry : incomeMap.entrySet()) {
            System.out.println("Reservation Type: " + entry.getKey() + ", Income: " + entry.getValue());
        }
        
        double totalRevenue = Report.getTotalRevenue();
        System.out.println("Total Revenue: " + totalRevenue);
    }
    
}
