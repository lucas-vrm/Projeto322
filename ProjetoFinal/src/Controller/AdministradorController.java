package Controller;

import Model.Administrador;
import View.AdministradorView;
import java.util.Scanner;

public class AdministradorController {
	private Administrador model;
	private AdministradorView view;
	
	AdministradorController(Administrador model, AdministradorView view) {
		this.model = model;
		this.view = view;
	}
}
