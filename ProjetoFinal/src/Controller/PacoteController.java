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

	public Pacote getModel(){
		return model;
	}

	public void setModel(Pacote model){
		this.model = model;
	}

	public PacoteView getView(){
		return view;
	}

	public void setView(PacoteView view){
		this.view = view;
	}
}
