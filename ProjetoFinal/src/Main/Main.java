package Main;

import Model.Administrador;
import Model.Cliente;
import Model.Login;

public class Main {

	public static void main(String[] args) {
		// Login telaDeLogin = new Login();
		Object user = Login.loginScreen(args);
		
		if (user != null) {
			String tipoDeUsuario = MainMethods.identificarUsuario(user);
			switch (tipoDeUsuario) {
				case "Client":
					Cliente cliente = (Cliente) user;
					System.out.println("---- Tela Cliente " + cliente.getNome() + " ----");
					// Chamar tela do Cliente 
				case "Administrador":
					Administrador admin = (Administrador) user;
					System.out.println("----- Tela Admin " + admin.getNome() + " -----");
					// Chamar tela do Admin 
				default:
					return;
			}
		} else {
			return;
		}
	}


	



}
