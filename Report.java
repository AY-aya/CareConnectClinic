package careconnectclinic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Report {

    private static Map<String, Integer> typeData = new HashMap<>();
    private static double totalRevenue;
    //private Connection connection;

    public Report() {
        
    }

    public void addReservationType(String newType, int fees, Connection connection) {
        try {
            String sql = "INSERT INTO ReservationTypes (type, fees) VALUES (?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, newType);
            statement.setInt(2, fees);
            statement.executeUpdate();
            typeData.put(newType, fees);
            System.out.println("added successfuulyy");
        } catch (SQLException e) {
          e.printStackTrace();
        }
        typeData.put(newType, fees);
    }

    public static Map<String, Integer> getTypeData(Connection connection) {
        fetchReservationTypesFromDB(connection);
        
        return typeData;
    }

     public static double getTotalRevenue() {
        return totalRevenue;
     }
     
    public HashMap<String, Integer> prepareMonthlyReport(int month, int year, Connection connection) {
        HashMap<String, Integer> incomeMap = new HashMap<>();
        ArrayList<Reservation> reservations = getReservations(connection);
        fetchReservationTypesFromDB(connection);
        // Initialize incomeMap with 0 for each reservation type
        for (String type : typeData.keySet()) {
            incomeMap.put(type, 0);
        }

        for (Reservation r : reservations) {
            if ((r.getDate().getMonthValue() == month) && (r.getDate().getYear() == year)) {
                String type = r.getReservationType();
                int fee = typeData.getOrDefault(type, 0);
                int currentIncome = incomeMap.get(type);
                int newIncome = currentIncome + fee;
                incomeMap.put(type, newIncome);
            }
        }
        totalRevenue = incomeMap.values().stream().mapToInt(Integer::intValue).sum();

        // Print for testing
        System.out.println("Total income for the clinic is: " + totalRevenue);
        for (Map.Entry<String, Integer> entry : incomeMap.entrySet()) {
            System.out.println(entry.getKey() + " income is: " + entry.getValue());
        }

        return incomeMap;
    }
    public void editReservationTypeFee(String type, int newFee, Connection connection) {
        System.out.println(type);
        fetchReservationTypesFromDB(connection);
        if (typeData.containsKey(type)) {
            try {
                String sql = "UPDATE ReservationTypes SET fees = ? WHERE type = ?";
                PreparedStatement statement = connection.prepareStatement(sql);
                statement.setInt(1, newFee);
                statement.setString(2, type);
                statement.executeUpdate();
                typeData.put(type, newFee);
                System.out.println("Fee for reservation type " + type + " has been updated to " + newFee);
            } catch (SQLException e) {
                e.printStackTrace();
            }
       } else {
            System.out.println("Reservation type " + type + " does not exist.");
        }
    }
    
     //database function to load reservations
    
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
    }

    private static void fetchReservationTypesFromDB(Connection connection) {
        typeData.clear(); // Clear existing data before fetching from DB
        try {
            String sql = "SELECT * FROM ReservationTypes";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String type = resultSet.getString("type");
                int fees = resultSet.getInt("fees");
                typeData.put(type, fees);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
