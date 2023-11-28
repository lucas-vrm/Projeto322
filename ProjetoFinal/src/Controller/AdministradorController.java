package Controller;

import Model.Administrador;
import View.AdministradorView;
import java.util.Scanner;

public class AdministradorController {
	private Administrador model;
	private AdministradorView view;
	private Scanner scanner;
	
	
	AdministradorController(Administrador model, AdministradorView view) {
		this.model = model;
		this.view = view;
		this.scanner = new Scanner(System.in);
	}
	
	public void getUserInput() {
        int userInput = scanner.nextInt();
        //model.adminScreen(model, scanner);
    }
}