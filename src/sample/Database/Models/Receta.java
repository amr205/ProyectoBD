package sample.Database.Models;

public class Receta {
    String CveMedicamento,CveConsulta;

    public Receta(String cveMedicamento, String cveConsulta) {
        CveMedicamento = cveMedicamento;
        CveConsulta = cveConsulta;
    }

    public String getCveMedicamento() {
        return CveMedicamento;
    }

    public void setCveMedicamento(String cveMedicamento) {
        CveMedicamento = cveMedicamento;
    }

    public String getCveConsulta() {
        return CveConsulta;
    }

    public void setCveConsulta(String cveConsulta) {
        CveConsulta = cveConsulta;
    }
}
