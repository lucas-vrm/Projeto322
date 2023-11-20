package Controller;

import Model.Pessoa;
import Model.Pessoa.TipoMembro;
import View.PessoaView;

public class PessoaController {
	private Pessoa model;
	private PessoaView view;
	
	public PessoaController(Pessoa model, PessoaView view) {
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
