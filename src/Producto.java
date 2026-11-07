
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

    // METODOS SETTERS
    // Permiten modificar los datos de un producto.

    public void setNombre(String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException(
                "El nombre no puede estar vacio.");
        }
        this.nombre = nombre.trim();
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion == null
                ? "" : descripcion.trim();
    }

    public void setSeccion(int seccion) {
        if (seccion < 1 || seccion > 6) {
            throw new IllegalArgumentException(
                "La seccion debe estar entre 1 y 6.");
        }
        this.seccion = seccion;
    }

    public void setPrecio(double precio) {
        if (!Double.isFinite(precio) || precio < 0) {
            throw new IllegalArgumentException(
                "El precio no puede ser negativo.");
        }
        this.precio = precio;
    }

    public void setStockMinimo(int minimo) {
        if (minimo < 0) {
            throw new IllegalArgumentException(
                "El stock minimo no puede ser negativo.");
        }
        this.stockMinimo = minimo;
    }

    // MOVIMIENTOS DE STOCK

    public void ingresarStock(int cantidad) {
        if (cantidad <= 0) {
            throw new IllegalArgumentException(
                "La cantidad debe ser mayor a cero.");
        }

        if ((long) stock + cantidad > Integer.MAX_VALUE) {
            throw new IllegalArgumentException(
                "La cantidad supera el limite permitido.");
        }

        stock += cantidad;
    }

    public void egresarStock(int cantidad) {
        if (cantidad <= 0) {
            throw new IllegalArgumentException(
                "La cantidad debe ser mayor a cero.");
        }

        if (cantidad > stock) {
            throw new IllegalArgumentException(
                "Stock insuficiente.");
        }

        stock -= cantidad;
    }

    // REPORTES Y ESTADO

    public boolean necesitaReposicion() {
        return activo && stock <= stockMinimo;
    }

    public double valorEnStock() {
        return precio * stock;
    }

    // Baja logica: el producto deja de estar activo.
    public void darDeBaja() {
        activo = false;
    }
}
