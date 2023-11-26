package Main;

import Model.Login;

public class Main {

	public static void main(String[] args) {
		// Login telaDeLogin = new Login();
		Object usuario = Login.loginScreen(args);
		MainMethods.controleDeTelas(usuario);
		

	}



}
