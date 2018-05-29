package sample.controller;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableView;
import sample.Tables.Departamento;
import sample.Tables.ParentTable;

import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {
    @FXML
    MenuItem departamentoMI;

    @FXML
    TableView table;

    //lista de las tablas
    private Departamento departamento = new Departamento();

    //dummyTable
    private ParentTable parentTable = new ParentTable();


    private EventHandler eventHandler = new EventHandler() {
        @Override
        public void handle(Event event) {
            if(event.getSource()==departamentoMI)
                parentTable=departamento;

            updateData();
        }
    };



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //initialize data
        parentTable=departamento;
        updateData();

        //addHandler to all menu Items
        departamentoMI.setOnAction(eventHandler);

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
