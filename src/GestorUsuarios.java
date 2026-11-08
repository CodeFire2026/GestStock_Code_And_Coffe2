
import java.util.ArrayList;
import java.util.List;

public class GestorUsuarios {

    // Lista donde se almacenan los usuarios
    private final ArrayList<Usuario> usuarios;

    // Constructor
    public GestorUsuarios() {
        usuarios = new ArrayList<Usuario>();
    }

    // Registrar un nuevo usuario
    public boolean registrarUsuario(String nombre, String contrasena, String rol) {

        if (buscarUsuario(nombre) != null) {
            return false;
        }

        Usuario nuevo = new Usuario(nombre, contrasena, rol);
        usuarios.add(nuevo);

        return true;
    }

    // Buscar un usuario por su nombre
    public Usuario buscarUsuario(String nombre) {

        for (Usuario usuario : usuarios) {
            if (usuario.getNombre().equalsIgnoreCase(nombre)) {
                return usuario;
            }
        }

        return null;
    }

    // Iniciar sesion
    public Usuario iniciarSesion(String nombre, String contrasena) {

        Usuario usuario = buscarUsuario(nombre);

        if (usuario != null
                && usuario.isActivo()
                && usuario.getContrasena().equals(contrasena)) {
            return usuario;
        }

        return null;
    }

    // Listar todos los usuarios
    public List<Usuario> listarUsuarios() {
        return new ArrayList<Usuario>(usuarios);
    }

    // Contar usuarios registrados
    public int cantidadUsuarios() {
        return usuarios.size();
    }
}
