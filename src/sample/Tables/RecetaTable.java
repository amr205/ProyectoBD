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
import sample.Database.RecetaDAO;
import sample.Database.Models.Receta;
import sample.Database.MySQL;
import sample.Main;
import sample.controller.RecetaController;

import java.io.IOException;

public class RecetaTable extends ParentTable {
    private RecetaDAO recetaDAO = new RecetaDAO(MySQL.getConnection());
    @Override
    public void initTable(TableView tableView){
        tableView.getItems().clear();
        tableView.getColumns().clear();

        TableColumn column = new TableColumn("clave medicamento");
        column.setCellValueFactory(new PropertyValueFactory<Receta,String>("cveMedicamento"));

        TableColumn column1 = new TableColumn("clave consulta");
        column1.setCellValueFactory(new PropertyValueFactory<Receta,String>("cveConsulta"));

        tableView.getColumns().addAll(column,column1);

        reloadData(tableView);
    }
    @Override
    public void update(TableView tableView){
        Stage stage=new Stage();
        stage.setTitle("UPDATE");
        stage.setResizable(false);


        FXMLLoader loader= new FXMLLoader(Main.class.getResource("fxml/Receta.fxml"));


        try {
            Parent parent = loader.load();
            RecetaController controller= loader.getController();
            controller.setTipo(1,(Receta)tableView.getSelectionModel().getSelectedItem());
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


        FXMLLoader loader= new FXMLLoader(Main.class.getResource("fxml/Receta.fxml"));


        try {
            Parent parent = loader.load();
            RecetaController controller= loader.getController();
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
        recetaDAO.delete((Receta)tableView.getSelectionModel().getSelectedItem());
        reloadData(tableView);
    }
    @Override
    public void reloadData(TableView tableView){
        tableView.setItems(recetaDAO.fetchAll());
    }
}
