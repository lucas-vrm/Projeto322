package Controller;

import Model.Usuario;
import Model.Usuario.TipoMembro;
import View.UsuarioView;

public class UsuarioController {
	private Usuario model;
	private UsuarioView view;
	
	public UsuarioController(Usuario model, UsuarioView view) {
		this.model = model;
		this.view = view;
	}
	
	public void setDetalhesDaPessoa(String nome, String contato, int id, TipoMembro tipoDeMembro) {
		model.setNome(nome);
		model.setContato(contato);
		model.setId(id);
		model.setTipoDeMembro(tipoDeMembro);
	}
}
