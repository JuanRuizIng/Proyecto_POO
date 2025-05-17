package Modelo;

import java.util.ArrayList;

public interface ProcesaPaciente {
    void ordenarPorId(ArrayList<Paciente> pacientes);
    String listarPacientes(ArrayList<Paciente> pacientes);
    String listarAfiliado(ArrayList<Paciente> pacientes, int identificacionDada);
    void almacenarArchivoObjetos(ArrayList<Paciente> pacientes);
    void recuperarArchivoObjetos(ArrayList<Paciente> pacientes);
}
