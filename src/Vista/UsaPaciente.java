/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Vista;

import Modelo.*;

import javax.swing.*;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.FileNotFoundException;
import java.io.NotSerializableException;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 *
 * @author LENOVO
 */
public class UsaPaciente extends javax.swing.JFrame implements ProcesaPaciente {

    ArrayList<Paciente> losPacientes = new ArrayList<>();

    private String nombreArchivo = "pacientes.obj";

    private boolean conDatosPrueba = true;

    @Override
    public void ordenarPorId(ArrayList<Paciente> pacientes) {
        pacientes.sort(new Comparator<Paciente>() {
            @Override
            public int compare(Paciente p1, Paciente p2) {
                return Integer.compare(p1.getIdentificacion(), p2.getIdentificacion());
            }
        });
    }

    @Override
    public String listarPacientes(ArrayList<Paciente> pacientes) {
        String res = "";
        for (Paciente paciente : pacientes) {
            String tipo;
            if (paciente instanceof PacienteAfiliado) {
                tipo = "AFILIADO";
            } else {
                tipo = "BENEFICIARIO";
            }
            String grupoSanguineo = paciente.getSuFichaMedica().getTipoSangre();
            double porcentajeSalud = paciente.calcularPorcentajeSalud();
            res += "Identificación: " + paciente.getIdentificacion()
                    + ", Nombre: " + paciente.getNombre()
                    + ", Tipo: " + tipo
                    + ", Grupo Sanguíneo: " + grupoSanguineo
                    + ", Porcentaje de Salud: " + porcentajeSalud + "%"
                    + "\n";
        }
        return res;
    }

    @Override
    public String listarAfiliado(ArrayList<Paciente> pacientes, int identificacionDada) {
        try {
            identificacionDada = Integer.parseInt(jTextField4.getText());
            for (Paciente paciente : pacientes) {
                if (paciente.getIdentificacion() == identificacionDada) {
                    String res = "";
                    res += paciente.toString();
                    res += "\n";
                    res += "Servicios de Salud: \n";
                    for (ServicioSalud servicio : paciente.getSusServicios()) {
                        res += servicio.toString();
                    }
                    return res;
                }
            }
            String res = "No se encontró el paciente con la identificación dada";
            JOptionPane.showMessageDialog(null, res);
            return res;
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Error: La identificación no es un número entero. Intente de nuevo");
            return "";
        }
    }

    @Override
    public void almacenarArchivoObjetos(ArrayList<Paciente> pacientes) {
        ObjectOutputStream salida=null;
        try {
            salida = new ObjectOutputStream(
                    new FileOutputStream( nombreArchivo ));
            salida.writeObject(pacientes);
        } catch (IOException e) {
            System.out.println("Error de entrada/salida: " + e.getMessage());
        } finally {
            try {
                if (salida != null) salida.close();
            } catch (IOException e) {
                System.out.println("Error cerrando el archivo");
            }
        }
    }

    @Override
    public void recuperarArchivoObjetos(ArrayList<Paciente> pacientes) {
        ArrayList<Paciente> pacientesRecuperados = null;
        ObjectInputStream entrada = null;

        try {
            entrada = new ObjectInputStream(
                    new FileInputStream( nombreArchivo ));
            pacientesRecuperados = (ArrayList<Paciente>) entrada.readObject();

            pacientes.clear();

            pacientes.addAll(pacientesRecuperados);

        } catch (IOException e) {
            System.out.println("Error de entrada/salida: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println("Clase no encontrada: " + e.getMessage());
        } finally {
            try {
                if (entrada != null) entrada.close();
            } catch (IOException e) {
                System.out.println("Error cerrando el archivo");
            }
        }
    }

    public void llenarDatosPrueba(ArrayList<Paciente> losPacientes) {
        ArrayList<ServicioSalud> servicios1 = new ArrayList<>();
        servicios1.add(new ServicioSalud("GENERAL", LocalDate.parse("2024-06-01"), 30));
        servicios1.add(new ServicioSalud("LABORATORIO", LocalDate.parse("2024-06-05"), 20));

        ArrayList<ServicioSalud> servicios2 = new ArrayList<>();
        servicios2.add(new ServicioSalud("MEDICAMENTO", LocalDate.parse("2024-06-02"), 10));

        ArrayList<ServicioSalud> servicios3 = new ArrayList<>();
        servicios3.add(new ServicioSalud("GENERAL", LocalDate.parse("2024-06-03"), 25));
        servicios3.add(new ServicioSalud("LABORATORIO", LocalDate.parse("2024-06-06"), 15));
        servicios3.add(new ServicioSalud("MEDICAMENTO", LocalDate.parse("2024-06-07"), 5));

        losPacientes.addFirst(new PacienteBeneficiario(123456, "Juan Perez", new FichaMedica("O+", 70.0, 12), servicios1, 25));
        losPacientes.addFirst(new PacienteAfiliado(654321, "Maria Lopez", new FichaMedica("A-", 60.0, 10), servicios2, 3));
        losPacientes.addFirst(new PacienteBeneficiario(345678, "Carlos Garcia", new FichaMedica("B+", 80.0, 15), new ArrayList<>(), 35));
        losPacientes.addFirst(new PacienteAfiliado(789012, "Ana Torres", new FichaMedica("O-", 55.0, 8), servicios3, 2));
        losPacientes.addFirst(new PacienteBeneficiario(267890, "Luis Martinez", new FichaMedica("O-", 75.0, 14), new ArrayList<>(), 40));
        losPacientes.addFirst(new PacienteAfiliado(901234, "Sofia Ramirez", new FichaMedica("A+", 65.0, 11), servicios1, 4));
        losPacientes.addFirst(new PacienteBeneficiario(234567, "Pedro Sanchez", new FichaMedica("B-", 85.0, 9), servicios2, 30));
        losPacientes.addFirst(new PacienteAfiliado(890123, "Laura Gomez", new FichaMedica("O+", 72.0, 13), new ArrayList<>(), 5));
    }

    /**
     * Creates new form UsaPaciente
     */
    public UsaPaciente() {
        initComponents();

        if (conDatosPrueba){
            llenarDatosPrueba(losPacientes);
        }else{
            JOptionPane.showMessageDialog(null,"No se usarán datos de prueba");
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jComboBox2 = new javax.swing.JComboBox<>();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jTextField6 = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jComboBox3 = new javax.swing.JComboBox<>();
        jLabel15 = new javax.swing.JLabel();
        jTextField7 = new javax.swing.JTextField();
        ingresarPaciente = new javax.swing.JButton();
        ingresarServicioSalud = new javax.swing.JButton();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jTextField9 = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        jTextField10 = new javax.swing.JTextField();
        jTextField11 = new javax.swing.JTextField();
        jTextField12 = new javax.swing.JTextField();
        jTextField3 = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        ejecutarReporte = new javax.swing.JButton();
        jComboBox4 = new javax.swing.JComboBox<>();
        jLabel18 = new javax.swing.JLabel();
        jTextField4 = new javax.swing.JTextField();
        salir = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel1.setText("Paciente");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel2.setText("Ficha Médica");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel3.setText("Servicio de Salud Recibido");

        jLabel4.setText("Identificación");

        jTextField1.setText("jTextField1");

        jLabel5.setText("Nombre");

        jTextField2.setText("jTextField2");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel6.setText("Tipo de Paciente");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "AFILIADO", "BENEFICIARIO" }));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel7.setText("Paciente Beneficiario");

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel8.setText("Paciente Afiliado");

        jLabel9.setText("Numero de Afiliados");

        jLabel10.setText("Edad");

        jLabel11.setText("Tipo de Sangre");

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "O+", "O-", "A+", "A-", "B+", "B-" }));

        jLabel12.setText("Peso en Kilogramos");

        jLabel13.setText("Frecuencia Cardiaca");

        jTextField6.setText("jTextField6");

        jLabel14.setText("Tipo de Servicio");

        jComboBox3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "GENERAL", "LABORATORIO", "MEDICAMENTO" }));

        jLabel15.setText("Duración en Minutos");

        jTextField7.setText("jTextField7");

        ingresarPaciente.setText("Ingresar Paciente");
        ingresarPaciente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ingresarPacienteActionPerformed(evt);
            }
        });

        ingresarServicioSalud.setText("Ingresar Servicio de Salud");
        ingresarServicioSalud.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ingresarServicioSaludActionPerformed(evt);
            }
        });

        jLabel16.setText("Identificación del Paciente");

        jLabel17.setText("que Recibe el Servicio");

        jTextField9.setText("jTextField9");

        jLabel19.setText("Fecha del Servicio");

        jTextField10.setText("jTextField10");

        jTextField11.setText("jTextField11");

        jTextField12.setText("jTextField12");

        jTextField3.setText("jTextField3");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel9)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextField11, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 87, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel7)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel10)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextField12, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(55, 55, 55)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(45, 45, 45))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(12, 12, 12)
                                .addComponent(jLabel1))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(103, 103, 103)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel12)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel13)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(ingresarPaciente)
                        .addGap(18, 18, 18)
                        .addComponent(ingresarServicioSalud)
                        .addGap(34, 34, 34))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel14)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel15)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jLabel17, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel16, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextField9, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel19)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextField10, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(158, 158, 158))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel4)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel11)
                        .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel14)
                        .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel5)
                        .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel12)
                        .addComponent(jLabel15)
                        .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel16)
                        .addGap(1, 1, 1)
                        .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jTextField9, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel13)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel19)
                    .addComponent(jTextField10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 29, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel6))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(ingresarPaciente)
                                .addComponent(ingresarServicioSalud))
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel10)
                                .addComponent(jTextField12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(6, 6, 6))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(jTextField11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap())))
        );

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        ejecutarReporte.setText("Reporte");
        ejecutarReporte.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ejecutarReporteActionPerformed(evt);
            }
        });

        jComboBox4.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Listado de los pacientes registrados", "Listado de datos del paciente con identificación suministrada" }));

        jLabel18.setText("Identificación del paciente a buscar");

        jTextField4.setText("jTextField4");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(176, 176, 176)
                .addComponent(jComboBox4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(ejecutarReporte)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel18)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(14, 14, 14))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 289, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ejecutarReporte)
                    .addComponent(jComboBox4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel18)
                    .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        salir.setText("Salir");
        salir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                salirActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(salir)
                .addGap(429, 429, 429))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(21, 21, 21))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(salir)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void salirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_salirActionPerformed
        almacenarArchivoObjetos(losPacientes);
        System.exit(0);
    }//GEN-LAST:event_salirActionPerformed

    private void ingresarPacienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ingresarPacienteActionPerformed
        String elNombre = jTextField2.getText();
        String suTipoSangre = (String) jComboBox2.getSelectedItem();

        int suIdentificacion = 0;
        double suPesoKg = 0.0;
        int suFrecuenciaCardiaca = 0;
        int suNumAfiliados = 0;
        int laEdad = 0;

        try {
            suIdentificacion = Integer.parseInt(jTextField1.getText());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Error: La identificación no es un número entero. Intente de nuevo");
            return;
        }

        try {
            suPesoKg = Double.parseDouble(jTextField3.getText());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Error: El peso no es un número real. Intente de nuevo");
            return;
        }

        try {
            suFrecuenciaCardiaca = Integer.parseInt(jTextField6.getText());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Error: La frecuencia cardiaca no es un número entero. Intente de nuevo");
            return;
        }

        FichaMedica suFicha = new FichaMedica(suTipoSangre, suPesoKg, suFrecuenciaCardiaca);

        if (jComboBox1.getSelectedItem().equals("AFILIADO")) {
            try {
                suNumAfiliados = Integer.parseInt(jTextField11.getText());
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Error: El número de afiliados no es un número entero. Intente de nuevo");
                return;
            }
            PacienteAfiliado nuevoPacienteAfiliado = new PacienteAfiliado(suIdentificacion, elNombre, suFicha, new ArrayList<>(), suNumAfiliados);
            losPacientes.add(nuevoPacienteAfiliado);
            JOptionPane.showMessageDialog(null, "Paciente Afiliado registrado con éxito");

        } else {
            try {
                laEdad = Integer.parseInt(jTextField12.getText());
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Error: La edad no es un número entero. Intente de nuevo");
                return;
            }
            PacienteBeneficiario nuevoPacienteBeneficiario = new PacienteBeneficiario(suIdentificacion, elNombre, suFicha, new ArrayList<>(), laEdad);
            losPacientes.add(nuevoPacienteBeneficiario);
            JOptionPane.showMessageDialog(null, "Paciente Beneficiario registrado con éxito");
        }

        almacenarArchivoObjetos(losPacientes);


    }//GEN-LAST:event_ingresarPacienteActionPerformed

    private void ingresarServicioSaludActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ingresarServicioSaludActionPerformed
        String elTipoServicio = (String) jComboBox3.getSelectedItem();
        LocalDate laFecha = LocalDate.now();
        int laDuracion = 0;

        try {
            laDuracion = Integer.parseInt(jTextField7.getText());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Error: La duración no es un número entero. Intente de nuevo");
            return;
        }

        try {
            laFecha = LocalDate.parse(jTextField10.getText());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: La fecha no es válida. " +
                    "Recuerde que el formato de fecha permitido es: YYYY-MM-DD. Intente de nuevo");
            return;
        }

        try {
            int idBuscado = Integer.parseInt(jTextField9.getText());
            boolean encontrado = false;
            for (Paciente paciente : losPacientes) {
                if (paciente.getIdentificacion() == idBuscado) {
                    String anticipacion = paciente.adicionarServicio(new ServicioSalud(elTipoServicio, laFecha, laDuracion));
                    encontrado = true;
                    JOptionPane.showMessageDialog(null, "Recuerde que debe llegar: " + anticipacion);
                    break;
                }
            }
            if (encontrado == false) {
                JOptionPane.showMessageDialog(null, "Paciente no encontrado. Intente de nuevo");
            } else {
                almacenarArchivoObjetos(losPacientes);
                JOptionPane.showMessageDialog(null, "Servicio de salud añadido con éxito");
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Error: La identificación no es un número entero. Intente de nuevo");
        }
    }//GEN-LAST:event_ingresarServicioSaludActionPerformed

    private void ejecutarReporteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ejecutarReporteActionPerformed
        recuperarArchivoObjetos(losPacientes);
        if (jComboBox4.getSelectedItem().equals("Listado de los pacientes registrados")) {
            ordenarPorId(losPacientes);
            jTextArea1.setText(listarPacientes(losPacientes));
        } else {
            try {
                int idBuscado = Integer.parseInt(jTextField4.getText());
                String afiliado = listarAfiliado(losPacientes, idBuscado);
                if (afiliado.equals("Paciente no encontrado.")) {
                    JOptionPane.showMessageDialog(null, "Paciente no encontrado. Intente de nuevo");
                } else {
                    jTextArea1.setText(afiliado);
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Error: La identificación no es un número entero. Intente de nuevo");
            }
        }
    }//GEN-LAST:event_ejecutarReporteActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(UsaPaciente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(UsaPaciente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(UsaPaciente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(UsaPaciente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new UsaPaciente().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton ejecutarReporte;
    private javax.swing.JButton ingresarPaciente;
    private javax.swing.JButton ingresarServicioSalud;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JComboBox<String> jComboBox3;
    private javax.swing.JComboBox<String> jComboBox4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField10;
    private javax.swing.JTextField jTextField11;
    private javax.swing.JTextField jTextField12;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JTextField jTextField7;
    private javax.swing.JTextField jTextField9;
    private javax.swing.JButton salir;
    // End of variables declaration//GEN-END:variables
}
