package sample.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.Database.DepartamentoDAO;
import sample.Database.Models.TipoMedicamento;
import sample.Database.Models.TipoMedicamento;
import sample.Database.MySQL;
import sample.Database.TipoMedicamentoDAO;

public class TipoMedicamentoController {
    @FXML
    TextField cveTextField, descTextField;

    int tipo =0;//0 representa crear, 1 update
    TipoMedicamentoDAO dao = new TipoMedicamentoDAO(MySQL.getConnection());

    public void aceptar(ActionEvent actionEvent) {
        String cve, nombre;
        cve = cveTextField.getText();
        nombre = descTextField.getText();
        TipoMedicamento model = new TipoMedicamento(cve,nombre);

        if(tipo==0){
            dao.insert(model);
        }
        else {
            dao.update(model);
        }

        Stage stage = (Stage)cveTextField.getScene().getWindow();
        stage.close();
    }

    public void cancelar(ActionEvent actionEvent) {
        Stage stage = (Stage)cveTextField.getScene().getWindow();
        stage.close();
    }

    public void setTipo(int tipo, TipoMedicamento tipoMedicamento) {
        this.tipo = tipo;

        //si el tipo es 1 hay que poner la inforción que se proporcione y bloquear los textFields que sean claves primarias
        if(tipoMedicamento!=null){
            cveTextField.setText(tipoMedicamento.getCveTipoMedicamento());
            descTextField.setText(tipoMedicamento.getDescripcion());

            cveTextField.setEditable(false);
        }
    }
}
