

/**
 * GESTSTOCK - Proyecto Integrador
 *
 * Clase principal del sistema.
 * Se encarga de mostrar los menus y controlar las opciones seleccionadas por el usuario.
 */
public class GestStock {

    // Objetos principales del sistema.
    private final Consola consola;
    private final Inventario inventario;
    private final GestorUsuarios gestorUsuarios;
    private Usuario usuarioActual;

    // Constructor.
    public GestStock() {
    consola = new Consola();
    inventario = new Inventario();
    DatosEjemplo.cargarProductos(inventario);
    gestorUsuarios = new GestorUsuarios();

    gestorUsuarios.registrarUsuario("admin", "admin123", "ADMIN");
    gestorUsuarios.registrarUsuario("empleado", "1234", "EMPLEADO");
}

    // Punto de entrada del programa.
    public static void main(String[] args) {
        GestStock programa = new GestStock();
        programa.ejecutar();
    }

    // Controla la ejecucion del menu principal.
    public void ejecutar() {

    if (!iniciarSesion()) {
        consola.cerrar();
        return;
    }

    int opcion;

        do {
            mostrarMenuPrincipal();

            opcion = consola.leerEntero("Opcion: ");

            switch (opcion) {
                case 1:
                    mostrarMenuProductos();
                    break;

                case 2:
                    mostrarMenuStock();
                    break;

                case 3:
                    if (usuarioActual.esAdministrador()) {
                        mostrarMenuUsuarios();
                    } else {
                        System.out.println(
                            "\nAcceso denegado. Solo los administradores pueden gestionar usuarios."
                        );
                        consola.esperarEnter();
                    }
                    break;
                    
                case 0:
                    System.out.println(
                        "\nGracias por utilizar GestStock."
                    );
                    break;

                default:
                    System.out.println("\nOpcion invalida.");
                    consola.esperarEnter();
                    break;
            }

        } while (opcion != 0);

        consola.cerrar();
    }

    // Solicita las credenciales para ingresar al sistema.
    private boolean iniciarSesion() {

        consola.limpiarPantalla();

        System.out.println("================================");
        System.out.println("     BIENVENIDO A GESTSTOCK");
        System.out.println("================================");

        for (int intento = 1; intento <= 3; intento++) {

            String nombre = consola.leerTextoNoVacio(
                "Usuario: "
            );

            String contrasena = consola.leerTexto(
                "Contrasena: "
            );

            usuarioActual = gestorUsuarios.iniciarSesion(
                nombre, contrasena
            );

            if (usuarioActual != null) {
                System.out.println(
                    "\nBienvenido, " + usuarioActual.getNombre()
                );

                consola.esperarEnter();
                return true;
            }

            System.out.println(
                "\nUsuario o contrasena incorrectos."
            );

            System.out.println(
                "Intentos restantes: " + (3 - intento)
            );
        }

        System.out.println(
            "\nDemasiados intentos. Acceso denegado."
        );

        return false;
    }

    // Muestra el menu de gestion de stock.
    private void mostrarMenuStock() {
        int opcion;

        do {
            consola.limpiarPantalla();

            System.out.println("================================");
            System.out.println("       GESTION DE STOCK");
            System.out.println("================================");
            System.out.println("1. Ingresar stock");
            System.out.println("2. Egresar stock");
            System.out.println("3. Consultar stock");
            System.out.println("4. Productos con stock bajo");
            System.out.println("0. Volver al menu principal");

            opcion = consola.leerEnteroEnRango(
                "Seleccione una opcion: ", 0, 4
            );

            switch (opcion) {
                case 1:            
                    ingresarStock();
                    break;
                   

                case 2:
                    egresarStock();
                    break;
                    
                case 3:
                    consultarStock();
                    break;

                case 4:
                    mostrarProductosStockBajo();
                    break;
            }

        } while (opcion != 0);
    }

// Muestra el menu de gestion de productos.
private void mostrarMenuProductos() {
    int opcion;

    do {
        consola.limpiarPantalla();

        System.out.println("================================");
        System.out.println("     GESTION DE PRODUCTOS");
        System.out.println("================================");
        System.out.println("1. Registrar producto");
        System.out.println("2. Listar productos");
        System.out.println("3. Buscar producto por nombre");
        System.out.println("4. Filtrar productos por seccion");
        System.out.println("5. Reportes y estadisticas");
        System.out.println("6. Consultar producto por codigo");
        System.out.println("7. Modificar producto (ADMIN)");
        System.out.println("8. Dar de baja producto (ADMIN)");
        System.out.println("9. Reactivar producto (ADMIN)");
        System.out.println("0. Volver al menu principal");

        opcion = consola.leerEnteroEnRango(
            "Seleccione una opcion: ", 0, 9
        );

        switch (opcion) {
            case 1:
                if (usuarioActual.esAdministrador()) {
                    registrarProducto();
                } else {
                    mostrarAccesoDenegado();
                }
                break;

            case 2:
                listarProductos();
                break;

            case 3:
                buscarProductoPorNombre();
                break;

            case 4:
                filtrarProductosPorSeccion();
                break;

            case 5:
                mostrarReportesInventario();
                break;

            case 6:
                consultarProductoPorCodigo();
                break;

            case 7:
                if (usuarioActual.esAdministrador()) {
                    modificarProducto();
                } else {
                    mostrarAccesoDenegado();
                }
                break;

            case 8:
                if (usuarioActual.esAdministrador()) {
                    darDeBajaProducto();
                } else {
                    mostrarAccesoDenegado();
                }
                break;

            case 9:
                if (usuarioActual.esAdministrador()) {
                    reactivarProducto();
                } else {
                    mostrarAccesoDenegado();
                }
                break;
        }

    } while (opcion != 0);
}

    // Muestra el menu de gestion de usuarios.
private void mostrarMenuUsuarios() {
    int opcion;

    do {
        consola.limpiarPantalla();

        System.out.println("================================");
        System.out.println("      GESTION DE USUARIOS");
        System.out.println("================================");
        System.out.println("1. Registrar usuario");
        System.out.println("2. Listar usuarios");
        System.out.println("3. Dar de baja usuario");
        System.out.println("4. Reactivar usuario");
        System.out.println("5. Cambiar rol de usuario");
        System.out.println("0. Volver al menu principal");

        opcion = consola.leerEnteroEnRango(
            "Seleccione una opcion: ", 0, 5
        );

        switch (opcion) {
            case 1:
                registrarUsuario();
                break;

            case 2:
                listarUsuarios();
                break;

            case 3:
                darDeBajaUsuario();
                break;

            case 4:
                reactivarUsuario();
                break;

            case 5:
                cambiarRolUsuario();
                break;
        }

    } while (opcion != 0);
}

// Registra un nuevo usuario.
private void registrarUsuario() {
    consola.limpiarPantalla();

    System.out.println("================================");
    System.out.println("       REGISTRAR USUARIO");
    System.out.println("================================");

    String nombre = consola.leerTextoNoVacio(
        "Nombre de usuario: "
    );

    String contrasena = consola.leerTextoNoVacio(
        "Contrasena: "
    );

    System.out.println("\nRoles disponibles:");
    System.out.println("1. ADMIN");
    System.out.println("2. EMPLEADO");

    int opcionRol = consola.leerEnteroEnRango(
        "Seleccione el rol: ", 1, 2
    );

    String rol = (opcionRol == 1) ? "ADMIN" : "EMPLEADO";

    try {
        boolean registrado = gestorUsuarios.registrarUsuario(
            nombre, contrasena, rol
        );

        if (registrado) {
            System.out.println("\nUsuario registrado correctamente.");
        } else {
            System.out.println("\nYa existe un usuario con ese nombre.");
        }

    } catch (IllegalArgumentException e) {
        System.out.println("\nError: " + e.getMessage());
    }

    consola.esperarEnter();
}

// Lista todos los usuarios registrados.
private void listarUsuarios() {
    consola.limpiarPantalla();

    System.out.println("================================");
    System.out.println("       LISTADO DE USUARIOS");
    System.out.println("================================");

    for (Usuario usuario : gestorUsuarios.listarUsuarios()) {
        System.out.println("\nNombre: " + usuario.getNombre());
        System.out.println("Rol: " + usuario.getRol());
        System.out.println(
            "Estado: " + (usuario.isActivo() ? "Activo" : "Inactivo")
        );
        System.out.println("--------------------------------");
    }

    if (gestorUsuarios.cantidadUsuarios() == 0) {
        System.out.println("\nNo hay usuarios registrados.");
    }

    consola.esperarEnter();
}

// Da de baja un usuario.
private void darDeBajaUsuario() {
    consola.limpiarPantalla();

    System.out.println("================================");
    System.out.println("       DAR DE BAJA USUARIO");
    System.out.println("================================");

    String nombre = consola.leerTextoNoVacio(
        "Ingrese el nombre del usuario: "
    );

    if (nombre.equalsIgnoreCase(usuarioActual.getNombre())) {
        System.out.println("\nNo puede darse de baja a si mismo.");
    } else if (gestorUsuarios.darDeBajaUsuario(nombre)) {
        System.out.println("\nUsuario dado de baja correctamente.");
    } else {
        System.out.println("\nNo existe el usuario o ya esta inactivo.");
    }

    consola.esperarEnter();
}

// Reactiva un usuario.
private void reactivarUsuario() {
    consola.limpiarPantalla();

    System.out.println("================================");
    System.out.println("       REACTIVAR USUARIO");
    System.out.println("================================");

    String nombre = consola.leerTextoNoVacio(
        "Ingrese el nombre del usuario: "
    );

    if (gestorUsuarios.reactivarUsuario(nombre)) {
        System.out.println("\nUsuario reactivado correctamente.");
    } else {
        System.out.println("\nNo existe el usuario o ya esta activo.");
    }

    consola.esperarEnter();
}

// Cambia el rol de un usuario.
private void cambiarRolUsuario() {
    consola.limpiarPantalla();

    System.out.println("================================");
    System.out.println("      CAMBIAR ROL DE USUARIO");
    System.out.println("================================");

    String nombre = consola.leerTextoNoVacio(
        "Ingrese el nombre del usuario: "
    );

    if (nombre.equalsIgnoreCase(usuarioActual.getNombre())) {
        System.out.println("\nNo puede cambiar su propio rol.");
        consola.esperarEnter();
        return;
    }

    System.out.println("\nRoles disponibles:");
    System.out.println("1. ADMIN");
    System.out.println("2. EMPLEADO");

    int opcion = consola.leerEnteroEnRango(
        "Seleccione el nuevo rol: ", 1, 2
    );

    String nuevoRol = (opcion == 1) ? "ADMIN" : "EMPLEADO";

    try {
        if (gestorUsuarios.cambiarRolUsuario(nombre, nuevoRol)) {
            System.out.println("\nRol actualizado correctamente.");
        } else {
            System.out.println("\nNo existe el usuario o esta inactivo.");
        }
    } catch (IllegalArgumentException e) {
        System.out.println("\nError: " + e.getMessage());
    }

    consola.esperarEnter();
}

// Permite consultar por codigo, incluso cuando el producto esta inactivo.
private void consultarProductoPorCodigo() {
    consola.limpiarPantalla();
    System.out.println("=== CONSULTAR PRODUCTO POR CODIGO ===");
    int codigo = consola.leerEnteroMayorACero("Codigo: ");
    Producto producto = inventario.buscarPorCodigoIncluyendoInactivos(codigo);

    if (producto == null) {
        System.out.println("\nNo se encontro un producto con ese codigo.");
    } else {
        mostrarDetalleProducto(producto);
    }
    consola.esperarEnter();
}

// Presenta todos los campos de un producto y su estado.
private void mostrarDetalleProducto(Producto producto) {
    System.out.println("\nCodigo: " + producto.getCodigo());
    System.out.println("Nombre: " + producto.getNombre());
    System.out.println("Descripcion: " + producto.getDescripcion());
    System.out.println("Seccion: " + Inventario.nombreSeccion(producto.getSeccion()));
    System.out.println("Precio: $" + Consola.dinero(producto.getPrecio()));
    System.out.println("Stock: " + producto.getStock());
    System.out.println("Stock minimo: " + producto.getStockMinimo());
    System.out.println("Estado: " + (producto.isActivo() ? "ACTIVO" : "INACTIVO"));
}

// Edita datos de un producto activo sin modificar su codigo ni su stock.
private void modificarProducto() {
    consola.limpiarPantalla();
    System.out.println("=== MODIFICAR PRODUCTO ===");
    int codigo = consola.leerEnteroMayorACero("Codigo: ");
    Producto producto = inventario.buscarPorCodigo(codigo);
    if (producto == null) {
        System.out.println("\nNo existe un producto activo con ese codigo.");
        consola.esperarEnter();
        return;
    }

    mostrarDetalleProducto(producto);
    System.out.println("\n1. Nombre");
    System.out.println("2. Descripcion");
    System.out.println("3. Seccion");
    System.out.println("4. Precio");
    System.out.println("5. Stock minimo");
    System.out.println("0. Cancelar");
    int opcion = consola.leerEnteroEnRango("Opcion: ", 0, 5);
    if (opcion == 0) {
        System.out.println("Modificacion cancelada.");
        consola.esperarEnter();
        return;
    }

    try {
        switch (opcion) {
            case 1:
                producto.setNombre(consola.leerTextoNoVacio("Nuevo nombre: "));
                break;
            case 2:
                producto.setDescripcion(consola.leerTexto("Nueva descripcion: "));
                break;
            case 3:
                int seccion = consola.leerEnteroEnRango("Nueva seccion (1-6): ", 1, 6);
                producto.setSeccion(seccion);
                break;
            case 4:
                producto.setPrecio(consola.leerRealNoNegativo("Nuevo precio: "));
                break;
            case 5:
                producto.setStockMinimo(consola.leerEnteroNoNegativo("Nuevo stock minimo: "));
                break;
            default:
                break;
        }
        System.out.println("\nProducto actualizado correctamente.");
        mostrarDetalleProducto(producto);
    } catch (IllegalArgumentException e) {
        System.out.println("\nError: " + e.getMessage());
    }
    consola.esperarEnter();
}

// Cambia el estado del producto sin borrar el registro.
private void darDeBajaProducto() {
    consola.limpiarPantalla();
    System.out.println("=== DAR DE BAJA PRODUCTO ===");
    int codigo = consola.leerEnteroMayorACero("Codigo: ");
    Producto producto = inventario.buscarPorCodigo(codigo);
    if (producto == null) {
        System.out.println("\nNo existe un producto activo con ese codigo.");
    } else {
        mostrarDetalleProducto(producto);
        String confirmacion = consola.leerTexto("\nConfirmar baja? (S/N): ");
        if (confirmacion.trim().equalsIgnoreCase("S")) {
            producto.darDeBaja();
            System.out.println("\nProducto dado de baja. Conserva su codigo.");
        } else {
            System.out.println("\nBaja cancelada.");
        }
    }
    consola.esperarEnter();
}

// Recupera el mismo registro y codigo de un producto dado de baja.
private void reactivarProducto() {
    consola.limpiarPantalla();
    System.out.println("=== REACTIVAR PRODUCTO ===");
    int codigo = consola.leerEnteroMayorACero("Codigo: ");
    Producto producto = inventario.buscarPorCodigoIncluyendoInactivos(codigo);
    if (producto == null) {
        System.out.println("\nNo existe un producto con ese codigo.");
    } else if (producto.isActivo()) {
        System.out.println("\nEl producto ya esta activo.");
    } else {
        mostrarDetalleProducto(producto);
        String confirmacion = consola.leerTexto("\nConfirmar reactivacion? (S/N): ");
        if (confirmacion.trim().equalsIgnoreCase("S")) {
            producto.reactivar();
            System.out.println("\nProducto reactivado correctamente.");
        } else {
            System.out.println("\nReactivacion cancelada.");
        }
    }
    consola.esperarEnter();
}

private void mostrarAccesoDenegado() {
    System.out.println("\nAcceso denegado: operacion exclusiva de administradores.");
    consola.esperarEnter();
}

// Registra un producto nuevo en el inventario.
private void registrarProducto() {
    consola.limpiarPantalla();

    System.out.println("================================");
    System.out.println("       REGISTRAR PRODUCTO");
    System.out.println("================================");

    String nombre = consola.leerTextoNoVacio(
        "Nombre: "
    );

    String descripcion = consola.leerTexto(
        "Descripcion: "
    );

    System.out.println("\nSecciones:");
    System.out.println("1. Bebidas");
    System.out.println("2. Frutas");
    System.out.println("3. Verduras");
    System.out.println("4. Carnes");
    System.out.println("5. Lacteos");
    System.out.println("6. Almacen");

    int seccion = consola.leerEnteroEnRango(
        "Seleccione la seccion: ", 1, 6
    );

    double precio = consola.leerRealNoNegativo(
        "Precio: "
    );

    int stock = consola.leerEnteroNoNegativo(
        "Stock inicial: "
    );

    int stockMinimo = consola.leerEnteroNoNegativo(
        "Stock minimo: "
    );

    try {
        Producto nuevo = inventario.agregarProducto(
            nombre,
            descripcion,
            seccion,
            precio,
            stock,
            stockMinimo
        );

        System.out.println(
            "\nProducto registrado correctamente."
        );
        System.out.println(
            "Codigo asignado: " + nuevo.getCodigo()
        );

    } catch (IllegalArgumentException |
             IllegalStateException e) {
        System.out.println("\nError: " + e.getMessage());
    }

    consola.esperarEnter();
}

// Muestra todos los productos activos.

private void listarProductos() {
    consola.limpiarPantalla();

    System.out.println(
        "\n--- LISTADO COMPLETO DE PRODUCTOS ---\n"
    );

    java.util.List<Producto> productos =
        inventario.listarActivos();

    if (productos.isEmpty()) {
        System.out.println("No hay productos registrados.");
        consola.esperarEnter();
        return;
    }

    System.out.printf(
        "%-4s | %-30s | %-10s | %-12s | %-7s | %-5s%n",
        "COD", "NOMBRE", "SECCION", "PRECIO", "STOCK", "MIN."
    );

    System.out.println(
        "-----+--------------------------------+------------+" +
        "--------------+---------+------"
    );

    for (Producto producto : productos) {
        String nombre = producto.getNombre();

        if (nombre.length() > 30) {
            nombre = nombre.substring(0, 27) + "...";
        }

        String stockTexto = String.valueOf(
            producto.getStock()
        );

        if (producto.necesitaReposicion()) {
            stockTexto += " (!)";
        }

        System.out.printf(
            "%-4d | %-30s | %-10s | $%-11s | %-7s | %-5d%n",
            producto.getCodigo(),
            nombre,
            Inventario.nombreSeccion(producto.getSeccion()),
            Consola.dinero(producto.getPrecio()),
            stockTexto,
            producto.getStockMinimo()
        );
    }

    System.out.println(
        "\nTotal de productos activos: " + productos.size()
    );

    System.out.println(
        "(!) Producto con stock bajo o en el minimo."
    );

    consola.esperarEnter();
}

// Busca productos por nombre o palabras.
private void buscarProductoPorNombre() {
    consola.limpiarPantalla();

    System.out.println("================================");
    System.out.println("      BUSCAR PRODUCTO");
    System.out.println("================================");

    String texto = consola.leerTextoNoVacio(
        "Ingrese el nombre o palabra a buscar: "
    );

    java.util.List<Producto> resultados =
        inventario.buscarPorNombre(texto);

    if (resultados.isEmpty()) {
        System.out.println("\nNo se encontraron productos.");
    } else {
        System.out.println("\nResultados encontrados:");

        for (Producto producto : resultados) {
            System.out.println("\nCodigo: " + producto.getCodigo());
            System.out.println("Nombre: " + producto.getNombre());
            System.out.println(
                "Seccion: " +
                Inventario.nombreSeccion(producto.getSeccion())
            );
            System.out.println(
                "Precio: $" + Consola.dinero(producto.getPrecio())
            );
            System.out.println("Stock: " + producto.getStock());
            System.out.println("--------------------------------");
        }
    }

    consola.esperarEnter();
}
// Permite ingresar unidades a un producto existente.
private void ingresarStock() {
    consola.limpiarPantalla();

    System.out.println("================================");
    System.out.println("         INGRESAR STOCK");
    System.out.println("================================");

    int codigo = consola.leerEnteroMayorACero(
        "Ingrese el codigo del producto: "
    );

    Producto producto = inventario.buscarPorCodigo(codigo);

    if (producto == null) {
        System.out.println("\nNo se encontro el producto.");
        consola.esperarEnter();
        return;
    }

    System.out.println("\nProducto: " + producto.getNombre());
    System.out.println("Stock actual: " + producto.getStock());

    int cantidad = consola.leerEnteroMayorACero(
        "Cantidad que desea ingresar: "
    );

    try {
        producto.ingresarStock(cantidad);

        System.out.println("\nStock actualizado correctamente.");
        System.out.println(
            "Nuevo stock: " + producto.getStock()
        );

    } catch (IllegalArgumentException e) {
        System.out.println("\nError: " + e.getMessage());
    }

    consola.esperarEnter();
}
// Filtra los productos segun su seccion.
private void filtrarProductosPorSeccion() {
    consola.limpiarPantalla();

    System.out.println("================================");
    System.out.println("    FILTRAR POR SECCION");
    System.out.println("================================");
    System.out.println("1. Bebidas");
    System.out.println("2. Frutas");
    System.out.println("3. Verduras");
    System.out.println("4. Carnes");
    System.out.println("5. Lacteos");
    System.out.println("6. Almacen");

    int seccion = consola.leerEnteroEnRango(
        "Seleccione una seccion: ", 1, 6
    );

    java.util.List<Producto> resultados =
        inventario.listarPorSeccion(seccion);

    System.out.println(
        "\nProductos de la seccion: " +
        Inventario.nombreSeccion(seccion)
    );

    if (resultados.isEmpty()) {
        System.out.println("\nNo hay productos en esta seccion.");
    } else {
        for (Producto producto : resultados) {
            System.out.println("\nCodigo: " + producto.getCodigo());
            System.out.println("Nombre: " + producto.getNombre());
            System.out.println(
                "Precio: $" + Consola.dinero(producto.getPrecio())
            );
            System.out.println("Stock: " + producto.getStock());
            System.out.println("--------------------------------");
        }
    }

    consola.esperarEnter();
}
// Muestra reportes y estadisticas generales del inventario.
private void mostrarReportesInventario() {
    consola.limpiarPantalla();

    System.out.println("================================");
    System.out.println("    REPORTES DEL INVENTARIO");
    System.out.println("================================");

    System.out.println(
        "\nCantidad de productos activos: " +
        inventario.cantidadActivos()
    );

    System.out.println(
        "Valor total del inventario: $" +
        Consola.dinero(inventario.valorTotal())
    );

    Producto masCaro = inventario.productoMasCaro();

    System.out.println("\nPRODUCTO DE MAYOR PRECIO:");

    if (masCaro == null) {
        System.out.println("No hay productos registrados.");
    } else {
        System.out.println("Nombre: " + masCaro.getNombre());
        System.out.println(
            "Precio: $" + Consola.dinero(masCaro.getPrecio())
        );
    }

    System.out.println("\nCANTIDAD DE PRODUCTOS POR SECCION:");

    int[] cantidades = inventario.contarPorSecciones();

    for (int i = 1; i <= 6; i++) {
        System.out.println(
            Inventario.nombreSeccion(i) + ": " + cantidades[i]
        );
    }

    consola.esperarEnter();
}

// Permite egresar unidades de un producto existente.
private void egresarStock() {
    consola.limpiarPantalla();

    System.out.println("================================");
    System.out.println("          EGRESAR STOCK");
    System.out.println("================================");

    int codigo = consola.leerEnteroMayorACero(
        "Ingrese el codigo del producto: "
    );

    Producto producto = inventario.buscarPorCodigo(codigo);

    if (producto == null) {
        System.out.println("\nNo se encontro el producto.");
        consola.esperarEnter();
        return;
    }

    System.out.println("\nProducto: " + producto.getNombre());
    System.out.println("Stock actual: " + producto.getStock());

    int cantidad = consola.leerEnteroMayorACero(
        "Cantidad que desea egresar: "
    );

    try {
        producto.egresarStock(cantidad);

        System.out.println("\nStock actualizado correctamente.");
        System.out.println(
            "Nuevo stock: " + producto.getStock()
        );

    } catch (IllegalArgumentException e) {
        System.out.println("\nError: " + e.getMessage());
    }

    consola.esperarEnter();
}

// Permite consultar la informacion de stock de un producto.
private void consultarStock() {
    consola.limpiarPantalla();

    System.out.println("================================");
    System.out.println("         CONSULTAR STOCK");
    System.out.println("================================");

    int codigo = consola.leerEnteroMayorACero(
        "Ingrese el codigo del producto: "
    );

    Producto producto = inventario.buscarPorCodigo(codigo);

    if (producto == null) {
        System.out.println("\nNo se encontro el producto.");
    } else {
        System.out.println("\n================================");
        System.out.println("       DATOS DEL PRODUCTO");
        System.out.println("================================");

        System.out.println("Codigo: " + producto.getCodigo());
        System.out.println("Nombre: " + producto.getNombre());

        System.out.println(
            "Seccion: " +
            Inventario.nombreSeccion(producto.getSeccion())
        );

        System.out.println(
            "Precio: $" + Consola.dinero(producto.getPrecio())
        );

        System.out.println("Stock actual: " + producto.getStock());
        System.out.println("Stock minimo: " + producto.getStockMinimo());

        if (producto.necesitaReposicion()) {
            System.out.println("\n¡ATENCION! Este producto necesita reposicion.");
        } else {
            System.out.println("\nStock suficiente.");
        }
    }

    consola.esperarEnter();
}
// Muestra los productos que necesitan reposicion.
private void mostrarProductosStockBajo() {
    consola.limpiarPantalla();

    System.out.println("================================");
    System.out.println("     PRODUCTOS CON STOCK BAJO");
    System.out.println("================================");

    if (inventario.productosBajoStock().isEmpty()) {
        System.out.println("\nNo hay productos con stock bajo.");
    } else {
        for (Producto producto : inventario.productosBajoStock()) {

            System.out.println("\nCodigo: " + producto.getCodigo());
            System.out.println("Nombre: " + producto.getNombre());
            System.out.println("Stock actual: " + producto.getStock());
            System.out.println("Stock minimo: " + producto.getStockMinimo());

            System.out.println("--------------------------------");
        }
    }

    consola.esperarEnter();
}

    // Muestra las opciones principales.
    private void mostrarMenuPrincipal() {
        consola.limpiarPantalla();

        System.out.println(
            "============================================"
        );
        System.out.println(
            "            G E S T S T O C K"
        );
        System.out.println(
            "      Sistema de Gestion de Productos"
        );
        System.out.println(
            "============================================"
        );

        System.out.println("\n  1. Gestion de productos");
        System.out.println("  2. Gestion de stock");
        System.out.println("  3. Gestion de usuarios");
        System.out.println("  0. Salir");

        System.out.println();
    }
}

