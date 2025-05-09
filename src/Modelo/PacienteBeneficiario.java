package Modelo;

import java.util.ArrayList;

public class PacienteBeneficiario extends Paciente {
    private int edad;

    public PacienteBeneficiario(int id, String nombre, FichaMedica suFichaMedica, ArrayList<ServicioSalud> susServicios, int edad) {
        super(id, nombre, suFichaMedica, susServicios);
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    @Override
    public String toString() {
        return "Modelo.PacienteBeneficiario{" +
                "edad=" + edad +
                ", id=" + id +
                ", nombre='" + nombre + '\'' +
                ", suFichaMedica=" + suFichaMedica +
                ", susServicios=" + susServicios +
                '}';
    }

    public double calcularPorcentajeSalud() {
        double porcentajeReduccionPorServicio;
        if (edad > 30) {
            porcentajeReduccionPorServicio = 3.0;
        } else {
            porcentajeReduccionPorServicio = 1.5;
        }
        double reduccionTotal = 0;
        if (susServicios != null) {
            reduccionTotal = susServicios.size() * porcentajeReduccionPorServicio;
        }
        return 100.0 - reduccionTotal;
    }
}
