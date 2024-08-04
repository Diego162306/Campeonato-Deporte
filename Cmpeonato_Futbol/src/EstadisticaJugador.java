import javax.swing.JTextArea;

public class EstadisticaJugador {
    private Jugador jugador;
    private int goles;
    private int asistencias;
    private int tarjetasAmarillas;
    private int tarjetasRojas;

    public EstadisticaJugador(Jugador jugador) {
        this.jugador = jugador;
        this.goles = 0;
        this.asistencias = 0;
        this.tarjetasAmarillas = 0;
        this.tarjetasRojas = 0;
    }

    public void registrarGol() {
        goles++;
    }

    public void registrarAsistencia() {
        asistencias++;
    }

    public void registrarTarjetaAmarilla() {
        tarjetasAmarillas++;
    }

    public void registrarTarjetaRoja() {
        tarjetasRojas++;
    }

    public void mostrarEstadisticas(JTextArea textArea) {
        textArea.append("Estadísticas de " + jugador.getNombre() + ":\n");
        textArea.append("Goles: " + goles + "\n");
        textArea.append("Asistencias: " + asistencias + "\n");
        textArea.append("Tarjetas Amarillas: " + tarjetasAmarillas + "\n");
        textArea.append("Tarjetas Rojas: " + tarjetasRojas + "\n");
    }

    public int getGoles() {
        return goles;
    }

    public int getAsistencias() {
        return asistencias;
    }

    public int getTarjetasAmarillas() {
        return tarjetasAmarillas;
    }

    public int getTarjetasRojas() {
        return tarjetasRojas;
    }
}
