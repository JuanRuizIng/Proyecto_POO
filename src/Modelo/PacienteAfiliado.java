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
}
