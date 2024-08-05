import java.util.List;
import javax.swing.JTextArea;

public class Campeonato {
    private String nombre;
    private List<Equipo> equipos;
    private List<Partido> partidos;
    private TablaDePosicion tablaDePosicion;

    public Campeonato(String nombre, List<Equipo> equipos, List<Partido> partidos, TablaDePosicion tablaDePosicion) {
        this.nombre = nombre;
        this.equipos = equipos;
        this.partidos = partidos;
        this.tablaDePosicion = tablaDePosicion;
    }

    public void agregarEquipo(Equipo equipo) {
        equipos.add(equipo);
    }

    public void agregarPartido(Partido partido) {
        partidos.add(partido);
    }

    public void jugarPartido() {
        System.out.println("Jugando partido...");
        // Lógica para simular un partido y actualizar las estadísticas de los equipos
    }

    public void mostrarTablaDePosiciones(JTextArea textArea) {
        tablaDePosicion.mostrarPosiciones(textArea);
    }

    // Getters y Setters
    public String getNombre() {
        return nombre;
    }

    public List<Equipo> getEquipos() {
        return equipos;
    }

    public List<Partido> getPartidos() {
        return partidos;
    }

    public TablaDePosicion getTablaDePosicion() {
        return tablaDePosicion;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setEquipos(List<Equipo> equipos) {
        this.equipos = equipos;
    }

    public void setPartidos(List<Partido> partidos) {
        this.partidos = partidos;
    }

    public void setTablaDePosicion(TablaDePosicion tablaDePosicion) {
        this.tablaDePosicion = tablaDePosicion;
    }
}