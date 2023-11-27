package Model;

import java.util.Scanner;

import Resources.RepositorioPacote;
import java.util.ArrayList;
import java.util.Date;

public class UsuarioTela {
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
        RepositorioPacote repPacote = new RepositorioPacote();
        
        ArrayList<Pacote> pacotesEncontrados = new ArrayList<>();
        pacotesEncontrados = repPacote.getAllPacotesFromSameAttribute("destino", destinoDesejado);
        
        if (pacotesEncontrados.isEmpty()){
            System.out.println("Não foi possível encontrar pacotes para esse destino!");
        } else {
            System.out.println("=== PACOTES ENCONTRADOS COM O DESTINO " + destinoDesejado + " ====");
            for (Pacote pacote : pacotesEncontrados){
                String nome = pacote.getNome();
                Date dataPartida = pacote.getDataPartida();
                int duracao = pacote.getDuracao();
                double preco = pacote.getPreco();
                int assentosDisponiveis = pacote.getAssentosDisponiveis();
                CategoriaDeDestino categoria = pacote.getCategoria();
                ArrayList<String> atividadesDisponiveis = pacote.getAtividadesDisponiveis();
                
                System.out.println("Nome: " + nome);
                System.out.println("Data de partida: " + dataPartida);
                System.out.println("Duração em dias: " + duracao);
                System.out.println("Preço: R$ " + preco);
                System.out.println("Assentos Disponíveis: " + assentosDisponiveis);
                System.out.println("Descrição da categoria: " + categoria.getDescricao());
                System.out.println("Atividades Disponíveis: " + atividadesDisponiveis);
                System.out.println("");
            }
        }
    }

    public void buscarPacotePorCategoria(String categoriaDesejada){
        RepositorioPacote repPacote = new RepositorioPacote();
        
        ArrayList<Pacote> pacotesEncontrados = new ArrayList<>();
        pacotesEncontrados = repPacote.getAllPacotesFromSameAttribute("categoria", categoriaDesejada);
        
        if (pacotesEncontrados.isEmpty()){
            System.out.println("Não foi possível encontrar pacotes com essa categoria!");
        } else {
            System.out.println("=== PACOTES ENCONTRADOS COM A CATEGORIA " + categoriaDesejada + " ====");
            for (Pacote pacote : pacotesEncontrados){
                String nome = pacote.getNome();
                Destino destino = pacote.getDestino();
                Date dataPartida = pacote.getDataPartida();
                int duracao = pacote.getDuracao();
                double preco = pacote.getPreco();
                int assentosDisponiveis = pacote.getAssentosDisponiveis();
                ArrayList<String> atividadesDisponiveis = pacote.getAtividadesDisponiveis();
                
                System.out.println("Nome: " + nome);
                System.out.println("Destino: " + destino.getNome());
                System.out.println("Data de partida: " + dataPartida);
                System.out.println("Duração em dias: " + duracao);
                System.out.println("Preço: R$ " + preco);
                System.out.println("Assentos Disponíveis: " + assentosDisponiveis);
                System.out.println("Atividades Disponíveis: " + atividadesDisponiveis);
                System.out.println("");
            }
        }
    }

    public void buscarPacotePorPrecoMaximo(double precoMaximo){
        RepositorioPacote repPacote = new RepositorioPacote();

        ArrayList<Pacote> pacotesEncontrados = new ArrayList<>();
        pacotesEncontrados = repPacote.getAllPacotes();
        int quantidadePacotes = 0;

        System.out.println("=== PACOTES ENCONTRADOS COM PREÇO MÁXIMO DE R$ " + precoMaximo + " ===");
        for (Pacote pacote : pacotesEncontrados){
            if (pacote.getPreco() <= precoMaximo){
                String nome = pacote.getNome();
                Destino destino = pacote.getDestino();
                Date dataPartida = pacote.getDataPartida();
                int duracao = pacote.getDuracao();
                int assentosDisponiveis = pacote.getAssentosDisponiveis();
                CategoriaDeDestino categoria = pacote.getCategoria();
                ArrayList<String> atividadesDisponiveis = pacote.getAtividadesDisponiveis();

                System.out.println("Nome: " + nome);
                System.out.println("Destino: " + destino.getNome());
                System.out.println("Data de partida: " + dataPartida);
                System.out.println("Duração em dias: " + duracao);
                System.out.println("Assentos Disponíveis: " + assentosDisponiveis);
                System.out.println("Descrição da categoria: " + categoria.getDescricao());
                System.out.println("Atividades Disponíveis: " + atividadesDisponiveis);
                System.out.println("");
                quantidadePacotes += 1;
            }
        }
        if (quantidadePacotes == 0){
            System.out.println("Não foi possível encontrar nenhum pacote com preço menor do que o desejado!");
        }
    }

}
