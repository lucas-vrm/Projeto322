package Resources;

import java.util.ArrayList;
import java.util.List;

import Model.Pacote;

public class RepositorioPacote {
    private List<Pacote> pacotes = new ArrayList<>();

    public void adicionarPacote(Pacote pacote) {
        pacotes.add(pacote);
    }

    public void removerPacote(Pacote pacote) {
        pacotes.remove(pacote);
    }

    public List<Pacote> getPacotes() {
        return pacotes;
    }
}