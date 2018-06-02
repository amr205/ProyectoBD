package sample.Database;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sample.Database.Models.EspecialidadDoctor;

import java.sql.*;

public class EspecialidadDoctorDAO {
    private Connection conn;

    public EspecialidadDoctorDAO(Connection conn){
        this.conn = conn;
    }



    public ObservableList<EspecialidadDoctor> fetchAll() {
        ObservableList<EspecialidadDoctor> data = FXCollections.observableArrayList();
        try {
            String query = "SELECT * FROM EspecialidadDoctor";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            EspecialidadDoctor p = null;
            while(rs.next()) {
                p = new EspecialidadDoctor(
                        rs.getString("CveEspecialidad"), rs.getString("Descripcion")
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

    public Boolean delete(EspecialidadDoctor dep) {
        try {
            String query = "delete from EspecialidadDoctor where CveEspecialidad = ?";
            PreparedStatement st = conn.prepareStatement(query);
            st.setString(1, dep.getCveEspecialidad());
            st.execute();
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    public Boolean insert(EspecialidadDoctor dep) {
        try {
            String query = "insert into EspecialidadDoctor "
                    + " (CveEspecialidad, Descripcion)"
                    + " values (?, ?)";
            PreparedStatement st =  conn.prepareStatement(query);
            st.setString(   1, dep.getCveEspecialidad());
            st.setString(  2, dep.getDescripcion());

            st.execute();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }

        return false;
    }

    public Boolean update(EspecialidadDoctor dep) {
        try {
            String query = "update EspecialidadDoctor "
                    + " set Descripcion = ?"
                    + " where CveEspecialidad = ?";
            PreparedStatement st =  conn.prepareStatement(query);

            st.setString(  1, dep.getDescripcion());
            st.setString(2, dep.getCveEspecialidad());

            st.execute();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }

        return false;
    }
}
