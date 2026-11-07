
/**
 * GESTSTOCK - Proyecto Integrador
 *
 * Clase Producto.
 * Representa un producto dentro del inventario.
 * Cada producto tiene un codigo, nombre, descripcion,
 * seccion, precio, stock y stock minimo.
 */
public class Producto {

    // ATRIBUTOS DEL PRODUCTO
    private final int codigo;
    private String nombre;
    private String descripcion;
    private int seccion;
    private double precio;
    private int stock;
    private int stockMinimo;
    private boolean activo;

    // CONSTRUCTOR
    // Permite crear un producto con sus datos iniciales.
    public Producto(int codigo, String nombre,
                    String descripcion, int seccion,
                    double precio, int stock,
                    int stockMinimo) {

        this.codigo = codigo;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.seccion = seccion;
        this.precio = precio;
        this.stock = stock;
        this.stockMinimo = stockMinimo;
        this.activo = true;
    }

    // METODOS GETTERS
    // Permiten consultar los atributos del producto.

    public int getCodigo() {
        return codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public int getSeccion() {
        return seccion;
    }

    public double getPrecio() {
        return precio;
    }

    public int getStock() {
        return stock;
    }

    public int getStockMinimo() {
        return stockMinimo;
    }

    public boolean isActivo() {
        return activo;
    }
}
