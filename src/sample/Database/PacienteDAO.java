package sample.Database;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sample.Database.Models.Paciente;

import java.sql.*;

public class PacienteDAO {
    private Connection conn;

    public PacienteDAO(Connection conn){
        this.conn = conn;
    }



    public ObservableList<Paciente> fetchAll() {
        ObservableList<Paciente> data = FXCollections.observableArrayList();
        try {
            String query = "SELECT * FROM Paciente";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            Paciente p = null;
            while(rs.next()) {
                p = new Paciente(
                        rs.getString("CvePaciente"), rs.getString("CveTipoPaciente"),
                        rs.getString("Nombre"),rs.getString("Sexo"),
                        rs.getInt("Edad"), rs.getString("CURP")
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

    public Boolean delete(Paciente dep) {
        try {
            String query = "delete from Paciente where CvePaciente = ?";
            PreparedStatement st = conn.prepareStatement(query);
            st.setString(1, dep.getCvePaciente());
            st.execute();
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    public Boolean insert(Paciente dep) {
        try {
            String query = "insert into Paciente "
                    + " (CvePaciente, CveTipoPaciente, Nombre, Sexo, Edad, CURP)"
                    + " values (?, ?, ?, ?, ?, ?)";
            PreparedStatement st =  conn.prepareStatement(query);
            st.setString(   1, dep.getCvePaciente());
            st.setString(  2, dep.getCveTipoPaciente());
            st.setString(3,dep.getNombre());
            st.setString(4,dep.getSexo());
            st.setInt(5,dep.getEdad());
            st.setString(6,dep.getCURP());

            st.execute();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }

        return false;
    }

    public Boolean update(Paciente dep) {
        try {
            String query = "update Paciente "
                    + " set CveTipoPaciente = ?, Nombre = ?, Sexo = ?, Edad = ?, CURP = ?"
                    + " where Licencia = ?";
            PreparedStatement st =  conn.prepareStatement(query);

            st.setString(  1, dep.getCveTipoPaciente());
            st.setString(2,dep.getNombre());
            st.setString(3,dep.getSexo());
            st.setInt(4,dep.getEdad());
            st.setString(5,dep.getCURP());
            st.setString(   6, dep.getCvePaciente());

            st.execute();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }

        return false;
    }
}
