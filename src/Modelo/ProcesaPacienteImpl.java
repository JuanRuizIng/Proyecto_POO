package Modelo;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class ProcesaPacienteImpl implements ProcesaPaciente {

    @Override
    public void ordenarPorId(ArrayList<Paciente> pacientes) {
        Collections.sort(pacientes, Comparator.comparingInt(Paciente::getIdentificacion));
    }

    @Override
    public String listarPacientes(ArrayList<Paciente> pacientes) {
        StringBuilder sb = new StringBuilder();
        for (Paciente paciente : pacientes) {
            sb.append(paciente.toString()).append("\n");
        }
        return sb.toString();
    }

    @Override
    public String listarAfiliado(ArrayList<Paciente> pacientes, int identificacionDada) {
        for (Paciente paciente : pacientes) {
            if (paciente.getIdentificacion() == identificacionDada) {
                return paciente.toString();
            }
        }
        return "Paciente no encontrado.";
    }
}