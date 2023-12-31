package View;
import Model.Pacote;

public class PacoteView {
    // Vamos exibir algumas informações simples sobre um determinado pacote de viagem
    public static void exibirInformacoesPacote(Pacote pacote){
        System.out.println("Informações do Pacote:");
        System.out.println("Destino: " + pacote.getDestino().getNome());
        System.out.println("Duração: " + pacote.getDuracao() + " dias");
        System.out.println("Preço: R$ " + pacote.getPreco());
        System.out.println("Assentos disponíveis: " + pacote.getAssentosDisponiveis());
    }
}