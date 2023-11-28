package Resources;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

import Model.Pacote;

public class RepositorioPacote extends Repositorio {
    private static Driver pacoteDriver = new Driver();
    private static String[] pacoteHeaderNames = new String[]{"nome", "destino", "dataPartida", "duracao", "preco",
                                                                "assentosDisponiveis", "categoria", "atividadesDisponiveis"};
    private static String diretorioPacote = "registro_pacote.csv";

    public RepositorioPacote() {
        super(pacoteDriver, diretorioPacote, pacoteHeaderNames);
    }

    private static java.util.Date convertStringToDate(String dateString) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            return dateFormat.parse(dateString);
        } catch (ParseException e) {
            System.err.println("Error: Unable to parse the date. " + e.getMessage());
            return null;
        }
    }

    private ArrayList<String> strToArray(String stringList) {
        return new ArrayList<>(Arrays.asList(stringList.split("\\|")));
    }

    public Pacote criarPacoteComMap(Map<String, String> usr) {
        RepositorioDestino repDestino = new RepositorioDestino();
        return new Pacote(usr.get("nome"), repDestino.getDestinoByName(usr.get("destino")), convertStringToDate(usr.get("dataPartida")), 
                            Integer.valueOf(usr.get("duracao")), Double.valueOf(usr.get("preco")), Integer.valueOf(usr.get("assentosDisponiveis")), 
                                usr.get("categoria"), strToArray(usr.get("atividadesDisponiveis")));
    }

    public Pacote getPacoteByName(String nomePacote) {
        return criarPacoteComMap(getObjectMapByAttribute("nome", nomePacote));
    }

    public ArrayList<Pacote> getAllPacotesFromSameAttribute(String nomeAtributo, String valorAtributo) {
        ArrayList<Map<String, String>> pacotesMaps = new ArrayList<>();
        ArrayList<Pacote> pacotesObjetos = new ArrayList<>();

        pacotesMaps = getAllObjectsMapBySameAttribute(nomeAtributo, valorAtributo);
        for(Map<String, String> pacote : pacotesMaps) {
            Pacote tempPacote = criarPacoteComMap(pacote);
            pacotesObjetos.add(tempPacote);
        }
        return pacotesObjetos;
    }

    public ArrayList<Pacote> getAllPacotes() {
        ArrayList<Map<String, String>> pacotesMaps = new ArrayList<>();
        ArrayList<Pacote> pacotesObjetos = new ArrayList<>();

        pacotesMaps = getAllObjectsMap();
        for(Map<String, String> pacote : pacotesMaps) {
            if(!(pacote.get("duracao").equals("duracao"))){
                Pacote tempPacote = criarPacoteComMap(pacote);
                pacotesObjetos.add(tempPacote);
            }
        }
        return pacotesObjetos;
    }
}