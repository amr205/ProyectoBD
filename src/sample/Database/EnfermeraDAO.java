package sample.Database;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sample.Database.Models.Enfermera;

import java.sql.*;

public class EnfermeraDAO {
    private Connection conn;

    public EnfermeraDAO(Connection conn){
        this.conn = conn;
    }



    public ObservableList<Enfermera> fetchAll() {
        ObservableList<Enfermera> data = FXCollections.observableArrayList();
        try {
            String query = "SELECT * FROM Enfermera";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            Enfermera p = null;
            while(rs.next()) {
                p = new Enfermera(
                        rs.getString("CveEnfermera"), rs.getString("Nombre"),
                        rs.getInt("Edad")
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

    public Boolean delete(Enfermera dep) {
        try {
            String query = "delete from Enfermera where CveEnfermera = ?";
            PreparedStatement st = conn.prepareStatement(query);
            st.setString(1, dep.getCveEnfermera());
            st.execute();
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    public Boolean insert(Enfermera dep) {
        try {
            String query = "insert into Enfermera "
                    + " (CveEnfermera, Nombre, Edad)"
                    + " values (?, ?, ?)";
            PreparedStatement st =  conn.prepareStatement(query);
            st.setString(   1, dep.getCveEnfermera());
            st.setString(  2, dep.getNombre());
            st.setInt(3,dep.getEdad());

            st.execute();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }

        return false;
    }

    public Boolean update(Enfermera dep) {
        try {
            String query = "update Enfermera "
                    + " set Nombre = ?, Edad = ?"
                    + " where CveEnfermera = ?";
            PreparedStatement st =  conn.prepareStatement(query);

            st.setString(  1, dep.getNombre());
            st.setInt(2, dep.getEdad());
            st.setString(3,dep.getCveEnfermera());

            st.execute();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }

        return false;
    }
}
