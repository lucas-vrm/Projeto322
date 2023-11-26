package Main;

import Model.Administrador;
import Model.Cliente;
import Model.UsuarioTela;

public class MainMethods {
    public static String identificarTipoUsuario(Object usuario) {
        String classeUsuario = usuario.getClass().getName();
        return classeUsuario.substring(classeUsuario.lastIndexOf(".") + 1);
    }

    public static void controleDeTelas(String[] args, Object user) {
        if (user != null) {
			String tipoDeUsuario = MainMethods.identificarTipoUsuario(user);
			switch (tipoDeUsuario) {
				case "Cliente":
					Cliente cliente = (Cliente) user;
					System.out.println("---- Tela Cliente " + cliente.getNome() + " ----");
					// Chamar tela do Cliente
                    UsuarioTela.UserScreen(cliente);
                    break;
				
                case "Administrador":
                    Administrador admin = (Administrador) user;
                    System.out.println("----- Tela Admin " + admin.getNome() + " -----");
                    // Chamar tela do Admin
                    break;
            
                default:
                    //return;
			}
		}
    }

    public static void telaCliente(Object user){

    }

    public static void telaAdmin(Object user){
        
    }



}
