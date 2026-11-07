
import java.util.Scanner;

/**
 * GESTSTOCK - Proyecto Integrador
 *
 * Clase Consola.
 * Se encarga de leer los datos ingresados por
 * el usuario desde la consola.
 *
 * Se utiliza un unico Scanner para evitar
 * conflictos durante la lectura.
 */
public class Consola {

    // Scanner para leer los datos del teclado
    private final Scanner entrada;

    // Constructor de la clase.
    public Consola() {
        entrada = new Scanner(System.in);
    }

    // Lee un texto ingresado por el usuario
    public String leerTexto(String mensaje) {
        System.out.print(mensaje);
        return entrada.nextLine();
    }

    // Espera que el usuario presione enter
    public void esperarEnter() {
        System.out.print(
            "\nPresione Enter para continuar..."
        );
        entrada.nextLine();
    }

    // Separa visualmente los menus.
    public void limpiarPantalla() {
        System.out.println("\n\n\n");
    }

    // Cierra el Scanner al salir del programa
    public void cerrar() {
        entrada.close();
    }
}
