
package careconnectclinic;

import careconnectclinic.Reservation;
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
    
    public static Map<LocalDateTime, ArrayList<Reservation>> getDocReservations(String doctorID, Connection connection){
        //load all reservations from file/database
        ArrayList<Reservation> allReservations = getReservations(connection);
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
    
    public static ArrayList<Reservation> viewSchedule(String doctorID, LocalDate d,Connection connection){
        Map<LocalDateTime, ArrayList<Reservation>> reservationsMap = getDocReservations(doctorID, connection);
        //implement the method to display the schedule gui
        //this is an example for testing
        ArrayList<Reservation> r= new ArrayList<>();
        for (Map.Entry<LocalDateTime, ArrayList<Reservation>> entry : reservationsMap.entrySet()) {
            LocalDate day = entry.getKey().toLocalDate();
            if(d.equals(day)){
                r=reservationsMap.get(entry.getKey());
                break;
            }
            }
            return r;
      
    }
    
    
    //database function to load reservations
    
    public static ArrayList<Reservation> getReservations(Connection connection) {
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
    }
}
