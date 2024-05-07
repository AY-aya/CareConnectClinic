package gui1;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 *
 * @author Aya Yasser
 */
public class Schedule {
    
    public static Map<LocalDateTime, ArrayList<Reservation>> getDocReservations(String doctorID){
        //load all reservations from file/database
        ArrayList<Reservation> allReservations = getReservations();
        ArrayList<Reservation> docReservations= new ArrayList<>();
        //get the reservations of a specific doctor
        for(Reservation r: allReservations){
            if(r.getDoctorId().equals(doctorID))
                docReservations.add(r);
        }
        Collections.sort(docReservations);
        return getReservationsByDay(docReservations);
    }
    
    //funtion that returns the reservations for each day (filters out past reservations)
    public static Map<LocalDateTime, ArrayList<Reservation>> getReservationsByDay(ArrayList<Reservation> reservations) {
        Map<LocalDateTime, ArrayList<Reservation>> reservationMap = new HashMap<>();
        LocalDate today = LocalDate.now();
        
        for (Reservation reservation : reservations) {
            LocalDateTime date = reservation.getDate();
            if (date.toLocalDate().isAfter(today) || date.toLocalDate().isEqual(today)) {
                reservationMap.computeIfAbsent(date.toLocalDate().atStartOfDay(), k -> new ArrayList<>()).add(reservation);
            }
        }
        //to sort the hashmap
        Map<LocalDateTime, ArrayList<Reservation>> sortedReservationMap = new LinkedHashMap<>();
        reservationMap.entrySet().stream()
                .sorted(Map.Entry.comparingByKey())
                .forEachOrdered(x -> sortedReservationMap.put(x.getKey(), x.getValue()));
        
        return sortedReservationMap;
        
    }
    
    public static ArrayList<Reservation> viewSchedule(String doctorID, LocalDate date){
        Map<LocalDateTime, ArrayList<Reservation>> reservationsMap = getDocReservations(doctorID);
        //implement the method to display the schedule gui
        ArrayList<Reservation> r= new ArrayList<>();
        for(Map.Entry<LocalDateTime, ArrayList<Reservation>> entry : reservationsMap.entrySet()){
            LocalDate d = entry.getKey().toLocalDate();
            if(date.equals(d)){
                r= reservationsMap.get(entry.getKey());
                break;
            }
                
        }
         //System.out.println(r);       
        //this is an example for testing
        /**for (Map.Entry<LocalDateTime, ArrayList<Reservation>> entry : reservationsMap.entrySet()) {
            LocalDateTime day = entry.getKey();
            ArrayList<Reservation> reservationsForDay = entry.getValue();
            System.out.println("Reservations for " + day.toLocalDate() + ": " + reservationsForDay);
        }*****/
        return r;
    }
    
    
    //database function to load reservations
    /******
    private static ArrayList<Reservation> getReservations(Connection connection) {
        ArrayList<Reservation> reservations = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            String sql = "SELECT patient_id, datei, reservation_type, dr_id, paid FROM Reservations";
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                String patientID=resultSet.getString("patient_id");
                LocalDateTime date=resultSet.getTimestamp("datei").toLocalDateTime();
                String reservationtype= resultSet.getString("reservation_type");
                String doctorID= resultSet.getString("dr_id");
                boolean paid=resultSet.getBoolean("paid");
                Reservation r= new Reservation(patientID,date,reservationtype,doctorID,paid);
                reservations.add(r);
            }
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return reservations;
    }***********/
    static ArrayList<Reservation> getReservations(){
        ArrayList<Reservation> r= new ArrayList<>();
        Reservation r1= new Reservation("1", LocalDateTime.of(2024,5,8,1,0),"ENT", "dr0002", true);
        Reservation r2= new Reservation("2", LocalDateTime.of(2024,5,7,15,0),"ENT", "dr0002", true);
        Reservation r3= new Reservation("3", LocalDateTime.of(2024,5,7,16,0),"ENT", "dr0002", true);
        Reservation r4= new Reservation("4", LocalDateTime.of(2024,5,5,12,0),"ENT", "dr0002", true);
        Reservation r5= new Reservation("5", LocalDateTime.of(2024,5,7,12,0),"ENT", "dr0002", true);
        Reservation r6= new Reservation("6", LocalDateTime.of(2024,5,5,1,0),"Cardiology", "dr0002", true);
        Reservation r7= new Reservation("7", LocalDateTime.of(2024,5,5,3,0),"ENT", "dr0002", true);
        Reservation r8= new Reservation("8", LocalDateTime.of(2024,5,5,2,0),"ENT", "dr0002", true);
        Reservation r9= new Reservation("9", LocalDateTime.of(2024,5,5,4,0),"ENT", "dr0002", true);
        Reservation r10= new Reservation("24", LocalDateTime.of(2024,5,5,12,0),"ENT", "dr0002", true);
        Reservation r11= new Reservation("57", LocalDateTime.of(2024,5,4,12,0),"ENT", "dr0002", true);
        Reservation r12= new Reservation("61", LocalDateTime.of(2024,5,5,1,0),"Cardiology", "dr0002", true);
        Reservation r13= new Reservation("7", LocalDateTime.of(2024,5,5,3,0),"ENT", "dr0002", true);
        Reservation r14= new Reservation("80", LocalDateTime.of(2024,5,5,2,0),"ENT", "dr0002", true);
        Reservation r15= new Reservation("91", LocalDateTime.of(2024,5,5,4,0),"ENT", "dr0002", true);
        r.add(r1);
        r.add(r2);
        r.add(r3);
        r.add(r4);
        r.add(r5);
        r.add(r6);
        r.add(r7);
        r.add(r8);
        r.add(r9);
        r.add(r10);
        r.add(r11);
        r.add(r12);
        r.add(r13);
        r.add(r14);
        r.add(r15);
        return r;
    }
}
