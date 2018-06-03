package sample.Database;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sample.Database.Models.Diagnostico;

import java.sql.*;

public class DiagnosticoDAO {
    private Connection conn;

    public DiagnosticoDAO(Connection conn){
        this.conn = conn;
    }



    public ObservableList<Diagnostico> fetchAll() {
        ObservableList<Diagnostico> data = FXCollections.observableArrayList();
        try {
            String query = "SELECT * FROM Diagnostico";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            Diagnostico p = null;
            while(rs.next()) {
                p = new Diagnostico(
                        rs.getString("CveConsulta"), rs.getString("CveEnfermedad"),
                        rs.getString("Descripcion"), rs.getInt("Numero")
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

    public Boolean delete(Diagnostico dep) {
        try {
            String query = "delete from Diagnostico where CveConsulta = ? and Numero = ?";
            PreparedStatement st = conn.prepareStatement(query);
            st.setString(1, dep.getCveConsulta());
            st.setInt(2,dep.getNumero());
            st.execute();
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    public Boolean insert(Diagnostico dep) {
        try {
            String query = "insert into Diagnostico "
                    + " (CveConsulta, Numero, CveEnfermedad, Descripcion)"
                    + " values (?, ?, ?, ?)";
            PreparedStatement st =  conn.prepareStatement(query);
            st.setString(   1, dep.getCveConsulta());
            st.setInt(  2, dep.getNumero());
            st.setString(3,dep.getCveEnfermedad());
            st.setString(4,dep.getDescripcion());

            st.execute();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }

        return false;
    }

    public Boolean update(Diagnostico dep) {
        try {
            String query = "update Diagnostico "
                    + " set CveEnfermedad = ?, Descripcion = ?"
                    + " where CveConsulta = ? and Numero = ?";
            PreparedStatement st =  conn.prepareStatement(query);

            st.setString(  1, dep.getCveEnfermedad());
            st.setString(2, dep.getDescripcion());
            st.setString(3,dep.getCveConsulta());
            st.setInt(4,dep.getNumero());

            st.execute();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }

        return false;
    }


}
