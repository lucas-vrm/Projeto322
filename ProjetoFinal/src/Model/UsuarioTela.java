package Model;

import java.util.Scanner;

public class UsuarioTela {
    public static void UserScreen(String[] args){
        try(Scanner scanner = new Scanner(System.in)){
            System.out.println("---- Janela do Usuário ----");
            System.out.println();
            System.out.println("1. Visualizar Informações");
            System.out.println("2. Adicionar Crédito");
            System.out.println("3. Ver saldo de Crédito");            
            System.out.println("4. Sair");
            System.out.println();
            System.out.print("Escolha uma opção: ");
        }

    }
}
