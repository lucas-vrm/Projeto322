package Main;

import java.io.FileNotFoundException;
import java.util.Scanner;

import Model.Login;

public class Main {
	public static Scanner scanner = new Scanner(System.in);
	public static void main(String[] args) throws FileNotFoundException {
		Object usuario = Login.loginScreen(scanner);
		if (usuario != null) {
			MainMethods.controleDeTelas(usuario, scanner);
		}
		//MainMethods.controleDeTelas(usuario);
	}



}
