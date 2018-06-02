package sample.Database;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sample.Database.Models.TipoEstudio;

import java.sql.*;

public class TipoEstudioDAO {
    private Connection conn;

    public TipoEstudioDAO(Connection conn){
        this.conn = conn;
    }



    public ObservableList<TipoEstudio> fetchAll() {
        ObservableList<TipoEstudio> data = FXCollections.observableArrayList();
        try {
            String query = "SELECT * FROM TipoEstudio";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            TipoEstudio p = null;
            while(rs.next()) {
                p = new TipoEstudio(
                        rs.getString("CveTipoEstudio"), rs.getString("Descripcion")
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

    public Boolean delete(TipoEstudio dep) {
        try {
            String query = "delete from TipoEstudio where CveTipoEstudio = ?";
            PreparedStatement st = conn.prepareStatement(query);
            st.setString(1, dep.getCveTipoEstudio());
            st.execute();
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    public Boolean insert(TipoEstudio dep) {
        try {
            String query = "insert into TipoEstudio "
                    + " (CveTipoEstudio, Descripcion)"
                    + " values (?, ?)";
            PreparedStatement st =  conn.prepareStatement(query);
            st.setString(   1, dep.getCveTipoEstudio());
            st.setString(  2, dep.getDescripcion());

            st.execute();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }

        return false;
    }

    public Boolean update(TipoEstudio dep) {
        try {
            String query = "update TipoEstudio "
                    + " set Descripcion = ?"
                    + " where CveTipoEstudio = ?";
            PreparedStatement st =  conn.prepareStatement(query);

            st.setString(  1, dep.getDescripcion());
            st.setString(2, dep.getCveTipoEstudio());

            st.execute();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }

        return false;
    }
}
