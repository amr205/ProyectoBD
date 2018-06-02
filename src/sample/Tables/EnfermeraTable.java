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
import sample.Database.EnfermeraDAO;
import sample.Database.Models.Enfermera;
import sample.Database.MySQL;
import sample.Main;
import sample.controller.EnfermeraController;

import java.io.IOException;

public class EnfermeraTable extends ParentTable {
    private EnfermeraDAO enfermeraDAO = new EnfermeraDAO(MySQL.getConnection());
    @Override
    public void initTable(TableView tableView){
        tableView.getItems().clear();
        tableView.getColumns().clear();

        TableColumn column = new TableColumn("clave");
        column.setCellValueFactory(new PropertyValueFactory<Enfermera,String>("cveEnfermera"));

        TableColumn column1 = new TableColumn("nombre");
        column1.setCellValueFactory(new PropertyValueFactory<Enfermera,String>("nombre"));

        TableColumn column2 = new TableColumn("edad");
        column2.setCellValueFactory(new PropertyValueFactory<Enfermera,String>("edad"));

        tableView.getColumns().addAll(column,column1, column2);

        reloadData(tableView);
    }
    @Override
    public void update(TableView tableView){
        Stage stage=new Stage();
        stage.setTitle("UPDATE");
        stage.setResizable(false);


        FXMLLoader loader= new FXMLLoader(Main.class.getResource("fxml/Enfermera.fxml"));


        try {
            Parent parent = loader.load();
            EnfermeraController controller= loader.getController();
            controller.setTipo(1,(Enfermera)tableView.getSelectionModel().getSelectedItem());
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


        FXMLLoader loader= new FXMLLoader(Main.class.getResource("fxml/Enfermera.fxml"));


        try {
            Parent parent = loader.load();
            EnfermeraController controller= loader.getController();
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
        enfermeraDAO.delete((Enfermera)tableView.getSelectionModel().getSelectedItem());
        reloadData(tableView);
    }
    @Override
    public void reloadData(TableView tableView){
        tableView.setItems(enfermeraDAO.fetchAll());
    }
}
