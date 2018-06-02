package sample.Database.Models;

public class EspecialidadDoctor {
    String CveEspecialidad,Descripcion;

    public EspecialidadDoctor(String cveEspecialidad, String descripcion) {
        CveEspecialidad = cveEspecialidad;
        Descripcion = descripcion;
    }

    public String getCveEspecialidad() {
        return CveEspecialidad;
    }

    public void setCveEspecialidad(String cveEspecialidad) {
        CveEspecialidad = cveEspecialidad;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String descripcion) {
        Descripcion = descripcion;
    }
}
