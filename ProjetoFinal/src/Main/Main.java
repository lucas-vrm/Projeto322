package Main;

import java.io.FileNotFoundException;
import java.util.Scanner;

import Model.Administrador;
import Model.Login;
import Model.Pacote;

public class Main {
	public static Scanner scanner = new Scanner(System.in);
	public static void main(String[] args) throws FileNotFoundException {
		// Login telaDeLogin = new Login();
		Object usuario = Login.loginScreen(scanner);
		if (usuario != null) {
			Administrador lucas = (Administrador) usuario;
			//lucas.adicionarPacote(scanner);
			//lucas.removerPacote("asdasd");
			Pacote pacote = lucas.getRepPacote().getPacoteByName("Pacote Rio de Janeiro para casal");
			System.out.println(pacote.getNome());
		}
		//MainMethods.controleDeTelas(usuario);
	}



}
