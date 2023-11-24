package Model;

public enum CategoriaDeDestino {
	PRAIA("Praia"),
    MONTANHA("Montanha"),
    CIDADE("Cidade"),
    AVENTURA("Aventura"),
    HISTORICO("Histórico"),
    RELAXAMENTO("Relaxamento"),
    RURAL("Rural");

    private final String descricao;

    CategoriaDeDestino(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
