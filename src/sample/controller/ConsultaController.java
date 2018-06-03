package sample.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.Database.ConsultaDAO;
import sample.Database.Models.Consulta;
import sample.Database.MySQL;

public class ConsultaController {
    @FXML
    TextField cveConsultaTextField, cveConsultorioTextField, cvePacienteTextField, licenciaTextField;

    int tipo =0;//0 representa crear, 1 update
    ConsultaDAO dao = new ConsultaDAO(MySQL.getConnection());

    public void aceptar(ActionEvent actionEvent) {
        try {
            String cveConsulta, cveConsultorio, cvePaciente, licencia;

            cveConsulta = cveConsultaTextField.getText();
            cveConsultorio = cveConsultorioTextField.getText();
            cvePaciente = cvePacienteTextField.getText();
            licencia = licenciaTextField.getText();

            Consulta model = new Consulta(cveConsulta,cveConsultorio,cvePaciente,licencia);

            if (tipo == 0) {
                dao.insert(model);
            } else {
                dao.update(model);
            }

            Stage stage = (Stage) cveConsultaTextField.getScene().getWindow();
            stage.close();


        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void cancelar(ActionEvent actionEvent) {
        Stage stage = (Stage)cveConsultaTextField.getScene().getWindow();
        stage.close();
    }

    public void setTipo(int tipo, Consulta consulta) {
        this.tipo = tipo;

        //si el tipo es 1 hay que poner la inforción que se proporcione y bloquear los textFields que sean claves primarias
        if(consulta!=null){
            cveConsultaTextField.setText(consulta.getCveConsulta());
            cveConsultorioTextField.setText(consulta.getCveConsultorio());
            cvePacienteTextField.setText(consulta.getCvePaciente());
            licenciaTextField.setText(consulta.getLicencia());

            cveConsultaTextField.setEditable(false);
        }
    }
}
