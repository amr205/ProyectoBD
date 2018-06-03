package sample.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.Database.RecetaDAO;
import sample.Database.Models.Receta;
import sample.Database.MySQL;

public class RecetaController {
    @FXML
    TextField cveMedicamentoTextField, cveConsultaTextField;

    int tipo =0;//0 representa crear, 1 update
    RecetaDAO dao = new RecetaDAO(MySQL.getConnection());

    public void aceptar(ActionEvent actionEvent) {
        try {
            String cveMed, cveCon;
            int edad;

            cveMed = cveMedicamentoTextField.getText();
            cveCon = cveConsultaTextField.getText();

            Receta model = new Receta(cveMed,cveCon);

            if (tipo == 0) {
                dao.insert(model);
            } else {
                dao.update(model);
            }

            Stage stage = (Stage) cveMedicamentoTextField.getScene().getWindow();
            stage.close();


        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void cancelar(ActionEvent actionEvent) {
        Stage stage = (Stage)cveMedicamentoTextField.getScene().getWindow();
        stage.close();
    }

    public void setTipo(int tipo, Receta receta) {
        this.tipo = tipo;

        //si el tipo es 1 hay que poner la inforción que se proporcione y bloquear los textFields que sean claves primarias
        if(receta!=null){
            cveMedicamentoTextField.setText(receta.getCveMedicamento());
            cveConsultaTextField.setText(receta.getCveConsulta());
            cveMedicamentoTextField.setEditable(false);
            cveConsultaTextField.setEditable(false);
        }
    }
}
