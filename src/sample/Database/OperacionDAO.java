package sample.Database;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sample.Database.Models.Operacion;

import java.sql.*;

public class OperacionDAO {
    private Connection conn;

    public OperacionDAO(Connection conn){
        this.conn = conn;
    }



    public ObservableList<Operacion> fetchAll() {
        ObservableList<Operacion> data = FXCollections.observableArrayList();
        try {
            String query = "SELECT * FROM Operacion";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            Operacion p = null;
            while(rs.next()) {
                p = new Operacion(
                        rs.getString("CvePaciente"), rs.getString("CveSala"),
                        rs.getString("Descripcion"),rs.getInt("Numero"),
                        rs.getDate("Fecha")
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

    public Boolean delete(Operacion dep) {
        try {
            String query = "delete from Operacion where CvePaciente = ? and Numero =?";
            PreparedStatement st = conn.prepareStatement(query);
            st.setString(1, dep.getCvePaciente());
            st.setInt(2,dep.getNumero());
            st.execute();
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    public Boolean insert(Operacion dep) {
        try {
            String query = "insert into Operacion "
                    + " (CvePaciente, Numero, CveSala, Descripcion, Fecha)"
                    + " values (?, ?, ?, ?, ?)";
            PreparedStatement st =  conn.prepareStatement(query);
            st.setString(   1, dep.getCvePaciente());
            st.setInt(  2, dep.getNumero());
            st.setString(3,dep.getCveSala());
            st.setString(4,dep.getDescripcion());
            st.setDate(5,dep.getFecha());


            st.execute();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }

        return false;
    }

    public Boolean update(Operacion dep) {
        try {
            String query = "update Operacion "
                    + " set CveSala = ?, Descripcion = ?, Fecha = ?"
                    + " where CvePaciente = ? and Numero = ?";
            PreparedStatement st =  conn.prepareStatement(query);

            st.setString(  1, dep.getCveSala());
            st.setString(2,dep.getDescripcion());
            st.setDate(3,dep.getFecha());
            st.setString(4,dep.getCvePaciente());
            st.setInt(   5, dep.getNumero());

            st.execute();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }

        return false;
    }


}
