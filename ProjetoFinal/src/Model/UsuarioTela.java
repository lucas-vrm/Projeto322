package Model;

import java.util.Scanner;

import Resources.RepositorioPacote;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;

public class UsuarioTela {
    RepositorioPacote repPacote = new RepositorioPacote();
    public UsuarioTela() {
    }

    public void userScreen(Cliente cliente, Scanner scanner){
        System.out.println("---- Janela do Cliente ----");
        System.out.println();
        System.out.println("1. Visualizar Informações");
        System.out.println("2. Adicionar Crédito");
        System.out.println("3. Ver saldo de Crédito");
        System.out.println("4. Buscar pacotes de viagem");            
        System.out.println("5. Sair");
        System.out.println();
        System.out.print("Escolha uma opção: ");


        //scanner.nextLine(); // Limpar o buffer
        int opcao;
        try {
            opcao = scanner.nextInt();
            //scanner.nextLine(); // Limpar o buffer
        } catch (Exception e) {
            System.out.println("Erro: Opção inválida. Certifique-se de inserir um número válido.");
            userScreen(cliente, scanner);
            return;
        }

        switch (opcao) {
            case 1:
                mostrarInfo(cliente, scanner);
                break;
            case 2:
                addCredito(cliente, scanner);
                break;
            case 3:
                mostrarCredito(cliente, scanner);
                break;
            case 4:
                buscarPacote(cliente, scanner);
            case 5:
                System.out.println("Saindo da plataforma");
                break;
            default:
                System.out.println();
                System.out.println("Opção inválida. Tente novamente.");
                userScreen(cliente, scanner);
        }
    }
    
    public void mostrarInfo(Cliente cliente, Scanner scanner) {
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
        

        scanner.nextLine();
        System.out.print("Digite algo para retornar: ");
        scanner.nextLine();
        userScreen(cliente, scanner);

    }

    public void addCredito(Cliente cliente, Scanner scanner) {
        try {
            System.out.println("Digite o valor que você deseja adicionar:");
            double valor = scanner.nextDouble();
            double creditoAntigo = cliente.getCreditos();
            cliente.setCreditos(creditoAntigo + valor);
            scanner.nextLine(); // Limpar o buffer
            System.out.println("Crédito adicionado com sucesso!!!");
        } catch (Exception e) {
            System.out.println("Erro: Certifique-se de inserir um valor de crédito válido.");
            addCredito(cliente, scanner);
            return;
        }

        System.out.print("Digite algo para retornar: ");
        scanner.nextLine();
        userScreen(cliente, scanner);
    }

    public void mostrarCredito(Cliente cliente, Scanner scanner) {
        System.out.println();
        System.out.println();
        System.out.println("=== Créditos do Cliente ===");
        System.out.println();
        System.out.println("Saldo de Créditos atual: " + cliente.getCreditos());
        System.out.println();

        scanner.nextLine();
        System.out.print("Digite algo para retornar: ");
        scanner.nextLine();
        userScreen(cliente, scanner);
    }

    public void buscarPacote(Cliente cliente, Scanner scanner){
        System.out.println("---- Busca por Pacote ----");
        System.out.println("Qual filtro para busca você gostaria de utilizar?");
        System.out.println("1. Buscar por Destino");
        System.out.println("2. Buscar por Categoria");
        System.out.println("3. Buscar por preço máximo");
        System.out.println("4. Retornar");

        //scanner.nextLine(); // Limpar o buffer
        int opcao;
        try {
            opcao = scanner.nextInt();
            scanner.nextLine(); // Limpar o buffer
        } catch (Exception e) {
            System.out.println("Erro: Opção inválida. Certifique-se de inserir um número válido.");
            userScreen(cliente, scanner);
            return;
        }

        switch(opcao){
            case 1:
                System.out.println("Digite o destino desejado:");
                //scanner.nextLine(); // Limpar o buffer do teclado
                String destinoDesejado = scanner.nextLine();
                buscarPacotePorDestino(destinoDesejado);
                break;

            case 2:
                System.out.println("Digite a categoria desejada:");
                System.out.println(CategoriaDeDestino.getCategorias());
                //scanner.nextLine(); // Limpar o buffer do teclado
                String categoriaDesejada = scanner.nextLine();
                if (CategoriaDeDestino.isCategoriaValida(categoriaDesejada)) {
                    buscarPacotePorCategoria(categoriaDesejada.toUpperCase());
                } else {
                    System.out.println("Opção inválida. Por favor, selecione uma das opções acima.");
                }
                break;

            case 3:
                System.out.println("Digite o preço máximo desejado:");
                //scanner.nextLine();
                Double precoMaximo = scanner.nextDouble();
                scanner.nextLine();
                //scanner.nextLine();
                buscarPacotePorPrecoMaximo(precoMaximo);
                break;

            case 4:
                System.out.println();
                userScreen(cliente, scanner);
                return;

            default:
            System.out.println("Opção inválida. Por favor, selecione uma das opções acima.");
            buscarPacote(cliente, scanner);
        }

        System.out.print("Digite algo para continuar a busca por pacotes: ");
        scanner.nextLine();
        buscarPacote(cliente, scanner);
    }

    public void buscarPacotePorDestino(String destinoDesejado){
        
        ArrayList<Pacote> pacotesEncontrados = new ArrayList<>();
        pacotesEncontrados = repPacote.getAllPacotesFromSameAttribute("destino", destinoDesejado);
        
        if (pacotesEncontrados.isEmpty()){
            System.out.println("Não foi possível encontrar pacotes para esse destino!");
        } else {
            System.out.println("=== PACOTES ENCONTRADOS COM O DESTINO " + destinoDesejado + " ====");
            for (Pacote pacote : pacotesEncontrados){
                String nome = pacote.getNome();

                LocalDate localDate = pacote.getDataPartida().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                java.time.format.DateTimeFormatter formatter = java.time.format.DateTimeFormatter.ofPattern("yyyy-MM-dd");
                String dataPartida = localDate.format(formatter);

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
        ArrayList<Pacote> pacotesEncontrados = new ArrayList<>();
        pacotesEncontrados = repPacote.getAllPacotesFromSameAttribute("categoria", categoriaDesejada);
        
        if (pacotesEncontrados.isEmpty()){
            System.out.println("Não foi possível encontrar pacotes com essa categoria!");
        } else {
            System.out.println("=== PACOTES ENCONTRADOS COM A CATEGORIA " + categoriaDesejada + " ====");
            for (Pacote pacote : pacotesEncontrados){
                String nome = pacote.getNome();
                Destino destino = pacote.getDestino();

                LocalDate localDate = pacote.getDataPartida().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                java.time.format.DateTimeFormatter formatter = java.time.format.DateTimeFormatter.ofPattern("yyyy-MM-dd");
                String dataPartida = localDate.format(formatter);

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

        ArrayList<Pacote> pacotesEncontrados = new ArrayList<>();
        pacotesEncontrados = repPacote.getAllPacotes();
        int quantidadePacotes = 0;

        System.out.println("=== PACOTES ENCONTRADOS COM PREÇO MÁXIMO DE R$ " + precoMaximo + " ===");
        for (Pacote pacote : pacotesEncontrados){
            if (pacote.getPreco() <= precoMaximo){
                String nome = pacote.getNome();
                Destino destino = pacote.getDestino();

                LocalDate localDate = pacote.getDataPartida().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                java.time.format.DateTimeFormatter formatter = java.time.format.DateTimeFormatter.ofPattern("yyyy-MM-dd");
                String dataPartida = localDate.format(formatter);

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
