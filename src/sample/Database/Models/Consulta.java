package sample.Database.Models;

public class Consulta {
    String CveConsulta,CveConsultorio,CvePaciente,Licencia;

    public Consulta(String cveConsulta, String cveConsultorio, String cvePaciente, String licencia) {
        CveConsulta = cveConsulta;
        CveConsultorio = cveConsultorio;
        CvePaciente = cvePaciente;
        Licencia = licencia;
    }

    public String getCveConsulta() {
        return CveConsulta;
    }

    public void setCveConsulta(String cveConsulta) {
        CveConsulta = cveConsulta;
    }

    public String getCveConsultorio() {
        return CveConsultorio;
    }

    public void setCveConsultorio(String cveConsultorio) {
        CveConsultorio = cveConsultorio;
    }

    public String getCvePaciente() {
        return CvePaciente;
    }

    public void setCvePaciente(String cvePaciente) {
        CvePaciente = cvePaciente;
    }

    public String getLicencia() {
        return Licencia;
    }

    public void setLicencia(String licencia) {
        Licencia = licencia;
    }
}
