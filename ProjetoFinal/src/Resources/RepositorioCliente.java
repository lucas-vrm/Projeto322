package Resources;

public class RepositorioCliente extends Repositorio {
    private static Driver clientDriver = new Driver();
    private static String[] clientHeaderNames = new String[]{"nome", "contato", "email", "senha", "id",
            "dataDeRegistro", "destinos", "creditos"};
    private static String diretorioCliente = "registro_cliente.csv";

    public RepositorioCliente() {
        super(clientDriver, diretorioCliente, clientHeaderNames);
    }
}