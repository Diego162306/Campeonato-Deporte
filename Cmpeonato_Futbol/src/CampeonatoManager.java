import java.util.ArrayList;
import java.util.List;

public class CampeonatoManager {
    private List<Campeonato> campeonatos;

    public CampeonatoManager() {
        campeonatos = new ArrayList<>();
    }

    public void agregarCampeonato(Campeonato campeonato) {
        campeonatos.add(campeonato);
    }

    public List<Campeonato> getCampeonatos() {
        return campeonatos;
    }

    public Campeonato buscarCampeonatoPorNombre(String nombre) {
        for (Campeonato campeonato : campeonatos) {
            if (campeonato.getNombre().equals(nombre)) {
                return campeonato;
            }
        }
        return null;
    }
}

