package sample.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.Database.HabitacionDAO;
import sample.Database.Models.Habitacion;
import sample.Database.MySQL;

public class HabitacionController {
    @FXML
    TextField cveTextField, cveTipoTextField, cvePacienteTextField, descTextField;

    int tipo =0;//0 representa crear, 1 update
    HabitacionDAO dao = new HabitacionDAO(MySQL.getConnection());

    public void aceptar(ActionEvent actionEvent) {
        try {
            String cve, cveTipo, cvePac, desc;

            cve = cveTextField.getText();
            cveTipo = cveTipoTextField.getText();
            cvePac = cvePacienteTextField.getText();
            desc = descTextField.getText();

            Habitacion model = new Habitacion(cve,cveTipo,cvePac,desc);

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

    public void setTipo(int tipo, Habitacion habitacion) {
        this.tipo = tipo;

        //si el tipo es 1 hay que poner la inforción que se proporcione y bloquear los textFields que sean claves primarias
        if(habitacion!=null){
            cveTextField.setText(habitacion.getCveHabitacion());
            cveTipoTextField.setText(habitacion.getCveTipoHabitacion());
            cvePacienteTextField.setText(habitacion.getCvePaciente());
            descTextField.setText(habitacion.getDescripcion());

            cveTextField.setEditable(false);
        }
    }
}
