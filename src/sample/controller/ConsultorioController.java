package sample.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.Database.ConsultorioDAO;
import sample.Database.Models.Consultorio;
import sample.Database.MySQL;

public class ConsultorioController {
    @FXML
    TextField cveTextField, cveDepTextField;

    int tipo =0;//0 representa crear, 1 update
    ConsultorioDAO dao = new ConsultorioDAO(MySQL.getConnection());

    public void aceptar(ActionEvent actionEvent) {
        try {
            String cve, dep;

            cve = cveTextField.getText();
            dep = cveDepTextField.getText();

            Consultorio model = new Consultorio(cve, dep);

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

    public void setTipo(int tipo, Consultorio consultorio) {
        this.tipo = tipo;

        //si el tipo es 1 hay que poner la inforción que se proporcione y bloquear los textFields que sean claves primarias
        if(consultorio!=null){
            cveTextField.setText(consultorio.getCveConsultorio());
            cveDepTextField.setText(consultorio.getCveDepartamento());

            cveTextField.setEditable(false);
        }
    }
}
