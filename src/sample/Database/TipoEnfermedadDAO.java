package sample.Database;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sample.Database.Models.TipoEnfermedad;

import java.sql.*;

public class TipoEnfermedadDAO {
    private Connection conn;

    public TipoEnfermedadDAO(Connection conn){
        this.conn = conn;
    }



    public ObservableList<TipoEnfermedad> fetchAll() {
        ObservableList<TipoEnfermedad> data = FXCollections.observableArrayList();
        try {
            String query = "SELECT * FROM TipoEnfermedad";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            TipoEnfermedad p = null;
            while(rs.next()) {
                p = new TipoEnfermedad(
                        rs.getString("CveTipoEnfermedad"), rs.getString("Descripcion")
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

    public Boolean delete(TipoEnfermedad dep) {
        try {
            String query = "delete from TipoEnfermedad where CveTipoEnfermedad = ?";
            PreparedStatement st = conn.prepareStatement(query);
            st.setString(1, dep.getCveTipoEnfermedad());
            st.execute();
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    public Boolean insert(TipoEnfermedad dep) {
        try {
            String query = "insert into TipoEnfermedad "
                    + " (CveTipoEnfermedad, Descripcion)"
                    + " values (?, ?)";
            PreparedStatement st =  conn.prepareStatement(query);
            st.setString(   1, dep.getCveTipoEnfermedad());
            st.setString(  2, dep.getDescripcion());

            st.execute();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }

        return false;
    }

    public Boolean update(TipoEnfermedad dep) {
        try {
            String query = "update TipoEnfermedad "
                    + " set Descripcion = ?"
                    + " where CveTipoEnfermedad = ?";
            PreparedStatement st =  conn.prepareStatement(query);

            st.setString(  1, dep.getDescripcion());
            st.setString(2, dep.getCveTipoEnfermedad());

            st.execute();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }

        return false;
    }
}
