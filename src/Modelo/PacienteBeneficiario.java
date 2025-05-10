package Modelo;

import java.io.Serializable;
import java.util.ArrayList;

public class PacienteBeneficiario extends Paciente implements Serializable {
    private int edad;
    //private static final long serialVersionUID = 1L;

    public PacienteBeneficiario(int identificacion, String nombre, FichaMedica suFichaMedica, ArrayList<ServicioSalud> susServicios, int edad) {
        super(identificacion, nombre, suFichaMedica, susServicios);
        this.edad = edad;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    @Override
    public String toString() {
        return "Paciente Beneficiario: \n" +
                "  Identificacion: " + identificacion + "\n" +
                "  Nombre: " + nombre + "\n" +
                "  Edad: " + edad + "\n" +
                suFichaMedica  + "\n" +
                "  Sus Servicios de Salud Registrados: " + susServicios + "\n";
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
