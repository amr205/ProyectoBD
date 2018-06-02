package sample.Database;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sample.Database.Models.Doctor;

import java.sql.*;

public class DoctorDAO {

    private Connection conn;

    public DoctorDAO(Connection conn){
        this.conn = conn;
    }



    public ObservableList<Doctor> fetchAll() {
        ObservableList<Doctor> data = FXCollections.observableArrayList();
        try {
            String query = "SELECT * FROM Doctor";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            Doctor p = null;
            while(rs.next()) {
                p = new Doctor(
                        rs.getString("Licencia"), rs.getString("CveEspecialidad"),
                        rs.getString("Nombre"),rs.getString("CURP"),
                        rs.getString("Sexo"), rs.getInt("Edad")
                );
                data.add(p);
            }
            rs.close();
            st.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("Error al recuperar información...");
        }
        return data;
    }

    public Boolean delete(Doctor dep) {
        try {
            String query = "delete from Doctor where Licencia = ?";
            PreparedStatement st = conn.prepareStatement(query);
            st.setString(1, dep.getLicencia());
            st.execute();
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    public Boolean insert(Doctor dep) {
        try {
            String query = "insert into Doctor "
                    + " (Licencia, CveEspecialidad, Nombre, CURP, Sexo, Edad)"
                    + " values (?, ?, ?, ?, ?, ?)";
            PreparedStatement st =  conn.prepareStatement(query);
            st.setString(   1, dep.getLicencia());
            st.setString(  2, dep.getCveEspecialidad());
            st.setString(3,dep.getNombre());
            st.setString(4,dep.getCURP());
            st.setString(5,dep.getSexo());
            st.setInt(6,dep.getEdad());

            st.execute();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }

        return false;
    }

    public Boolean update(Doctor dep) {
        try {
            String query = "update Doctor "
                    + " set CveEspecialidad = ?, Nombre = ?, CURP = ?, Sexo = ?, Edad = ?"
                    + " where Licencia = ?";
            PreparedStatement st =  conn.prepareStatement(query);

            st.setString(  1, dep.getCveEspecialidad());
            st.setString(2,dep.getNombre());
            st.setString(3,dep.getCURP());
            st.setString(4,dep.getSexo());
            st.setInt(5,dep.getEdad());
            st.setString(   6, dep.getLicencia());

            st.execute();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }

        return false;
    }
}
