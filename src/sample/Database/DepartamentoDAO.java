package sample.Database;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sample.Database.Models.DepartamentoModel;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DepartamentoDAO {
    private Connection conn;

    public DepartamentoDAO(Connection conn){
        this.conn = conn;
    }



    public ObservableList<DepartamentoModel> fetchAll() {
        ObservableList<DepartamentoModel> data = FXCollections.observableArrayList();
        try {
            String query = "SELECT * FROM Departamento";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            DepartamentoModel p = null;
            while(rs.next()) {
                p = new DepartamentoModel(
                        rs.getString("CveDepartamento"), rs.getString("Nombre")
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

    public Boolean delete(DepartamentoModel dep) {
        try {
            String query = "delete from Departamento where CveDepartamento = ?";
            PreparedStatement st = conn.prepareStatement(query);
            st.setString(1, dep.getCveDepartamento());
            st.execute();
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    public Boolean insert(DepartamentoModel dep) {
        try {
            String query = "insert into Departamento "
                    + " (CveDepartamento, Nombre)"
                    + " values (?, ?)";
            PreparedStatement st =  conn.prepareStatement(query);
            st.setString(   1, dep.getCveDepartamento());
            st.setString(  2, dep.getNombre());

            st.execute();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }

        return false;
    }

    public Boolean update(DepartamentoModel dep) {
        try {
            String query = "update Departamento "
                    + " set Nombre = ?"
                    + " where CveDepartamento = ?";
            PreparedStatement st =  conn.prepareStatement(query);

            st.setString(  1, dep.getNombre());
            st.setString(2, dep.getCveDepartamento());

            st.execute();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }

        return false;
    }

}
