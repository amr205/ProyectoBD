package sample.Database.Models;

public class TipoHabitacion {
    String CveTipoHabitacion,Descripcion;

    public TipoHabitacion(String cveTipoHabitacion, String descripcion) {
        CveTipoHabitacion = cveTipoHabitacion;
        Descripcion = descripcion;
    }

    public String getCveTipoHabitacion() {
        return CveTipoHabitacion;
    }

    public void setCveTipoHabitacion(String cveTipoHabitacion) {
        CveTipoHabitacion = cveTipoHabitacion;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String descripcion) {
        Descripcion = descripcion;
    }
}
