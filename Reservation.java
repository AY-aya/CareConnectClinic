
package com.mycompany.careconnectclinic;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public class Reservation implements Comparable<Reservation> {
    private String patientId;
    private LocalDateTime date;
    private String reservationType;
    private String doctorId;
    private boolean paid;

    

    public Reservation(String patientId, LocalDateTime date, String reservationType, String doctorId, boolean paid) {
        this.patientId = patientId;
        this.date = date;
        this.reservationType = reservationType;
        this.doctorId = doctorId;
        this.paid = paid;
    }

    public void saveToDatabase(Connection connection) {
        try {
            String query ="INSERT INTO reservations (patient_id, date, reservation_type, doctor_id, paid) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement statement= connection.prepareStatement(query);
            
            statement.setString(1, patientId);
            statement.setTimestamp(2,Timestamp.valueOf(date));
            statement.setString(3,reservationType);
            statement.setString(4, doctorId);
            statement.setBoolean(5, paid);
            statement.executeUpdate();
            System.out.println("Reservation saved to the database.");
        } catch (SQLException e) {
            System.out.println("Error saving reservation: " + e.getMessage());
        }
    }

    public void updateReservation(Connection connection){
        String sql = "UPDATE Reservations SET reservation_type = ?, paid = ? WHERE dr_id = ? AND datei = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, reservationType);
            statement.setBoolean(2, paid);
            statement.setString(3, patientId);
            statement.setTimestamp(4, Timestamp.valueOf(date));
            statement.setString(5, doctorId);
            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Reservation updated successfully.");
            } else {
                System.out.println("No reservation found for the specified criteria.");
            }
            statement.close();
        } 
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Getters and setters
    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public String getReservationType() {
        return reservationType;
    }

    public void setReservationType(String reservationType) {
        this.reservationType = reservationType;
    }

    public String getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(String doctorId) {
        this.doctorId = doctorId;
    }

    public boolean isPaid() {
        return paid;
    }

    public void setPaid(boolean paid) {
        this.paid = paid;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "patientId='" + patientId + '\'' +
                ", date=" + date +
                ", reservationType='" + reservationType + '\'' +
                ", doctorId='" + doctorId + '\'' +
                ", paid=" + paid +
                '}';
    }
    
    @Override
    public int compareTo(Reservation o) {
        return this.getDate().compareTo(o.getDate());
    }
}