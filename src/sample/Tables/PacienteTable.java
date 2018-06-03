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
import sample.Database.PacienteDAO;
import sample.Database.Models.Paciente;
import sample.Database.MySQL;
import sample.Main;
import sample.controller.PacienteController;

import java.io.IOException;

public class PacienteTable extends ParentTable {
    private PacienteDAO pacienteDAO = new PacienteDAO(MySQL.getConnection());
    @Override
    public void initTable(TableView tableView){
        tableView.getItems().clear();
        tableView.getColumns().clear();

        TableColumn column = new TableColumn("Clave");
        column.setCellValueFactory(new PropertyValueFactory<Paciente,String>("cvePaciente"));

        TableColumn column1 = new TableColumn("Nombre");
        column1.setCellValueFactory(new PropertyValueFactory<Paciente,String>("Nombre"));

        TableColumn column2 = new TableColumn("CURP");
        column2.setCellValueFactory(new PropertyValueFactory<Paciente,String>("CURP"));

        TableColumn column3 = new TableColumn("Sexo");
        column3.setCellValueFactory(new PropertyValueFactory<Paciente,String>("sexo"));

        TableColumn column4 = new TableColumn("Edad");
        column4.setCellValueFactory(new PropertyValueFactory<Paciente,Integer>("edad"));

        TableColumn column5 = new TableColumn("Tipo paciente");
        column5.setCellValueFactory(new PropertyValueFactory<Paciente,Integer>("cveTipoPaciente"));

        tableView.getColumns().addAll(column,column1, column2, column3, column4, column5);

        reloadData(tableView);
    }
    @Override
    public void update(TableView tableView){
        Stage stage=new Stage();
        stage.setTitle("UPDATE");
        stage.setResizable(false);


        FXMLLoader loader= new FXMLLoader(Main.class.getResource("fxml/Paciente.fxml"));


        try {
            Parent parent = loader.load();
            PacienteController controller= loader.getController();
            controller.setTipo(1,(Paciente)tableView.getSelectionModel().getSelectedItem());
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


        FXMLLoader loader= new FXMLLoader(Main.class.getResource("fxml/Paciente.fxml"));


        try {
            Parent parent = loader.load();
            PacienteController controller= loader.getController();
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
        pacienteDAO.delete((Paciente)tableView.getSelectionModel().getSelectedItem());
        reloadData(tableView);
    }
    @Override
    public void reloadData(TableView tableView){
        tableView.setItems(pacienteDAO.fetchAll());
    }
}
