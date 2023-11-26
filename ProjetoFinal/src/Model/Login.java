package Model;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import Resources.LoginMethods;
import Resources.RepositorioAdmin;
import Resources.RepositorioCliente;

public class Login implements LoginMethods {
    static RepositorioCliente repCliente = new RepositorioCliente();
    static RepositorioAdmin repAdmin = new RepositorioAdmin();

    public static void loginScreen(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("---- Plataforma Turistando ----");
            System.out.println();
            System.out.println("1. Login");
            System.out.println("2. Cadastro");
            System.out.println("3. Sair");
            System.out.println();
            System.out.print("Escolha uma opção: ");

            int opcao;
            try {
                opcao = scanner.nextInt();
                scanner.nextLine(); // Limpar o buffer
            } catch (Exception e) {
                System.out.println("Erro: Opção inválida. Certifique-se de inserir um número válido.");
                loginScreen(args);
                return;
            }

            switch (opcao) {
                case 1:
                    fazerLogin(scanner);
                    break;
                case 2:
                    fazerCadastro(scanner);
                    break;
                case 3:
                    System.out.println("Saindo da plataforma");
                    return;
                default:
                    System.out.println();
                    System.out.println("Opção inválida. Tente novamente.");
                    loginScreen(args);
            }
        }
    }

    public static void fazerLogin(Scanner scanner) {
        System.out.println();
        System.out.println("=== Tela de Login ===");
        System.out.println();

        System.out.print("Digite o email: ");
        String email = scanner.nextLine();
        System.out.print("Digite a senha: ");
        String senha = scanner.nextLine();

        String nome = procurarAdmin(email, senha);
        if (nome != null) {
            System.out.println("Login bem-sucedido! Bem-vindo, Admin " + nome + "!");
        } else {
            nome = procurarUsuario(email, senha);
            if (nome != null) {
                System.out.println("Login bem-sucedido! Bem-vindo, Cliente " + nome + "!");
            } else {
                System.out.println("Usuário ou senha incorretos. Tente novamente.");
                fazerLogin(scanner);
            }
        }
    }

    public static void fazerCadastro(Scanner scanner) {
        System.out.println();
        System.out.println();
        System.out.println("=== Tela de Cadastro ===");
        System.out.println();

        boolean entradaValida = false;
        String nome = null, contato = null, email = null, senha = null;
        int id = 0;


        while (!entradaValida) {
            try {
                System.out.println("==== Cadastro de Cliente ====");

                System.out.print("Nome: ");
                nome = scanner.nextLine();

                System.out.print("Contato: ");
                contato = scanner.nextLine();

                System.out.print("Email: ");
                email = scanner.nextLine();

                System.out.print("ID: ");
                id = scanner.nextInt();

                System.out.print("Senha: ");
                senha = scanner.nextLine();

                scanner.nextLine();

                entradaValida = true;
                
            } catch (InputMismatchException e) {
                System.out.println("Erro: Valor inserido inválido. Certifique-se de inserir o tipo correto de dado.");
                scanner.nextLine();
            }
        }

        scanner.close();

        Cliente novoCliente = new Cliente(nome, contato, email, senha, id);
        addClient(repCliente, novoCliente);
        System.out.println("Cadastro realizado com sucesso!");
    }

    private static void addClient(RepositorioCliente repCliente2, Cliente novoCliente) {
        try {
            repCliente.addItem(novoCliente);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static String procurarAdmin(String email, String senha) {
        String[] headernames = repAdmin.getHeaderNames();
        int email_column = repAdmin.getDriver().attributeColumn("email", headernames);
        int password_column = repAdmin.getDriver().attributeColumn("senha", headernames);
        int name_column = repAdmin.getDriver().attributeColumn("nome", headernames);
        ArrayList<ArrayList<String>> items = null;
        
        try {
            items = repAdmin.pullAllItems();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

        for (ArrayList<String> item : items) {
            if (item.get(email_column).equals(email) && item.get(password_column).equals(senha)) {
                return item.get(name_column);
            }
        }
        return null;
    }

    private static String procurarUsuario(String email, String senha) {
        String[] headernames = repCliente.getHeaderNames();
        int email_column = repCliente.getDriver().attributeColumn("email", headernames);
        int password_column = repCliente.getDriver().attributeColumn("senha", headernames);
        int name_column = repCliente.getDriver().attributeColumn("nome", headernames);
        ArrayList<ArrayList<String>> items = null;
        
        try {
            items = repCliente.pullAllItems();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

        for (ArrayList<String> item : items) {
            if (item.get(email_column).equals(email) && item.get(password_column).equals(senha)) {
                return item.get(name_column);
            }
        }
        return null;
    }

}
