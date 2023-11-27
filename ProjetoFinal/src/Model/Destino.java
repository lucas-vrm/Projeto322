package Model;
import java.util.*;

public class Destino {
    private String nome;
    private String descricao;
    private List<String> pontosTuristicos;

    // Construtor
    public Destino(String nome, String descricao) {
        this.nome = nome;
        this.descricao = descricao;
        this.pontosTuristicos = new ArrayList<>();
    }

    // Métodos getters e setters

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public List<String> getPontosTuristicos() {
        return pontosTuristicos;
    }

    public void setPontosTuristicos(List<String> pontosTuristicos) {
        this.pontosTuristicos = pontosTuristicos;
    }

    // Outros métodos relevantes para a classe Destino
    public void adicionaPontoTuristico(String pontoTuristico) {
        pontosTuristicos.add(pontoTuristico);
    }
}
