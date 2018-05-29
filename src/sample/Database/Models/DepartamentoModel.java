package sample.Database.Models;

public class DepartamentoModel {
    String cveDepartamento, nombre;

    public DepartamentoModel(String cveDepartamento, String nombre) {
        this.cveDepartamento = cveDepartamento;
        this.nombre = nombre;
    }

    public String getCveDepartamento() {
        return cveDepartamento;
    }

    public void setCveDepartamento(String cveDepartamento) {
        this.cveDepartamento = cveDepartamento;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
