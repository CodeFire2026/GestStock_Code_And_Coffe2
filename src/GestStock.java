

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

    // Constructor.
    public GestStock() {
        consola = new Consola();
        inventario = new Inventario();
    }

    // Punto de entrada del programa.
    public static void main(String[] args) {
        GestStock programa = new GestStock();
        programa.ejecutar();
    }

    // Controla la ejecucion del menu principal.
    public void ejecutar() {
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
                    System.out.println(
                        "\nGestion de usuarios (en desarrollo)."
                    );
                    consola.esperarEnter();
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
        System.out.println("0. Volver al menu principal");

        opcion = consola.leerEnteroEnRango(
            "Seleccione una opcion: ", 0, 2
        );

        switch (opcion) {
            case 1:
                registrarProducto();
                break;

            case 2:
                listarProductos();
                break;
        }

    } while (opcion != 0);
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

    System.out.println("================================");
    System.out.println("       LISTADO DE PRODUCTOS");
    System.out.println("================================");

    if (inventario.listarActivos().isEmpty()) {
        System.out.println("\nNo hay productos registrados.");
    } else {
        for (Producto producto : inventario.listarActivos()) {
            System.out.println("\nCodigo: " + producto.getCodigo());
            System.out.println("Nombre: " + producto.getNombre());
            System.out.println(
                "Seccion: " +
                Inventario.nombreSeccion(producto.getSeccion())
            );
            System.out.println("Precio: $" +
                Consola.dinero(producto.getPrecio()));
            System.out.println("Stock: " + producto.getStock());
            System.out.println(
                "Stock minimo: " + producto.getStockMinimo()
            );
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

