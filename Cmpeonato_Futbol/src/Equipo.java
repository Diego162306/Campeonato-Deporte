import java.util.List;

public class Equipo {
    private String nombre;
    private List<Jugador> jugadores;
    private EstadisticaEquipo estadistica;

    public Equipo(String nombre, List<Jugador> jugadores, EstadisticaEquipo estadistica) {
        this.nombre = nombre;
        this.jugadores = jugadores;
        this.estadistica = estadistica;
    }

    public void agregarJugador(Jugador jugador) {
        jugadores.add(jugador);
    }

    public void eliminarJugador(Jugador jugador) {
        jugadores.remove(jugador);
    }

    public void listarJugadores() {
        for (Jugador jugador : jugadores) {
            System.out.println(jugador.getNombre());
        }
    }

    public void participar() {
        System.out.println("Participando...");
    }

    public String getNombre() {
        return nombre;
    }

    public List<Jugador> getJugadores() {
        return jugadores;
    }

    public EstadisticaEquipo getEstadistica() {
        return estadistica;
    }
}