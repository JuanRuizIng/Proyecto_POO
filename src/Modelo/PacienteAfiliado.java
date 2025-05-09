package Modelo;

import java.util.ArrayList;

public class PacienteAfiliado extends Paciente {
    private int numAfiliados;

    public PacienteAfiliado(int id, String nombre, FichaMedica suFichaMedica, ArrayList<ServicioSalud> susServicios, int numAfiliados) {
        super(id, nombre, suFichaMedica, susServicios);
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
        return "Modelo.PacienteAfiliado{" +
                "numAfiliados=" + numAfiliados +
                ", id=" + id +
                ", nombre='" + nombre + '\'' +
                ", suFichaMedica=" + suFichaMedica +
                ", susServicios=" + susServicios +
                '}';
    }

    public double calcularPorcentajeSalud() {
        double porcentajeReduccionPorServicio;
        if (suFichaMedica.getFrecuenciaCardiaca() < 11) {
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
