package Model;
import java.util.Date;

public class Pacote implements Reservavel {
	private String destino;
    private Date dataPartida;
    private int duracao; // em dias
    private double preco;
    private int assentosDisponiveis;

    // Construtor
    public Pacote(String destino, Date dataPartida, int duracao, double preco, int assentosDisponiveis) {
        this.destino = destino;
        this.dataPartida = dataPartida;
        this.duracao = duracao;
        this.preco = preco;
        this.assentosDisponiveis = assentosDisponiveis;
    }

    // Métodos getters e setters

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
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
