public class Jugador extends EstadisticaJugador {
    private String nombre;
    private int numeroCamiseta;
    private String posicion;
    private EstadisticaJugador estadistica;

    public Jugador(String nombre, int numeroCamiseta, String posicion) {
        super(null);
        this.nombre = nombre;
        this.numeroCamiseta = numeroCamiseta;
        this.posicion = posicion;
        this.estadistica = estadistica;
    }

    public Jugador(Jugador jugador) {
        super(jugador);
    }

    public void mostrarEstadisticas() {
        estadistica.mostrarEstadisticas();
    }

    public void registrarGol() {
        estadistica.registrarGol();
    }

    public void registrarAsistencia() {
        estadistica.registrarAsistencia();
    }

    public void registrarTarjetaAmarilla() {
        estadistica.registrarTarjetaAmarilla();
    }

    public void registrarTarjetaRoja() {
        estadistica.registrarTarjetaRoja();
    }

    public String getNombre() {
        return nombre;
    }

    public int getNumeroCamiseta() {
        return numeroCamiseta;
    }

    public String getPosicion() {
        return posicion;
    }

    public EstadisticaJugador getEstadistica() {
        return estadistica;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setNumeroCamiseta(int numeroCamiseta) {
        this.numeroCamiseta = numeroCamiseta;
    }

    public void setPosicion(String posicion) {
        this.posicion = posicion;
    }

    public void setEstadistica(EstadisticaJugador estadistica) {
        this.estadistica = estadistica;
    }

}
