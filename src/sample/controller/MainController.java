package sample.controller;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableView;
import sample.Tables.*;

import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {
    @FXML
    MenuItem departamentoMI, tipoMedicamentoMI, especialidadDoctorMI,tipoEnfermedadMI,tipoPacienteMI,tipoHabitacionMI,tipoEstudioMI,salaQuirofanoMI,
            enfermeraMI, doctorMI, medicamentoMI, consultorioMI, laboratoriosMI, pacienteMI, estudioMI,habitacionMI,asignadosMI, operacionMI,
            consultaMI, recetaMI,diagnosticoMI;

    @FXML
    TableView table;

    //lista de las tablas
    private DepartamentoTable departamentoTable = new DepartamentoTable();
    private TipoMedicamentoTable tipoMedicamentoTable = new TipoMedicamentoTable();
    private EspecialidadDoctorTable especialidadDoctorTable = new EspecialidadDoctorTable();
    private EnfermedadTable tipoEnfermedadTable = new EnfermedadTable();
    private TipoPacienteTable tipoPacienteTable = new TipoPacienteTable();
    private TipoHabitacionTable tipoHabitacionTable = new TipoHabitacionTable();
    private TipoEstudioTable tipoEstudioTable = new TipoEstudioTable();
    private SalaQuirofanoTable salaQuirofanoTable = new SalaQuirofanoTable();
    private EnfermeraTable enfermeraTable = new EnfermeraTable();
    private DoctorTable doctorTable = new DoctorTable();
    private MedicamentoTable medicamentoTable = new MedicamentoTable();
    private ConsultorioTable consultorioTable = new ConsultorioTable();
    private LaboratoriosTable laboratoriosTable = new LaboratoriosTable();
    private PacienteTable pacienteTable = new PacienteTable();
    private EstudioTable estudioTable = new EstudioTable();
    private HabitacionTable habitacionTable = new HabitacionTable();
    private AsignadoTable asignadoTable = new AsignadoTable();
    private OperacionTable operacionTable = new OperacionTable();
    private ConsultaTable consultaTable = new ConsultaTable();
    private RecetaTable recetaTable = new RecetaTable();
    private DiagnosticoTable diagnosticoTable = new DiagnosticoTable();

    //dummyTable
    private ParentTable parentTable = new ParentTable();


    private EventHandler eventHandler = new EventHandler() {
        @Override
        public void handle(Event event) {
            if(event.getSource()==departamentoMI)
                parentTable= departamentoTable;
            else if(event.getSource()==tipoMedicamentoMI)
                parentTable= tipoMedicamentoTable;
            else if(event.getSource()==especialidadDoctorMI)
                parentTable= especialidadDoctorTable;
            else if(event.getSource()==tipoEnfermedadMI)
                parentTable= tipoEnfermedadTable;
            else if(event.getSource()==tipoPacienteMI)
                parentTable= tipoPacienteTable;
            else if(event.getSource()==tipoHabitacionMI)
                parentTable= tipoHabitacionTable;
            else if(event.getSource()==tipoEstudioMI)
                parentTable= tipoEstudioTable;
            else if(event.getSource()==salaQuirofanoMI)
                parentTable= salaQuirofanoTable;
            else if(event.getSource()==enfermeraMI)
                parentTable= enfermeraTable;
            else if(event.getSource()==doctorMI)
                parentTable= doctorTable;
            else if(event.getSource()==medicamentoMI)
                parentTable= medicamentoTable;
            else if(event.getSource()==consultorioMI)
                parentTable= consultorioTable;
            else if(event.getSource()==laboratoriosMI)
                parentTable= laboratoriosTable;
            else if(event.getSource()==pacienteMI)
                parentTable= pacienteTable;
            else if(event.getSource()==estudioMI)
                parentTable= estudioTable;
            else if(event.getSource()==habitacionMI)
                parentTable= habitacionTable;
            else if(event.getSource()==asignadosMI)
                parentTable= asignadoTable;
            else if(event.getSource()==operacionMI)
                parentTable= operacionTable;
            else if(event.getSource()==consultaMI)
                parentTable= consultaTable;
            else if(event.getSource()==recetaMI)
                parentTable= recetaTable;
            else if(event.getSource()==diagnosticoMI)
                parentTable= diagnosticoTable;


            updateData();
        }
    };



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //initialize data
        parentTable= departamentoTable;
        updateData();

        //addHandler to all menu Items
        departamentoMI.setOnAction(eventHandler);
        tipoMedicamentoMI.setOnAction(eventHandler);
        especialidadDoctorMI.setOnAction(eventHandler);
        tipoEnfermedadMI.setOnAction(eventHandler);
        tipoPacienteMI.setOnAction(eventHandler);
        tipoHabitacionMI.setOnAction(eventHandler);
        tipoEstudioMI.setOnAction(eventHandler);
        salaQuirofanoMI.setOnAction(eventHandler);
        enfermeraMI.setOnAction(eventHandler);
        doctorMI.setOnAction(eventHandler);
        medicamentoMI.setOnAction(eventHandler);
        consultorioMI.setOnAction(eventHandler);
        laboratoriosMI.setOnAction(eventHandler);
        pacienteMI.setOnAction(eventHandler);
        estudioMI.setOnAction(eventHandler);
        habitacionMI.setOnAction(eventHandler);
        asignadosMI.setOnAction(eventHandler);
        operacionMI.setOnAction(eventHandler);
        consultaMI.setOnAction(eventHandler);
        recetaMI.setOnAction(eventHandler);
        diagnosticoMI.setOnAction(eventHandler);

    }

    private void updateData() {
        parentTable.initTable(table);
    }

    public void create(ActionEvent actionEvent) {
        parentTable.create(table);
    }

    public void update(ActionEvent actionEvent) {
        if(table.getSelectionModel().getSelectedItem()!=null)
            parentTable.update(table);
        else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("No se puede realizar la acción");
            alert.setContentText("Seleccione un elemento de la tabla");
            alert.show();
        }
    }

    public void delete(ActionEvent actionEvent) {
        if(table.getSelectionModel().getSelectedItem()!=null)
            parentTable.delete(table);
        else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("No se puede realizar la acción");
            alert.setContentText("Seleccione un elemento de la tabla");
            alert.show();
        }
    }

}
