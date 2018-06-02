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
import sample.Database.EspecialidadDoctorDAO;
import sample.Database.Models.EspecialidadDoctor;
import sample.Database.MySQL;
import sample.Main;
import sample.controller.EspecialidadDoctorController;

import java.io.IOException;

public class EspecialidadDoctorTable extends ParentTable {
    private EspecialidadDoctorDAO especialidadDoctorDAO = new EspecialidadDoctorDAO(MySQL.getConnection());
    @Override
    public void initTable(TableView tableView){
        tableView.getItems().clear();
        tableView.getColumns().clear();

        TableColumn column = new TableColumn("Clave de especialidad");
        column.setCellValueFactory(new PropertyValueFactory<EspecialidadDoctor,String>("CveEspecialidad"));

        TableColumn column1 = new TableColumn("Descripción");
        column1.setCellValueFactory(new PropertyValueFactory<EspecialidadDoctor,String>("Descripcion"));

        tableView.getColumns().addAll(column,column1);

        reloadData(tableView);
    }
    @Override
    public void update(TableView tableView){
        Stage stage=new Stage();
        stage.setTitle("UPDATE");
        stage.setResizable(false);


        FXMLLoader loader= new FXMLLoader(Main.class.getResource("fxml/EspecialidadDoctor.fxml"));


        try {
            Parent parent = loader.load();
            EspecialidadDoctorController controller= loader.getController();
            controller.setTipo(1,(EspecialidadDoctor)tableView.getSelectionModel().getSelectedItem());
            Scene scene=new Scene(parent,400,250);
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


        FXMLLoader loader= new FXMLLoader(Main.class.getResource("fxml/EspecialidadDoctor.fxml"));


        try {
            Parent parent = loader.load();
            EspecialidadDoctorController controller= loader.getController();
            Scene scene=new Scene(parent,400,250);
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
        especialidadDoctorDAO.delete((EspecialidadDoctor)tableView.getSelectionModel().getSelectedItem());
        reloadData(tableView);
    }
    @Override
    public void reloadData(TableView tableView){
        tableView.setItems(especialidadDoctorDAO.fetchAll());
    }
}
