package sample.Database.Models;

public class Enfermera {
    String CveEnfermera,Nombre;
    int Edad;

    public Enfermera(String cveEnfermera, String nombre, int edad) {
        CveEnfermera = cveEnfermera;
        Nombre = nombre;
        Edad = edad;
    }

    public String getCveEnfermera() {
        return CveEnfermera;
    }

    public void setCveEnfermera(String cveEnfermera) {
        CveEnfermera = cveEnfermera;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public int getEdad() {
        return Edad;
    }

    public void setEdad(int edad) {
        Edad = edad;
    }
}
