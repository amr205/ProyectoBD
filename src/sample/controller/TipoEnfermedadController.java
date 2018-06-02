package sample.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.Database.Models.TipoEnfermedad;
import sample.Database.MySQL;
import sample.Database.TipoEnfermedadDAO;

public class TipoEnfermedadController {
    @FXML
    TextField cveTextField, descTextField;

    int tipo =0;//0 representa crear, 1 update
    TipoEnfermedadDAO dao = new TipoEnfermedadDAO(MySQL.getConnection());

    public void aceptar(ActionEvent actionEvent) {
        String cve, nombre;
        cve = cveTextField.getText();
        nombre = descTextField.getText();
        TipoEnfermedad model = new TipoEnfermedad(cve,nombre);

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

    public void setTipo(int tipo, TipoEnfermedad tipoEnfermedad) {
        this.tipo = tipo;

        //si el tipo es 1 hay que poner la inforción que se proporcione y bloquear los textFields que sean claves primarias
        if(tipoEnfermedad!=null){
            cveTextField.setText(tipoEnfermedad.getCveTipoEnfermedad());
            descTextField.setText(tipoEnfermedad.getDescripcion());

            cveTextField.setEditable(false);
        }
    }
}
