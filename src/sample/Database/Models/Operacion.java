package sample.Database.Models;

import java.sql.Date;

public class Operacion {
    String CvePaciente,CveSala,Descripcion;
    int Numero;
    Date Fecha;

    public Operacion(String cvePaciente, String cveSala, String descripcion, int numero, Date fecha) {
        CvePaciente = cvePaciente;
        CveSala = cveSala;
        Descripcion = descripcion;
        Numero = numero;
        Fecha = fecha;
    }

    public String getCvePaciente() {
        return CvePaciente;
    }

    public void setCvePaciente(String cvePaciente) {
        CvePaciente = cvePaciente;
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

    public int getNumero() {
        return Numero;
    }

    public void setNumero(int numero) {
        Numero = numero;
    }

    public Date getFecha() {
        return Fecha;
    }

    public void setFecha(Date fecha) {
        Fecha = fecha;
    }
}
