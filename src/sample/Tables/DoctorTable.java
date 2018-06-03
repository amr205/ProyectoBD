package sample.Tables;


import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import sample.Database.DoctorDAO;
import sample.Database.Models.Doctor;
import sample.Database.MySQL;
import sample.Main;
import sample.controller.DoctorController;

import java.io.IOException;

public class DoctorTable extends ParentTable {
    private DoctorDAO doctorDAO = new DoctorDAO(MySQL.getConnection());
    @Override
    public void initTable(TableView tableView){
        tableView.getItems().clear();
        tableView.getColumns().clear();

        TableColumn column = new TableColumn("Licencia");
        column.setCellValueFactory(new PropertyValueFactory<Doctor,String>("Licencia"));

        TableColumn column1 = new TableColumn("Nombre");
        column1.setCellValueFactory(new PropertyValueFactory<Doctor,String>("Nombre"));

        TableColumn column2 = new TableColumn("CURP");
        column2.setCellValueFactory(new PropertyValueFactory<Doctor,String>("CURP"));

        TableColumn column3 = new TableColumn("Sexo");
        column3.setCellValueFactory(new PropertyValueFactory<Doctor,String>("sexo"));

        TableColumn column4 = new TableColumn("Edad");
        column4.setCellValueFactory(new PropertyValueFactory<Doctor,Integer>("edad"));

        TableColumn column5 = new TableColumn("Especialidad");
        column5.setCellValueFactory(new PropertyValueFactory<Doctor,Integer>("CveEspecialidad"));

        tableView.getColumns().addAll(column,column1, column2, column3, column4, column5);

        reloadData(tableView);
    }
    @Override
    public void update(TableView tableView){
        Stage stage=new Stage();
        stage.setTitle("UPDATE");
        stage.setResizable(false);


        FXMLLoader loader= new FXMLLoader(Main.class.getResource("fxml/Doctor.fxml"));


        try {
            Parent parent = loader.load();
            DoctorController controller= loader.getController();
            controller.setTipo(1,(Doctor)tableView.getSelectionModel().getSelectedItem());
            Scene scene=new Scene(parent,400,350);
            stage.setScene(scene);
            stage.show();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        stage.addEventHandler(WindowEvent.WINDOW_HIDDEN, new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                reloadData(tableView);
            }
        });

    }
    @Override
    public void create(TableView tableView){
        Stage stage=new Stage();
        stage.setTitle("CREATE");
        stage.setResizable(false);


        FXMLLoader loader= new FXMLLoader(Main.class.getResource("fxml/Doctor.fxml"));


        try {
            Parent parent = loader.load();
            DoctorController controller= loader.getController();
            Scene scene=new Scene(parent,400,350);
            stage.setScene(scene);
            stage.show();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        stage.addEventHandler(WindowEvent.WINDOW_HIDDEN, new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                reloadData(tableView);
            }
        });


    }
    @Override
    public void delete(TableView tableView){
        doctorDAO.delete((Doctor)tableView.getSelectionModel().getSelectedItem());
        reloadData(tableView);
    }
    @Override
    public void reloadData(TableView tableView){
        tableView.setItems(doctorDAO.fetchAll());
    }
}
