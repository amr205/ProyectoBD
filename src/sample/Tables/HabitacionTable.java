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
import sample.Database.HabitacionDAO;
import sample.Database.Models.Habitacion;
import sample.Database.MySQL;
import sample.Main;
import sample.controller.HabitacionController;

import java.io.IOException;

public class HabitacionTable extends ParentTable {
    private HabitacionDAO habitacionDAO = new HabitacionDAO(MySQL.getConnection());
    @Override
    public void initTable(TableView tableView){
        tableView.getItems().clear();
        tableView.getColumns().clear();

        TableColumn column = new TableColumn("clave");
        column.setCellValueFactory(new PropertyValueFactory<Habitacion,String>("cveHabitacion"));

        TableColumn column1 = new TableColumn("tipo");
        column1.setCellValueFactory(new PropertyValueFactory<Habitacion,String>("cveTipoHabitacion"));

        TableColumn column2 = new TableColumn("paciente");
        column2.setCellValueFactory(new PropertyValueFactory<Habitacion,String>("cvePaciente"));

        TableColumn column3 = new TableColumn("descripcion");
        column3.setCellValueFactory(new PropertyValueFactory<Habitacion,String>("descripcion"));

        tableView.getColumns().addAll(column,column1, column2, column3);

        reloadData(tableView);
    }
    @Override
    public void update(TableView tableView){
        Stage stage=new Stage();
        stage.setTitle("UPDATE");
        stage.setResizable(false);


        FXMLLoader loader= new FXMLLoader(Main.class.getResource("fxml/Habitacion.fxml"));


        try {
            Parent parent = loader.load();
            HabitacionController controller= loader.getController();
            controller.setTipo(1,(Habitacion)tableView.getSelectionModel().getSelectedItem());
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


        FXMLLoader loader= new FXMLLoader(Main.class.getResource("fxml/Habitacion.fxml"));


        try {
            Parent parent = loader.load();
            HabitacionController controller= loader.getController();
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
        habitacionDAO.delete((Habitacion)tableView.getSelectionModel().getSelectedItem());
        reloadData(tableView);
    }
    @Override
    public void reloadData(TableView tableView){
        tableView.setItems(habitacionDAO.fetchAll());
    }
}
