package sample.Database;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sample.Database.Models.Estudio;

import java.sql.*;

public class EstudioDAO {
    private Connection conn;

    public EstudioDAO(Connection conn){
        this.conn = conn;
    }



    public ObservableList<Estudio> fetchAll() {
        ObservableList<Estudio> data = FXCollections.observableArrayList();
        try {
            String query = "SELECT * FROM Estudio";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            Estudio p = null;
            while(rs.next()) {
                p = new Estudio(
                        rs.getString("CveEstudio"), rs.getString("Licencia"),
                        rs.getString("CveTipoEstudio"),rs.getString("Razon"),
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

    public Boolean delete(Estudio dep) {
        try {
            String query = "delete from Estudio where CveEstudio = ?";
            PreparedStatement st = conn.prepareStatement(query);
            st.setString(1, dep.getCveEstudio());
            st.execute();
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    public Boolean insert(Estudio dep) {
        try {
            String query = "insert into Estudio "
                    + " (CveEstudio, Licencia, CveTipoEstudio, Razon, Fecha)"
                    + " values (?, ?, ?, ?, ?)";
            PreparedStatement st =  conn.prepareStatement(query);
            st.setString(   1, dep.getCveEstudio());
            st.setString(  2, dep.getLicencia());
            st.setString(3,dep.getCveTipoEstudio());
            st.setString(4,dep.getRazon());
            st.setDate(5,dep.getFecha());


            st.execute();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }

        return false;
    }

    public Boolean update(Estudio dep) {
        try {
            String query = "update Estudio "
                    + " set Licencia = ?, CveTipoEstudio = ?, Razon = ?, Fecha = ?"
                    + " where CveEstudio = ?";
            PreparedStatement st =  conn.prepareStatement(query);

            st.setString(  1, dep.getLicencia());
            st.setString(2,dep.getCveTipoEstudio());
            st.setString(3,dep.getRazon());
            st.setDate(4,dep.getFecha());
            st.setString(   5, dep.getCveEstudio());

            st.execute();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }

        return false;
    }
}
