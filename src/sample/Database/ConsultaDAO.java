package sample.Database;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sample.Database.Models.Consulta;

import java.sql.*;

public class ConsultaDAO {
    private Connection conn;

    public ConsultaDAO(Connection conn){
        this.conn = conn;
    }



    public ObservableList<Consulta> fetchAll() {
        ObservableList<Consulta> data = FXCollections.observableArrayList();
        try {
            String query = "SELECT * FROM Consulta";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            Consulta p = null;
            while(rs.next()) {
                p = new Consulta(
                        rs.getString("CveConsulta"), rs.getString("CveConsultorio"),
                        rs.getString("CvePaciente"), rs.getString("Licencia")
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

    public Boolean delete(Consulta dep) {
        try {
            String query = "delete from Consulta where CveConsulta = ?";
            PreparedStatement st = conn.prepareStatement(query);
            st.setString(1, dep.getCveConsulta());
            st.execute();
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    public Boolean insert(Consulta dep) {
        try {
            String query = "insert into Consulta "
                    + " (CveConsulta, CveConsultorio, CvePaciente, Licencia)"
                    + " values (?, ?, ?, ?)";
            PreparedStatement st =  conn.prepareStatement(query);
            st.setString(   1, dep.getCveConsulta());
            st.setString(  2, dep.getCveConsultorio());
            st.setString(3,dep.getCvePaciente());
            st.setString(4,dep.getLicencia());

            st.execute();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }

        return false;
    }

    public Boolean update(Consulta dep) {
        try {
            String query = "update Consulta "
                    + " set CveConsultorio = ?, CvePaciente = ?, Licencia= ?"
                    + " where CveConsulta = ?";
            PreparedStatement st =  conn.prepareStatement(query);

            st.setString(  1, dep.getCveConsultorio());
            st.setString(2, dep.getCvePaciente());
            st.setString(3,dep.getLicencia());
            st.setString(4,dep.getCveConsulta());

            st.execute();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }

        return false;
    }

}
