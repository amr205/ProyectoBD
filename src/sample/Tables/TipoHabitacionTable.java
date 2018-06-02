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
import sample.Database.Models.TipoHabitacion;
import sample.Database.MySQL;
import sample.Database.TipoHabitacionDAO;
import sample.Main;
import sample.controller.TipoHabitacionController;

import java.io.IOException;

public class TipoHabitacionTable extends ParentTable {

    private TipoHabitacionDAO tipoHabitacionDAO = new TipoHabitacionDAO(MySQL.getConnection());

    @Override
    public void initTable(TableView tableView){
        tableView.getItems().clear();
        tableView.getColumns().clear();

        TableColumn column = new TableColumn("clave");
        column.setCellValueFactory(new PropertyValueFactory<TipoHabitacion,String>("CveTipoHabitacion"));

        TableColumn column1 = new TableColumn("descripcion");
        column1.setCellValueFactory(new PropertyValueFactory<TipoHabitacion,String>("Descripcion"));

        tableView.getColumns().addAll(column,column1);

        reloadData(tableView);
    }
    @Override
    public void update(TableView tableView){
        Stage stage=new Stage();
        stage.setTitle("UPDATE");
        stage.setResizable(false);


        FXMLLoader loader= new FXMLLoader(Main.class.getResource("fxml/TipoHabitacion.fxml"));


        try {
            Parent parent = loader.load();
            TipoHabitacionController controller= loader.getController();
            controller.setTipo(1,(TipoHabitacion)tableView.getSelectionModel().getSelectedItem());
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


        FXMLLoader loader= new FXMLLoader(Main.class.getResource("fxml/TipoHabitacion.fxml"));


        try {
            Parent parent = loader.load();
            TipoHabitacionController controller= loader.getController();
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
        tipoHabitacionDAO.delete((TipoHabitacion)tableView.getSelectionModel().getSelectedItem());
        reloadData(tableView);
    }
    @Override
    public void reloadData(TableView tableView){
        tableView.setItems(tipoHabitacionDAO.fetchAll());
    }

}
