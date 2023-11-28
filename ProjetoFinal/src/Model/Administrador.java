package Model;

import java.util.ArrayList;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;

import Resources.RepositorioDestino;
import Resources.RepositorioPacote;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Administrador extends Usuario {
    RepositorioDestino repDestino = new RepositorioDestino();
    RepositorioPacote repPacote = new RepositorioPacote();

    public Administrador(String nome, String contato, String email, String senha, int id) {
		super(nome, contato, email, senha, id);
	}
	
    public RepositorioDestino getRepDestino() {
        return this.repDestino;
    }

    public void setRepDestino(RepositorioDestino repDestino) {
        this.repDestino = repDestino;
    }
    
	public RepositorioPacote getRepPacote() {
        return this.repPacote;
    }

    public void setRepPacote(RepositorioPacote repPacote) {
        this.repPacote = repPacote;
    }

	public void adminScreen(Scanner scanner){
        System.out.println("---- Janela Admin ----");
        System.out.println();
        System.out.println("1. Adicionar Destino");
        System.out.println("2. Remover Destino");
        System.out.println("3. Adicionar Pacote");
        System.out.println("4. Remover Pacote");            
        System.out.println("5. Sair");
        System.out.println();
        System.out.print("Escolha uma opção: ");

        int opcao;
        try {
            opcao = scanner.nextInt();
            scanner.nextLine();
        
        } catch (Exception e) {
            System.out.println("Erro: Opção inválida. Certifique-se de inserir um número válido.");
            adminScreen(scanner);
            return;
        }

        switch (opcao) {
            case 1:
                adicionarDestino(scanner);
                break;
            case 2:
                System.out.println("nome do destino a ser removido:");
                String nomeDestino = scanner.nextLine();
                removerDestino(nomeDestino);
                break;
            case 3:
                adicionarPacote(scanner);
                break;
            case 4:
                System.out.println("nome do pacote a ser removido:");
                String nomePacote = scanner.nextLine();
                removerPacote(nomePacote);
            case 5:
                System.out.println("Saindo da plataforma");
                return;
            default:
                System.out.println();
                System.out.println("Opção inválida. Tente novamente.");
            
        }
        adminScreen(scanner);
    }

    public Destino criaNovoDestino(Scanner scanner) {
        boolean entradaValida = false;
        String nome = null, descricao = null;
        ArrayList<String> pontosTuristicos = new ArrayList<>();

        while (!entradaValida) {
            try {
                System.out.println("==== Cadastro de Destino ====");

                System.out.println("Digite o nome do Destino:");
                nome = scanner.nextLine();

                System.out.println("De uma descricao do Destino:");
                descricao = scanner.nextLine();

                System.out.println("Adicione pontos turisticos (digite 0 para parar):");
                while (true) {
                    String entrada = scanner.nextLine();
                    if (entrada.equals("0")) {
                        break;  // Saia do loop se o usuário digitar "0"
                    }
                    pontosTuristicos.add(entrada);
                }
                //scanner.nextLine();
                entradaValida = true;
                
            } catch (InputMismatchException e) {
                System.out.println("Erro: Valor inserido inválido. Certifique-se de inserir o tipo correto de dado.");
                scanner.nextLine();
            }
        }
        return new Destino(nome, descricao, pontosTuristicos);
    }

    public Pacote criaNovoPacote(Scanner scanner) {
        boolean entradaValida = false;
        String nome = null, nomeDestino = null, categoria = null;
        Destino destino = null;
        Date dataDePartida = null;
        int duracao = 0, assentosDisponiveis = 0;
        double preco = 0.0;
        ArrayList<String> atividadesDisponiveis = new ArrayList<>();

        while (!entradaValida) {
            try {
                System.out.println("==== Cadastro de Pacote ====");

                System.out.println("Digite o nome do Pacote:");
                nome = scanner.nextLine();

                System.out.println("Digite o nome do Destino:");
                nomeDestino = scanner.nextLine();
                destino = repDestino.getDestinoByName(nomeDestino);

                System.out.println("Digite a data de partida (formato YYYY-MM-DD):");
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                dataDePartida = dateFormat.parse(scanner.nextLine());

                System.out.println("Digite a duração da viagem em dias:");
                duracao = scanner.nextInt();

                System.out.println("Digite o preço do Pacote de Viagem:");
                preco = scanner.nextDouble();

                System.out.println("Digite a quantidade de assentos disponíveis:");
                assentosDisponiveis = scanner.nextInt();

                scanner.nextLine();
                boolean categoriaValida = false;
                while(!categoriaValida) {
                    System.out.println("Digite a categoria do pacote:");
                    CategoriaDeDestino.imprimirTodasCategorias();
                    categoria = scanner.nextLine();
                    categoriaValida = CategoriaDeDestino.isCategoriaValida(categoria);
                    if (!categoriaValida) {
                        System.out.println("Erro: Categoria inválida.");
                    }
                }

                System.out.println("Adicione atividades (digite 0 para parar):");
                while (true) {
                    String entrada = scanner.nextLine();
                    if (entrada.equals("0")) {
                        break;  // Saia do loop se o usuário digitar "0"
                    }
                    atividadesDisponiveis.add(entrada);
                }
                
                entradaValida = true;
                
            } catch (InputMismatchException e) {
                System.out.println("Erro: Valor inserido inválido. Certifique-se de inserir o tipo correto de dado.");
                //scanner.nextLine();
            } catch (ParseException e) {
                System.out.println("Erro: Formato de data inválido.");
                //scanner.nextLine();
            }
        }
        return new Pacote(nome, destino, dataDePartida, duracao, preco, assentosDisponiveis, categoria, atividadesDisponiveis);
    }
	
    public void adicionarDestino(Scanner scanner) {
        try {
            repDestino.addItem(criaNovoDestino(scanner));
        } catch (FileNotFoundException e) {
            System.out.println("Erro ao criar destino");
            e.printStackTrace();
        }
    }

    public void removerDestino(String nomeDestino) {
        try {
            repDestino.removeItemByStringValue("nome", nomeDestino);
            System.out.println("Destino " + nomeDestino + " removido com sucesso");
        } catch (IOException e) {
            System.out.println("Erro ao remover destino");
            e.printStackTrace();
        }
    }

    public void adicionarPacote(Scanner scanner) {
        try {
            repPacote.addItem(criaNovoPacote(scanner));
        } catch (FileNotFoundException e) {
            System.out.println("Erro ao criar pacote");
            e.printStackTrace();
        }
    }

    public void removerPacote(String nomePacote) {
        try {
            repPacote.removeItemByStringValue("nome", nomePacote);
            System.out.println("Pacote " + nomePacote + " removido com sucesso");
        } catch (IOException e) {
            System.out.println("Erro ao remover pacote");
            e.printStackTrace();
        }
    }

}