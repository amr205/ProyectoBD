package sample.Database;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sample.Database.Models.SalaQuirofano;

import java.sql.*;

public class SalaQuirofanoDAO {

    private Connection conn;

    public SalaQuirofanoDAO(Connection conn){
        this.conn = conn;
    }



    public ObservableList<SalaQuirofano> fetchAll() {
        ObservableList<SalaQuirofano> data = FXCollections.observableArrayList();
        try {
            String query = "SELECT * FROM SalaQuirofano";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            SalaQuirofano p = null;
            while(rs.next()) {
                p = new SalaQuirofano(
                        rs.getString("CveSala"), rs.getString("Descripcion")
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

    public Boolean delete(SalaQuirofano dep) {
        try {
            String query = "delete from SalaQuirofano where CveSala = ?";
            PreparedStatement st = conn.prepareStatement(query);
            st.setString(1, dep.getCveSala());
            st.execute();
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    public Boolean insert(SalaQuirofano dep) {
        try {
            String query = "insert into SalaQuirofano "
                    + " (CveSala, Descripcion)"
                    + " values (?, ?)";
            PreparedStatement st =  conn.prepareStatement(query);
            st.setString(   1, dep.getCveSala());
            st.setString(  2, dep.getDescripcion());

            st.execute();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }

        return false;
    }

    public Boolean update(SalaQuirofano dep) {
        try {
            String query = "update SalaQuirofano "
                    + " set Descripcion = ?"
                    + " where CveSala = ?";
            PreparedStatement st =  conn.prepareStatement(query);

            st.setString(  1, dep.getDescripcion());
            st.setString(2, dep.getCveSala());

            st.execute();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }

        return false;
    }
}
