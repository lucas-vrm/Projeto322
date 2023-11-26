package Resources;

import java.util.Map;

import Model.Administrador;

public class RepositorioAdmin extends Repositorio{
    private static Driver adminDriver = new Driver();
    private static String[] adminHeaderNames = new String[]{"nome", "contato", "email",
                                                "senha", "id", "dataDeRegistro"};
    private static String diretorioAdmin = "registro_admin.csv";

    public RepositorioAdmin() {
        super(adminDriver, diretorioAdmin, adminHeaderNames);
    }
    
    public Administrador criarAdminComMap(Map<String, String> usr) {
        return new Administrador(usr.get("nome"), usr.get("contato"), usr.get("email"), 
                            usr.get("senha"), Integer.valueOf(usr.get("id")));
    }

}