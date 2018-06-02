package sample.Database.Models;

public class Doctor {
    String Licencia,CveEspecialidad,Nombre,CURP,Sexo;
    int Edad;

    public Doctor(String licencia, String cveEspecialidad, String nombre, String CURP, String sexo, int edad) {
        Licencia = licencia;
        CveEspecialidad = cveEspecialidad;
        Nombre = nombre;
        this.CURP = CURP;
        Sexo = sexo;
        Edad = edad;
    }

    public String getLicencia() {
        return Licencia;
    }

    public void setLicencia(String licencia) {
        Licencia = licencia;
    }

    public String getCveEspecialidad() {
        return CveEspecialidad;
    }

    public void setCveEspecialidad(String cveEspecialidad) {
        CveEspecialidad = cveEspecialidad;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getCURP() {
        return CURP;
    }

    public void setCURP(String CURP) {
        this.CURP = CURP;
    }

    public String getSexo() {
        return Sexo;
    }

    public void setSexo(String sexo) {
        Sexo = sexo;
    }

    public int getEdad() {
        return Edad;
    }

    public void setEdad(int edad) {
        Edad = edad;
    }
}
