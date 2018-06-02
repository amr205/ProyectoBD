package sample.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.Database.EspecialidadDoctorDAO;
import sample.Database.Models.EspecialidadDoctor;
import sample.Database.MySQL;

public class EspecialidadDoctorController {
    @FXML
    TextField cveTextField, nombreTextField;

    int tipo =0;//0 representa crear, 1 update
    EspecialidadDoctorDAO dao = new EspecialidadDoctorDAO(MySQL.getConnection());

    public void aceptar(ActionEvent actionEvent) {
        String cve, nombre;
        cve = cveTextField.getText();
        nombre = nombreTextField.getText();
        EspecialidadDoctor model = new EspecialidadDoctor(cve,nombre);

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

    public void setTipo(int tipo, EspecialidadDoctor departamentoModel) {
        this.tipo = tipo;

        //si el tipo es 1 hay que poner la inforción que se proporcione y bloquear los textFields que sean claves primarias
        if(departamentoModel!=null){
            cveTextField.setText(departamentoModel.getCveEspecialidad());
            nombreTextField.setText(departamentoModel.getDescripcion());

            cveTextField.setEditable(false);
        }
    }
}
