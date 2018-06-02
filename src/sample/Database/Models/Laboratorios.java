package sample.Database.Models;

public class Laboratorios {
    String CveLaboratorios,CveDepartamento,Descripcion;

    public Laboratorios(String cveLaboratorios, String cveDepartamento, String descripcion) {
        CveLaboratorios = cveLaboratorios;
        CveDepartamento = cveDepartamento;
        Descripcion = descripcion;
    }

    public String getCveLaboratorios() {
        return CveLaboratorios;
    }

    public void setCveLaboratorios(String cveLaboratorios) {
        CveLaboratorios = cveLaboratorios;
    }

    public String getCveDepartamento() {
        return CveDepartamento;
    }

    public void setCveDepartamento(String cveDepartamento) {
        CveDepartamento = cveDepartamento;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String descripcion) {
        Descripcion = descripcion;
    }
}
