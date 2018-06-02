package sample.Database;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sample.Database.Models.Consultorio;

import java.sql.*;

public class ConsultorioDAO {
    private Connection conn;

    public ConsultorioDAO(Connection conn){
        this.conn = conn;
    }



    public ObservableList<Consultorio> fetchAll() {
        ObservableList<Consultorio> data = FXCollections.observableArrayList();
        try {
            String query = "SELECT * FROM Consultorio";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            Consultorio p = null;
            while(rs.next()) {
                p = new Consultorio(
                        rs.getString("CveConsultorio"), rs.getString("CveDepartamento")

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

    public Boolean delete(Consultorio dep) {
        try {
            String query = "delete from Consultorio where CveConsultorio = ?";
            PreparedStatement st = conn.prepareStatement(query);
            st.setString(1, dep.getCveConsultorio());
            st.execute();
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    public Boolean insert(Consultorio dep) {
        try {
            String query = "insert into Consultorio "
                    + " (CveConsultorio, CveDepartamento)"
                    + " values (?, ?)";
            PreparedStatement st =  conn.prepareStatement(query);
            st.setString(   1, dep.getCveConsultorio());
            st.setString(  2, dep.getCveDepartamento());


            st.execute();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }

        return false;
    }

    public Boolean update(Consultorio dep) {
        try {
            String query = "update Consultorio "
                    + " set CveDepartamento = ?"
                    + " where CveConsultorio = ?";
            PreparedStatement st =  conn.prepareStatement(query);

            st.setString(  1, dep.getCveDepartamento());
            st.setString(2, dep.getCveConsultorio());


            st.execute();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }

        return false;
    }
}
