package sample.Database.Models;

public class TipoMedicamento {
    String CveTipoMedicamento,Descripcion;

    public TipoMedicamento(String cveTipoMedicamento, String descripcion) {
        CveTipoMedicamento = cveTipoMedicamento;
        Descripcion = descripcion;
    }

    public String getCveTipoMedicamento() {
        return CveTipoMedicamento;
    }

    public void setCveTipoMedicamento(String cveTipoMedicamento) {
        CveTipoMedicamento = cveTipoMedicamento;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String descripcion) {
        Descripcion = descripcion;
    }
}
