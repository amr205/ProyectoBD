package sample.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.Database.DepartamentoDAO;
import sample.Database.Models.DepartamentoModel;
import sample.Database.MySQL;

public class DepartamentoController {
    @FXML
    TextField cveTextField, nombreTextField;

    int tipo =0;//0 representa crear, 1 update
    DepartamentoDAO dao = new DepartamentoDAO(MySQL.getConnection());

    public void aceptar(ActionEvent actionEvent) {
        String cve, nombre;
        cve = cveTextField.getText();
        nombre = nombreTextField.getText();
        DepartamentoModel model = new DepartamentoModel(cve,nombre);

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

    public void setTipo(int tipo, DepartamentoModel departamentoModel) {
        this.tipo = tipo;

        //si el tipo es 1 hay que poner la inforción que se proporcione y bloquear los textFields que sean claves primarias
        if(departamentoModel!=null){
            cveTextField.setText(departamentoModel.getCveDepartamento());
            nombreTextField.setText(departamentoModel.getNombre());

            cveTextField.setEditable(false);
        }
    }
}
