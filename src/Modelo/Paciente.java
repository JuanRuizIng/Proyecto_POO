package Modelo;

import java.io.Serializable;
import java.util.ArrayList;

public abstract class Paciente implements Serializable {

    //private static final long serialVersionUID = 2L;

    protected int identificacion;
    protected String nombre;
    protected FichaMedica suFichaMedica;
    protected ArrayList<ServicioSalud> susServicios;

    public Paciente(int identificacion, String nombre, FichaMedica suFichaMedica, ArrayList<ServicioSalud> susServicios) {
        this.identificacion = identificacion;
        this.nombre = nombre;
        this.suFichaMedica = suFichaMedica;
        this.susServicios = susServicios;
    }

    public int getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(int identificacion) {
        this.identificacion = identificacion;
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

    public ArrayList<ServicioSalud> getSusServicios() {
        return susServicios;
    }

    public void setSusServicios(ArrayList<ServicioSalud> susServicios) {
        this.susServicios = susServicios;
    }

    @Override
    public String toString() {
        return "Paciente\n" +
                "  Identificacion: " + identificacion + "\n" +
                "  Nombre: " + nombre + "\n" +
                "  Su Ficha Médica: " + suFichaMedica + "\n" +
                "  Sus Servicios de Salud Registrados: " + susServicios + "\n";
    }

    public final String adicionarServicio(ServicioSalud nvoServicioS) {
        susServicios.addFirst(nvoServicioS);
        return nvoServicioS.calcularAnticipacion();
    }

    public abstract double calcularPorcentajeSalud();

}
