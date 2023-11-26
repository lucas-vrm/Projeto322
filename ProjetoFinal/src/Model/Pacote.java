package Model;
import java.util.Date;
import java.util.List;

public class Pacote implements Reservavel {
	private Destino destino;
    private Date dataPartida;
    private int duracao; // em dias
    private double preco;
    private int assentosDisponiveis;
    private CategoriaDeDestino categoria;
    private List<String> atividadesDisponiveis;

    // Construtor
    public Pacote(Destino destino, Date dataPartida, int duracao, double preco, int assentosDisponiveis, CategoriaDeDestino categoria, List<String> atividadesDisponiveis) {
        this.destino = destino;
        this.dataPartida = dataPartida;
        this.duracao = duracao;
        this.preco = preco;
        this.assentosDisponiveis = assentosDisponiveis;
        this.categoria = categoria;
        this.atividadesDisponiveis = atividadesDisponiveis;
    }

    // Métodos getters e setters

    public Destino getDestino() {
        return destino;
    }

    public void setDestino(Destino destino) {
        this.destino = destino;
    }

    public Date getDataPartida() {
        return dataPartida;
    }

    public void setDataPartida(Date dataPartida) {
        this.dataPartida = dataPartida;
    }

    public int getDuracao() {
        return duracao;
    }

    public void setDuracao(int duracao) {
        this.duracao = duracao;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public int getAssentosDisponiveis() {
        return assentosDisponiveis;
    }

    public void setAssentosDisponiveis(int assentosDisponiveis) {
        this.assentosDisponiveis = assentosDisponiveis;
    }

    public CategoriaDeDestino getCategoria() {
        return categoria;
    }

    public void setCategoria(CategoriaDeDestino categoria) {
        this.categoria = categoria;
    }
    
    public List<String> getAtividadesDisponiveis() {
        return atividadesDisponiveis;
    }

    public void setAtividadesDisponiveis(List<String> atividadesDisponiveis) {
        this.atividadesDisponiveis = atividadesDisponiveis;
    }

    public void exibirAtividadesDisponiveis() {
        System.out.println("Atividades disponíveis:");
        for (String atividade : atividadesDisponiveis) {
            System.out.println("- " + atividade);
        }
    }

    @Override
    public boolean reservar(int quantidadeAssentos) {
        if (quantidadeAssentos > 0 && quantidadeAssentos <= assentosDisponiveis) {
            assentosDisponiveis -= quantidadeAssentos;
            System.out.println("Reserva efetuada com sucesso!");
            return true;
        } else {
            System.out.println("Não há assentos suficientes para a reserva.");
            return false;
        }
    }

}
