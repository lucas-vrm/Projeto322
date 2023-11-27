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

    public static CategoriaDeDestino fromDescricao(String descricao) {
        for (CategoriaDeDestino categoria : CategoriaDeDestino.values()) {
            if (categoria.descricao.equalsIgnoreCase(descricao)) {
                return categoria;
            }
        }
        throw new IllegalArgumentException("Categoria de destino inválida: " + descricao);
    }
}