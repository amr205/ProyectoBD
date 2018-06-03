package sample.Database;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sample.Database.Models.Asignado;

import java.sql.*;

public class AsignadoDAO {
    private Connection conn;

    public AsignadoDAO(Connection conn){
        this.conn = conn;
    }



    public ObservableList<Asignado> fetchAll() {
        ObservableList<Asignado> data = FXCollections.observableArrayList();
        try {
            String query = "SELECT * FROM Asignado";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            Asignado p = null;
            while(rs.next()) {
                p = new Asignado(
                        rs.getString("CvePaciente"), rs.getString("CveEnfermera")
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

    public Boolean delete(Asignado dep) {
        try {
            String query = "delete from Asignado where CvePaciente = ? and CveEnfermera = ?";
            PreparedStatement st = conn.prepareStatement(query);
            st.setString(1, dep.getCvePaciente());
            st.setString(2,dep.getCveEnfermera());
            st.execute();
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    public Boolean insert(Asignado dep) {
        try {
            String query = "insert into Asignado "
                    + " (CvePaciente, CveEnfermera)"
                    + " values (?, ?)";
            PreparedStatement st =  conn.prepareStatement(query);
            st.setString(   1, dep.getCvePaciente());
            st.setString(  2, dep.getCveEnfermera());

            st.execute();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }

        return false;
    }

    public Boolean update(Asignado dep) {
        try {
            String query = "update Asignado "
                    + " set CveEnfermera = ?"
                    + " where CvePaciente = ?";
            PreparedStatement st =  conn.prepareStatement(query);

            st.setString(  1, dep.getCveEnfermera());
            st.setString(2, dep.getCvePaciente());

            st.execute();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }

        return false;
    }

}
