package sample.Database.Models;

public class Paciente {
    String CvePaciente,CveTipoPaciente,Nombre,Sexo,CURP;
    int Edad;

    public Paciente(String cvePaciente, String cveTipoPaciente, String nombre, String sexo, int edad, String CURP) {
        CvePaciente = cvePaciente;
        CveTipoPaciente = cveTipoPaciente;
        Nombre = nombre;
        Sexo = sexo;
        this.CURP = CURP;
        Edad = edad;
    }

    public String getCvePaciente() {
        return CvePaciente;
    }

    public void setCvePaciente(String cvePaciente) {
        CvePaciente = cvePaciente;
    }

    public String getCveTipoPaciente() {
        return CveTipoPaciente;
    }

    public void setCveTipoPaciente(String cveTipoPaciente) {
        CveTipoPaciente = cveTipoPaciente;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getSexo() {
        return Sexo;
    }

    public void setSexo(String sexo) {
        Sexo = sexo;
    }

    public String getCURP() {
        return CURP;
    }

    public void setCURP(String CURP) {
        this.CURP = CURP;
    }

    public int getEdad() {
        return Edad;
    }

    public void setEdad(int edad) {
        Edad = edad;
    }
}
