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
import sample.Database.DiagnosticoDAO;
import sample.Database.Models.Diagnostico;
import sample.Database.MySQL;
import sample.Main;
import sample.controller.DiagnosticoController;

import java.io.IOException;

public class DiagnosticoTable extends ParentTable {
    private DiagnosticoDAO diagnosticoDAO = new DiagnosticoDAO(MySQL.getConnection());
    @Override
    public void initTable(TableView tableView){
        tableView.getItems().clear();
        tableView.getColumns().clear();

        TableColumn column = new TableColumn("clave consulta");
        column.setCellValueFactory(new PropertyValueFactory<Diagnostico,String>("cveConsulta"));

        TableColumn column1 = new TableColumn("numero");
        column1.setCellValueFactory(new PropertyValueFactory<Diagnostico,Integer>("numero"));

        TableColumn column2 = new TableColumn("clave enfermedad");
        column2.setCellValueFactory(new PropertyValueFactory<Diagnostico,String>("cveEnfermedad"));

        TableColumn column3 = new TableColumn("descripcion");
        column3.setCellValueFactory(new PropertyValueFactory<Diagnostico,String>("descripcion"));


        tableView.getColumns().addAll(column,column1, column2, column3);

        reloadData(tableView);
    }
    @Override
    public void update(TableView tableView){
        Stage stage=new Stage();
        stage.setTitle("UPDATE");
        stage.setResizable(false);


        FXMLLoader loader= new FXMLLoader(Main.class.getResource("fxml/Diagnostico.fxml"));


        try {
            Parent parent = loader.load();
            DiagnosticoController controller= loader.getController();
            controller.setTipo(1,(Diagnostico)tableView.getSelectionModel().getSelectedItem());
            Scene scene=new Scene(parent,400,300);
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


        FXMLLoader loader= new FXMLLoader(Main.class.getResource("fxml/Diagnostico.fxml"));


        try {
            Parent parent = loader.load();
            DiagnosticoController controller= loader.getController();
            Scene scene=new Scene(parent,400,300);
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
        diagnosticoDAO.delete((Diagnostico)tableView.getSelectionModel().getSelectedItem());
        reloadData(tableView);
    }
    @Override
    public void reloadData(TableView tableView){
        tableView.setItems(diagnosticoDAO.fetchAll());
    }
}
