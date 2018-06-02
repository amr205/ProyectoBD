package sample.Database;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sample.Database.Models.DepartamentoModel;
import sample.Database.Models.TipoMedicamento;

import java.sql.*;

public class TipoMedicamentoDAO {
    private Connection conn;

    public TipoMedicamentoDAO(Connection conn){
        this.conn = conn;
    }



    public ObservableList<TipoMedicamento> fetchAll() {
        ObservableList<TipoMedicamento> data = FXCollections.observableArrayList();
        try {
            String query = "SELECT * FROM TipoMedicamento";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            TipoMedicamento p = null;
            while(rs.next()) {
                p = new TipoMedicamento(
                        rs.getString("CveTipoMedicamento"), rs.getString("Descripcion")
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

    public Boolean delete(TipoMedicamento dep) {
        try {
            String query = "delete from TipoMedicamento where CveTipoMedicamento = ?";
            PreparedStatement st = conn.prepareStatement(query);
            st.setString(1, dep.getCveTipoMedicamento());
            st.execute();
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    public Boolean insert(TipoMedicamento dep) {
        try {
            String query = "insert into TipoMedicamento "
                    + " (CveTipoMedicamento, Descripcion)"
                    + " values (?, ?)";
            PreparedStatement st =  conn.prepareStatement(query);
            st.setString(   1, dep.getCveTipoMedicamento());
            st.setString(  2, dep.getDescripcion());

            st.execute();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }

        return false;
    }

    public Boolean update(TipoMedicamento dep) {
        try {
            String query = "update TipoMedicamento "
                    + " set Descripcion = ?"
                    + " where CveTipoMedicamento = ?";
            PreparedStatement st =  conn.prepareStatement(query);

            st.setString(  1, dep.getDescripcion());
            st.setString(2, dep.getCveTipoMedicamento());

            st.execute();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }

        return false;
    }
}
