package Resources;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

import Model.Destino;

public class RepositorioDestino extends Repositorio {
    private static Driver destinoDriver = new Driver();
    private static String[] destinoHeaderNames = new String[]{"nome", "descricao", "pontosTuristicos"};
    private static String diretorioDestino = "registro_destino.csv";

    public RepositorioDestino() {
        super(destinoDriver, diretorioDestino, destinoHeaderNames);
    }

    private ArrayList<String> strToArray(String stringList) {
        return new ArrayList<>(Arrays.asList(stringList.split("|")));
    }

    public Destino criarDestinoComMap(Map<String, String> destino) {
        if (destino != null) {
            return new Destino(destino.get("nome"), destino.get("descricao"), strToArray(destino.get("pontosTuristicos")));
        }
        return null;
    }

    public Destino getDestinoByName(String nomeDestino) {
        return criarDestinoComMap(getObjectMapByAttribute("nome", nomeDestino));
    }

    
    
}