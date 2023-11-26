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

	public Destino getModel(){
		return model;
	}

	public void setModel(Destino model){
		this.model = model;
	}

	public DestinoView getView(){
		return view;
	}

	public void setView(DestinoView view){
		this.view = view;
	}
}
