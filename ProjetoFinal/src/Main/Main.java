package Main;

import java.io.FileNotFoundException;
import java.util.Scanner;

import Model.Administrador;
import Model.Login;

public class Main {
	public static Scanner scanner = new Scanner(System.in);
	public static void main(String[] args) throws FileNotFoundException {
		// Login telaDeLogin = new Login();
		Object usuario = Login.loginScreen(scanner);
		Administrador lucas = (Administrador) usuario;
		lucas.adicionarPacote(scanner);
		//MainMethods.controleDeTelas(usuario);

	}



}
