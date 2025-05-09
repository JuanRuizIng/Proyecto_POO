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
        return "Servicio de Salud:\n" +
                "  Tipo de Servicio: " + tipoServicio + "\n" +
                "  Fecha: " + fecha + "\n" +
                "  Duración (en minutos): " + duracionMinutos + "\n";
    }

    public String calcularAnticipacion() {
        if (tipoServicio.equals("GENERAL") || tipoServicio.equals("LABORATORIO")) {
            return "20 Minutos Antes";
        } else {
            return "40 Minutos Antes";
        }
    }

}
