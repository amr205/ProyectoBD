package sample.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.Database.OperacionDAO;
import sample.Database.Models.Operacion;
import sample.Database.MySQL;

import java.sql.Date;
import java.time.LocalDate;

public class OperacionController {
    @FXML
    TextField cvePacienteTextField, cveSalaTextField, descripcionTextField, numeroTextField;

    @FXML
    DatePicker fechaDP;

    int tipo =0;//0 representa crear, 1 update
    OperacionDAO dao = new OperacionDAO(MySQL.getConnection());

    public void aceptar(ActionEvent actionEvent) {
        String cvePaciente, cveSala, desc;
        int numero;

        cvePaciente = cvePacienteTextField.getText();
        cveSala = cveSalaTextField.getText();
        desc = descripcionTextField.getText();
        numero = Integer.parseInt(numeroTextField.getText());

        LocalDate localDate = fechaDP.getValue();

        Operacion model = new Operacion(cvePaciente,cveSala,desc,numero,Date.valueOf(localDate));

        if(tipo==0){
            dao.insert(model);
        }
        else {
            dao.update(model);
        }

        Stage stage = (Stage)cvePacienteTextField.getScene().getWindow();
        stage.close();
    }

    public void cancelar(ActionEvent actionEvent) {
        Stage stage = (Stage)cvePacienteTextField.getScene().getWindow();
        stage.close();
    }

    public void setTipo(int tipo, Operacion operacion) {
        this.tipo = tipo;

        //si el tipo es 1 hay que poner la inforción que se proporcione y bloquear los textFields que sean claves primarias
        if(operacion!=null){
            cvePacienteTextField.setText(operacion.getCvePaciente());
            numeroTextField.setText(operacion.getNumero()+"");
            cveSalaTextField.setText(operacion.getCveSala());
            descripcionTextField.setText(operacion.getDescripcion());
            fechaDP.setValue(operacion.getFecha().toLocalDate());

            cvePacienteTextField.setEditable(false);
            numeroTextField.setEditable(false);
        }
    }
}
