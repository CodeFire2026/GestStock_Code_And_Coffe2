
import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

/**
 * GESTSTOCK - Proyecto Integrador
 *
 * Clase Inventario.
 * Administra todos los productos del negocio.
 *
 * Utiliza ArrayList para almacenar objetos Producto.
 */
public class Inventario {

    // Cantidad maxima de productos permitidos.
    public static final int MAX_PRODUCTOS = 100;

    // Lista donde se guardan los productos.
    private final ArrayList<Producto> productos;

    // Codigo que recibira el proximo producto.
    private int proximoCodigo;

    // Constructor
    public Inventario() {
        productos = new ArrayList<Producto>();
        proximoCodigo = 1;
    }

    // Agrega un nuevo producto al inventario.
    public Producto agregarProducto(
            String nombre,
            String descripcion,
            int seccion,
            double precio,
            int stock,
            int stockMinimo) {

        if (productos.size() >= MAX_PRODUCTOS) {
            throw new IllegalStateException(
                "No se pueden agregar mas productos."
            );
        }

        Producto nuevo = new Producto(
            proximoCodigo,
            nombre,
            descripcion,
            seccion,
            precio,
            stock,
            stockMinimo
        );

        productos.add(nuevo);
        proximoCodigo++;

        return nuevo;
    }

    // Devuelve la cantidad de registros guardados.
    public int cantidadRegistros() {
        return productos.size();
    }

    // Permite consultar todos los registros.
    public List<Producto> todosLosRegistros() {
        return Collections.unmodifiableList(productos);
    }
    
    // Busca un producto activo utilizando su codigo.
    public Producto buscarPorCodigo(int codigo) {
        for (Producto producto : productos) {
            if (producto.isActivo()
                    && producto.getCodigo() == codigo) {
                return producto;
            }
        }
        return null;
    }

    // Registra el ingreso de stock de un producto.
    public void registrarIngresoStock(int codigo, int cantidad) {
        Producto producto = buscarPorCodigo(codigo);

        if (producto == null) {
            throw new IllegalArgumentException(
                "No existe un producto activo con ese codigo."
            );
        }

        producto.ingresarStock(cantidad);
    }

    // Registra el egreso de stock de un producto.
    public void registrarEgresoStock(int codigo, int cantidad) {
        Producto producto = buscarPorCodigo(codigo);

        if (producto == null) {
            throw new IllegalArgumentException(
                "No existe un producto activo con ese codigo."
            );
        }

        producto.egresarStock(cantidad);
    }

    // Devuelve todos los productos activos.
    public List<Producto> listarActivos() {
        ArrayList<Producto> encontrados =
                new ArrayList<Producto>();

        for (Producto producto : productos) {
            if (producto.isActivo()) {
                encontrados.add(producto);
            }
        }

        return encontrados;
    }

    // Filtra los productos segun su seccion.
    public List<Producto> listarPorSeccion(int seccion) {
                if (seccion < 1 || seccion > 6) {
            throw new IllegalArgumentException(
                "La seccion debe estar entre 1 y 6."
            );
        }
        ArrayList<Producto> encontrados =
                new ArrayList<Producto>();

        for (Producto producto : productos) {
            if (producto.isActivo()
                    && producto.getSeccion() == seccion) {
                encontrados.add(producto);
            }
        }

        return encontrados;
    }

    // Busca productos por nombre o palabras.
    public List<Producto> buscarPorNombre(String texto) {
        ArrayList<Producto> encontrados =
                new ArrayList<Producto>();

        if (texto == null || texto.trim().isEmpty()) {
            return encontrados;
        }

        String busqueda = texto.trim().toUpperCase(
                java.util.Locale.ROOT);

        String[] palabras = busqueda.split("\\s+");

        for (Producto producto : productos) {
            if (!producto.isActivo()) {
                continue;
            }

            String nombre = producto.getNombre().toUpperCase(
                    java.util.Locale.ROOT);

            boolean coincide = nombre.contains(busqueda);

            if (!coincide) {
                for (String palabra : palabras) {
                    if (nombre.contains(palabra)) {
                        coincide = true;
                        break;
                    }
                }
            }

            if (coincide) {
                encontrados.add(producto);
            }
        }

        return encontrados;
    }

    // Devuelve los productos que necesitan reposicion.
    public List<Producto> productosBajoStock() {
        ArrayList<Producto> encontrados =
                new ArrayList<Producto>();

for (Producto producto : productos) {
    if (producto.isActivo() && producto.necesitaReposicion()) {
        encontrados.add(producto);
    }
}

        return encontrados;
    }

    // Cuenta cuantos productos estan activos.
    public int cantidadActivos() {
        return listarActivos().size();
    }

    // Calcula el valor total del inventario.
    public double valorTotal() {
        double total = 0;

        for (Producto producto : productos) {
            if (producto.isActivo()) {
                total += producto.valorEnStock();
            }
        }

        return total;
    }

    // Busca el producto activo de mayor precio.
    public Producto productoMasCaro() {
        Producto mayor = null;

        for (Producto producto : productos) {
            if (producto.isActivo()
                    && (mayor == null
                    || producto.getPrecio() > mayor.getPrecio())) {
                mayor = producto;
            }
        }

        return mayor;
    }

    // Cuenta los productos por seccion.
    public int[] contarPorSecciones() {
        int[] contador = new int[7];

        for (Producto producto : productos) {
            if (producto.isActivo()) {
                contador[producto.getSeccion()]++;
            }
        }

        return contador;
    }

    // Devuelve el nombre de cada seccion.
    public static String nombreSeccion(int seccion) {
        switch (seccion) {
            case 1: return "Bebidas";
            case 2: return "Frutas";
            case 3: return "Verduras";
            case 4: return "Carnes";
            case 5: return "Lacteos";
            case 6: return "Almacen";
            default: return "Desconocida";
        }
    }
   
    
    // Busca un producto por codigo, incluso si esta inactivo.
    // Se utiliza para consultar y reactivar productos.
    public Producto buscarPorCodigoIncluyendoInactivos(int codigo) {

        for (Producto producto : productos) {

            if (producto.getCodigo() == codigo) {
                return producto;
            }
        }

        return null;
    }

    

}