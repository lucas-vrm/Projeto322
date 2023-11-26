package Main;

public class MainMethods {
    public static String identificarUsuario(Object usuario) {
        String classeUsuario = usuario.getClass().getName();
        return classeUsuario.substring(classeUsuario.lastIndexOf(".") + 1);
    }

}
