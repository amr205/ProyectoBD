package sample.Database.Models;

public class Habitacion {
    String CveHabitacion,CveTipoHabitacion,CvePaciente,Descripcion;

    public Habitacion(String cveHabitacion, String cveTipoHabitacion, String cvePaciente, String descripcion) {
        CveHabitacion = cveHabitacion;
        CveTipoHabitacion = cveTipoHabitacion;
        CvePaciente = cvePaciente;
        Descripcion = descripcion;
    }

    public String getCveHabitacion() {
        return CveHabitacion;
    }

    public void setCveHabitacion(String cveHabitacion) {
        CveHabitacion = cveHabitacion;
    }

    public String getCveTipoHabitacion() {
        return CveTipoHabitacion;
    }

    public void setCveTipoHabitacion(String cveTipoHabitacion) {
        CveTipoHabitacion = cveTipoHabitacion;
    }

    public String getCvePaciente() {
        return CvePaciente;
    }

    public void setCvePaciente(String cvePaciente) {
        CvePaciente = cvePaciente;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String descripcion) {
        Descripcion = descripcion;
    }
}
