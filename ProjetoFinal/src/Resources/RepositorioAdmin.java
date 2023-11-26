package Resources;

public class RepositorioAdmin extends Repositorio{
    private static Driver adminDriver = new Driver();
    private static String[] adminHeaderNames = new String[]{"nome", "contato", "email",
                                                "senha", "id", "dataDeRegistro"};
    private static String diretorioAdmin = "registro_admin.csv";

    public RepositorioAdmin() {
        super(adminDriver, diretorioAdmin, adminHeaderNames);
    }
}