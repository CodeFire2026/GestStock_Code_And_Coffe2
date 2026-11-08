
/**
 * GESTSTOCK - Proyecto Integrador
 *
 * Clase principal del sistema.
 * Se encarga de mostrar los menus y controlar las opciones seleccionadas por el usuario.
 *
 * Esta es la primera version del menu.
 */
public class GestStock {

    // Objeto encargado de leer los datos.
    private final Consola consola;

    // Constructor
    public GestStock() {
        consola = new Consola();
    }

    // Punto de entrada del programa.
    public static void main(String[] args) {
        GestStock programa = new GestStock();
        programa.ejecutar();
    }

    // Controla la ejecucion del menu.
    public void ejecutar() {
        int opcion;

        do {
            mostrarMenuPrincipal();

            opcion = consola.leerEntero("Opcion: ");

            switch (opcion) {
                case 1:
                    System.out.println(
                        "\nGestion de productos (en desarrollo)."
                    );
                    consola.esperarEnter();
                    break;

                case 2:
                    System.out.println(
                        "\nGestion de stock (en desarrollo)."
                    );
                    consola.esperarEnter();
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
                    System.out.println(
                        "\nOpcion invalida."
                    );
                    consola.esperarEnter();
                    break;
            }

        } while (opcion != 0);

        consola.cerrar();
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
