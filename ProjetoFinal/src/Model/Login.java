package Model;

import java.util.Scanner;

public class Login {

    public static void loginScreen(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("---- Sistema de Login e Cadastro ----");
        System.out.println();
        System.out.println("1. Login");
        System.out.println("2. Cadastro");
        System.out.println("3. Sair");
        System.out.println();
        System.out.print("Escolha uma opção: ");

        int opcao = scanner.nextInt();
        scanner.nextLine(); // Limpar o buffer

        switch (opcao) {
            case 1:
                fazerLogin(scanner);
                break;
            case 2:
                fazerCadastro(scanner);
                break;
            case 3:
                System.out.println("Saindo da plataforma");
                scanner.close();
                return;
            default:
                System.out.println();
                System.out.println("Opção inválida. Tente novamente.");
                scanner.close();
                loginScreen(args);
        }
        
    }

    private static void fazerLogin(Scanner scanner) {
        System.out.println();
        System.out.println();
        System.out.println("=== Tela de Login ===");
        System.out.println();

        System.out.print("Digite o nome de usuário: ");
        String usuario = scanner.nextLine();
        System.out.print("Digite a senha: ");
        String senha = scanner.nextLine();

        if (checkarLogin(usuario, senha)) {
            System.out.println("Login bem-sucedido! Bem-vindo, " + usuario + "!");
        } else {
            System.out.println("Usuário ou senha incorretos. Tente novamente.");
        }
    }

    private static void fazerCadastro(Scanner scanner) {
        System.out.println();
        System.out.println();
        System.out.println("=== Tela de Cadastro ===");
        System.out.println();

        // Lógica de cadastro aqui
        // ...

        // Exemplo de volta para o menu principal
        System.out.println("Cadastro realizado com sucesso!");
    }

    private static boolean checkarLogin(String usuario, String senha) {
        return true;
    }


}
