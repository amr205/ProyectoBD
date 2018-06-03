package sample.Database.Models;

public class Enfermedad {
    String CveEnfermedad,Descripcion;

    public Enfermedad(String cveEnfermedad, String descripcion) {
        CveEnfermedad = cveEnfermedad;
        Descripcion = descripcion;
    }

    public String getCveEnfermedad() {
        return CveEnfermedad;
    }

    public void setCveEnfermedad(String cveEnfermedad) {
        CveEnfermedad = cveEnfermedad;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String descripcion) {
        Descripcion = descripcion;
    }
}
