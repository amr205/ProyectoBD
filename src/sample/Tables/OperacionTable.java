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
import sample.Database.OperacionDAO;
import sample.Database.Models.Operacion;
import sample.Database.MySQL;
import sample.Main;
import sample.controller.OperacionController;

import java.io.IOException;

public class OperacionTable extends ParentTable {
    private OperacionDAO operacionDAO = new OperacionDAO(MySQL.getConnection());
    @Override
    public void initTable(TableView tableView){
        tableView.getItems().clear();
        tableView.getColumns().clear();

        TableColumn column = new TableColumn("clave paciente");
        column.setCellValueFactory(new PropertyValueFactory<Operacion,String>("cveOperacion"));

        TableColumn column1 = new TableColumn("numero");
        column1.setCellValueFactory(new PropertyValueFactory<Operacion,Integer>("numero"));

        TableColumn column2 = new TableColumn("clave sala");
        column2.setCellValueFactory(new PropertyValueFactory<Operacion,String>("CveSala"));

        TableColumn column3 = new TableColumn("descripcion");
        column3.setCellValueFactory(new PropertyValueFactory<Operacion,String>("descripcion"));

        TableColumn column4 = new TableColumn("fecha");
        column4.setCellValueFactory(new PropertyValueFactory<Operacion,String>("fecha"));

        tableView.getColumns().addAll(column,column1, column2);

        reloadData(tableView);
    }
    @Override
    public void update(TableView tableView){
        Stage stage=new Stage();
        stage.setTitle("UPDATE");
        stage.setResizable(false);


        FXMLLoader loader= new FXMLLoader(Main.class.getResource("fxml/Operacion.fxml"));


        try {
            Parent parent = loader.load();
            OperacionController controller= loader.getController();
            controller.setTipo(1,(Operacion)tableView.getSelectionModel().getSelectedItem());
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


        FXMLLoader loader= new FXMLLoader(Main.class.getResource("fxml/Operacion.fxml"));


        try {
            Parent parent = loader.load();
            OperacionController controller= loader.getController();
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
        operacionDAO.delete((Operacion)tableView.getSelectionModel().getSelectedItem());
        reloadData(tableView);
    }
    @Override
    public void reloadData(TableView tableView){
        tableView.setItems(operacionDAO.fetchAll());
    }
}
