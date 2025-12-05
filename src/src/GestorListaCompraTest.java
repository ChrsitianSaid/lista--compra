package src;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class GestorListaCompraTest {
    
    @BeforeEach
    public void setUp() {
        // Antes de cada test, vaciamos la lista para empezar desde cero
        GestorListaCompra.vaciarLista();
    }
    
    @Test
    public void testAddProducto() {
        System.out.println("=== Test: Añadir producto ===");
        
        // Caso 1: Añadir producto correctamente
        boolean resultado1 = GestorListaCompra.addProducto("Manzanas");
        assertTrue(resultado1, "Debería añadir 'Manzanas' correctamente");
        
        // Caso 2: Añadir producto duplicado (no debería añadirse)
        boolean resultado2 = GestorListaCompra.addProducto("Manzanas");
        assertFalse(resultado2, "No debería añadir 'Manzanas' duplicado");
        
        // Caso 3: Añadir producto vacío
        boolean resultado3 = GestorListaCompra.addProducto("");
        assertFalse(resultado3, "No debería añadir producto vacío");
        
        // Caso 4: Añadir producto con espacios
        boolean resultado4 = GestorListaCompra.addProducto("  Leche  ");
        assertTrue(resultado4, "Debería añadir 'Leche' recortando espacios");
        
        // Caso 5: Añadir null
        boolean resultado5 = GestorListaCompra.addProducto(null);
        assertFalse(resultado5, "No debería añadir null");
        
        System.out.println("✓ Test addProducto completado\n");
    }
    
    @Test
    public void testEliminarProducto() {
        System.out.println("=== Test: Eliminar producto ===");
        
        // Preparación: añadimos algunos productos
        GestorListaCompra.addProducto("Pan");
        GestorListaCompra.addProducto("Huevos");
        GestorListaCompra.addProducto("Leche");
        
        // Caso 1: Eliminar producto existente
        boolean resultado1 = GestorListaCompra.eliminarProducto("Huevos");
        assertTrue(resultado1, "Debería eliminar 'Huevos' existente");
        
        // Caso 2: Eliminar producto que no existe
        boolean resultado2 = GestorListaCompra.eliminarProducto("Queso");
        assertFalse(resultado2, "No debería eliminar 'Queso' que no existe");
        
        // Caso 3: Eliminar producto con diferente capitalización
        GestorListaCompra.addProducto("yogur");
        boolean resultado3 = GestorListaCompra.eliminarProducto("YOGUR");
        assertTrue(resultado3, "Debería eliminar 'yogur' con búsqueda case-insensitive");
        
        // Caso 4: Eliminar producto vacío
        boolean resultado4 = GestorListaCompra.eliminarProducto("");
        assertFalse(resultado4, "No debería eliminar producto vacío");
        
        // Caso 5: Eliminar null
        boolean resultado5 = GestorListaCompra.eliminarProducto(null);
        assertFalse(resultado5, "No debería eliminar null");
        
        System.out.println("✓ Test eliminarProducto completado\n");
    }
    
    @Test
    public void testBuscarProducto() {
        System.out.println("=== Test: Buscar producto ===");
        
        // Preparación: añadimos algunos productos
        GestorListaCompra.addProducto("Pan");
        GestorListaCompra.addProducto("Huevos");
        GestorListaCompra.addProducto("Leche");
        
        // Caso 1: Buscar producto existente
        int posicion1 = GestorListaCompra.buscarProducto("Pan");
        assertEquals(0, posicion1, "'Pan' debería estar en posición 0");
        
        // Caso 2: Buscar producto existente en otra posición
        int posicion2 = GestorListaCompra.buscarProducto("Leche");
        assertEquals(2, posicion2, "'Leche' debería estar en posición 2");
        
        // Caso 3: Buscar producto que no existe
        int posicion3 = GestorListaCompra.buscarProducto("Queso");
        assertEquals(-1, posicion3, "'Queso' no debería existir (devolver -1)");
        
        // Caso 4: Buscar con diferente capitalización
        int posicion4 = GestorListaCompra.buscarProducto("HUEVOS");
        assertEquals(1, posicion4, "Búsqueda case-insensitive debería encontrar 'HUEVOS'");
        
        // Caso 5: Buscar producto vacío
        int posicion5 = GestorListaCompra.buscarProducto("");
        assertEquals(-1, posicion5, "Búsqueda vacía debería devolver -1");
        
        // Caso 6: Buscar null
        int posicion6 = GestorListaCompra.buscarProducto(null);
        assertEquals(-1, posicion6, "Búsqueda null debería devolver -1");
        
        System.out.println("✓ Test buscarProducto completado\n");
    }
    
    @Test
    public void testObtenerLista() {
        System.out.println("=== Test: Obtener lista completa ===");
        
        // Caso 1: Lista vacía
        String[] listaVacia = GestorListaCompra.obtenerLista();
        assertEquals(0, listaVacia.length, "Lista vacía debería tener longitud 0");
        
        // Caso 2: Lista con productos
        GestorListaCompra.addProducto("Pan");
        GestorListaCompra.addProducto("Leche");
        
        String[] listaConProductos = GestorListaCompra.obtenerLista();
        
        // Verificar longitud
        assertEquals(2, listaConProductos.length, "Lista debería tener 2 productos");
        
        // Verificar contenido
        assertEquals("Pan", listaConProductos[0], "Primer producto debería ser 'Pan'");
        assertEquals("Leche", listaConProductos[1], "Segundo producto debería ser 'Leche'");
        
        // Verificar que no es el mismo array (debería ser una copia)
        assertNotSame(GestorListaCompra.obtenerLista(), GestorListaCompra.obtenerLista(), 
                     "Debería devolver una copia nueva cada vez");
        
        System.out.println("✓ Test obtenerLista completado\n");
    }
    
    @Test
    public void testVaciarLista() {
        System.out.println("=== Test: Vaciar lista ===");
        
        // Preparación: añadimos algunos productos
        GestorListaCompra.addProducto("Pan");
        GestorListaCompra.addProducto("Leche");
        GestorListaCompra.addProducto("Huevos");
        
        // Verificar que hay productos antes de vaciar
        String[] listaAntes = GestorListaCompra.obtenerLista();
        assertTrue(listaAntes.length > 0, "Debería haber productos antes de vaciar");
        
        // Vaciar la lista
        GestorListaCompra.vaciarLista();
        
        // Verificar que la lista está vacía
        String[] listaDespues = GestorListaCompra.obtenerLista();
        assertEquals(0, listaDespues.length, "Lista debería estar vacía después de vaciar");
        
        // Verificar que se pueden añadir productos después de vaciar
        boolean resultado = GestorListaCompra.addProducto("Nuevo Producto");
        assertTrue(resultado, "Debería poder añadir productos después de vaciar la lista");
        
        System.out.println("✓ Test vaciarLista completado\n");
    }
    
    @Test
    public void testLimiteCapacidad() {
        System.out.println("=== Test: Límite de capacidad ===");
        
        // Añadir 50 productos (máximo permitido)
        for (int i = 1; i <= 50; i++) {
            boolean resultado = GestorListaCompra.addProducto("Producto " + i);
            assertTrue(resultado, "Debería añadir producto " + i);
        }
        
        // Intentar añadir el producto 51 (debería fallar)
        boolean resultadoExceso = GestorListaCompra.addProducto("Producto 51");
        assertFalse(resultadoExceso, "No debería añadir más de 50 productos");
        
        // Verificar que tenemos exactamente 50 productos
        String[] lista = GestorListaCompra.obtenerLista();
        assertEquals(50, lista.length, "Debería tener exactamente 50 productos");
        
        System.out.println("✓ Test límite de capacidad completado\n");
    }
    
    @Test
    public void testIntegracionCompleta() {
        System.out.println("=== Test: Integración completa ===");
        
        // Flujo completo de operaciones
        GestorListaCompra.addProducto("Manzanas");
        GestorListaCompra.addProducto("Peras");
        GestorListaCompra.addProducto("Uvas");
        
        // Verificar que están todos
        assertEquals(3, GestorListaCompra.obtenerLista().length);
        
        // Eliminar uno
        GestorListaCompra.eliminarProducto("Peras");
        
        // Verificar que quedan 2
        assertEquals(2, GestorListaCompra.obtenerLista().length);
        
        // Buscar uno existente
        int posicion = GestorListaCompra.buscarProducto("Uvas");
        assertTrue(posicion >= 0);
        
        // Buscar uno eliminado
        int posicionEliminado = GestorListaCompra.buscarProducto("Peras");
        assertEquals(-1, posicionEliminado);
        
        // Vaciar lista
        GestorListaCompra.vaciarLista();
        
        // Verificar lista vacía
        assertEquals(0, GestorListaCompra.obtenerLista().length);
        
        System.out.println("✓ Test integración completa completado\n");
    }
}
