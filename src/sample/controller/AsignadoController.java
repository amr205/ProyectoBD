package sample.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.Database.AsignadoDAO;
import sample.Database.Models.Asignado;
import sample.Database.MySQL;

public class AsignadoController {
    @FXML
    TextField cvePacienteTextField, cveEnfermeraTextField;

    int tipo =0;//0 representa crear, 1 update
    AsignadoDAO dao = new AsignadoDAO(MySQL.getConnection());

    public void aceptar(ActionEvent actionEvent) {
        try {
            String cveP, cveE;

            cveP = cvePacienteTextField.getText();
            cveE = cveEnfermeraTextField.getText();

            Asignado model = new Asignado(cveP,cveE);

            if (tipo == 0) {
                dao.insert(model);
            } else {
                dao.update(model);
            }

            Stage stage = (Stage) cvePacienteTextField.getScene().getWindow();
            stage.close();


        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void cancelar(ActionEvent actionEvent) {
        Stage stage = (Stage)cvePacienteTextField.getScene().getWindow();
        stage.close();
    }

    public void setTipo(int tipo, Asignado asignado) {
        this.tipo = tipo;

        //si el tipo es 1 hay que poner la inforción que se proporcione y bloquear los textFields que sean claves primarias
        if(asignado!=null){
            cvePacienteTextField.setText(asignado.getCvePaciente());
            cveEnfermeraTextField.setText(asignado.getCveEnfermera());


            cvePacienteTextField.setEditable(false);
            cveEnfermeraTextField.setEditable(false);

        }
    }
}
