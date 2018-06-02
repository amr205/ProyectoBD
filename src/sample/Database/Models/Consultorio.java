package sample.Database.Models;

public class Consultorio {
    String CveConsultorio,CveDepartamento;

    public Consultorio(String cveConsultorio, String cveDepartamento) {
        CveConsultorio = cveConsultorio;
        CveDepartamento = cveDepartamento;
    }

    public String getCveConsultorio() {
        return CveConsultorio;
    }

    public void setCveConsultorio(String cveConsultorio) {
        CveConsultorio = cveConsultorio;
    }

    public String getCveDepartamento() {
        return CveDepartamento;
    }

    public void setCveDepartamento(String cveDepartamento) {
        CveDepartamento = cveDepartamento;
    }
}
