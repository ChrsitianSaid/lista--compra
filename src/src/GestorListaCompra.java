package src;

import java.util.Scanner;

/**
 * Clase principal que gestiona una lista de la compra.
 * Permite añadir, eliminar, buscar y mostrar productos.
 * La lista tiene una capacidad máxima de 50 productos.
 * 
 * @author TuNombre
 * @version 1.0
 */
public class GestorListaCompra {
    /**
     * Array para almacenar los productos con una capacidad máxima de 50 elementos.
     */
    private static String[] listaProductos = new String[50];
    
    /**
     * Contador del número actual de productos en la lista.
     * Este valor siempre estará entre 0 y 50.
     */
    private static int numeroProductos = 0;
    
    /**
     * Método principal que inicia la aplicación y muestra el menú interactivo.
     * El menú se repite hasta que el usuario selecciona la opción de salir (6).
     * 
     * @param args Argumentos de línea de comandos (no utilizados)
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int opcion;
        
        do {
            mostrarMenu();
            System.out.print("Elige una opción: ");
            
            while (!scanner.hasNextInt()) {
                System.out.println("Por favor, introduce un número válido.");
                scanner.next();
                System.out.print("Elige una opción: ");
            }
            
            opcion = scanner.nextInt();
            scanner.nextLine();
            
            procesarOpcion(opcion, scanner);
            
        } while (opcion != 6);
        
        scanner.close();
    }
    
    /**
     * Muestra el menú principal de la aplicación con todas las opciones disponibles.
     * El menú se muestra en la consola con un formato legible.
     */
    public static void mostrarMenu() {
        System.out.println("=== Lista de la Compra ===");
        System.out.println("1. Añadir producto");
        System.out.println("2. Eliminar producto");
        System.out.println("3. Buscar producto");
        System.out.println("4. Mostrar lista completa");
        System.out.println("5. Vaciar lista");
        System.out.println("6. Salir");
    }
    
    /**
     * Procesa la opción seleccionada por el usuario en el menú.
     * 
     * @param opcion El número de opción seleccionada (1-6)
     * @param scanner Objeto Scanner para leer la entrada del usuario
     */
    private static void procesarOpcion(int opcion, Scanner scanner) {
        switch (opcion) {
            case 1:
                añadirProductoMenu(scanner);
                break;
            case 2:
                eliminarProductoMenu(scanner);
                break;
            case 3:
                buscarProductoMenu(scanner);
                break;
            case 4:
                mostrarListaCompleta();
                break;
            case 5:
                vaciarLista();
                System.out.println("La lista ha sido vaciada correctamente.\n");
                break;
            case 6:
                System.out.println("¡Gracias por usar el gestor de lista de la compra!");
                break;
            default:
                System.out.println("Opción no válida. Por favor, elige una opción del 1 al 6.\n");
        }
    }
    
    /**
     * Muestra la interfaz para añadir un producto y procesa la entrada.
     * 
     * @param scanner Objeto Scanner para leer la entrada del usuario
     */
    private static void añadirProductoMenu(Scanner scanner) {
        System.out.print("Introduce el producto a añadir: ");
        String productoAñadir = scanner.nextLine().trim();
        
        if (productoAñadir.isEmpty()) {
            System.out.println("Error: No se puede añadir un producto vacío.\n");
        } else if (addProducto(productoAñadir)) {
            System.out.println("Producto añadido correctamente.\n");
        } else {
            System.out.println("Error: No se pudo añadir el producto. La lista puede estar llena o el producto ya existe.\n");
        }
    }
    
    /**
     * Muestra la interfaz para eliminar un producto y procesa la entrada.
     * 
     * @param scanner Objeto Scanner para leer la entrada del usuario
     */
    private static void eliminarProductoMenu(Scanner scanner) {
        if (numeroProductos == 0) {
            System.out.println("La lista está vacía. No hay productos para eliminar.\n");
        } else {
            System.out.print("Introduce el producto a eliminar: ");
            String productoEliminar = scanner.nextLine().trim();
            
            if (eliminarProducto(productoEliminar)) {
                System.out.println("Producto eliminado correctamente.\n");
            } else {
                System.out.println("Error: El producto no se encuentra en la lista.\n");
            }
        }
    }
    
    /**
     * Muestra la interfaz para buscar un producto y procesa la entrada.
     * 
     * @param scanner Objeto Scanner para leer la entrada del usuario
     */
    private static void buscarProductoMenu(Scanner scanner) {
        if (numeroProductos == 0) {
            System.out.println("La lista está vacía. No hay productos para buscar.\n");
        } else {
            System.out.print("Introduce el producto a buscar: ");
            String productoBuscar = scanner.nextLine().trim();
            int posicion = buscarProducto(productoBuscar);
            
            if (posicion != -1) {
                System.out.println("El producto '" + productoBuscar + "' se encuentra en la posición " + (posicion + 1) + ".\n");
            } else {
                System.out.println("El producto '" + productoBuscar + "' no se encuentra en la lista.\n");
            }
        }
    }
    
    /**
     * Añade un nuevo producto a la lista de la compra.
     * La operación falla si la lista está llena, el producto ya existe,
     * o el producto es nulo o vacío.
     * 
     * @param producto El nombre del producto a añadir (no sensible a mayúsculas/minúsculas)
     * @return true si el producto se añadió correctamente, false en caso contrario
     */
    public static boolean addProducto(String producto) {
        if (numeroProductos >= 50 || producto == null || producto.trim().isEmpty()) {
            return false;
        }
        
        String productoTrimmed = producto.trim();
        
        for (int i = 0; i < numeroProductos; i++) {
            if (listaProductos[i].equalsIgnoreCase(productoTrimmed)) {
                return false;
            }
        }
        
        listaProductos[numeroProductos] = productoTrimmed;
        numeroProductos++;
        return true;
    }
    
    /**
     * Elimina un producto de la lista de la compra.
     * La búsqueda no es sensible a mayúsculas/minúsculas.
     * 
     * @param producto El nombre del producto a eliminar
     * @return true si el producto se encontró y eliminó, false si no se encontró
     */
    public static boolean eliminarProducto(String producto) {
        if (producto == null || producto.trim().isEmpty() || numeroProductos == 0) {
            return false;
        }
        
        String productoBuscado = producto.trim();
        
        for (int i = 0; i < numeroProductos; i++) {
            if (listaProductos[i].equalsIgnoreCase(productoBuscado)) {
                for (int j = i; j < numeroProductos - 1; j++) {
                    listaProductos[j] = listaProductos[j + 1];
                }
                listaProductos[numeroProductos - 1] = null;
                numeroProductos--;
                return true;
            }
        }
        
        return false;
    }
    
    /**
     * Busca un producto en la lista y devuelve su posición.
     * La búsqueda no es sensible a mayúsculas/minúsculas.
     * 
     * @param producto El nombre del producto a buscar
     * @return La posición del producto (0-based) si se encuentra, -1 si no se encuentra
     */
    public static int buscarProducto(String producto) {
        if (producto == null || producto.trim().isEmpty() || numeroProductos == 0) {
            return -1;
        }
        
        String productoBuscado = producto.trim();
        
        for (int i = 0; i < numeroProductos; i++) {
            if (listaProductos[i].equalsIgnoreCase(productoBuscado)) {
                return i;
            }
        }
        
        return -1;
    }
    
    /**
     * Obtiene una copia de la lista actual de productos.
     * El array devuelto solo contiene los productos existentes.
     * 
     * @return Un array de String con los productos actuales en la lista
     */
    public static String[] obtenerLista() {
        String[] listaActual = new String[numeroProductos];
        for (int i = 0; i < numeroProductos; i++) {
            listaActual[i] = listaProductos[i];
        }
        return listaActual;
    }
    
    /**
     * Vacía completamente la lista de la compra.
     * Este método elimina todos los productos y reinicia el contador.
     */
    public static void vaciarLista() {
        for (int i = 0; i < numeroProductos; i++) {
            listaProductos[i] = null;
        }
        numeroProductos = 0;
    }
    
    /**
     * Muestra la lista completa de productos en la consola.
     * Si la lista está vacía, muestra un mensaje indicándolo.
     * El formato incluye números y el total de productos.
     */
    private static void mostrarListaCompleta() {
        if (numeroProductos == 0) {
            System.out.println("La lista de la compra está vacía.\n");
        } else {
            System.out.println("\n=== Lista de la Compra ===");
            for (int i = 0; i < numeroProductos; i++) {
                System.out.println((i + 1) + ". " + listaProductos[i]);
            }
            System.out.println("Total: " + numeroProductos + " producto(s)\n");
        }
    }
}