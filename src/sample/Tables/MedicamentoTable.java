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
import sample.Database.MedicamentoDAO;
import sample.Database.Models.Medicamento;
import sample.Database.MySQL;
import sample.Main;
import sample.controller.MedicamentoController;

import java.io.IOException;

public class MedicamentoTable extends ParentTable {
    private MedicamentoDAO medicamentoDAO = new MedicamentoDAO(MySQL.getConnection());
    @Override
    public void initTable(TableView tableView){
        tableView.getItems().clear();
        tableView.getColumns().clear();

        TableColumn column = new TableColumn("Clave");
        column.setCellValueFactory(new PropertyValueFactory<Medicamento,String>("cveMedicamento"));

        TableColumn column1 = new TableColumn("Clave tipo");
        column1.setCellValueFactory(new PropertyValueFactory<Medicamento,String>("cveTipoMedicamento"));

        TableColumn column2 = new TableColumn("Nombre");
        column2.setCellValueFactory(new PropertyValueFactory<Medicamento,String>("nombre"));

        tableView.getColumns().addAll(column,column1, column2);

        reloadData(tableView);
    }
    @Override
    public void update(TableView tableView){
        Stage stage=new Stage();
        stage.setTitle("UPDATE");
        stage.setResizable(false);


        FXMLLoader loader= new FXMLLoader(Main.class.getResource("fxml/Medicamento.fxml"));


        try {
            Parent parent = loader.load();
            MedicamentoController controller= loader.getController();
            controller.setTipo(1,(Medicamento)tableView.getSelectionModel().getSelectedItem());
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


        FXMLLoader loader= new FXMLLoader(Main.class.getResource("fxml/Medicamento.fxml"));


        try {
            Parent parent = loader.load();
            MedicamentoController controller= loader.getController();
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
        medicamentoDAO.delete((Medicamento)tableView.getSelectionModel().getSelectedItem());
        reloadData(tableView);
    }
    @Override
    public void reloadData(TableView tableView){
        tableView.setItems(medicamentoDAO.fetchAll());
    }
}
