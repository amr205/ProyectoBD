package sample.Database;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sample.Database.Models.Laboratorios;

import java.sql.*;

public class LaboratoriosDAO {
    private Connection conn;

    public LaboratoriosDAO(Connection conn){
        this.conn = conn;
    }



    public ObservableList<Laboratorios> fetchAll() {
        ObservableList<Laboratorios> data = FXCollections.observableArrayList();
        try {
            String query = "SELECT * FROM Laboratorios";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            Laboratorios p = null;
            while(rs.next()) {
                p = new Laboratorios(
                        rs.getString("CveLaboratorios"), rs.getString("CveDepartamento"),
                        rs.getString("Descripcion")
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

    public Boolean delete(Laboratorios dep) {
        try {
            String query = "delete from Laboratorios where CveLaboratorios = ?";
            PreparedStatement st = conn.prepareStatement(query);
            st.setString(1, dep.getCveLaboratorios());
            st.execute();
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    public Boolean insert(Laboratorios dep) {
        try {
            String query = "insert into Laboratorios "
                    + " (CveLaboratorios, CveDepartamento, Descripcion)"
                    + " values (?, ?, ?)";
            PreparedStatement st =  conn.prepareStatement(query);
            st.setString(   1, dep.getCveLaboratorios());
            st.setString(  2, dep.getCveDepartamento());
            st.setString(3,dep.getDescripcion());

            st.execute();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }

        return false;
    }

    public Boolean update(Laboratorios dep) {
        try {
            String query = "update Laboratorios "
                    + " set CveDepartamento = ?, Descripcion = ?"
                    + " where CveLaboratorios = ?";
            PreparedStatement st =  conn.prepareStatement(query);

            st.setString(  1, dep.getCveDepartamento());
            st.setString(2, dep.getDescripcion());
            st.setString(3,dep.getCveLaboratorios());

            st.execute();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }

        return false;
    }
}
