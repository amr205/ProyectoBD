package sample.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.Database.EstudioDAO;
import sample.Database.Models.Estudio;
import sample.Database.MySQL;

import java.sql.Date;
import java.time.LocalDate;

public class EstudioController {
    @FXML
    TextField cveTextField, licenciaTextField, cveTipoTextField, razonTextField;

    @FXML
    DatePicker fechaDP;

    int tipo =0;//0 representa crear, 1 update
    EstudioDAO dao = new EstudioDAO(MySQL.getConnection());

    public void aceptar(ActionEvent actionEvent) {
        String cve, licencia, tipoE, razon;
        cve = cveTextField.getText();
        licencia = licenciaTextField.getText();
        tipoE = cveTipoTextField.getText();
        razon = razonTextField.getText();

        LocalDate localDate = fechaDP.getValue();

        Estudio model = new Estudio(cve,licencia,tipoE,razon, Date.valueOf(localDate));

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

    public void setTipo(int tipo, Estudio tipoEstudio) {
        this.tipo = tipo;

        //si el tipo es 1 hay que poner la inforción que se proporcione y bloquear los textFields que sean claves primarias
        if(tipoEstudio!=null){
            cveTextField.setText(tipoEstudio.getCveEstudio());
            licenciaTextField.setText(tipoEstudio.getLicencia());
            cveTipoTextField.setText(tipoEstudio.getCveTipoEstudio());
            razonTextField.setText(tipoEstudio.getRazon());
            fechaDP.setValue(tipoEstudio.getFecha().toLocalDate());

            cveTextField.setEditable(false);
        }
    }
}
