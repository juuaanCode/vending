package uva.poo.tests;
import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import uva.poo.vending.Pack;
import uva.poo.vending.Product;
/**
 * Clase para hacer pruebas unitarias de caja negra de la clase Pack.
 * @author juagonz0
 * @author sanbarb
 */
public class PackTest {
	private Product productoValido1, productoValido2, productoValido3;
	@Before
	public void setUp() throws Exception {	
		productoValido1 = new Product(5.5, LocalDate.of(2021,11,18), "Patatas", "123456789012");
		productoValido2 = new Product(4, LocalDate.of(2021,10,20), "Palomitas", "109876543212");
		productoValido3 = new Product(7, LocalDate.of(2021,10,10), "Aceitunas", "654017891238");
		
		}
// public Pack(Product[] productos, String nombre, String identificador) 
	@Test
	public void testConstructorCorrecto() {
		ArrayList<Product> contenido = new ArrayList<>();
		contenido.add(productoValido1);
		contenido.add(productoValido2);
		contenido.add(productoValido3);
		String nombre =  "Desayuno";
		String id = "50";
		Pack paquete = new Pack(contenido.toArray(new Product[0]),nombre,id);
		assertNotNull(paquete);
		assertEquals(contenido.size(), paquete.getCantidadProductos());
		for (Product producto : paquete.getProductos()) {
			assertTrue(contenido.contains(producto));
		}
		assertEquals(nombre,paquete.getNombre());
		assertEquals(id,paquete.getIdentificador());
	}
	
	@Test
	public void testConstructorCorrectoAVL2Productos() {
		ArrayList<Product> contenido = new ArrayList<>();
		contenido.add(productoValido1);
		contenido.add(productoValido2);
		String nombre =  "Desayuno";
		String id = "50";
		Pack paquete = new Pack(contenido.toArray(new Product[0]),nombre,id);
		assertNotNull(paquete);
		assertEquals(contenido.size(), paquete.getCantidadProductos());
		for (Product producto : paquete.getProductos()) {
			assertTrue(contenido.contains(producto));
		}
		assertEquals(nombre,paquete.getNombre());
		assertEquals(id,paquete.getIdentificador());
	}
	
	@Test (expected=IllegalArgumentException.class)
	public void testConstructorIncorrectoUnProducto() {
		Product [] contenido = new Product[1];
		contenido[0] = productoValido1;
		String nombre =  "Desayuno";
		String id = "50";
		@SuppressWarnings("unused")
		Pack paquete = new Pack(contenido,nombre,id);
	}
	
	@Test (expected=NullPointerException.class)
	public void testConstructorIncorrectoProductoNull() {
		Product [] contenido = new Product[3];
		contenido[0] = productoValido1;
		contenido[1] = productoValido2;
		
		String nombre =  "Desayuno";
		String id = "50";
		@SuppressWarnings("unused")
		Pack paquete = new Pack(contenido,nombre,id);
	}
	
	@SuppressWarnings("unused")
	@Test  (expected=IllegalArgumentException.class)
	public void testConstructorIncorrectoProductosRepetidos() {
		Product [] contenido = new Product[3];
		contenido[0] = productoValido1;
		contenido[1] = productoValido2;
		contenido[2] = productoValido1;
		String nombre =  "Desayuno";
		String id = "50";
		Pack paquete = new Pack(contenido,nombre,id);
	}
	
	@SuppressWarnings("unused")
	@Test (expected=NullPointerException.class)
	public void testConstructorIncorrectoIdentificadorNull() {
		Product [] contenido = new Product[3];
		contenido[0] = productoValido1;
		contenido[1] = productoValido2;
		contenido[2] = productoValido3;
		String nombre =  "Desayuno";
		String id = null;
		Pack paquete = new Pack(contenido,nombre,id);
	}
	
	@SuppressWarnings("unused")
	@Test (expected=IllegalArgumentException.class)
	public void testConstructorIncorrectoIdentificadorVacio() {
		Product [] contenido = new Product[3];
		contenido[0] = productoValido1;
		contenido[1] = productoValido2;
		contenido[2] = productoValido3;
		String nombre =  "Desayuno";
		String id = "";
		Pack paquete = new Pack(contenido,nombre,id);
	}
	
// public Pack clonar()
	@Test
	public void testClonarCorrecto() {
		Product [] contenido = new Product[3];
		contenido[0] = productoValido1;
		contenido[1] = productoValido2;
		contenido[2] = productoValido3;
		String nombre =  "Desayuno";
		String id = "987";
		Pack original = new Pack(contenido,nombre,id);
		Pack copia = original.clonar();
		assertNotNull(copia);
		assertNotSame(copia, original);
		assertEquals(copia,original);
	}

// ESPECIAL - public double getPrecio()
	// Comprobamos que el cálculo es correcto
	@Test
	public void testGetPrecio() {
		Product [] contenido = new Product[3];
		contenido[0] = productoValido1;
		contenido[1] = productoValido2;
		contenido[2] = productoValido3;
		Pack paquete = new Pack(contenido,"Desayuno","50");
		double precioEsperado = (productoValido1.getPrecio() + productoValido2.getPrecio() + productoValido3.getPrecio())*0.8;
		 
		assertEquals(precioEsperado, paquete.getPrecio(), 0.01);
	}
	
	// Y que cambia
	@Test
	public void testGetPrecioCambia() {
		Product [] contenido = new Product[3];
		contenido[0] = productoValido1;
		contenido[1] = productoValido2;
		contenido[2] = productoValido3;
		Pack paquete = new Pack(contenido,"Desayuno","50");
		productoValido1.setPrecio(987418465);
		double precioEsperado = (987418465 + productoValido2.getPrecio() + productoValido3.getPrecio())*0.8;
		 
		assertEquals(precioEsperado, paquete.getPrecio(), 0.01);
	}
	
	

// public void setIdentificador(String nuevoIdentificador) 
	@Test 
	public void testSetIndentificadorCorrecto() {
		Product [] contenido = new Product[3];
		contenido[0] = productoValido1;
		contenido[1] = productoValido2;
		contenido[2] = productoValido3;
		String nombre =  "Desayuno";
		String id = "50";
		String nuevoId = "805";
		Pack paquete = new Pack(contenido,nombre,id);
		paquete.setIdentificador(nuevoId);
		assertEquals(nuevoId, paquete.getIdentificador());
	}
	
	@SuppressWarnings("unused")
	@Test (expected=NullPointerException.class)
	public void testSetIndentificadorIncorrectoNull() {
		Product [] contenido = new Product[3];
		contenido[0] = productoValido1;
		contenido[1] = productoValido2;
		contenido[2] = productoValido3;
		String nombre =  "Desayuno";
		String id = "50";
		String nuevoId = "805";
		Pack paquete = new Pack(contenido,nombre,id);
		paquete.setIdentificador(null);
	}
	
	@SuppressWarnings("unused")
	@Test (expected=IllegalArgumentException.class)
	public void testSetIndentificadorIncorrectoVacio() {
		Product [] contenido = new Product[3];
		contenido[0] = productoValido1;
		contenido[1] = productoValido2;
		contenido[2] = productoValido3;
		String nombre =  "Desayuno";
		String id = "50";
		String nuevoId = "805";
		Pack paquete = new Pack(contenido,nombre,id);
		paquete.setIdentificador("");
	}
	
// ESPECIAL - public LocalDate getFechaConsumoPreferente() 
	// Comprobamos que se devuelve la fecha correcta
	@Test
	public void testGetFechaConsumoPreferente() {
		Product [] contenido = new Product[3];
		contenido[0] = productoValido1;
		contenido[1] = productoValido2;
		contenido[2] = productoValido3;
		Pack paquete = new Pack(contenido,"Desayuno","50");
		 
		assertEquals(LocalDate.of(2021,10,10), paquete.getFechaConsumoPreferente());
	}
	
	
	// Que cambia si tiene que hacerlo
	@Test
	public void testGetFechaConsumoPreferenteCambia() {
		Product [] contenido = new Product[3];
		contenido[0] = productoValido1;
		contenido[1] = productoValido2;
		contenido[2] = productoValido3;
		Pack paquete = new Pack(contenido,"Desayuno","50");
		@SuppressWarnings("unused")
		LocalDate fechaOriginal = paquete.getFechaConsumoPreferente();
		
		productoValido1.setFechaConsumoPreferente(LocalDate.of(1900, 12, 25));
		 
		assertEquals(LocalDate.of(1900, 12, 25), paquete.getFechaConsumoPreferente());
	}
	
	//Y solo si tiene que hacerlo
	@Test
	public void testGetFechaConsumoPreferenteNoCambia() {
		Product [] contenido = new Product[3];
		contenido[0] = productoValido1;
		contenido[1] = productoValido2;
		contenido[2] = productoValido3;
		Pack paquete = new Pack(contenido,"Desayuno","50");
		LocalDate fechaOriginal = paquete.getFechaConsumoPreferente();
		
		productoValido1.setFechaConsumoPreferente(LocalDate.of(2500, 12, 25));
		 
		assertEquals(fechaOriginal, paquete.getFechaConsumoPreferente());
	}
	
// public int getCantidadProductos()
	@Test
	public void testGetCantidadProductos() {
		Product [] contenido;
		contenido = new Product[3];
		contenido[0] = productoValido1;
		contenido[1] = productoValido2;
		contenido[2] = productoValido3;
		String nombre =  "Desayuno";
		String id = "50";
		Pack paquete = new Pack(contenido,nombre,id);
		assertEquals(3, paquete.getCantidadProductos());
	}
	
	@Test
	public void testGetCantidadProductosAVL() {
		Product [] contenido;
		contenido = new Product[2];
		contenido[0] = productoValido1;
		contenido[1] = productoValido2;
		String nombre =  "Desayuno";
		String id = "50";
		Pack paquete = new Pack(contenido,nombre,id);
		assertEquals(2, paquete.getCantidadProductos());
	}
	
// public boolean contiene(Product producto) 
	@Test 
	public void testContieneProductoSi() {
		Product [] contenido;
		contenido = new Product[3];
		contenido[0] = productoValido1;
		contenido[1] = productoValido2;
		contenido[2] = productoValido3;
		String nombre =  "Desayuno";
		String id = "50";
		Pack paquete = new Pack(contenido,nombre,id);
		assertTrue(paquete.contiene(productoValido1));
	}
	@Test 
	public void testContieneProductoNo() {
		Product [] contenido;
		contenido = new Product[2];
		contenido[0] = productoValido1;
		contenido[1] = productoValido2;
		String nombre =  "Desayuno";
		String id = "50";
		Pack paquete = new Pack(contenido,nombre,id);
		assertFalse(paquete.contiene(productoValido3));
	}
	@Test (expected=NullPointerException.class)
	public void testContieneProductoNull() {
		Product [] contenido;
		contenido = new Product[3];
		contenido[0] = productoValido1;
		contenido[1] = productoValido2;
		contenido[2] = productoValido3;
		String nombre =  "Desayuno";
		String id = "50";
		Pack paquete = new Pack(contenido,nombre,id);
		paquete.contiene(null);				
	}	

// public Product[] getProductos()
	@Test
	public void testGetProductos() {
		Product [] contenido;
		contenido = new Product[3];
		contenido[0] = productoValido1;
		contenido[1] = productoValido2;
		contenido[2] = productoValido3;
		String nombre =  "Desayuno";
		String id = "50";
		Pack paquete = new Pack(contenido,nombre,id);
		assertTrue(paquete.contiene(productoValido1));	
		assertTrue(paquete.contiene(productoValido2));	
		assertTrue(paquete.contiene(productoValido3));	
		assertEquals(3, paquete.getCantidadProductos());
	}
	
// public void anadirProducto(Product producto) 
	@Test 
	public void testAnadirProductoCorrecto() {
		Product [] contenido;
		contenido = new Product[2];
		contenido[0] = productoValido1;
		contenido[1] = productoValido2;
		String nombre =  "Desayuno";
		String id = "50";
		Pack paquete = new Pack(contenido,nombre,id);
		paquete.anadirProducto(productoValido3);
		assertTrue(paquete.contiene(productoValido1));	
		assertTrue(paquete.contiene(productoValido2));	
		assertTrue(paquete.contiene(productoValido3));	
		assertEquals(3, paquete.getCantidadProductos());
	}
	@Test (expected=NullPointerException.class)
	public void testAnadirProductoIncorrectoNull() {
		Product [] contenido;
		contenido = new Product[2];
		contenido[0] = productoValido1;
		contenido[1] = productoValido2;
		String nombre =  "Desayuno";
		String id = "50";
		Pack paquete = new Pack(contenido,nombre,id);
		paquete.anadirProducto(null);	
	}
	@Test (expected=IllegalArgumentException.class)
	public void testAnadirProductoRepetidoIncorrecto() {
		Product [] contenido;
		contenido = new Product[3];
		contenido[0] = productoValido1;
		contenido[1] = productoValido2;
		contenido[2] = productoValido3;
		String nombre =  "Desayuno";
		String id = "50";
		Pack paquete = new Pack(contenido,nombre,id);
		paquete.anadirProducto(productoValido3);	
	}

// public void eliminarProducto(Product producto)  
	@Test
	public void testEliminarProductoCorrecto() {
		Product [] contenido;
		contenido = new Product[3];
		contenido[0] = productoValido1;
		contenido[1] = productoValido2;
		contenido[2] = productoValido3;
		String nombre =  "Desayuno";
		String id = "50";
		Pack paquete = new Pack(contenido,nombre,id);
		paquete.eliminarProducto(productoValido3);
		assertTrue(paquete.contiene(productoValido1));	
		assertTrue(paquete.contiene(productoValido2));	
		assertFalse(paquete.contiene(productoValido3));	
		assertEquals(2, paquete.getCantidadProductos());
	}
	@Test (expected=NullPointerException.class)
	public void testEliminarProductoNullIncorrecto() {
		Product [] contenido;
		contenido = new Product[3];
		contenido[0] = productoValido1;
		contenido[1] = productoValido2;
		contenido[2] = productoValido3;
		String nombre =  "Desayuno";
		String id = "50";
		Pack paquete = new Pack(contenido,nombre,id);
		paquete.eliminarProducto(null);
	}
	@Test (expected=IllegalArgumentException.class)
	public void testEliminarProductoNoExistenteIncorrecto() {
		Product [] contenido;
		contenido = new Product[2];
		contenido[0] = productoValido1;
		contenido[1] = productoValido2;
		String nombre =  "Desayuno";
		String id = "50";
		Pack paquete = new Pack(contenido,nombre,id);
		paquete.eliminarProducto(productoValido3);
	}
	@Test (expected=IllegalStateException.class)
	public void testEliminarProductoPackMinimosIncorrecto() {
		Product [] contenido;
		contenido = new Product[2];
		contenido[0] = productoValido1;
		contenido[1] = productoValido2;
		String nombre =  "Desayuno";
		String id = "50";
		Pack paquete = new Pack(contenido,nombre,id);
		paquete.eliminarProducto(productoValido2);
	}
	
//public boolean equals(Object obj)
	@Test
	public void testEqualsMismoPackCorrecto() {
		Product [] contenido;
		contenido = new Product[3];
		contenido[0] = productoValido1;
		contenido[1] = productoValido2;
		contenido[2] = productoValido3;
		String nombre =  "Desayuno";
		String id = "50";
		Pack original = new Pack(contenido,nombre,id);
		Pack mismoPack = original;
		
		assertTrue(original.equals(mismoPack));		
	}
	
	@Test
	public void testEqualsCopiaPackCorrecto() {
		Product [] contenido;
		contenido = new Product[3];
		contenido[0] = productoValido1;
		contenido[1] = productoValido2;
		contenido[2] = productoValido3;
		String nombre =  "Desayuno";
		String id = "50";
		Pack original = new Pack(contenido,nombre,id);
		Pack copia = original.clonar();
		
		assertTrue(original.equals(copia));
	}
	
	@Test
	public void testEqualsConNull() {
		Product [] contenido;
		contenido = new Product[3];
		contenido[0] = productoValido1;
		contenido[1] = productoValido2;
		contenido[2] = productoValido3;
		String nombre =  "Desayuno";
		String id = "50";
		Pack original = new Pack(contenido,nombre,id);
		
		assertFalse(original.equals(null));
	}
	
	@Test
	public void testEqualsNombreDistinto() {
		Product [] contenido;
		contenido = new Product[3];
		contenido[0] = productoValido1;
		contenido[1] = productoValido2;
		contenido[2] = productoValido3;
		Pack original = new Pack(contenido,"Desayuno","50");
		
		contenido = new Product[3];
		contenido[0] = productoValido1;
		contenido[1] = productoValido2;
		contenido[2] = productoValido3;
		Pack otro = new Pack(contenido,"Cena","50");
		
		assertFalse(original.equals(otro));
	}
	
	@Test
	public void testEqualsIdentificadorDistinto() {
		Product [] contenido;
		contenido = new Product[3];
		contenido[0] = productoValido1;
		contenido[1] = productoValido2;
		contenido[2] = productoValido3;
		Pack original = new Pack(contenido,"Desayuno","50");
		
		contenido = new Product[3];
		contenido[0] = productoValido1;
		contenido[1] = productoValido2;
		contenido[2] = productoValido3;
		Pack otro = new Pack(contenido,"Desayuno","874");
		
		assertFalse(original.equals(otro));
	}
	
	@Test
	public void testEqualsNumeroProductosIgualPeroDistintos() {
		Product [] contenido;
		contenido = new Product[2];
		contenido[0] = productoValido1;
		contenido[1] = productoValido2;
		Pack original = new Pack(contenido,"Desayuno","50");
		
		contenido = new Product[2];
		contenido[0] = productoValido1;
		contenido[1] = productoValido3;
		Pack otro = new Pack(contenido,"Desayuno","50");
		
		assertFalse(original.equals(otro));
	}
	
	@Test
	public void testEqualsNumeroProductosDistinto() {
		Product [] contenido;
		contenido = new Product[2];
		contenido[0] = productoValido1;
		contenido[1] = productoValido2;
		Pack original = new Pack(contenido,"Desayuno","50");
		
		contenido = new Product[3];
		contenido[0] = productoValido1;
		contenido[1] = productoValido2;
		contenido[2] = productoValido3;
		Pack otro = new Pack(contenido,"Desayuno","50");
		
		assertFalse(original.equals(otro));
	}
	
	//TEST ESPECIALES PARA EQUALS()
	@SuppressWarnings("unlikely-arg-type")
	@Test
	public void testEqualsOtraClase() {
		Product [] contenido;
		contenido = new Product[2];
		contenido[0] = productoValido1;
		contenido[1] = productoValido2;
		Pack original = new Pack(contenido,"Desayuno","50");
		assertFalse(original.equals(new String("jaaj")));
	}
	
	@Test
	public void testEqualsReflexividad() {
		Product [] contenido;
		contenido = new Product[2];
		contenido[0] = productoValido1;
		contenido[1] = productoValido2;
		Pack original = new Pack(contenido,"Desayuno","50");
		assertTrue(original.equals(original));
	}
	@Test
	public void testEqualsSimetria() {
		Product [] contenido;
		contenido = new Product[2];
		contenido[0] = productoValido1;
		contenido[1] = productoValido2;
		Pack original = new Pack(contenido,"Desayuno","50");
		
		Pack copia = original.clonar();
		assertTrue(original.equals(copia));
		assertTrue(copia.equals(original));
	}
	@Test
	public void testEqualsTransitividad() {
		Product [] contenido;
		contenido = new Product[2];
		contenido[0] = productoValido1;
		contenido[1] = productoValido2;
		Pack original = new Pack(contenido,"Desayuno","50");
		Pack copia = original.clonar();
		Pack otraCopia = original.clonar();
		assertTrue(original.equals(copia));
		assertTrue(copia.equals(otraCopia));
		assertTrue(original.equals(otraCopia));
	}
	
//public int hashCode()
	@Test
	public void testHashCodeMismoProductoCorrecto() {
		Product [] contenido;
		contenido = new Product[3];
		contenido[0] = productoValido1;
		contenido[1] = productoValido2;
		contenido[2] = productoValido3;
		String nombre =  "Desayuno";
		String id = "50";
		Pack original = new Pack(contenido,nombre,id);
		Pack mismoPack = original;
		
		assertEquals(original.hashCode(), mismoPack.hashCode());		
	}
	
	@Test
	public void testHashCodeCopiaProductoCorrecto() {
		Product [] contenido;
		contenido = new Product[3];
		contenido[0] = productoValido1;
		contenido[1] = productoValido2;
		contenido[2] = productoValido3;
		String nombre =  "Desayuno";
		String id = "50";
		Pack original = new Pack(contenido,nombre,id);
		Pack copia = original.clonar();
		
		assertEquals(original.hashCode(), copia.hashCode());
	}
	
	/* Técnicamente no es necesario que objetos distintos tengan hash codes distintos, pero nosotros
	 * lo comprobamos como garantía de un buen rendimiento en las hash tables (ver documentación de hashCode()
	 * en Object)
	 */
	
	@Test
	public void testHashCodeNombreDistinto() {
		Product [] contenido;
		contenido = new Product[3];
		contenido[0] = productoValido1;
		contenido[1] = productoValido2;
		contenido[2] = productoValido3;
		Pack original = new Pack(contenido,"Desayuno","50");
		
		contenido = new Product[3];
		contenido[0] = productoValido1;
		contenido[1] = productoValido2;
		contenido[2] = productoValido3;
		Pack otro = new Pack(contenido,"Cena","50");
		
		assertNotEquals(original.hashCode(), otro.hashCode());
	}
	
	@Test
	public void testHashCodeIdentificadorDistinto() {
		Product [] contenido;
		contenido = new Product[3];
		contenido[0] = productoValido1;
		contenido[1] = productoValido2;
		contenido[2] = productoValido3;
		Pack original = new Pack(contenido,"Desayuno","50");
		
		contenido = new Product[3];
		contenido[0] = productoValido1;
		contenido[1] = productoValido2;
		contenido[2] = productoValido3;
		Pack otro = new Pack(contenido,"Desayuno","874");
		
		assertNotEquals(original.hashCode(), otro.hashCode());
	}
	
	@Test
	public void testHashCodeNumeroProductosIgualPeroDistintos() {
		Product [] contenido;
		contenido = new Product[2];
		contenido[0] = productoValido1;
		contenido[1] = productoValido2;
		Pack original = new Pack(contenido,"Desayuno","50");
		
		contenido = new Product[2];
		contenido[0] = productoValido1;
		contenido[1] = productoValido3;
		Pack otro = new Pack(contenido,"Desayuno","50");
		
		assertNotEquals(original.hashCode(), otro.hashCode());
	}
	
	@Test
	public void testHashCodeNumeroProductosDistinto() {
		Product [] contenido;
		contenido = new Product[2];
		contenido[0] = productoValido1;
		contenido[1] = productoValido2;
		Pack original = new Pack(contenido,"Desayuno","50");
		
		contenido = new Product[3];
		contenido[0] = productoValido1;
		contenido[1] = productoValido2;
		contenido[2] = productoValido3;
		Pack otro = new Pack(contenido,"Desayuno","50");
		
		assertNotEquals(original.hashCode(), otro.hashCode());
	}
	
	//TEST ESPECIAL PARA HASHCODE()
	@Test
	public void testHashCodeReflexividadConsistencia() {
		Product [] contenido;
		contenido = new Product[2];
		contenido[0] = productoValido1;
		contenido[1] = productoValido2;
		Pack original = new Pack(contenido,"Desayuno","50");
		assertEquals(original.hashCode(), original.hashCode());
	}
	
	
	
	
}
