import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class Main {
    private JFrame frame;
    private DefaultTableModel posicionesTableModel;
    private DefaultTableModel equiposTableModel;
    private DefaultTableModel jugadoresTableModel;
    private DefaultTableModel campeonatosTableModel;
    private JTable posicionesTable;
    private JTable equiposTable;
    private JTable jugadoresTable;
    private JTable campeonatosTable;
    private CampeonatoManager campeonatoManager;

    public Main() {
        campeonatoManager = new CampeonatoManager();
        initialize();
    }

    private void initialize() {
        frame = new JFrame("Campeonato de Futbol");
        frame.setBounds(100, 100, 1200, 800);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(new BorderLayout());

        // Panel para los botones
        JPanel panel = new JPanel();
        frame.getContentPane().add(panel, BorderLayout.NORTH);

        JButton btnAgregarCampeonato = new JButton("Agregar Campeonato");
        btnAgregarCampeonato.addActionListener(e -> agregarCampeonato());
        panel.add(btnAgregarCampeonato);

        JButton btnAgregarEquipo = new JButton("Agregar Equipo");
        btnAgregarEquipo.addActionListener(e -> agregarEquipo());
        panel.add(btnAgregarEquipo);

        JButton btnAgregarJugador = new JButton("Agregar Jugador");
        btnAgregarJugador.addActionListener(e -> agregarJugador());
        panel.add(btnAgregarJugador);

        JButton btnJugarPartido = new JButton("Jugar Partido");
        btnJugarPartido.addActionListener(e -> jugarPartido());
        panel.add(btnJugarPartido);

        JButton btnMostrarEstadisticas = new JButton("Mostrar Estadísticas de Jugador");
        btnMostrarEstadisticas.addActionListener(e -> mostrarEstadisticasJugador());
        panel.add(btnMostrarEstadisticas);

        // Crear la tabla para la tabla de posiciones
        posicionesTableModel = new DefaultTableModel(new Object[]{"Equipo", "Puntos", "Partidos Jugados", "Ganados", "Perdidos", "Empates", "Goles a Favor", "Goles en Contra"}, 0);
        posicionesTable = new JTable(posicionesTableModel);
        JScrollPane posicionesScrollPane = new JScrollPane(posicionesTable);
        posicionesScrollPane.setBorder(BorderFactory.createTitledBorder("Tabla de Posiciones"));

        // Crear la tabla para equipos y jugadores
        equiposTableModel = new DefaultTableModel(new Object[]{"Equipo", "Jugador", "Número", "Posición"}, 0);
        equiposTable = new JTable(equiposTableModel);
        JScrollPane equiposScrollPane = new JScrollPane(equiposTable);
        equiposScrollPane.setBorder(BorderFactory.createTitledBorder("Equipos y Jugadores"));

        // Crear la tabla para estadísticas de jugadores
        jugadoresTableModel = new DefaultTableModel(new Object[]{"Jugador", "Goles", "Asistencias", "Tarjetas Amarillas", "Tarjetas Rojas"}, 0);
        jugadoresTable = new JTable(jugadoresTableModel);
        JScrollPane jugadoresScrollPane = new JScrollPane(jugadoresTable);
        jugadoresScrollPane.setBorder(BorderFactory.createTitledBorder("Estadísticas de Jugadores"));

        // Crear la tabla para campeonatos
        campeonatosTableModel = new DefaultTableModel(new Object[]{"Campeonato"}, 0);
        campeonatosTable = new JTable(campeonatosTableModel);
        campeonatosTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        campeonatosTable.getSelectionModel().addListSelectionListener(event -> seleccionarCampeonato());
        JScrollPane campeonatosScrollPane = new JScrollPane(campeonatosTable);
        campeonatosScrollPane.setBorder(BorderFactory.createTitledBorder("Campeonatos"));

        // Panel para las tablas
        JPanel tablesPanel = new JPanel();
        tablesPanel.setLayout(new GridLayout(4, 1));
        tablesPanel.add(campeonatosScrollPane);
        tablesPanel.add(posicionesScrollPane);
        tablesPanel.add(equiposScrollPane);
        tablesPanel.add(jugadoresScrollPane);
        frame.getContentPane().add(tablesPanel, BorderLayout.CENTER);

        frame.setVisible(true);
    }

    private void agregarCampeonato() {
        String nombreCampeonato = JOptionPane.showInputDialog("Nombre del campeonato:");
        if (nombreCampeonato != null && !nombreCampeonato.isEmpty()) {
            List<Equipo> equipos = new ArrayList<>();
            List<Partido> partidos = new ArrayList<>();
            List<EstadisticaEquipo> posiciones = new ArrayList<>();
            TablaDePosicion tablaDePosicion = new TablaDePosicion(posiciones);
            Campeonato campeonato = new Campeonato(nombreCampeonato, equipos, partidos, tablaDePosicion);
            campeonatoManager.agregarCampeonato(campeonato);
            campeonatosTableModel.addRow(new Object[]{nombreCampeonato});
            JOptionPane.showMessageDialog(frame, "Campeonato agregado: " + nombreCampeonato);
        }
    }

    private void agregarEquipo() {
        String nombreCampeonato = JOptionPane.showInputDialog("Nombre del campeonato:");
        Campeonato campeonato = campeonatoManager.buscarCampeonatoPorNombre(nombreCampeonato);
        if (campeonato != null) {
            String nombreEquipo = JOptionPane.showInputDialog("Nombre del equipo:");
            if (nombreEquipo != null && !nombreEquipo.isEmpty()) {
                List<Jugador> jugadores = new ArrayList<>();
                EstadisticaEquipo estadisticaEquipo = new EstadisticaEquipo(nombreEquipo, 0, 0, 0, 0, 0, 0, 0);
                Equipo equipo = new Equipo(nombreEquipo, jugadores, estadisticaEquipo);
                campeonato.agregarEquipo(equipo);
                campeonato.getTablaDePosicion().agregarEquipo(estadisticaEquipo);
                JOptionPane.showMessageDialog(frame, "Equipo agregado: " + nombreEquipo + " al campeonato " + nombreCampeonato);
                if (campeonatosTable.getSelectedRow() != -1 && nombreCampeonato.equals(campeonatosTableModel.getValueAt(campeonatosTable.getSelectedRow(), 0))) {
                    mostrarTablaDePosiciones(campeonato);
                    mostrarEquiposYJugadores(campeonato);
                }
            }
        } else {
            JOptionPane.showMessageDialog(frame, "Campeonato no encontrado: " + nombreCampeonato);
        }
    }

    private void agregarJugador() {
        String nombreCampeonato = JOptionPane.showInputDialog("Nombre del campeonato:");
        Campeonato campeonato = campeonatoManager.buscarCampeonatoPorNombre(nombreCampeonato);
        if (campeonato != null) {
            String nombreEquipo = JOptionPane.showInputDialog("Nombre del equipo al que quieres agregar un jugador:");
            Equipo equipoEncontrado = null;
            for (Equipo eq : campeonato.getEquipos()) {
                if (eq.getNombre().equals(nombreEquipo)) {
                    equipoEncontrado = eq;
                    break;
                }
            }
            if (equipoEncontrado != null) {
                String nombreJugador = JOptionPane.showInputDialog("Nombre del jugador:");
                String numeroCamisetaStr = JOptionPane.showInputDialog("Número de camiseta:");
                String posicion = JOptionPane.showInputDialog("Posición:");
                int numeroCamiseta = Integer.parseInt(numeroCamisetaStr);
                Jugador jugador = new Jugador(nombreJugador, numeroCamiseta, posicion);
                equipoEncontrado.agregarJugador(jugador);
                JOptionPane.showMessageDialog(frame, "Jugador agregado: " + nombreJugador + " al equipo " + nombreEquipo);
                if (campeonatosTable.getSelectedRow() != -1 && nombreCampeonato.equals(campeonatosTableModel.getValueAt(campeonatosTable.getSelectedRow(), 0))) {
                    mostrarEquiposYJugadores(campeonato);
                }
            } else {
                JOptionPane.showMessageDialog(frame, "Equipo no encontrado: " + nombreEquipo);
            }
        } else {
            JOptionPane.showMessageDialog(frame, "Campeonato no encontrado: " + nombreCampeonato);
        }
    }

    private void jugarPartido() {
        String nombreCampeonato = JOptionPane.showInputDialog("Nombre del campeonato:");
        Campeonato campeonato = campeonatoManager.buscarCampeonatoPorNombre(nombreCampeonato);
        if (campeonato != null) {
            String local = JOptionPane.showInputDialog("Nombre del equipo local:");
            String visitante = JOptionPane.showInputDialog("Nombre del equipo visitante:");
            Equipo equipoLocal = null;
            Equipo equipoVisitante = null;
            for (Equipo eq : campeonato.getEquipos()) {
                if (eq.getNombre().equals(local)) {
                    equipoLocal = eq;
                }
                if (eq.getNombre().equals(visitante)) {
                    equipoVisitante = eq;
                }
            }
            if (equipoLocal != null && equipoVisitante != null) {
                String golesLocalStr = JOptionPane.showInputDialog("Goles del equipo local:");
                String golesVisitanteStr = JOptionPane.showInputDialog("Goles del equipo visitante:");
                int golesLocal = Integer.parseInt(golesLocalStr);
                int golesVisitante = Integer.parseInt(golesVisitanteStr);
                Partido partido = new Partido(equipoLocal, equipoVisitante, golesLocal, golesVisitante);
                partido.jugar();  // Actualizar estadísticas
                campeonato.agregarPartido(partido);
                JOptionPane.showMessageDialog(frame, "Partido jugado: " + local + " " + golesLocal + " - " + golesVisitante + " " + visitante);
                if (campeonatosTable.getSelectedRow() != -1 && nombreCampeonato.equals(campeonatosTableModel.getValueAt(campeonatosTable.getSelectedRow(), 0))) {
                    mostrarTablaDePosiciones(campeonato);
                }
            } else {
                JOptionPane.showMessageDialog(frame, "Uno o ambos equipos no encontrados en el campeonato: " + nombreCampeonato);
            }
        } else {
            JOptionPane.showMessageDialog(frame, "Campeonato no encontrado: " + nombreCampeonato);
        }
    }

    private void mostrarEstadisticasJugador() {
        String nombreCampeonato = JOptionPane.showInputDialog("Nombre del campeonato:");
        Campeonato campeonato = campeonatoManager.buscarCampeonatoPorNombre(nombreCampeonato);
        if (campeonato != null) {
            String nombreJugador = JOptionPane.showInputDialog("Nombre del jugador:");
            jugadoresTableModel.setRowCount(0);  // Limpiar la tabla
            for (Equipo equipo : campeonato.getEquipos()) {
                for (Jugador jugador : equipo.getJugadores()) {
                    if (jugador.getNombre().equals(nombreJugador)) {
                        jugadoresTableModel.addRow(new Object[]{jugador.getNombre(), jugador.getGoles(), jugador.getAsistencias(), jugador.getTarjetasAmarillas(), jugador.getTarjetasRojas()});
                    }
                }
            }
        } else {
            JOptionPane.showMessageDialog(frame, "Campeonato no encontrado: " + nombreCampeonato);
        }
    }

    private void seleccionarCampeonato() {
        int selectedRow = campeonatosTable.getSelectedRow();
        if (selectedRow != -1) {
            String nombreCampeonato = (String) campeonatosTableModel.getValueAt(selectedRow, 0);
            Campeonato campeonato = campeonatoManager.buscarCampeonatoPorNombre(nombreCampeonato);
            if (campeonato != null) {
                mostrarTablaDePosiciones(campeonato);
                mostrarEquiposYJugadores(campeonato);
            }
        }
    }

    private void mostrarTablaDePosiciones(Campeonato campeonato) {
        posicionesTableModel.setRowCount(0);  // Limpiar la tabla
        for (EstadisticaEquipo equipo : campeonato.getTablaDePosicion().getPosiciones()) {
            posicionesTableModel.addRow(new Object[]{equipo.getNombre(), equipo.getPuntos(), equipo.getPartidosJugados(), equipo.getPartidosGanados(), equipo.getPartidosPerdidos(), equipo.getEmpates(), equipo.getGolesFavor(), equipo.getGolesContra()});
        }
    }

    private void mostrarEquiposYJugadores(Campeonato campeonato) {
        equiposTableModel.setRowCount(0);  // Limpiar la tabla
        for (Equipo equipo : campeonato.getEquipos()) {
            for (Jugador jugador : equipo.getJugadores()) {
                equiposTableModel.addRow(new Object[]{equipo.getNombre(), jugador.getNombre(), jugador.getNumeroCamiseta(), jugador.getPosicion()});
            }
        }
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                Main window = new Main();
                window.frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}

