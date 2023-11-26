package Model;

import java.util.Scanner;

public class UsuarioTela {
    public static void userScreen(Cliente cliente){
        try(Scanner scanner = new Scanner(System.in)){
            System.out.println("---- Janela do Cliente ----");
            System.out.println();
            System.out.println("1. Visualizar Informações");
            System.out.println("2. Adicionar Crédito");
            System.out.println("3. Ver saldo de Crédito");            
            System.out.println("4. Sair");
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
}
