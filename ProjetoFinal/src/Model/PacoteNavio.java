package Model;

import java.util.ArrayList;
import java.util.Date;

public class PacoteNavio extends Pacote {
    private String companhiaNaval;
    private int cabinesDisponiveis;

    public PacoteNavio(String nome, Destino destino, Date dataPartida, int duracao, double preco, int assentosDisponiveis, String categoria, ArrayList<String> atividadesDisponiveis, String companhiaNaval, int cabinesDisponiveis) {
        super(nome, destino,dataPartida,duracao,preco,assentosDisponiveis,categoria,atividadesDisponiveis);
        this.companhiaNaval = companhiaNaval;
        this.cabinesDisponiveis = cabinesDisponiveis;
    }

    public String getCompanhiaNaval() {
        return companhiaNaval;
    }

    public void setCompanhiaNaval(String companhiaNaval) {
        this.companhiaNaval = companhiaNaval;
    }

    public int getQuantidadeCabinesDisponiveis() {
        return cabinesDisponiveis;
    }

    public void setQuantidadeCabinesDisponiveis(int cabinesDisponiveis) {
        this.cabinesDisponiveis = cabinesDisponiveis;
    }        

    @Override
    public boolean reservar(int cabinesRequisitadas) {
        if (cabinesRequisitadas > 0 && cabinesRequisitadas <= cabinesDisponiveis) {
            cabinesDisponiveis -= cabinesRequisitadas;
            System.out.println("Cabines no navio alugadas!");
            return true;
        } else {
            System.out.println("Não foi possível encontrar o número de cabines necessárias para a sua reserva.");
            return false;
        }
    }
}