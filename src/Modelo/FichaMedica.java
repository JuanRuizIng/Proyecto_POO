package Modelo;

public class FichaMedica {
    private String tipoSangre;
    private double pesoKg;
    private int frecuenciaCardiaca;

    public FichaMedica(String tipoSangre, double pesoKg, int frecuenciaCardiaca) {
        this.tipoSangre = tipoSangre;
        this.pesoKg = pesoKg;
        this.frecuenciaCardiaca = frecuenciaCardiaca;
    }

    public String getTipoSangre() {
        return tipoSangre;
    }

    public void setTipoSangre(String tipoSangre) {
        this.tipoSangre = tipoSangre;
    }

    public double getPesoKg() {
        return pesoKg;
    }

    public void setPesoKg(double pesoKg) {
        this.pesoKg = pesoKg;
    }

    public int getFrecuenciaCardiaca() {
        return frecuenciaCardiaca;
    }

    public void setFrecuenciaCardiaca(int frecuenciaCardiaca) {
        this.frecuenciaCardiaca = frecuenciaCardiaca;
    }

    @Override
    public String toString() {
        return "Modelo.FichaMedica{" +
                "tipoSangre='" + tipoSangre + '\'' +
                ", pesoKg=" + pesoKg +
                ", frecuenciaCardiaca=" + frecuenciaCardiaca +
                '}';
    }
}
