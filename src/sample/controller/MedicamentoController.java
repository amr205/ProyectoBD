package sample.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.Database.MedicamentoDAO;
import sample.Database.Models.Medicamento;
import sample.Database.MySQL;

public class MedicamentoController {
    @FXML
    TextField cveTextField, nombreTextField, cveTipoTextField;

    int tipo =0;//0 representa crear, 1 update
    MedicamentoDAO dao = new MedicamentoDAO(MySQL.getConnection());

    public void aceptar(ActionEvent actionEvent) {
        try {
            String cve, nombre, tipoM;

            cve = cveTextField.getText();
            nombre = nombreTextField.getText();
            tipoM = cveTipoTextField.getText();

            Medicamento model = new Medicamento(cve,tipoM,nombre);

            if (tipo == 0) {
                dao.insert(model);
            } else {
                dao.update(model);
            }

            Stage stage = (Stage) cveTextField.getScene().getWindow();
            stage.close();


        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void cancelar(ActionEvent actionEvent) {
        Stage stage = (Stage)cveTextField.getScene().getWindow();
        stage.close();
    }

    public void setTipo(int tipo, Medicamento medicamento) {
        this.tipo = tipo;

        //si el tipo es 1 hay que poner la inforción que se proporcione y bloquear los textFields que sean claves primarias
        if(medicamento!=null){
            cveTextField.setText(medicamento.getCveMedicamento());
            nombreTextField.setText(medicamento.getNombre());
            cveTipoTextField.setText(medicamento.getCveTipoMedicamento());

            cveTextField.setEditable(false);
        }
    }
}
