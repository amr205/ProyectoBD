package sample.Database.Models;

public class Medicamento {
    String CveMedicamento,CveTipoMedicamento,Nombre;

    public Medicamento(String cveMedicamento, String cveTipoMedicamento, String nombre) {
        CveMedicamento = cveMedicamento;
        CveTipoMedicamento = cveTipoMedicamento;
        Nombre = nombre;
    }

    public String getCveMedicamento() {
        return CveMedicamento;
    }

    public void setCveMedicamento(String cveMedicamento) {
        CveMedicamento = cveMedicamento;
    }

    public String getCveTipoMedicamento() {
        return CveTipoMedicamento;
    }

    public void setCveTipoMedicamento(String cveTipoMedicamento) {
        CveTipoMedicamento = cveTipoMedicamento;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }
}
