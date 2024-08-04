import java.util.List;
import javax.swing.JOptionPane;

public class Partido {
    private Equipo equipoLocal;
    private Equipo equipoVisitante;
    private int golesLocal;
    private int golesVisitante;

    public Partido(Equipo equipoLocal, Equipo equipoVisitante, int golesLocal, int golesVisitante) {
        this.equipoLocal = equipoLocal;
        this.equipoVisitante = equipoVisitante;
        this.golesLocal = golesLocal;
        this.golesVisitante = golesVisitante;
    }

    public void jugar() {
        // Actualizar estadísticas de los equipos
        equipoLocal.getEstadistica().registrarPartido(golesLocal, golesVisitante);
        equipoVisitante.getEstadistica().registrarPartido(golesVisitante, golesLocal);

        // Registrar estadísticas de jugadores
        registrarEstadisticasJugador(equipoLocal);
        registrarEstadisticasJugador(equipoVisitante);
    }

    private void registrarEstadisticasJugador(Equipo equipo) {
        for (Jugador jugador : equipo.getJugadores()) {
            String golesStr = JOptionPane.showInputDialog("Goles de " + jugador.getNombre() + ":");
            String asistenciasStr = JOptionPane.showInputDialog("Asistencias de " + jugador.getNombre() + ":");
            String tarjetasAmarillasStr = JOptionPane.showInputDialog("Tarjetas Amarillas de " + jugador.getNombre() + ":");
            String tarjetasRojasStr = JOptionPane.showInputDialog("Tarjetas Rojas de " + jugador.getNombre() + ":");

            int goles = Integer.parseInt(golesStr);
            int asistencias = Integer.parseInt(asistenciasStr);
            int tarjetasAmarillas = Integer.parseInt(tarjetasAmarillasStr);
            int tarjetasRojas = Integer.parseInt(tarjetasRojasStr);

            for (int i = 0; i < goles; i++) jugador.registrarGol();
            for (int i = 0; i < asistencias; i++) jugador.registrarAsistencia();
            for (int i = 0; i < tarjetasAmarillas; i++) jugador.registrarTarjetaAmarilla();
            for (int i = 0; i < tarjetasRojas; i++) jugador.registrarTarjetaRoja();
        }
    }

    public int ganador() {
        if (golesLocal > golesVisitante) {
            return 1; // Equipo local gana
        } else if (golesLocal < golesVisitante) {
            return 2; // Equipo visitante gana
        } else {
            return 0; // Empate
        }
    }

    public boolean empate() {
        return golesLocal == golesVisitante;
    }

    // Getters y Setters
    public Equipo getEquipoLocal() {
        return equipoLocal;
    }

    public void setEquipoLocal(Equipo equipoLocal) {
        this.equipoLocal = equipoLocal;
    }

    public Equipo getEquipoVisitante() {
        return equipoVisitante;
    }

    public void setEquipoVisitante(Equipo equipoVisitante) {
        this.equipoVisitante = equipoVisitante;
    }

    public int getGolesLocal() {
        return golesLocal;
    }

    public void setGolesLocal(int golesLocal) {
        this.golesLocal = golesLocal;
    }

    public int getGolesVisitante() {
        return golesVisitante;
    }

    public void setGolesVisitante(int golesVisitante) {
        this.golesVisitante = golesVisitante;
    }
}
