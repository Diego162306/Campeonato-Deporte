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
    private JTable posicionesTable;
    private JTable equiposTable;
    private JTable jugadoresTable;
    private Campeonato campeonato;

    public Main() {
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

        // Panel para las tablas
        JPanel tablesPanel = new JPanel();
        tablesPanel.setLayout(new GridLayout(3, 1));
        tablesPanel.add(posicionesScrollPane);
        tablesPanel.add(equiposScrollPane);
        tablesPanel.add(jugadoresScrollPane);
        frame.getContentPane().add(tablesPanel, BorderLayout.CENTER);

        // Inicializar el campeonato
        List<Equipo> equipos = new ArrayList<>();
        List<Partido> partidos = new ArrayList<>();
        List<EstadisticaEquipo> posiciones = new ArrayList<>();
        TablaDePosicion tablaDePosicion = new TablaDePosicion(posiciones);
        campeonato = new Campeonato("Campeonato de Futbol", equipos, partidos, tablaDePosicion);

        frame.setVisible(true);
    }

    private void agregarEquipo() {
        String nombreEquipo = JOptionPane.showInputDialog("Nombre del equipo:");
        if (nombreEquipo != null && !nombreEquipo.isEmpty()) {
            List<Jugador> jugadores = new ArrayList<>();
            EstadisticaEquipo estadisticaEquipo = new EstadisticaEquipo(nombreEquipo, 0, 0, 0, 0, 0, 0, 0);
            Equipo equipo = new Equipo(nombreEquipo, jugadores, estadisticaEquipo);
            campeonato.agregarEquipo(equipo);
            campeonato.getTablaDePosicion().agregarEquipo(estadisticaEquipo);
            JOptionPane.showMessageDialog(frame, "Equipo agregado: " + nombreEquipo);
            mostrarTablaDePosiciones();
            mostrarEquiposYJugadores();
        }
    }

    private void agregarJugador() {
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
            mostrarEquiposYJugadores();
        } else {
            JOptionPane.showMessageDialog(frame, "Equipo no encontrado: " + nombreEquipo);
        }
    }

    private void jugarPartido() {
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
            mostrarTablaDePosiciones();
        } else {
            JOptionPane.showMessageDialog(frame, "Uno o ambos equipos no encontrados.");
        }
    }

    private void mostrarTablaDePosiciones() {
        // Limpiar tabla existente
        posicionesTableModel.setRowCount(0);

        // Obtener y mostrar las posiciones
        List<EstadisticaEquipo> posiciones = campeonato.getTablaDePosicion().getPosiciones();
        for (EstadisticaEquipo estadistica : posiciones) {
            posicionesTableModel.addRow(new Object[]{
                estadistica.getNombre(),
                estadistica.getPuntos(),
                estadistica.getPartidosJugados(),
                estadistica.getPartidosGanados(),
                estadistica.getPartidosPerdidos(),
                estadistica.getEmpates(),
                estadistica.getGolesFavor(),
                estadistica.getGolesContra()
            });
        }
    }

    private void mostrarEquiposYJugadores() {
        // Limpiar tabla existente
        equiposTableModel.setRowCount(0);

        // Obtener y mostrar los equipos y sus jugadores
        for (Equipo equipo : campeonato.getEquipos()) {
            // Añadir el equipo
            equiposTableModel.addRow(new Object[]{
                equipo.getNombre(), "", "", ""
            });

            // Añadir los jugadores
            for (Jugador jugador : equipo.getJugadores()) {
                equiposTableModel.addRow(new Object[]{
                    "", jugador.getNombre(),
                    jugador.getNumeroCamiseta(),
                    jugador.getPosicion()
                });
            }
        }
    }

    private void mostrarEstadisticasJugador() {
        // Limpiar tabla existente
        jugadoresTableModel.setRowCount(0);

        // Obtener y mostrar las estadísticas de los jugadores
        for (Equipo equipo : campeonato.getEquipos()) {
            for (Jugador jugador : equipo.getJugadores()) {
                EstadisticaJugador estadisticas = jugador.getEstadistica();
                jugadoresTableModel.addRow(new Object[]{
                    jugador.getNombre(),
                    estadisticas.getGoles(),
                    estadisticas.getAsistencias(),
                    estadisticas.getTarjetasAmarillas(),
                    estadisticas.getTarjetasRojas()
                });
            }
        }
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                Main window = new Main();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}


