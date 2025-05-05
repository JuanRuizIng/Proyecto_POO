package Modelo;

import java.time.LocalDate;

public class ServicioSalud {
    private String tipoServicio;
    private LocalDate fecha;
    private int duracionMinutos;

    public ServicioSalud(String tipoServicio, LocalDate fecha, int duracionMinutos) {
        this.tipoServicio = tipoServicio;
        this.fecha = fecha;
        this.duracionMinutos = duracionMinutos;
    }

    public String getTipoServicio() {
        return tipoServicio;
    }

    public void setTipoServicio(String tipoServicio) {
        this.tipoServicio = tipoServicio;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public int getDuracionMinutos() {
        return duracionMinutos;
    }

    public void setDuracionMinutos(int duracionMinutos) {
        this.duracionMinutos = duracionMinutos;
    }

    @Override
    public String toString() {
        return "Modelo.ServicioSalud{" +
                "tipoServicio='" + tipoServicio + '\'' +
                ", fecha=" + fecha +
                ", duracionMinutos=" + duracionMinutos +
                '}';
    }
}
