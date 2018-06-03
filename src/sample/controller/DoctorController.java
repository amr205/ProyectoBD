package sample.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.Database.DoctorDAO;
import sample.Database.Models.Doctor;
import sample.Database.MySQL;

public class DoctorController {
    @FXML
    TextField cveTextField, nombreTextField, edadTextField, curpTextField, sexoTextField, cveEspTextField;

    int tipo =0;//0 representa crear, 1 update
    DoctorDAO dao = new DoctorDAO(MySQL.getConnection());

    public void aceptar(ActionEvent actionEvent) {
        try {
            String cve, nombre, sexo, curp, cveEsp;
            int edad;

            cve = cveTextField.getText();
            nombre = nombreTextField.getText();
            sexo = sexoTextField.getText();
            curp = curpTextField.getText();
            cveEsp = cveEspTextField.getText();
            edad = Integer.parseInt(edadTextField.getText());

            Doctor model = new Doctor(cve,cveEsp, nombre,curp,sexo,edad);

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

    public void setTipo(int tipo, Doctor doctor) {
        this.tipo = tipo;

        //si el tipo es 1 hay que poner la inforción que se proporcione y bloquear los textFields que sean claves primarias
        if(doctor!=null){
            cveTextField.setText(doctor.getLicencia());
            nombreTextField.setText(doctor.getNombre());
            edadTextField.setText(doctor.getEdad()+"");
            curpTextField.setText(doctor.getCURP());
            sexoTextField.setText(doctor.getSexo());
            cveEspTextField.setText(doctor.getCveEspecialidad());

            cveTextField.setEditable(false);
        }
    }
}
