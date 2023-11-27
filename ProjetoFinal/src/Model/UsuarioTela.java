package Model;

import java.util.Scanner;

import Resources.RepositorioPacote;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class UsuarioTela {
    private String arquivoPacotes; // Nome do arquivo CSV com os pacotes de viagem

    public UsuarioTela(String arquivoPacotes){
        this.arquivoPacotes = arquivoPacotes;
    }

    public void userScreen(Cliente cliente){
        try(Scanner scanner = new Scanner(System.in)){
            System.out.println("---- Janela do Cliente ----");
            System.out.println();
            System.out.println("1. Visualizar Informações");
            System.out.println("2. Adicionar Crédito");
            System.out.println("3. Ver saldo de Crédito");
            System.out.println("4. Buscar pacotes de viagem");            
            System.out.println("5. Sair");
            System.out.println();
            System.out.print("Escolha uma opção: ");


            scanner.nextLine(); // Limpar o buffer
            int opcao;
            try {
                opcao = scanner.nextInt();
                scanner.nextLine(); // Limpar o buffer
            } catch (Exception e) {
                System.out.println("Erro: Opção inválida. Certifique-se de inserir um número válido.");
                scanner.close();
                userScreen(cliente);
                return;
            }

            switch (opcao) {
                case 1:
                    mostrarInfo(cliente);
                    break;
                case 2:
                    addCredito(cliente);
                    break;
                case 3:
                    mostrarCredito(cliente);
                    break;
                case 4:
                    buscarPacote(cliente);
                case 5:
                    System.out.println("Saindo da plataforma");
                    break;
                default:
                    System.out.println();
                    System.out.println("Opção inválida. Tente novamente.");
                    userScreen(cliente);
            }
        }
    }
    
    public static void mostrarInfo(Cliente cliente) {
        System.out.println();
        System.out.println();
        System.out.println("=== Informações de Usuário ===");
        System.out.println();        
        
        System.out.printf("Nome: %s", cliente.getNome());
        System.out.println();

        System.out.printf("Contato: %s", cliente.getContato());
        System.out.println();

        System.out.printf("Email: %s", cliente.getEmail());
        System.out.println();

        System.out.printf("ID: %d", cliente.getId());
        System.out.println();

    }

    public static void addCredito(Cliente cliente) {
        Scanner entrada = new Scanner(System.in);
        try {
            System.out.println("Digite o valor que você deseja adicionar:");
            double valor = entrada.nextDouble();
            double creditoAntigo = cliente.getCreditos();
            cliente.setCreditos(creditoAntigo + valor);
            entrada.nextLine(); // Limpar o buffer
            System.out.println("Crédito adicionado com sucesso!!!");
        } catch (Exception e) {
            System.out.println("Erro: Certifique-se de inserir um valor de crédito válido.");
            entrada.close();
            addCredito(cliente);
            return;
        }
        entrada.close();
    }
    public static void mostrarCredito(Cliente cliente) {
        System.out.println();
        System.out.println();
        System.out.println("=== Créditos do Cliente ===");
        System.out.println();
        System.out.println("Saldo de Créditos atual: " + cliente.getCreditos());
        System.out.println();
    }

    public void buscarPacote(Cliente cliente){
        try(Scanner scanner = new Scanner(System.in)){
            System.out.println("---- Busca por Pacote ----");
            System.out.println("Qual filtro para busca você gostaria de utilizar?");
            System.out.println("1. Buscar por Destino");
            System.out.println("2. Buscar por Categoria");
            System.out.println("3. Buscar por preço máximo");

            scanner.nextLine(); // Limpar o buffer
            int opcao;
            try {
                opcao = scanner.nextInt();
                scanner.nextLine(); // Limpar o buffer
            } catch (Exception e) {
                System.out.println("Erro: Opção inválida. Certifique-se de inserir um número válido.");
                scanner.close();
                userScreen(cliente);
                return;
            }

            switch(opcao){
                case 1:
                System.out.println("Digite o destino desejado:");
                scanner.nextLine(); // Limpar o buffer do teclado
                String destinoDesejado = scanner.nextLine();
                buscarPacotePorDestino(destinoDesejado);
                break;

                case 2:
                System.out.println("Digite a categoria desejada:");
                scanner.nextLine(); // Limpar o buffer do teclado
                String categoriaDesejada = scanner.nextLine();
                buscarPacotePorCategoria(categoriaDesejada);
                break;

                case 3:
                System.out.println("Digite o preço máximo desejado:");
                scanner.nextLine();
                Double precoMaximo = scanner.nextDouble();
                buscarPacotePorPrecoMaximo(precoMaximo);
                break;

                default:
                System.out.println("Opção inválida. Por favor, selecione uma das opções acima.");
                break;
            }
        }
    }

    public void buscarPacotePorDestino(String destinoDesejado){
        String linhaAtual;
        RepositorioPacote repPacote = new RepositorioPacote();
        Pacote pacote = repPacote.getPacoteByName(destinoDesejado);
            
        System.out.println("=== PACOTES ENCONTRADOS COM O DESTINO " + destinoDesejado + " ====");

 
        String nome = pacote.getNome();
        String dataPartida = dadosPacote[2];
        String duracao = dadosPacote[3];
        String preco = dadosPacote[4];
        String assentosDisponiveis = dadosPacote[5];
        String categoria = dadosPacote[6];
        String atividadesDisponiveis = dadosPacote[7];
        System.out.println("Nome: " + nome);
        System.out.println("Data de partida: " + dataPartida);
        System.out.println("Duração em dias: " + duracao);
        System.out.println("Preço: R$ " + preco);
        System.out.println("Assentos Disponíveis: " + assentosDisponiveis);
        System.out.println("Categoria: " + categoria);
        System.out.println("Atividades Disponíveis: " + atividadesDisponiveis);
        System.out.println("");

    }

    public void buscarPacotePorCategoria(String categoriaDesejada){
        try{
            BufferedReader reader = new BufferedReader(new FileReader(arquivoPacotes));
            String linhaAtual;
            System.out.println("=== PACOTES ENCONTRADOS COM A CATEGORIA " + categoriaDesejada + " ====");
            while ((linhaAtual = reader.readLine()) != null) {
                String[] dadosPacote = linhaAtual.split(",");
                String categoriaPacote = dadosPacote[6];

                // Verificar se o destino atual é o desejado
                if (categoriaPacote.equalsIgnoreCase(categoriaDesejada)){ 
                    String nome = dadosPacote[0];
                    String destino = dadosPacote[1];
                    String dataPartida = dadosPacote[2];
                    String duracao = dadosPacote[3];
                    String preco = dadosPacote[4];
                    String assentosDisponiveis = dadosPacote[5];
                    String atividadesDisponiveis = dadosPacote[7];
                    System.out.println("Nome: " + nome);
                    System.out.println("Destino: " + destino);
                    System.out.println("Data de partida: " + dataPartida);
                    System.out.println("Duração em dias: " + duracao);
                    System.out.println("Preço: R$ " + preco);
                    System.out.println("Assentos Disponíveis: " + assentosDisponiveis);
                    System.out.println("Atividades Disponíveis: " + atividadesDisponiveis);
                    System.out.println("");
                }
            }
            reader.close();
        } catch (IOException e){
            System.out.println("Erro ao ler o arquivo de pacotes de viagem: " + e.getMessage());
        }
    }

    public void buscarPacotePorPrecoMaximo(double precoMaximo){
        try{
            BufferedReader reader = new BufferedReader(new FileReader(arquivoPacotes));
            String linhaAtual;
            System.out.println("=== PACOTES ENCONTRADOS COM PREÇO MÁXIMO DE R$S " + precoMaximo + " ====");

            while ((linhaAtual = reader.readLine()) != null) {
                String[] dadosPacote = linhaAtual.split(",");
                double precoAtual = Double.parseDouble(dadosPacote[4]);

                // Verificar se o destino atual é o desejado
                if (precoAtual <= precoMaximo){ 
                    String nome = dadosPacote[0];
                    String destino = dadosPacote[1];
                    String dataPartida = dadosPacote[2];
                    String duracao = dadosPacote[3];
                    String assentosDisponiveis = dadosPacote[5];
                    String categoria = dadosPacote[6];
                    String atividadesDisponiveis = dadosPacote[7];
                    System.out.println("Nome: " + nome);
                    System.out.println("Destino: " + destino);
                    System.out.println("Data de partida: " + dataPartida);
                    System.out.println("Duração em dias: " + duracao);
                    System.out.println("Assentos Disponíveis: " + assentosDisponiveis);
                    System.out.println("Categoria: " + categoria);
                    System.out.println("Atividades Disponíveis: " + atividadesDisponiveis);
                    System.out.println("");
                }
            }
            reader.close();
        } catch (IOException e){
            System.out.println("Erro ao ler o arquivo de pacotes de viagem: " + e.getMessage());
        }
    }

}
