package sample.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.Database.DiagnosticoDAO;
import sample.Database.Models.Diagnostico;
import sample.Database.MySQL;

public class DiagnosticoController {
    @FXML
    TextField cveConsultaTextField, cveEnfermedadTextField, descripcionTextField, numeroTextField;

    int tipo =0;//0 representa crear, 1 update
    DiagnosticoDAO dao = new DiagnosticoDAO(MySQL.getConnection());

    public void aceptar(ActionEvent actionEvent) {
        try {
            String cveConsulta, cveEnfermedad, descripcion;
            int num;

            cveConsulta = cveConsultaTextField.getText();
            cveEnfermedad = cveEnfermedadTextField.getText();
            descripcion = descripcionTextField.getText();
            num = Integer.parseInt(numeroTextField.getText());

            Diagnostico model = new Diagnostico(cveConsulta,cveEnfermedad,descripcion,num);

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

    public void setTipo(int tipo, Diagnostico diagnostico) {
        this.tipo = tipo;

        //si el tipo es 1 hay que poner la inforción que se proporcione y bloquear los textFields que sean claves primarias
        if(diagnostico!=null){
            cveConsultaTextField.setText(diagnostico.getCveConsulta());
            cveEnfermedadTextField.setText(diagnostico.getCveEnfermedad());
            numeroTextField.setText(diagnostico.getNumero()+"");
            descripcionTextField.setText(diagnostico.getDescripcion());

            cveConsultaTextField.setEditable(false);
            numeroTextField.setEditable(false);
        }
    }
}
