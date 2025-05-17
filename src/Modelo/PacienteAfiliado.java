package Modelo;

import java.io.Serializable;
import java.util.ArrayList;

public class PacienteAfiliado extends Paciente implements Serializable {
    private int numAfiliados;
    //private static final long serialVersionUID = 1L;

    public PacienteAfiliado(int identificacion, String nombre, FichaMedica suFichaMedica, ArrayList<ServicioSalud> susServicios, int numAfiliados) {
        super(identificacion, nombre, suFichaMedica, susServicios);
        this.numAfiliados = numAfiliados;
    }

    public int getNumAfiliados() {
        return numAfiliados;
    }

    public void setNumAfiliados(int numAfiliados) {
        this.numAfiliados = numAfiliados;
    }

    @Override
    public String toString() {
        return "Paciente Afiliado: \n" +
                "Identificacion: " + identificacion +
                ", Nombre: " + nombre +
                ", Número de Afiliados: " + numAfiliados +
                ", Tipo de Sangre: " + suFichaMedica.getTipoSangre() +
                ", Peso (Kg): " + suFichaMedica.getPesoKg() +
                ", Frecuencia Cardiaca: " + suFichaMedica.getFrecuenciaCardiaca();
    }

    @Override
    public double calcularPorcentajeSalud() {
        double porcentajeReduccionPorServicio;
        if (suFichaMedica.getFrecuenciaCardiaca() < 110) {
            porcentajeReduccionPorServicio = 4.0;
        } else {
            porcentajeReduccionPorServicio = 6.0;
        }
        double reduccionTotal = 0;
        if (susServicios != null) {
            reduccionTotal = susServicios.size() * porcentajeReduccionPorServicio;
        }
        return 100.0 - reduccionTotal;
    }
}
