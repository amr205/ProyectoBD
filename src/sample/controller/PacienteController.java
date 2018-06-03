package sample.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.Database.PacienteDAO;
import sample.Database.Models.Paciente;
import sample.Database.MySQL;

public class PacienteController {
    @FXML
    TextField cveTextField, nombreTextField, edadTextField, curpTextField, sexoTextField, cveTipoTextField;

    int tipo =0;//0 representa crear, 1 update
    PacienteDAO dao = new PacienteDAO(MySQL.getConnection());

    public void aceptar(ActionEvent actionEvent) {
        try {
            String cve, nombre, sexo, curp, tipoP;
            int edad;

            cve = cveTextField.getText();
            nombre = nombreTextField.getText();
            sexo = sexoTextField.getText();
            curp = curpTextField.getText();
            tipoP = cveTipoTextField.getText();
            edad = Integer.parseInt(edadTextField.getText());

            Paciente model = new Paciente(cve,tipoP, nombre,sexo,edad,curp);

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

    public void setTipo(int tipo, Paciente doctor) {
        this.tipo = tipo;

        //si el tipo es 1 hay que poner la inforción que se proporcione y bloquear los textFields que sean claves primarias
        if(doctor!=null){
            cveTextField.setText(doctor.getCvePaciente());
            nombreTextField.setText(doctor.getNombre());
            edadTextField.setText(doctor.getEdad()+"");
            curpTextField.setText(doctor.getCURP());
            sexoTextField.setText(doctor.getSexo());
            cveTipoTextField.setText(doctor.getCveTipoPaciente());

            cveTextField.setEditable(false);
        }
    }
}
