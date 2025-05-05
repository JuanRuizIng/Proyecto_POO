package Modelo;

import java.util.ArrayList;

public abstract class Paciente {
    protected int id;
    protected String nombre;
    protected FichaMedica suFichaMedica;
    protected ArrayList<ServicioSalud> susServicios;

    public Paciente(int id, String nombre, FichaMedica suFichaMedica, ArrayList<ServicioSalud> susServicios) {
        this.id = id;
        this.nombre = nombre;
        this.suFichaMedica = suFichaMedica;
        this.susServicios = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public FichaMedica getSuFichaMedica() {
        return suFichaMedica;
    }

    public void setSuFichaMedica(FichaMedica suFichaMedica) {
        this.suFichaMedica = suFichaMedica;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return "Modelo.Paciente{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", suFichaMedica=" + suFichaMedica +
                ", susServicios=" + susServicios +
                '}';
    }
}
