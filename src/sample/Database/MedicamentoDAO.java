package sample.Database;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sample.Database.Models.Medicamento;

import java.sql.*;

public class MedicamentoDAO {
    private Connection conn;

    public MedicamentoDAO(Connection conn){
        this.conn = conn;
    }



    public ObservableList<Medicamento> fetchAll() {
        ObservableList<Medicamento> data = FXCollections.observableArrayList();
        try {
            String query = "SELECT * FROM Medicamento";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            Medicamento p = null;
            while(rs.next()) {
                p = new Medicamento(
                        rs.getString("CveMedicamento"), rs.getString("CveTipoMedicamento"),
                        rs.getString("Nombre")
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

    public Boolean delete(Medicamento dep) {
        try {
            String query = "delete from Medicamento where CveMedicamento = ?";
            PreparedStatement st = conn.prepareStatement(query);
            st.setString(1, dep.getCveMedicamento());
            st.execute();
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    public Boolean insert(Medicamento dep) {
        try {
            String query = "insert into Medicamento "
                    + " (CveMedicamento, CveTipoMedicamento, Nombre)"
                    + " values (?, ?, ?)";
            PreparedStatement st =  conn.prepareStatement(query);
            st.setString(   1, dep.getCveMedicamento());
            st.setString(  2, dep.getCveTipoMedicamento());
            st.setString(3,dep.getNombre());

            st.execute();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }

        return false;
    }

    public Boolean update(Medicamento dep) {
        try {
            String query = "update Medicamento "
                    + " set CveTipoMedicamento = ?, Nombre = ?"
                    + " where CveMedicamento = ?";
            PreparedStatement st =  conn.prepareStatement(query);

            st.setString(  1, dep.getCveTipoMedicamento());
            st.setString(2, dep.getNombre());
            st.setString(3,dep.getCveMedicamento());

            st.execute();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }

        return false;
    }
}
