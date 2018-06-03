package sample.Database.Models;

public class Diagnostico {
    String CveConsulta,CveEnfermedad,Descripcion;
    int Numero;

    public Diagnostico(String cveConsulta, String cveEnfermedad, String descripcion, int numero) {
        CveConsulta = cveConsulta;
        CveEnfermedad = cveEnfermedad;
        Descripcion = descripcion;
        Numero = numero;
    }

    public String getCveConsulta() {
        return CveConsulta;
    }

    public void setCveConsulta(String cveConsulta) {
        CveConsulta = cveConsulta;
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

    public int getNumero() {
        return Numero;
    }

    public void setNumero(int numero) {
        Numero = numero;
    }
}
