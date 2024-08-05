public class EstadisticaEquipo {
    private String nombre;
    private int partidosJugados;
    private int partidosGanados;
    private int partidosPerdidos;
    private int empates;
    private int golesFavor;
    private int golesContra;
    private int puntos;

    public EstadisticaEquipo(String nombre, int partidosJugados, int partidosGanados, int partidosPerdidos, int empates, int golesFavor, int golesContra, int puntos) {
        this.nombre = nombre;
        this.partidosJugados = partidosJugados;
        this.partidosGanados = partidosGanados;
        this.partidosPerdidos = partidosPerdidos;
        this.empates = empates;
        this.golesFavor = golesFavor;
        this.golesContra = golesContra;
        this.puntos = puntos;
    }

    public void registrarPartido(int golesFavor, int golesContra) {
        this.partidosJugados++;
        this.golesFavor += golesFavor;
        this.golesContra += golesContra;

        if (golesFavor > golesContra) {
            this.partidosGanados++;
            this.puntos += 3;
        } else if (golesFavor < golesContra) {
            this.partidosPerdidos++;
        } else {
            this.empates++;
            this.puntos++;
        }
    }

    public String getNombre() {
        return nombre;
    }

    public int getPartidosJugados() {
        return partidosJugados;
    }

    public int getPartidosGanados() {
        return partidosGanados;
    }

    public int getPartidosPerdidos() {
        return partidosPerdidos;
    }

    public int getEmpates() {
        return empates;
    }

    public int getGolesFavor() {
        return golesFavor;
    }

    public int getGolesContra() {
        return golesContra;
    }

    public int getPuntos() {
        return puntos;
    }
}
