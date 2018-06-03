package sample.Database;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sample.Database.Models.Habitacion;

import java.sql.*;

public class HabitacionDAO {
    private Connection conn;

    public HabitacionDAO(Connection conn){
        this.conn = conn;
    }



    public ObservableList<Habitacion> fetchAll() {
        ObservableList<Habitacion> data = FXCollections.observableArrayList();
        try {
            String query = "SELECT * FROM Habitacion";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            Habitacion p = null;
            while(rs.next()) {
                p = new Habitacion(
                        rs.getString("CveHabitacion"), rs.getString("CveTipoHabitacion"),
                        rs.getString("CvePaciente"), rs.getString("Descripcion")
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

    public Boolean delete(Habitacion dep) {
        try {
            String query = "delete from Habitacion where CveHabitacion = ?";
            PreparedStatement st = conn.prepareStatement(query);
            st.setString(1, dep.getCveHabitacion());
            st.execute();
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    public Boolean insert(Habitacion dep) {
        try {
            String query = "insert into Habitacion "
                    + " (CveHabitacion, CveTipoHabitacion, CvePaciente, Descripcion)"
                    + " values (?, ?, ?, ?)";
            PreparedStatement st =  conn.prepareStatement(query);
            st.setString(   1, dep.getCveHabitacion());
            st.setString(  2, dep.getCveTipoHabitacion());
            st.setString(3,dep.getCvePaciente());
            st.setString(4,dep.getDescripcion());

            st.execute();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }

        return false;
    }

    public Boolean update(Habitacion dep) {
        try {
            String query = "update Habitacion "
                    + " set CveTipoHabitacion = ?, CvePaciente = ?, Descripcion= ?"
                    + " where CveHabitacion = ?";
            PreparedStatement st =  conn.prepareStatement(query);

            st.setString(  1, dep.getCveTipoHabitacion());
            st.setString(2, dep.getCvePaciente());
            st.setString(3,dep.getDescripcion());
            st.setString(4,dep.getCveHabitacion());

            st.execute();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }

        return false;
    }
}
