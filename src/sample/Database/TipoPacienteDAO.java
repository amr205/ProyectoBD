package sample.Database;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sample.Database.Models.TipoPaciente;

import java.sql.*;

public class TipoPacienteDAO {

    private Connection conn;

    public TipoPacienteDAO(Connection conn){
        this.conn = conn;
    }



    public ObservableList<TipoPaciente> fetchAll() {
        ObservableList<TipoPaciente> data = FXCollections.observableArrayList();
        try {
            String query = "SELECT * FROM TipoPaciente";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            TipoPaciente p = null;
            while(rs.next()) {
                p = new TipoPaciente(
                        rs.getString("CveTipoPaciente"), rs.getString("Descripcion")
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

    public Boolean delete(TipoPaciente dep) {
        try {
            String query = "delete from TipoPaciente where CveTipoPaciente = ?";
            PreparedStatement st = conn.prepareStatement(query);
            st.setString(1, dep.getCveTipoPaciente());
            st.execute();
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    public Boolean insert(TipoPaciente dep) {
        try {
            String query = "insert into TipoPaciente "
                    + " (CveTipoPaciente, Descripcion)"
                    + " values (?, ?)";
            PreparedStatement st =  conn.prepareStatement(query);
            st.setString(   1, dep.getCveTipoPaciente());
            st.setString(  2, dep.getDescripcion());

            st.execute();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }

        return false;
    }

    public Boolean update(TipoPaciente dep) {
        try {
            String query = "update TipoPaciente "
                    + " set Descripcion = ?"
                    + " where CveTipoPaciente = ?";
            PreparedStatement st =  conn.prepareStatement(query);

            st.setString(  1, dep.getDescripcion());
            st.setString(2, dep.getCveTipoPaciente());

            st.execute();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }

        return false;
    }
}
