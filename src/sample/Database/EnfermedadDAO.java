package sample.Database;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sample.Database.Models.Enfermedad;

import java.sql.*;

public class EnfermedadDAO {
    private Connection conn;

    public EnfermedadDAO(Connection conn){
        this.conn = conn;
    }



    public ObservableList<Enfermedad> fetchAll() {
        ObservableList<Enfermedad> data = FXCollections.observableArrayList();
        try {
            String query = "SELECT * FROM Enfermedad";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            Enfermedad p = null;
            while(rs.next()) {
                p = new Enfermedad(
                        rs.getString("CveEnfermedad"), rs.getString("Descripcion")
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

    public Boolean delete(Enfermedad dep) {
        try {
            String query = "delete from Enfermedad where CveEnfermedad = ?";
            PreparedStatement st = conn.prepareStatement(query);
            st.setString(1, dep.getCveEnfermedad());
            st.execute();
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    public Boolean insert(Enfermedad dep) {
        try {
            String query = "insert into Enfermedad "
                    + " (CveEnfermedad, Descripcion)"
                    + " values (?, ?)";
            PreparedStatement st =  conn.prepareStatement(query);
            st.setString(   1, dep.getCveEnfermedad());
            st.setString(  2, dep.getDescripcion());

            st.execute();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }

        return false;
    }

    public Boolean update(Enfermedad dep) {
        try {
            String query = "update Enfermedad "
                    + " set Descripcion = ?"
                    + " where CveEnfermedad = ?";
            PreparedStatement st =  conn.prepareStatement(query);

            st.setString(  1, dep.getDescripcion());
            st.setString(2, dep.getCveEnfermedad());

            st.execute();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }

        return false;
    }
}
