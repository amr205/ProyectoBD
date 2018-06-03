package sample.Database.Models;

public class Asignado {
    String CvePaciente,CveEnfermera;

    public Asignado(String cvePaciente, String cveEnfermera) {
        CvePaciente = cvePaciente;
        CveEnfermera = cveEnfermera;
    }

    public String getCvePaciente() {
        return CvePaciente;
    }

    public void setCvePaciente(String cvePaciente) {
        CvePaciente = cvePaciente;
    }

    public String getCveEnfermera() {
        return CveEnfermera;
    }

    public void setCveEnfermera(String cveEnfermera) {
        CveEnfermera = cveEnfermera;
    }
}
