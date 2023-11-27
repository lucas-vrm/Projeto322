package Model;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;

public class Pacote implements Reservavel {
    private String nome;
    private Destino destino;
    private Date dataPartida;
    private int duracao; // em dias
    private double preco;
    private int assentosDisponiveis;
    private CategoriaDeDestino categoria;
    protected ArrayList<String> atividadesDisponiveis;

    // Construtor
    public Pacote(String nome, Destino destino, Date dataPartida, int duracao, double preco, int assentosDisponiveis, 
                    String categoria, ArrayList<String> atividadesDisponiveis2) {
        this.nome = nome;
        this.destino = destino;
        this.dataPartida = dataPartida;
        this.duracao = duracao;
        this.preco = preco;
        this.assentosDisponiveis = assentosDisponiveis;
        this.categoria = CategoriaDeDestino.fromDescricao(categoria);
        this.atividadesDisponiveis = atividadesDisponiveis2;
    }

    // Métodos getters e setters

	public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

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
    
    public ArrayList<String> getAtividadesDisponiveis() {
        return atividadesDisponiveis;
    }

    public void setAtividadesDisponiveis(ArrayList<String> atividadesDisponiveis) {
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

    @Override
    public String toString() {
        LocalDate localDate = getDataPartida().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        java.time.format.DateTimeFormatter formatter = java.time.format.DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return "Pacote{" +
                "nome='" + nome + '\'' +
                ", destino=" + destino +
                ", dataPartida=" + localDate.format(formatter) +
                ", duracao=" + duracao +
                ", preco=" + preco +
                ", assentosDisponiveis=" + assentosDisponiveis +
                ", categoria=" + categoria +
                ", atividadesDisponiveis=" + atividadesDisponiveis +
                '}';
    }

    public void adicionaAtividades(String atividade) {
        atividadesDisponiveis.add(atividade);
    }

}
