package sample.controller;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableView;
import sample.Tables.*;

import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {
    @FXML
    MenuItem departamentoMI, tipoMedicamentoMI, especialidadDoctorMI,tipoEnfermedadMI,tipoPacienteMI,tipoHabitacionMI,tipoEstudioMI,salaQuirofanoMI,
            enfermeraMI;

    @FXML
    TableView table;

    //lista de las tablas
    private DepartamentoTable departamentoTable = new DepartamentoTable();
    private TipoMedicamentoTable tipoMedicamentoTable = new TipoMedicamentoTable();
    private EspecialidadDoctorTable especialidadDoctorTable = new EspecialidadDoctorTable();
    private TipoEnfermedadTable tipoEnfermedadTable = new TipoEnfermedadTable();
    private TipoPacienteTable tipoPacienteTable = new TipoPacienteTable();
    private TipoHabitacionTable tipoHabitacionTable = new TipoHabitacionTable();
    private TipoEstudioTable tipoEstudioTable = new TipoEstudioTable();
    private SalaQuirofanoTable salaQuirofanoTable = new SalaQuirofanoTable();
    private EnfermeraTable enfermeraTable = new EnfermeraTable();

    //dummyTable
    private ParentTable parentTable = new ParentTable();


    private EventHandler eventHandler = new EventHandler() {
        @Override
        public void handle(Event event) {
            if(event.getSource()==departamentoMI)
                parentTable= departamentoTable;
            if(event.getSource()==tipoMedicamentoMI)
                parentTable= tipoMedicamentoTable;
            if(event.getSource()==especialidadDoctorMI)
                parentTable= especialidadDoctorTable;
            if(event.getSource()==tipoEnfermedadMI)
                parentTable= tipoEnfermedadTable;
            if(event.getSource()==tipoPacienteMI)
                parentTable= tipoPacienteTable;
            if(event.getSource()==tipoHabitacionMI)
                parentTable= tipoHabitacionTable;
            if(event.getSource()==tipoEstudioMI)
                parentTable= tipoEstudioTable;
            if(event.getSource()==salaQuirofanoMI)
                parentTable= salaQuirofanoTable;
            if(event.getSource()==enfermeraMI)
                parentTable= enfermeraTable;

            updateData();
        }
    };



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //initialize data
        parentTable= departamentoTable;
        updateData();

        //addHandler to all menu Items
        departamentoMI.setOnAction(eventHandler);
        tipoMedicamentoMI.setOnAction(eventHandler);
        especialidadDoctorMI.setOnAction(eventHandler);
        tipoEnfermedadMI.setOnAction(eventHandler);
        tipoPacienteMI.setOnAction(eventHandler);
        tipoHabitacionMI.setOnAction(eventHandler);
        tipoEstudioMI.setOnAction(eventHandler);
        salaQuirofanoMI.setOnAction(eventHandler);
        enfermeraMI.setOnAction(eventHandler);







    }

    private void updateData() {
        parentTable.initTable(table);
    }

    public void create(ActionEvent actionEvent) {
        parentTable.create(table);
    }

    public void update(ActionEvent actionEvent) {
        if(table.getSelectionModel().getSelectedItem()!=null)
            parentTable.update(table);
        else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("No se puede realizar la acción");
            alert.setContentText("Seleccione un elemento de la tabla");
            alert.show();
        }
    }

    public void delete(ActionEvent actionEvent) {
        if(table.getSelectionModel().getSelectedItem()!=null)
            parentTable.delete(table);
        else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("No se puede realizar la acción");
            alert.setContentText("Seleccione un elemento de la tabla");
            alert.show();
        }
    }

}
