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
import sample.Database.EstudioDAO;
import sample.Database.Models.Estudio;
import sample.Database.MySQL;
import sample.Main;
import sample.controller.EstudioController;

import java.io.IOException;
import java.sql.Date;

public class EstudioTable extends ParentTable {
    private EstudioDAO estudioDAO = new EstudioDAO(MySQL.getConnection());
    @Override
    public void initTable(TableView tableView){
        tableView.getItems().clear();
        tableView.getColumns().clear();

        TableColumn column = new TableColumn("clave");
        column.setCellValueFactory(new PropertyValueFactory<Estudio,String>("cveEstudio"));

        TableColumn column1 = new TableColumn("doctor");
        column1.setCellValueFactory(new PropertyValueFactory<Estudio,String>("licencia"));

        TableColumn column2 = new TableColumn("tipo");
        column2.setCellValueFactory(new PropertyValueFactory<Estudio,String>("cveTipoEstudio"));

        TableColumn column3 = new TableColumn("razon");
        column3.setCellValueFactory(new PropertyValueFactory<Estudio,String>("razon"));

        TableColumn column4 = new TableColumn("fecha");
        column4.setCellValueFactory(new PropertyValueFactory<Estudio,Date>("fecha"));

        tableView.getColumns().addAll(column,column1, column2, column3, column4);

        reloadData(tableView);
    }
    @Override
    public void update(TableView tableView){
        Stage stage=new Stage();
        stage.setTitle("UPDATE");
        stage.setResizable(false);


        FXMLLoader loader= new FXMLLoader(Main.class.getResource("fxml/Estudio.fxml"));


        try {
            Parent parent = loader.load();
            EstudioController controller= loader.getController();
            controller.setTipo(1,(Estudio)tableView.getSelectionModel().getSelectedItem());
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


        FXMLLoader loader= new FXMLLoader(Main.class.getResource("fxml/Estudio.fxml"));


        try {
            Parent parent = loader.load();
            EstudioController controller= loader.getController();
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
        estudioDAO.delete((Estudio)tableView.getSelectionModel().getSelectedItem());
        reloadData(tableView);
    }
    @Override
    public void reloadData(TableView tableView){
        tableView.setItems(estudioDAO.fetchAll());
    }
}
