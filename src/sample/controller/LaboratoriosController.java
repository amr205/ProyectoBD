package sample.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.Database.LaboratoriosDAO;
import sample.Database.Models.Laboratorios;
import sample.Database.MySQL;

public class LaboratoriosController {
    @FXML
    TextField cveTextField, cveDepTextField, descTextField;

    int tipo =0;//0 representa crear, 1 update
    LaboratoriosDAO dao = new LaboratoriosDAO(MySQL.getConnection());

    public void aceptar(ActionEvent actionEvent) {
        try {
            String cve, dep, desc;

            cve = cveTextField.getText();
            dep = cveDepTextField.getText();
            desc = descTextField.getText();
            

            Laboratorios model = new Laboratorios(cve, dep,desc);

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

    public void setTipo(int tipo, Laboratorios laboratorios) {
        this.tipo = tipo;

        //si el tipo es 1 hay que poner la inforción que se proporcione y bloquear los textFields que sean claves primarias
        if(laboratorios!=null){
            cveTextField.setText(laboratorios.getCveLaboratorios());
            cveDepTextField.setText(laboratorios.getCveDepartamento());
            descTextField.setText(laboratorios.getDescripcion());


            cveTextField.setEditable(false);
        }
    }
}
