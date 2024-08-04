import javax.swing.JTextArea;
import java.util.List;

public class TablaDePosicion {
    private List<EstadisticaEquipo> posiciones;

    public TablaDePosicion(List<EstadisticaEquipo> posiciones) {
        this.posiciones = posiciones;
    }

    public void agregarEquipo(EstadisticaEquipo equipo) {
        posiciones.add(equipo);
    }

    public void actualizarPosicion() {
        System.out.println("Actualizando posición...");
    }

    public void mostrarPosiciones(JTextArea textArea) {
        for (EstadisticaEquipo equipo : posiciones) {
            textArea.append(equipo.getNombre() + ": " + equipo.getPuntos() + " puntos\n");
        }
    }

    public List<EstadisticaEquipo> getPosiciones() {
        return posiciones;
    }
}
