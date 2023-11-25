package Model;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.io.BufferedReader;
import java.io.File;
import java.nio.charset.StandardCharsets;

public class Administrador extends Usuario {
	
	private static final String arquivo = "./files/pacotes_viagem.csv";

	public Administrador(String nome, String contato, String email, int id) {
		super(nome, contato, email, id);
	}
	
	public void fazAcao(int acao) {
		switch(acao) {
			case 0:
			
			case 1:
				//add novo pacote de viagem
			case 2:
				//apagar pacote de viagem
			case 3:
				//apagar usuario
			default:
				System.out.println("Algo errado");
		}
	}
	
	public void adicionaPacote(Pacote pacote) {
		try {
			//verificar se arquivo ja existe
			boolean arquivoExiste = new File(arquivo).exists();
			
			
			//Abre o escritor para adicionar dados ao arquivo
			FileWriter escritor = new FileWriter(arquivo, StandardCharsets.ISO_8859_1, arquivoExiste);
			if (!arquivoExiste) {
                escritor.write("Nome;Nota;Assistido\n");
            }
			
			//Escrever os dados do pacote de viagem
			escritor.write(pacote.getDestino() + ";" + 
					pacote.getDataPartida() + ";" + 
					pacote.getDuracao() + ";" + 
					pacote.getPreco() + ";" + 
					pacote.getAssentosDisponiveis() + "\n"
			);
			
			// Escrever todos os dados do buffer no arquivo imediatamente
            escritor.flush();
            
            // Fecha o recurso de escrita
            escritor.close();
           
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	// Método para listar os Pacotes do arquivo CSV
    public static ArrayList<Pacote> ListarPacote() {
        ArrayList<Pacote> lista = new ArrayList<>();
        
        try {
            // Abrir o leitor para ler o arquivo
            BufferedReader leitor = new BufferedReader(new FileReader(arquivo));
            String linha;
            boolean primeiraLinha = true;
            
            while ( (linha = leitor.readLine()) != null ) {
                // Ignora a primeira linha 
                if (primeiraLinha) {
                    primeiraLinha = false;
                    continue;
                }
                
                // Dividir a linha em partes usando o ponto e vírgula como separador
                String[] partes = linha.split(";");
                
                //Destino destino = partes[0];
                //Date dataPartida = partes[1];
                //int duracao = partes[2];
                //double preco = partes[3];
                //int assentosDisponiveis = partes[4];
                
                // Criar o objeto Pacote
                //Pacote pacote = new Pacote(destino, dataPartida, duracao, preco, assentosDisponiveis);
                
                // Adiciona na lista
                //lista.add(pacote);
                
                // Imprimir informações do Pacote
                //System.out.println("Nome: " + nome + " - Nota: " + nota + " - Assistido: " + assistido);
            }
            
            leitor.close();
            
        } catch(IOException e) {
            e.printStackTrace();
        }
        
        return lista;
    }

}
















