/**
 * GESTSTOCK - Datos iniciales del proyecto original.
 * Carga 60 productos (10 por cada una de las 6 secciones).
 * Los codigos se asignan de forma secuencial desde Inventario.
 */
public final class DatosEjemplo {

    private DatosEjemplo() {
        // Clase utilitaria: no se instancia.
    }

    public static void cargarProductos(Inventario inventario) {
        if (inventario == null) {
            throw new IllegalArgumentException("El inventario es obligatorio.");
        }
        if (inventario.cantidadRegistros() != 0) {
            throw new IllegalStateException(
                "Los datos iniciales solo se cargan en un inventario vacio."
            );
        }

        // --- BEBIDAS (seccion 1) ---
        inventario.agregarProducto("Coca Cola 2.25L ", "Gaseosa cola", 1, 2500.00, 50, 10);
        inventario.agregarProducto("Sprite 1.5L ", "Gaseosa lima limon", 1, 1800.00, 30, 8);
        inventario.agregarProducto("Agua Mineral 2L ", "Sin gas", 1, 900.00, 80, 20);
        inventario.agregarProducto("Cerveza Quilmes 1L ", "Cerveza rubia", 1, 2200.00, 40, 10);
        inventario.agregarProducto("Fernet Branca 750ml ", "Aperitivo", 1, 12500.00, 15, 5);
        inventario.agregarProducto("Vino Toro 1L ", "Vino tinto", 1, 2800.00, 25, 8);
        inventario.agregarProducto("Jugo Cepita 1L ", "Naranja", 1, 1700.00, 35, 10);
        inventario.agregarProducto("Pepsi 2.25L ", "Gaseosa cola", 1, 2300.00, 28, 10);
        inventario.agregarProducto("Gatorade 500ml ", "Bebida isotonica", 1, 1500.00, 5, 8);
        inventario.agregarProducto("Red Bull 250ml", "Energizante", 1, 2900.00, 18, 6);
        // --- FRUTAS (seccion 2) ---
        inventario.agregarProducto("Banana x kg", "Fruta fresca", 2, 1500.00, 25, 5);
        inventario.agregarProducto("Manzana Roja x kg", "Fruta fresca", 2, 2100.00, 18, 5);
        inventario.agregarProducto("Naranja x kg", "Fruta fresca", 2, 1300.00, 30, 8);
        inventario.agregarProducto("Pera x kg", "Fruta fresca", 2, 1900.00, 15, 5);
        inventario.agregarProducto("Uva x kg", "Fruta fresca", 2, 3500.00, 12, 4);
        inventario.agregarProducto("Frutilla x kg", "Fruta fresca", 2, 4200.00, 8, 3);
        inventario.agregarProducto("Mandarina x kg", "Fruta fresca", 2, 1400.00, 20, 6);
        inventario.agregarProducto("Limon x kg", "Fruta citrica", 2, 1600.00, 22, 7);
        inventario.agregarProducto("Kiwi x kg", "Fruta fresca", 2, 3800.00, 6, 4);
        inventario.agregarProducto("Durazno x kg", "Fruta fresca", 2, 2400.00, 14, 5);
        // --- VERDURAS (seccion 3) ---
        inventario.agregarProducto("Papa x kg", "Verdura", 3, 1100.00, 60, 15);
        inventario.agregarProducto("Cebolla x kg", "Verdura", 3, 1400.00, 35, 10);
        inventario.agregarProducto("Tomate x kg", "Verdura fresca", 3, 2800.00, 22, 6);
        inventario.agregarProducto("Lechuga unidad", "Verdura de hoja", 3, 1200.00, 18, 5);
        inventario.agregarProducto("Zanahoria x kg", "Verdura", 3, 1300.00, 25, 8);
        inventario.agregarProducto("Morron rojo x kg", "Verdura", 3, 3200.00, 12, 5);
        inventario.agregarProducto("Zapallo x kg", "Verdura", 3, 1500.00, 16, 6);
        inventario.agregarProducto("Choclo unidad", "Verdura", 3, 800.00, 30, 10);
        inventario.agregarProducto("Espinaca atado", "Verdura de hoja", 3, 1700.00, 14, 5);
        inventario.agregarProducto("Ajo cabeza", "Condimento", 3, 600.00, 40, 12);
        // --- CARNES (seccion 4) ---
        inventario.agregarProducto("Asado x kg", "Carne vacuna", 4, 8500.00, 15, 4);
        inventario.agregarProducto("Pollo entero x kg", "Ave", 4, 3200.00, 20, 5);
        inventario.agregarProducto("Bondiola x kg", "Cerdo", 4, 6800.00, 10, 3);
        inventario.agregarProducto("Milanesa nalga x kg", "Carne vacuna", 4, 9800.00, 12, 4);
        inventario.agregarProducto("Chorizo x kg", "Embutido", 4, 5500.00, 18, 5);
        inventario.agregarProducto("Morcilla x kg", "Embutido", 4, 4200.00, 14, 5);
        inventario.agregarProducto("Pata muslo pollo x kg", "Ave", 4, 2800.00, 22, 6);
        inventario.agregarProducto("Vacio x kg", "Carne vacuna", 4, 9200.00, 8, 3);
        inventario.agregarProducto("Costilla cerdo x kg", "Cerdo", 4, 5800.00, 9, 3);
        inventario.agregarProducto("Carne picada x kg", "Carne vacuna", 4, 6500.00, 25, 8);
        // --- LACTEOS (seccion 5) ---
        inventario.agregarProducto("Leche La Serenisima 1L", "Entera", 5, 1600.00, 45, 12);
        inventario.agregarProducto("Queso Cremoso x kg", "Lacteo", 5, 8900.00, 8, 3);
        inventario.agregarProducto("Yogur Bebible 1L", "Frutilla", 5, 2400.00, 20, 5);
        inventario.agregarProducto("Manteca 200g", "Lacteo", 5, 2100.00, 18, 6);
        inventario.agregarProducto("Crema de leche 200ml", "Lacteo", 5, 1500.00, 24, 8);
        inventario.agregarProducto("Queso Rallado 80g", "Lacteo", 5, 1900.00, 30, 10);
        inventario.agregarProducto("Dulce de Leche 400g", "Postre", 5, 2700.00, 16, 5);
        inventario.agregarProducto("Queso Provolone x kg", "Lacteo", 5, 11000.00, 5, 3);
        inventario.agregarProducto("Ricota 500g", "Lacteo", 5, 2300.00, 12, 4);
        inventario.agregarProducto("Leche Chocolatada 1L", "Saborizada", 5, 1900.00, 22, 7);
        // --- ALMACEN (seccion 6) ---
        inventario.agregarProducto("Fideos Matarazzo 500g", "Pasta seca", 6, 1200.00, 50, 15);
        inventario.agregarProducto("Arroz Gallo 1kg", "Grano largo fino", 6, 2300.00, 35, 10);
        inventario.agregarProducto("Aceite Natura 900ml", "Girasol", 6, 3800.00, 25, 8);
        inventario.agregarProducto("Azucar Ledesma 1kg", "Comun", 6, 1900.00, 40, 10);
        inventario.agregarProducto("Yerba Playadito 1kg", "Yerba mate", 6, 4500.00, 30, 10);
        inventario.agregarProducto("Sal Celusal 500g", "Sal fina", 6, 800.00, 45, 12);
        inventario.agregarProducto("Harina 000 1kg", "Harina de trigo", 6, 1400.00, 38, 10);
        inventario.agregarProducto("Mayonesa Hellmanns 500g", "Aderezo", 6, 2800.00, 20, 6);
        inventario.agregarProducto("Cafe La Virginia 250g", "Molido", 6, 5500.00, 14, 5);
        inventario.agregarProducto("Galletitas Oreo 118g", "Dulces", 6, 1600.00, 28, 8);
    }
}
