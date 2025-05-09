package Modelo;

import java.util.ArrayList;

public abstract class Paciente {
    protected int identificacion;
    protected String nombre;
    protected FichaMedica suFichaMedica;
    protected ArrayList<ServicioSalud> susServicios;

    public Paciente(int identificacion, String nombre, FichaMedica suFichaMedica, ArrayList<ServicioSalud> susServicios) {
        this.identificacion = identificacion;
        this.nombre = nombre;
        this.suFichaMedica = suFichaMedica;
        this.susServicios = new ArrayList<>();
    }

    public int getIdentificacion() {
        return identificacion;
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
                "id=" + identificacion +
                ", nombre='" + nombre + '\'' +
                ", suFichaMedica=" + suFichaMedica +
                ", susServicios=" + susServicios +
                '}';
    }

    public final String adicionarServicio(ServicioSalud nvoServicioS) {
        susServicios.addFirst(nvoServicioS);
        return nvoServicioS.calcularAnticipacion();
    }

    public abstract double calcularPorcentajeSalud();

}
