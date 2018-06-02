package sample.Database.Models;

public class SalaQuirofano {
    String CveSala,Descripcion;

    public SalaQuirofano(String cveSala, String descripcion) {
        CveSala = cveSala;
        Descripcion = descripcion;
    }

    public String getCveSala() {
        return CveSala;
    }

    public void setCveSala(String cveSala) {
        CveSala = cveSala;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String descripcion) {
        Descripcion = descripcion;
    }
}
