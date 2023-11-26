package Resources;

import java.util.Map;

import Model.Cliente;

public class RepositorioCliente extends Repositorio {
    private static Driver clientDriver = new Driver();
    private static String[] clientHeaderNames = new String[]{"nome", "contato", "email", "senha", "id",
            "dataDeRegistro", "pacotesComprados", "creditos"};
    private static String diretorioCliente = "registro_cliente.csv";

    public RepositorioCliente() {
        super(clientDriver, diretorioCliente, clientHeaderNames);
    }

    public Cliente criarClienteComMap(Map<String, String> usr) {
        return new Cliente(usr.get("nome"), usr.get("contato"), usr.get("email"), 
                            usr.get("senha"), Integer.valueOf(usr.get("id")));
    }
}