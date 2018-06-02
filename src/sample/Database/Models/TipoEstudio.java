package sample.Database.Models;

public class TipoEstudio {
    String CveTipoEstudio,Descripcion;

    public TipoEstudio(String cveTipoEstudio, String descripcion) {
        CveTipoEstudio = cveTipoEstudio;
        Descripcion = descripcion;
    }

    public String getCveTipoEstudio() {
        return CveTipoEstudio;
    }

    public void setCveTipoEstudio(String cveTipoEstudio) {
        CveTipoEstudio = cveTipoEstudio;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String descripcion) {
        Descripcion = descripcion;
    }
}
