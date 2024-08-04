public class Jugador {
    private String nombre;
    private int numeroCamiseta;
    private String posicion;
    private EstadisticaJugador estadistica;

    public Jugador(String nombre, int numeroCamiseta, String posicion) {
        this.nombre = nombre;
        this.numeroCamiseta = numeroCamiseta;
        this.posicion = posicion;
        this.estadistica = new EstadisticaJugador(this);
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
}
