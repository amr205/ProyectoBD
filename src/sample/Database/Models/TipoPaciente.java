package sample.Database.Models;

public class TipoPaciente {
    String CveTipoPaciente,Descripcion;

    public TipoPaciente(String cveTipoPaciente, String descripcion) {
        CveTipoPaciente = cveTipoPaciente;
        Descripcion = descripcion;
    }

    public String getCveTipoPaciente() {
        return CveTipoPaciente;
    }

    public void setCveTipoPaciente(String cveTipoPaciente) {
        CveTipoPaciente = cveTipoPaciente;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String descripcion) {
        Descripcion = descripcion;
    }
}
