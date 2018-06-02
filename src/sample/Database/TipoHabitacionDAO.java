package sample.Database;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sample.Database.Models.TipoHabitacion;

import java.sql.*;

public class TipoHabitacionDAO {
    private Connection conn;

    public TipoHabitacionDAO(Connection conn){
        this.conn = conn;
    }



    public ObservableList<TipoHabitacion> fetchAll() {
        ObservableList<TipoHabitacion> data = FXCollections.observableArrayList();
        try {
            String query = "SELECT * FROM TipoHabitacion";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            TipoHabitacion p = null;
            while(rs.next()) {
                p = new TipoHabitacion(
                        rs.getString("CveTipoHabitacion"), rs.getString("Descripcion")
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

    public Boolean delete(TipoHabitacion dep) {
        try {
            String query = "delete from TipoHabitacion where CveTipoHabitacion = ?";
            PreparedStatement st = conn.prepareStatement(query);
            st.setString(1, dep.getCveTipoHabitacion());
            st.execute();
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    public Boolean insert(TipoHabitacion dep) {
        try {
            String query = "insert into TipoHabitacion "
                    + " (CveTipoHabitacion, Descripcion)"
                    + " values (?, ?)";
            PreparedStatement st =  conn.prepareStatement(query);
            st.setString(   1, dep.getCveTipoHabitacion());
            st.setString(  2, dep.getDescripcion());

            st.execute();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }

        return false;
    }

    public Boolean update(TipoHabitacion dep) {
        try {
            String query = "update TipoHabitacion "
                    + " set Descripcion = ?"
                    + " where CveTipoHabitacion = ?";
            PreparedStatement st =  conn.prepareStatement(query);

            st.setString(  1, dep.getDescripcion());
            st.setString(2, dep.getCveTipoHabitacion());

            st.execute();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }

        return false;
    }
}
