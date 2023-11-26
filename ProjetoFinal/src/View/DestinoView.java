package View;

import Model.Destino;

public class DestinoView {
    public static void exibirInformacoesDestino(Destino destino){
        System.out.println("Informações do Destino:");
        System.out.println("Nome do destino: " + destino.getNome());
        System.out.println("Descrição do destino: " + destino.getDescricao());
        System.out.println("Principais pontos turísticos: ");
        for (String pontoTuristico : destino.getPontosTuristicos()) {
            System.out.println(pontoTuristico);
        }
    }
}
