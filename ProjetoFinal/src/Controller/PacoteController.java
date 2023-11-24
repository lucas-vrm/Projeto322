package Controller;

import Model.Pacote;
import View.PacoteView;

public class PacoteController {
	private Pacote model;
	private PacoteView view;
	
	PacoteController(Pacote model, PacoteView view){
		this.model = model;
		this.view = view;
		
	}
}
