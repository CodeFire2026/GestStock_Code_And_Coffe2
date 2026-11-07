
import java.util.Scanner;
import java.util.Locale;

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

    // Lee un texto y evita que quede vacio
    public String leerTextoNoVacio(String mensaje) {
        while (true) {
            String texto = leerTexto(mensaje).trim();

            if (!texto.isEmpty()) {
                return texto;
            }

            System.out.println(
                "El dato no puede estar vacio."
            );
        }
    }

    // Lee numeros enteros y controla entradas incorrectas
    public int leerEntero(String mensaje) {
        while (true) {
            try {
                return Integer.parseInt(
                    leerTexto(mensaje).trim()
                );
            } catch (NumberFormatException e) {
                System.out.println(
                    "Ingrese un numero entero valido."
                );
            }
        }
    }

    // Permite numeros iguales o mayores a cero
    public int leerEnteroNoNegativo(String mensaje) {
        while (true) {
            int numero = leerEntero(mensaje);

            if (numero >= 0) {
                return numero;
            }

            System.out.println(
                "El numero no puede ser negativo."
            );
        }
    }

    // Permite solamente numeros mayores a cero
    public int leerEnteroMayorACero(String mensaje) {
        while (true) {
            int numero = leerEntero(mensaje);

            if (numero > 0) {
                return numero;
            }

            System.out.println(
                "El numero debe ser mayor a cero."
            );
        }
    }

    // Valida que el numero este dentro de un rango
    public int leerEnteroEnRango(
            String mensaje, int minimo, int maximo) {

        while (true) {
            int numero = leerEntero(mensaje);

            if (numero >= minimo && numero <= maximo) {
                return numero;
            }

            System.out.println(
                "Ingrese un valor entre " +
                minimo + " y " + maximo
            );
        }
    }

    // Lee precios y otros valores decimales
    public double leerRealNoNegativo(String mensaje) {
        while (true) {
            String texto = leerTexto(mensaje)
                    .trim().replace(',', '.');

            try {
                double numero = Double.parseDouble(texto);

                if (Double.isFinite(numero) && numero >= 0) {
                    return numero;
                }
            } catch (NumberFormatException e) {
                // Se vuelve a pedir el numero.
            }

            System.out.println(
                "Ingrese un numero valido mayor o igual a cero."
            );
        }
    }

    // Muestra cantidades de dinero con dos decimales
    public static String dinero(double valor) {
        return String.format(Locale.US, "%.2f", valor);
    }
}
