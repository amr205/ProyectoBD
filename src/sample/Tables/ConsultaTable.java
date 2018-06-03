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
import sample.Database.ConsultaDAO;
import sample.Database.Models.Consulta;
import sample.Database.MySQL;
import sample.Main;
import sample.controller.ConsultaController;

import java.io.IOException;

public class ConsultaTable extends ParentTable {
    private ConsultaDAO asignadoDAO = new ConsultaDAO(MySQL.getConnection());
    @Override
    public void initTable(TableView tableView){
        tableView.getItems().clear();
        tableView.getColumns().clear();

        TableColumn column = new TableColumn("clave consulta");
        column.setCellValueFactory(new PropertyValueFactory<Consulta,String>("cveConsulta"));

        TableColumn column1 = new TableColumn("clave consultorio");
        column1.setCellValueFactory(new PropertyValueFactory<Consulta,String>("cveConsultorio"));

        TableColumn column2 = new TableColumn("clave paciente");
        column2.setCellValueFactory(new PropertyValueFactory<Consulta,String>("cvePaciente"));

        TableColumn column3 = new TableColumn("licencia doctor");
        column3.setCellValueFactory(new PropertyValueFactory<Consulta,String>("licencia"));


        tableView.getColumns().addAll(column,column1, column2, column3);

        reloadData(tableView);
    }
    @Override
    public void update(TableView tableView){
        Stage stage=new Stage();
        stage.setTitle("UPDATE");
        stage.setResizable(false);


        FXMLLoader loader= new FXMLLoader(Main.class.getResource("fxml/Consulta.fxml"));


        try {
            Parent parent = loader.load();
            ConsultaController controller= loader.getController();
            controller.setTipo(1,(Consulta)tableView.getSelectionModel().getSelectedItem());
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


        FXMLLoader loader= new FXMLLoader(Main.class.getResource("fxml/Consulta.fxml"));


        try {
            Parent parent = loader.load();
            ConsultaController controller= loader.getController();
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
        asignadoDAO.delete((Consulta)tableView.getSelectionModel().getSelectedItem());
        reloadData(tableView);
    }
    @Override
    public void reloadData(TableView tableView){
        tableView.setItems(asignadoDAO.fetchAll());
    }
}
