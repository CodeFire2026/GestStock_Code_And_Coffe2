
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

        
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException(
                "El nombre de usuario no puede estar vacio."
            );
        }

        if (contrasena == null || contrasena.trim().isEmpty()) {
            throw new IllegalArgumentException(
                "La contrasena no puede estar vacia."
            );
        }

        if (rol == null ||
                (!rol.equalsIgnoreCase("ADMIN") &&
                 !rol.equalsIgnoreCase("EMPLEADO"))) {
            throw new IllegalArgumentException(
                "El rol debe ser ADMIN o EMPLEADO."
            );
        }

        nombre = nombre.trim();
        rol = rol.toUpperCase();

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

    // Desactiva un usuario por su nombre
    public boolean darDeBajaUsuario(String nombre) {
        Usuario usuario = buscarUsuario(nombre);

        if (usuario == null || !usuario.isActivo()) {
            return false;
        }

        usuario.darDeBaja();
        return true;
    }

    // Reactiva un usuario existente
    public boolean reactivarUsuario(String nombre) {
        Usuario usuario = buscarUsuario(nombre);

        if (usuario == null || usuario.isActivo()) {
            return false;
        }

        usuario.reactivar();
        return true;
    }

    // Cambia el rol de un usuario
    public boolean cambiarRolUsuario(
            String nombre, String nuevoRol) {

        Usuario usuario = buscarUsuario(nombre);

        if (usuario == null || !usuario.isActivo()) {
            return false;
        }

        usuario.cambiarRol(nuevoRol);
        return true;
    }

    // Devuelve todos los usuarios activos
    public List<Usuario> listarActivos() {
        ArrayList<Usuario> activos = new ArrayList<>();

        for (Usuario usuario : usuarios) {
            if (usuario.isActivo()) {
                activos.add(usuario);
            }
        }

        return activos;
    }

}
