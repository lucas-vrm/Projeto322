package Model;

import java.util.Scanner;

public class UsuarioTela {
    public static void UserScreen(String[] args, Cliente cliente){
        try(Scanner scanner = new Scanner(System.in)){
            System.out.println("---- Janela do Usuário ----");
            System.out.println();
            System.out.println("1. Visualizar Informações");
            System.out.println("2. Adicionar Crédito");
            System.out.println("3. Ver saldo de Crédito");            
            System.out.println("4. Sair");
            System.out.println();
            System.out.print("Escolha uma opção: ");


            //scanner.nextLine(); // Limpar o buffer
            int opcao;
            try {
                opcao = scanner.nextInt();
                scanner.nextLine(); // Limpar o buffer
            } catch (Exception e) {
                System.out.println("Erro: Opção inválida. Certifique-se de inserir um número válido.");
                scanner.close();
                UserScreen(args, cliente);
                return;
            }

            switch (opcao) {
                case 1:
                    mostrarInfo(cliente);
                    System.out.println("jjaj");
                    break;
                case 2:
                    // chamada do método para add crédito 
                    System.out.println("lalal");
                    break;
                case 3:
                    System.out.println("Saindo da plataforma");
                    return;
                case 4:
                    System.out.println("Saindo da plataforma");
                    break;
                default:
                    System.out.println();
                    System.out.println("Opção inválida. Tente novamente.");
                    UserScreen(args, cliente);
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
        
    }
}
