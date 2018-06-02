package sample.Database.Models;

public class TipoEnfermedad {
    String CveTipoEnfermedad,Descripcion;

    public TipoEnfermedad(String cveTipoEnfermedad, String descripcion) {
        CveTipoEnfermedad = cveTipoEnfermedad;
        Descripcion = descripcion;
    }

    public String getCveTipoEnfermedad() {
        return CveTipoEnfermedad;
    }

    public void setCveTipoEnfermedad(String cveTipoEnfermedad) {
        CveTipoEnfermedad = cveTipoEnfermedad;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String descripcion) {
        Descripcion = descripcion;
    }
}
