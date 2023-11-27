package Model;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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

    public static boolean isCategoriaValida(String descricao) {
        try {
            CategoriaDeDestino.fromDescricao(descricao);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

        public static List<String> getCategorias() {
        return Arrays.stream(CategoriaDeDestino.values())
                .map(CategoriaDeDestino::getDescricao)
                .collect(Collectors.toList());
    }

    public static void imprimirTodasCategorias() {
        List<String> categorias = getCategorias();
        System.out.println(String.join(", ", categorias));
    }
}