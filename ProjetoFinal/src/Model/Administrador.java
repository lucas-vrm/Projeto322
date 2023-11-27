package Model;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Administrador extends Usuario {
	
	private static final String arquivo = "./files/pacotes_viagem.csv";

	public Administrador(String nome, String contato, String email, String senha, int id) {
		super(nome, contato, email, senha, id);
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

    public Destino criaNovoDestino() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Digite o destino do Pacote de Viagem:");
        String destino = scanner.nextLine();

        System.out.println("De uma descricao do Pacote de Viagem:");
        String descricao = scanner.nextLine();

        Destino d = new Destino(destino, descricao);

        System.out.println("Adicione pontos turisticos (digite 0 para parar):");
        while (true) {
            String entrada = scanner.nextLine();

            if (entrada.equals("0")) {
                break;  // Saia do loop se o usuário digitar "0"
            }

            d.adicionaPontoTuristico(entrada);
        }

        return d;
    }

    public Pacote criaNovoPacote() throws ParseException {
        Scanner scanner = new Scanner(System.in);
        
        Destino d = criaNovoDestino();

        System.out.println("Digite a data de partida (formato YYYY-MM-DD):");
        SimpleDateFormat formatoData = new SimpleDateFormat("yyyy-MM-dd");
        String dataDePartidaTexto = scanner.nextLine();
        Date dataDePartida = formatoData.parse(dataDePartidaTexto);

        System.out.println("Digite a duração da viagem em dias:");
        int duracao = scanner.nextInt();

        System.out.println("Digite o preço do Pacote de Viagem:");
        double preco = scanner.nextDouble();

        System.out.println("Digite a quantidade de assentos disponíveis:");
        int assentosDisponiveis = scanner.nextInt();

        System.out.println("Digite a categoria do pacote:");
        String categoria = scanner.nextLine();

        List<String> atividades = new ArrayList<>();

        Pacote p = new Pacote(d, dataDePartida, duracao, preco, assentosDisponiveis, categoria, atividades);

        System.out.println("Adicione atividades (digite 0 para parar):");
        while (true) {
            String entrada = scanner.nextLine();

            if (entrada.equals("0")) {
                break;  // Saia do loop se o usuário digitar "0"
            }

            p.adicionaAtividades(entrada);
        }

        return p;
    }
	
	public void adicionaPacote(Pacote pacote) {
		try {
			//verificar se arquivo ja existe
			boolean arquivoExiste = new File(arquivo).exists();
			
			
			//Abre o escritor para adicionar dados ao arquivo
			FileWriter escritor = new FileWriter(arquivo, StandardCharsets.ISO_8859_1, arquivoExiste);
			if (!arquivoExiste) {
                escritor.write("Destino;PartidaDuracao;Preço;AssentosDisponiveis\n");
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
	
	public void apagaPacote(Pacote pacote) {
		try {
            FileReader fileReader = new FileReader(arquivo);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            List<String> linhas = new ArrayList<>();
            String linhaAtual;

            while ((linhaAtual = bufferedReader.readLine()) != null) {
                linhas.add(linhaAtual);
            }

            bufferedReader.close();

            FileWriter fileWriter = new FileWriter(arquivo);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            boolean encontrado = false;

            for (String linha : linhas) {
                String[] dados = linha.split(",");
                if (dados.length > 0 && !dados[0].equals(pacote)) {
                    // Se o destino nao for o que queremos apagar, escrevemos no arquivo
                    bufferedWriter.write(linha);
                    bufferedWriter.newLine();
                } else {
                    encontrado = true;
                }
            }

            bufferedWriter.close();

            if (!encontrado) {
                System.out.println("Pacote de viagem não encontrado.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

	}
	
	// Método para listar os Pacotes do arquivo CSV
    /*public static ArrayList<Pacote> ListarPacote() {
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
    }*/

}