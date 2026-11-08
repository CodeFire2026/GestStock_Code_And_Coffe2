
public class Usuario {

    // Atributos del usuario
    private String nombre;
    private String contrasena;
    private String rol;
    private boolean activo;

    // Constructor
    public Usuario(String nombre, String contrasena, String rol) {
        this.nombre = nombre;
        this.contrasena = contrasena;
        this.rol = rol;
        this.activo = true;
    }

    // Metodos getters
    public String getNombre() {
        return nombre;
    }

    public String getContrasena() {
        return contrasena;
    }

    public String getRol() {
        return rol;
    }

    public boolean isActivo() {
        return activo;
    }

    // Verifica si el usuario es administrador
    public boolean esAdministrador() {
        return activo && rol.equalsIgnoreCase("ADMIN");
    }

    // Verifica si el usuario es empleado
    public boolean esEmpleado() {
        return activo && rol.equalsIgnoreCase("EMPLEADO");
    }

    // Permite cambiar la contrasena
    public void cambiarContrasena(String nuevaContrasena) {
        if (nuevaContrasena == null ||
                nuevaContrasena.trim().isEmpty()) {
            throw new IllegalArgumentException(
                "La contrasena no puede estar vacia."
            );
        }

        this.contrasena = nuevaContrasena;
    }

    // Permite cambiar el rol
    public void cambiarRol(String nuevoRol) {
        if (nuevoRol == null ||
                (!nuevoRol.equalsIgnoreCase("ADMIN") &&
                 !nuevoRol.equalsIgnoreCase("EMPLEADO"))) {
            throw new IllegalArgumentException(
                "El rol debe ser ADMIN o EMPLEADO."
            );
        }

        this.rol = nuevoRol.toUpperCase();
    }

    // Desactiva al usuario
    public void darDeBaja() {
        this.activo = false;
    }

    // Reactiva al usuario
    public void reactivar() {
        this.activo = true;
    }

}
