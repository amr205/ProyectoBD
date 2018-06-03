package sample.Database;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sample.Database.Models.Receta;

import java.sql.*;

public class RecetaDAO {
    private Connection conn;

    public RecetaDAO(Connection conn){
        this.conn = conn;
    }



    public ObservableList<Receta> fetchAll() {
        ObservableList<Receta> data = FXCollections.observableArrayList();
        try {
            String query = "SELECT * FROM Receta";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            Receta p = null;
            while(rs.next()) {
                p = new Receta(
                        rs.getString("CveMedicamento"), rs.getString("CveConsulta")
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

    public Boolean delete(Receta dep) {
        try {
            String query = "delete from Receta where CveMedicamento = ? and CveConsulta = ?";
            PreparedStatement st = conn.prepareStatement(query);
            st.setString(1, dep.getCveMedicamento());
            st.setString(2,dep.getCveConsulta());
            st.execute();
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    public Boolean insert(Receta dep) {
        try {
            String query = "insert into Receta "
                    + " (CveMedicamento, CveConsulta)"
                    + " values (?, ?)";
            PreparedStatement st =  conn.prepareStatement(query);
            st.setString(   1, dep.getCveMedicamento());
            st.setString(  2, dep.getCveConsulta());

            st.execute();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }

        return false;
    }

    public Boolean update(Receta dep) {
        try {
            String query = "update Receta "
                    + " set CveConsulta = ?"
                    + " where CveMedicamento = ?";
            PreparedStatement st =  conn.prepareStatement(query);

            st.setString(  1, dep.getCveConsulta());
            st.setString(2, dep.getCveMedicamento());

            st.execute();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }

        return false;
    }

}
