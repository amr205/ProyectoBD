package sample.Database.Models;

import java.sql.Date;

public class Estudio {
    String CveEstudio,Licencia,CveTipoEstudio,Razon;
    Date Fecha;

    public Estudio(String cveEstudio, String licencia, String cveTipoEstudio, String razon, Date fecha) {
        CveEstudio = cveEstudio;
        Licencia = licencia;
        CveTipoEstudio = cveTipoEstudio;
        Razon = razon;
        Fecha = fecha;
    }

    public String getCveEstudio() {
        return CveEstudio;
    }

    public void setCveEstudio(String cveEstudio) {
        CveEstudio = cveEstudio;
    }

    public String getLicencia() {
        return Licencia;
    }

    public void setLicencia(String licencia) {
        Licencia = licencia;
    }

    public String getCveTipoEstudio() {
        return CveTipoEstudio;
    }

    public void setCveTipoEstudio(String cveTipoEstudio) {
        CveTipoEstudio = cveTipoEstudio;
    }

    public String getRazon() {
        return Razon;
    }

    public void setRazon(String razon) {
        Razon = razon;
    }

    public Date getFecha() {
        return Fecha;
    }

    public void setFecha(Date fecha) {
        Fecha = fecha;
    }
}
