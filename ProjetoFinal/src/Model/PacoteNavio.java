package Model;

import java.util.Date;
import java.util.List;

public class PacoteNavio extends Pacote {
    private String companhiaAerea;
    private int quantidadeCabinesDisponiveis;

    public PacoteNavio(Destino destino, Date dataPartida, int duracao, double preco, int assentosDisponiveis, String categoria, List<String> atividadesDisponiveis, String companhiaAerea, int quantidadeCabinesDisponiveis) {
        super(destino,dataPartida,duracao,preco,assentosDisponiveis,categoria,atividadesDisponiveis);
        this.companhiaAerea = companhiaAerea;
        this.quantidadeCabinesDisponiveis = quantidadeCabinesDisponiveis;
    }

    public String getCompanhiaNaval() {
        return companhiaAerea;
    }

    public void setCompanhiaNaval(String companhiaAerea) {
        this.companhiaAerea = companhiaAerea;
    }

    public int getQuantidadeCabinesDisponiveis() {
        return quantidadeCabinesDisponiveis;
    }

    public void setQuantidadeCabinesDisponiveis(int quantidadeCabinesDisponiveis) {
        this.quantidadeCabinesDisponiveis = quantidadeCabinesDisponiveis;
    }        

    @Override
    public boolean reservar(int quantidadeCabinesRequisitadas) {
        if (quantidadeCabinesRequisitadas > 0 && quantidadeCabinesRequisitadas <= quantidadeCabinesDisponiveis) {
            quantidadeCabinesDisponiveis -= quantidadeCabinesRequisitadas;
            System.out.println("Cabines no navio alugadas!");
            return true;
        } else {
            System.out.println("Não foi possível encontrar o número de cabines necessárias para a sua reserva.");
            return false;
        }
    }
}