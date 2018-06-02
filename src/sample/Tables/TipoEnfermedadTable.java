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
import sample.Database.Models.TipoEnfermedad;
import sample.Database.MySQL;
import sample.Database.TipoEnfermedadDAO;
import sample.Main;
import sample.controller.TipoEnfermedadController;

import java.io.IOException;

public class TipoEnfermedadTable extends ParentTable {

    private TipoEnfermedadDAO tipoEnfermedadDAO = new TipoEnfermedadDAO(MySQL.getConnection());

    @Override
    public void initTable(TableView tableView){
        tableView.getItems().clear();
        tableView.getColumns().clear();

        TableColumn column = new TableColumn("clave");
        column.setCellValueFactory(new PropertyValueFactory<TipoEnfermedad,String>("CveTipoEnfermedad"));

        TableColumn column1 = new TableColumn("descripcion");
        column1.setCellValueFactory(new PropertyValueFactory<TipoEnfermedad,String>("Descripcion"));

        tableView.getColumns().addAll(column,column1);

        reloadData(tableView);
    }
    @Override
    public void update(TableView tableView){
        Stage stage=new Stage();
        stage.setTitle("UPDATE");
        stage.setResizable(false);


        FXMLLoader loader= new FXMLLoader(Main.class.getResource("fxml/TipoEnfermedad.fxml"));


        try {
            Parent parent = loader.load();
            TipoEnfermedadController controller= loader.getController();
            controller.setTipo(1,(TipoEnfermedad)tableView.getSelectionModel().getSelectedItem());
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


        FXMLLoader loader= new FXMLLoader(Main.class.getResource("fxml/TipoEnfermedad.fxml"));


        try {
            Parent parent = loader.load();
            TipoEnfermedadController controller= loader.getController();
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
        tipoEnfermedadDAO.delete((TipoEnfermedad)tableView.getSelectionModel().getSelectedItem());
        reloadData(tableView);
    }
    @Override
    public void reloadData(TableView tableView){
        tableView.setItems(tipoEnfermedadDAO.fetchAll());
    }

}
