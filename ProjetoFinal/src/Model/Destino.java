package Model;
import java.util.List;

public class Destino {
    private String nome;
    private String descricao;
    private List<String> pontosTuristicos;

    // Construtor
    public Destino(String nome, String descricao, List<String> pontosTuristicos) {
        this.nome = nome;
        this.descricao = descricao;
        this.pontosTuristicos = pontosTuristicos;
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
}
