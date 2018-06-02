package sample.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.Database.Models.Enfermera;
import sample.Database.MySQL;
import sample.Database.EnfermeraDAO;

public class EnfermeraController {
    @FXML
    TextField cveTextField, nombreTextField, edadTextField;

    int tipo =0;//0 representa crear, 1 update
    EnfermeraDAO dao = new EnfermeraDAO(MySQL.getConnection());

    public void aceptar(ActionEvent actionEvent) {
        try {
            String cve, nombre;
            int edad;

            cve = cveTextField.getText();
            nombre = nombreTextField.getText();
            edad = Integer.parseInt(edadTextField.getText());

            Enfermera model = new Enfermera(cve, nombre,edad);

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

    public void setTipo(int tipo, Enfermera enfermera) {
        this.tipo = tipo;

        //si el tipo es 1 hay que poner la inforción que se proporcione y bloquear los textFields que sean claves primarias
        if(enfermera!=null){
            cveTextField.setText(enfermera.getCveEnfermera());
            nombreTextField.setText(enfermera.getNombre());
            edadTextField.setText(enfermera.getEdad()+"");

            cveTextField.setEditable(false);
        }
    }
}
