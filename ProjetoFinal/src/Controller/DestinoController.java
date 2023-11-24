package Controller;

import Model.Destino;
import View.DestinoView;

public class DestinoController {
	private Destino model;
	private DestinoView view;
	
	DestinoController(Destino model, DestinoView view){
		this.model = model;
		this.view = view;
		
	}
}
