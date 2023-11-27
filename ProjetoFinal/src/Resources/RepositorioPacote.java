package Resources;

import java.sql.Date;
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

    private static Date convertStringToDate(String dateString) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            return (Date) dateFormat.parse(dateString);
        } catch (ParseException e) {
            // Handle the exception if the string is not in the expected format
            System.err.println("Error: Unable to parse the date. " + e.getMessage());
            return null; // or throw an exception if you prefer
        }
    }

    private ArrayList<String> strToArray(String stringList) {
        return new ArrayList<>(Arrays.asList(stringList.split("|")));
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
}