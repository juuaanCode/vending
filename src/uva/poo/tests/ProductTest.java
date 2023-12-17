package uva.poo.tests;

import java.time.LocalDate;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import uva.poo.vending.Product;

/**
 * Clase para hacer pruebas unitarias de caja negra de la clase Product.
 * @author juagonz0
 * @author sanbarb
 */
public class ProductTest {
	
//public Product(double precio, LocalDate caducidad, String nombre, long upc)
	@Test 
	public void testConstructorCorrecto() {
		LocalDate fecha = LocalDate.of(2021,11,18);
		String nombre = "Patatas";
		String upc = "123456789012";
		double precio = 5;
		Product i = new Product(precio, fecha, nombre, upc);
		assertNotNull(i);
		assertEquals(precio,i.getPrecio(), 0.01);
		assertEquals(fecha,i.getFechaConsumoPreferente());
		assertEquals(nombre,i.getNombre());
		assertEquals(upc,i.getIdentificador());
	}

	@Test 
	public void testConstructorAVLPrecioTodoCorrecto() {
		LocalDate fecha = LocalDate.of(2021,11,18);
		String nombre = "Patatas";
		String upc = "123456789012";
		double precio = 0.01;
		Product i = new Product(precio, fecha, nombre, upc);
		assertNotNull(i);
		assertEquals(precio,i.getPrecio(), 0.01);
		assertEquals(fecha,i.getFechaConsumoPreferente());
		assertEquals(nombre,i.getNombre());
		assertEquals(upc,i.getIdentificador());
	}
	
	//Test del constructor con UPC
	@Test 
	public void testConstructorAVLUPCmaxTodoCorrecto() {
		LocalDate fecha = LocalDate.of(2021,11,18);
		String nombre = "Patatas";
		String upc = "999999999993";
		double precio = 5;
		Product i = new Product(precio, fecha, nombre, upc);
		assertNotNull(i);
		assertEquals(precio,i.getPrecio(), 0.01);
		assertEquals(fecha,i.getFechaConsumoPreferente());
		assertEquals(nombre,i.getNombre());
		assertEquals(upc,i.getIdentificador());
	}
	
	@Test 
	public void testConstructorAVLUPCminTodoCorrecto() {
		LocalDate fecha = LocalDate.of(2021,11,18);
		String nombre = "Patatas";
		String upc = "000000000000";
		double precio = 5;
		Product i = new Product(precio, fecha, nombre, upc);
		assertNotNull(i);
		assertEquals(precio,i.getPrecio(), 0.01);
		assertEquals(fecha,i.getFechaConsumoPreferente());
		assertEquals(nombre,i.getNombre());
		assertEquals(upc,i.getIdentificador());
	}
	
	@Test (expected=NullPointerException.class)
	public void testConstructorUPCNullIncorrecto() {
		LocalDate fecha = LocalDate.of(2021,11,18);
		String nombre = "Patatas";
		double precio = 5;
		@SuppressWarnings("unused")
		Product i = new Product(precio, fecha, nombre, null);
	}
	
	@Test (expected=IllegalArgumentException.class)
	public void testConstructorUPCMenosCaracteresAVLIncorrecto() {
		LocalDate fecha = LocalDate.of(2021,11,18);
		String nombre = "Patatas";
		double precio = 5;
		@SuppressWarnings("unused")
		Product i = new Product(precio, fecha, nombre, "99999999999");
	}
	
	@Test (expected=IllegalArgumentException.class)
	public void testConstructorUPCMenosCaracteresIncorrecto() {
		LocalDate fecha = LocalDate.of(2021,11,18);
		String nombre = "Patatas";
		double precio = 5;
		@SuppressWarnings("unused")
		Product i = new Product(precio, fecha, nombre, "4");
	}
	
	@Test (expected=IllegalArgumentException.class)
	public void testConstructorUPCMasCaracteresAVLIncorrecto() {
		LocalDate fecha = LocalDate.of(2021,11,18);
		String nombre = "Patatas";
		double precio = 5;
		@SuppressWarnings("unused")
		Product i = new Product(precio, fecha, nombre, "1000000000000");
	}
	
	@Test (expected=IllegalArgumentException.class)
	public void testConstructorUPCMasCaracteresIncorrecto() {
		LocalDate fecha = LocalDate.of(2021,11,18);
		String nombre = "Patatas";
		double precio = 5;
		@SuppressWarnings("unused")
		Product i = new Product(precio, fecha, nombre, "100000006584894900000");
	}
	
	
	@Test (expected=IllegalArgumentException.class)
	public void testConstructorUPCPrimerCaracterNoDigito() {
		LocalDate fecha = LocalDate.of(2021,11,18);
		String nombre = "Patatas";
		double precio = 5;
		@SuppressWarnings("unused")
		Product i = new Product(precio, fecha, nombre, "A23456789012");
	}
	
	
	
	@Test (expected=IllegalArgumentException.class)
	public void testConstructorUPCSegundoCaracterNoDigito() {
		LocalDate fecha = LocalDate.of(2021,11,18);
		String nombre = "Patatas";
		double precio = 5;
		@SuppressWarnings("unused")
		Product i = new Product(precio, fecha, nombre, "1B3456789012");
	}
	
	@Test (expected=IllegalArgumentException.class)
	public void testConstructorUPCTercerCaracterNoDigito() {
		LocalDate fecha = LocalDate.of(2021,11,18);
		String nombre = "Patatas";
		double precio = 5;
		@SuppressWarnings("unused")
		Product i = new Product(precio, fecha, nombre, "12C456789012");
	}
	
	@Test (expected=IllegalArgumentException.class)
	public void testConstructorUPCCuartoCaracterNoDigito() {
		LocalDate fecha = LocalDate.of(2021,11,18);
		String nombre = "Patatas";
		double precio = 5;
		@SuppressWarnings("unused")
		Product i = new Product(precio, fecha, nombre, "123D56789012");
	}
	
	@Test (expected=IllegalArgumentException.class)
	public void testConstructorUPCQuintoCaracterNoDigito() {
		LocalDate fecha = LocalDate.of(2021,11,18);
		String nombre = "Patatas";
		double precio = 5;
		@SuppressWarnings("unused")
		Product i = new Product(precio, fecha, nombre, "1234E6789012");
	}
	
	@Test (expected=IllegalArgumentException.class)
	public void testConstructorUPCSextoCaracterNoDigito() {
		LocalDate fecha = LocalDate.of(2021,11,18);
		String nombre = "Patatas";
		double precio = 5;
		@SuppressWarnings("unused")
		Product i = new Product(precio, fecha, nombre, "12345F789012");
	}
	
	@Test (expected=IllegalArgumentException.class)
	public void testConstructorUPCSeptimoCaracterNoDigito() {
		LocalDate fecha = LocalDate.of(2021,11,18);
		String nombre = "Patatas";
		double precio = 5;
		@SuppressWarnings("unused")
		Product i = new Product(precio, fecha, nombre, "123456G89012");
	}
	
	@Test (expected=IllegalArgumentException.class)
	public void testConstructorUPCOctavoCaracterNoDigito() {
		LocalDate fecha = LocalDate.of(2021,11,18);
		String nombre = "Patatas";
		double precio = 5;
		@SuppressWarnings("unused")
		Product i = new Product(precio, fecha, nombre, "1234567H9012");
	}
	
	@Test (expected=IllegalArgumentException.class)
	public void testConstructorUPCNovenoCaracterNoDigito() {
		LocalDate fecha = LocalDate.of(2021,11,18);
		String nombre = "Patatas";
		double precio = 5;
		@SuppressWarnings("unused")
		Product i = new Product(precio, fecha, nombre, "12345678I012");
	}
	
	@Test (expected=IllegalArgumentException.class)
	public void testConstructorUPCDecimoCaracterNoDigito() {
		LocalDate fecha = LocalDate.of(2021,11,18);
		String nombre = "Patatas";
		double precio = 5;
		@SuppressWarnings("unused")
		Product i = new Product(precio, fecha, nombre, "123456789J12");
	}
	
	@Test (expected=IllegalArgumentException.class)
	public void testConstructorUPCUndecimoCaracterNoDigito() {
		LocalDate fecha = LocalDate.of(2021,11,18);
		String nombre = "Patatas";
		double precio = 5;
		@SuppressWarnings("unused")
		Product i = new Product(precio, fecha, nombre, "1234567890K2");
	}
	
	@Test (expected=IllegalArgumentException.class)
	public void testConstructorUPCDuodecimoCaracterNoDigito() {
		LocalDate fecha = LocalDate.of(2021,11,18);
		String nombre = "Patatas";
		double precio = 5;
		@SuppressWarnings("unused")
		Product i = new Product(precio, fecha, nombre, "12345678901L");
	}
	
	@Test (expected=IllegalArgumentException.class)
	public void testConstructorUPCIncorrectoUltimoDigito() {
		LocalDate fecha = LocalDate.of(2021, 11, 18);
		String nombre = "Patatas";
		String upc = "123456789018";
		double precio = 5.2;
		@SuppressWarnings("unused")
		Product i = new Product(precio, fecha, nombre, upc);
	}
	//Fin test del constructor con UPC
	
	
	@Test (expected=IllegalArgumentException.class)
	public void testConstructorPrecioIncorrecto() {
		LocalDate fecha = LocalDate.of(2021,11,18);
		String nombre = "Patatas";
		String upc = "123456789012";
		double precio = -2;
		@SuppressWarnings("unused")
		Product i = new Product(precio, fecha, nombre, upc);
	}

	@Test (expected=IllegalArgumentException.class)
	public void testConstructorPrecioIncorrectoAVL() {
		LocalDate fecha = LocalDate.of(2021,11,18);
		String nombre = "Patatas";
		String upc = "123456789012";
		double precio = 0;
		@SuppressWarnings("unused")
		Product i = new Product(precio, fecha, nombre, upc);
	}

	@Test (expected=NullPointerException.class)
	public void testConstructorFechaNullIncorrecto() {
		LocalDate fecha = null;
		String nombre = "Patatas";
		String upc = "123456789012";
		double precio = 6;
		@SuppressWarnings("unused")
		Product i = new Product(precio, fecha, nombre, upc);
	}
	
	
//public Product clonar()
	@Test
	public void testClonarCorrecto() {
		LocalDate fecha = LocalDate.of(2021,11,18);
		String nombre ;
		nombre= "Patatas";
		String upc = "123456789012";
		double precio = 5.5;
		Product original = new Product(precio, fecha, nombre, upc);
		Product copia = original.clonar();
		assertNotNull(copia);
		assertNotSame(copia, original);
		assertEquals(copia,original);
	}
	
	
//public void setPrecio(double nuevoPrecio)	
	@Test 
	public void testSetPrecioCorrecto() {
		LocalDate fecha = LocalDate.of(2021,11,18);
		String nombre = "Patatas";
		String upc = "123456789012";
		double precio = 5.5;
		Product i = new Product(precio, fecha, nombre, upc);
		double nuevoPrecio = 4.5;
		i.setPrecio(nuevoPrecio);
		assertEquals(nuevoPrecio, i.getPrecio(), 0.01);
	}

	@Test 
	public void testSetPrecioAVLCorrecto() {
		LocalDate fecha = LocalDate.of(2021,11,18);
		String nombre = "Patatas";
		String upc = "123456789012";
		double precio = 5.5;
		Product i = new Product(precio, fecha, nombre, upc);
		double nuevoPrecio = 0.01;
		i.setPrecio(nuevoPrecio);
		assertEquals(nuevoPrecio, i.getPrecio(), 0.01);
	
	}


	@Test (expected=IllegalArgumentException.class) 
	public void testSetPrecioNegativo() {
		LocalDate fecha = LocalDate.of(2021,11,18);
		String nombre = "Patatas";
		String upc = "123456789012";
		double precio = 5.5;
		Product i = new Product(precio, fecha, nombre, upc);
		double nuevoPrecio = -4.5;
		i.setPrecio(nuevoPrecio);
	}
	
	
	@Test (expected=IllegalArgumentException.class) 
	public void testSetPrecioAVLCero() {
		LocalDate fecha = LocalDate.of(2021,11,18);
		String nombre = "Patatas";
		String upc = "123456789012";
		double precio = 5.5;
		Product i = new Product(precio, fecha, nombre, upc);
		double nuevoPrecio = 0;
		i.setPrecio(nuevoPrecio);
	}

	
// public void setFechaConsumoPreferente(LocalDate nuevaCaducidad) 
	@Test 
	public void testSetFechaConsumoPreferente() {
		LocalDate fecha = LocalDate.of(2021,11,18);
		String nombre = "Patatas";
		String upc = "123456789012";
		double precio = 5.5;
		Product i = new Product(precio, fecha, nombre, upc);
		LocalDate nuevafecha = LocalDate.of(2021,12,22);
		i.setFechaConsumoPreferente(nuevafecha);
		assertEquals(nuevafecha, i.getFechaConsumoPreferente());
	}
	
	@Test (expected=NullPointerException.class) 
	public void testSetFechaConsumoPreferenteNull() {
		LocalDate fecha = LocalDate.of(2021,11,18);
		String nombre = "Patatas";
		String upc = "123456789012";
		double precio = 5.5;
		Product i = new Product(precio, fecha, nombre, upc);
		i.setFechaConsumoPreferente(null);
	}


// public void setIdentificador (String nuevoIdentificador) - Mismos test que para el constructor
	@Test 
	public void testSetIdentificadorUPCTodoCorrecto() {
		LocalDate fecha = LocalDate.of(2021,11,18);
		String nombre = "Patatas";
		String upc = "123456789012";
		double precio = 5;
		Product i = new Product(precio, fecha, nombre, upc);
		i.setIdentificador("845774984985");
		assertEquals("845774984985",i.getIdentificador());
	}
	
	
	@Test 
	public void testSetIdentificadorAVLUPCmaxTodoCorrecto() {
		LocalDate fecha = LocalDate.of(2021,11,18);
		String nombre = "Patatas";
		String upc = "123456789012";
		double precio = 5;
		Product i = new Product(precio, fecha, nombre, upc);
		i.setIdentificador("999999999993");
		assertEquals("999999999993",i.getIdentificador());
	}
	
	@Test 
	public void testSetIdentificadorAVLUPCminTodoCorrecto() {
		LocalDate fecha = LocalDate.of(2021,11,18);
		String nombre = "Patatas";
		String upc = "123456789012";
		double precio = 5;
		Product i = new Product(precio, fecha, nombre, upc);
		i.setIdentificador("000000000000");
		assertEquals("000000000000",i.getIdentificador());
	}
	
	@Test (expected=NullPointerException.class)
	public void testSetIdentificadorUPCNullIncorrecto() {
		LocalDate fecha = LocalDate.of(2021,11,18);
		String nombre = "Patatas";
		String upc = "123456789012";
		double precio = 5;
		Product i = new Product(precio, fecha, nombre, upc);
		i.setIdentificador(null);
	}
	
	@Test (expected=IllegalArgumentException.class)
	public void testSetIdentificadorUPCMenosCaracteresAVLIncorrecto() {
		LocalDate fecha = LocalDate.of(2021,11,18);
		String nombre = "Patatas";
		String upc = "123456789012";
		double precio = 5;
		Product i = new Product(precio, fecha, nombre, upc);
		i.setIdentificador("99999999999");
	}
	
	@Test (expected=IllegalArgumentException.class)
	public void testSetIdentificadorUPCMenosCaracteresIncorrecto() {
		LocalDate fecha = LocalDate.of(2021,11,18);
		String nombre = "Patatas";
		String upc = "123456789012";
		double precio = 5;
		Product i = new Product(precio, fecha, nombre, upc);
		i.setIdentificador("4");
	}
	
	@Test (expected=IllegalArgumentException.class)
	public void testSetIdentificadorUPCMasCaracteresAVLIncorrecto() {
		LocalDate fecha = LocalDate.of(2021,11,18);
		String nombre = "Patatas";
		String upc = "123456789012";
		double precio = 5;
		Product i = new Product(precio, fecha, nombre, upc);
		i.setIdentificador("1000000000000");
	}
	
	@Test (expected=IllegalArgumentException.class)
	public void testSetIdentificadorUPCMasCaracteresIncorrecto() {
		LocalDate fecha = LocalDate.of(2021,11,18);
		String nombre = "Patatas";
		String upc = "123456789012";
		double precio = 5;
		Product i = new Product(precio, fecha, nombre, upc);
		i.setIdentificador("100000006584894900000");
	}
	
	
	@Test (expected=IllegalArgumentException.class)
	public void testSetIdentificadorUPCPrimerCaracterNoDigito() {
		LocalDate fecha = LocalDate.of(2021,11,18);
		String nombre = "Patatas";
		String upc = "123456789012";
		double precio = 5;
		Product i = new Product(precio, fecha, nombre, upc);
		i.setIdentificador("A23456789012");
	}
	
	
	
	@Test (expected=IllegalArgumentException.class)
	public void testSetIdentificadorUPCSegundoCaracterNoDigito() {
		LocalDate fecha = LocalDate.of(2021,11,18);
		String nombre = "Patatas";
		String upc = "123456789012";
		double precio = 5;
		Product i = new Product(precio, fecha, nombre, upc);
		i.setIdentificador("1B3456789012");
	}
	
	@Test (expected=IllegalArgumentException.class)
	public void testSetIdentificadorUPCTercerCaracterNoDigito() {
		LocalDate fecha = LocalDate.of(2021,11,18);
		String nombre = "Patatas";
		String upc = "123456789012";
		double precio = 5;
		Product i = new Product(precio, fecha, nombre, upc);
		i.setIdentificador("12C456789012");
	}
	
	@Test (expected=IllegalArgumentException.class)
	public void testSetIdentificadorUPCCuartoCaracterNoDigito() {
		LocalDate fecha = LocalDate.of(2021,11,18);
		String nombre = "Patatas";
		String upc = "123456789012";
		double precio = 5;
		Product i = new Product(precio, fecha, nombre, upc);
		i.setIdentificador("123D56789012");
	}
	
	@Test (expected=IllegalArgumentException.class)
	public void testSetIdentificadorUPCQuintoCaracterNoDigito() {
		LocalDate fecha = LocalDate.of(2021,11,18);
		String nombre = "Patatas";
		String upc = "123456789012";
		double precio = 5;
		Product i = new Product(precio, fecha, nombre, upc);
		i.setIdentificador("1234E6789012");
	}
	
	@Test (expected=IllegalArgumentException.class)
	public void testSetIdentificadorUPCSextoCaracterNoDigito() {
		LocalDate fecha = LocalDate.of(2021,11,18);
		String nombre = "Patatas";
		String upc = "123456789012";
		double precio = 5;
		Product i = new Product(precio, fecha, nombre, upc);
		i.setIdentificador("12345F789012");
	}
	
	@Test (expected=IllegalArgumentException.class)
	public void testSetIdentificadorUPCSeptimoCaracterNoDigito() {
		LocalDate fecha = LocalDate.of(2021,11,18);
		String nombre = "Patatas";
		String upc = "123456789012";
		double precio = 5;
		Product i = new Product(precio, fecha, nombre, upc);
		i.setIdentificador("123456G89012");
	}
	
	@Test (expected=IllegalArgumentException.class)
	public void testSetIdentificadorUPCOctavoCaracterNoDigito() {
		LocalDate fecha = LocalDate.of(2021,11,18);
		String nombre = "Patatas";
		String upc = "123456789012";
		double precio = 5;
		Product i = new Product(precio, fecha, nombre, upc);
		i.setIdentificador("1234567H9012");
	}
	
	@Test (expected=IllegalArgumentException.class)
	public void testSetIdentificadorUPCNovenoCaracterNoDigito() {
		LocalDate fecha = LocalDate.of(2021,11,18);
		String nombre = "Patatas";
		String upc = "123456789012";
		double precio = 5;
		Product i = new Product(precio, fecha, nombre, upc);
		i.setIdentificador("12345678I012");
	}
	
	@Test (expected=IllegalArgumentException.class)
	public void testSetIdentificadorUPCDecimoCaracterNoDigito() {
		LocalDate fecha = LocalDate.of(2021,11,18);
		String nombre = "Patatas";
		String upc = "123456789012";
		double precio = 5;
		Product i = new Product(precio, fecha, nombre, upc);
		i.setIdentificador("123456789J12");
	}
	
	@Test (expected=IllegalArgumentException.class)
	public void testSetIdentificadorUPCUndecimoCaracterNoDigito() {
		LocalDate fecha = LocalDate.of(2021,11,18);
		String nombre = "Patatas";
		String upc = "123456789012";
		double precio = 5;
		Product i = new Product(precio, fecha, nombre, upc);
		i.setIdentificador("1234567890K2");
	}
	
	@Test (expected=IllegalArgumentException.class)
	public void testSetIdentificadorUPCDuodecimoCaracterNoDigito() {
		LocalDate fecha = LocalDate.of(2021,11,18);
		String nombre = "Patatas";
		String upc = "123456789012";
		double precio = 5;
		Product i = new Product(precio, fecha, nombre, upc);
		i.setIdentificador("12345678901L");
	}
	
	@Test (expected=IllegalArgumentException.class)
	public void testSetIdentificadorUPCIncorrectoUltimoDigito() {
		LocalDate fecha = LocalDate.of(2021,11,18);
		String nombre = "Patatas";
		String upc = "123456789012";
		double precio = 5;
		Product i = new Product(precio, fecha, nombre, upc);
		i.setIdentificador("123456789018");
	}

 	
//public boolean equals(Object obj)
	@Test
	public void testEqualsMismoProductoCorrecto() {
		Product original = new Product(0.01,LocalDate.of(2021,11,18),"Patatas","123456789012");
		Product mismoProducto = original;
		
		assertTrue(original.equals(mismoProducto));		
	}
	
	@Test
	public void testEqualsCopiaProductoCorrecto() {
		Product original = new Product(0.01,LocalDate.of(2021,11,18),"Patatas","123456789012");
		Product copia = original.clonar();
		
		assertTrue(original.equals(copia));
	}
	
	@Test
	public void testEqualsConNull() {
		Product original = new Product(0.01,LocalDate.of(2021,11,18),"Patatas","123456789012");
		
		assertFalse(original.equals(null));
	}
	
	@Test
	public void testEqualsPrecioDistinto() {
		Product original = new Product(0.01,LocalDate.of(2021,11,18),"Patatas","123456789012");
		Product otro = new Product(70,LocalDate.of(2021,11,18),"Patatas","123456789012");
		
		assertFalse(original.equals(otro));
	}
	
	@Test
	public void testEqualsFechaDistinta() {
		Product original = new Product(0.01,LocalDate.of(2021,11,18),"Patatas","123456789012");
		Product otro = new Product(0.01,LocalDate.of(2021,11,17),"Patatas","123456789012");
		
		assertFalse(original.equals(otro));
	}
	
	@Test
	public void testEqualsNombreDistinto() {
		Product original = new Product(0.01,LocalDate.of(2021,11,18),"Patatas","123456789012");
		Product otro = new Product(0.01,LocalDate.of(2021,11,18),"Fritas","123456789012");
		
		assertFalse(original.equals(otro));
	}
	
	@Test
	public void testEqualsUPCDistinto() {
		Product original = new Product(0.01,LocalDate.of(2021,11,18),"Patatas","123456789012");
		Product otro = new Product(0.01,LocalDate.of(2021,11,18),"Patatas","465689768977");
		
		assertFalse(original.equals(otro));
	}
	
	//TEST ESPECIALES PARA EQUALS()
	@SuppressWarnings("unlikely-arg-type")
	@Test
	public void testEqualsOtraClase() {
		Product original = new Product(0.01,LocalDate.of(2021,11,18),"Patatas","123456789012");
		assertFalse(original.equals(new String("jaaj")));
	}
	
	@Test
	public void testEqualsReflexividad() {
		Product original = new Product(0.01,LocalDate.of(2021,11,18),"Patatas","123456789012");
		assertTrue(original.equals(original));
	}
	@Test
	public void testEqualsSimetria() {
		Product original = new Product(0.01,LocalDate.of(2021,11,18),"Patatas","123456789012");
		Product copia = original.clonar();
		assertTrue(original.equals(copia));
		assertTrue(copia.equals(original));
	}
	@Test
	public void testEqualsTransitividad() {
		Product original = new Product(0.01,LocalDate.of(2021,11,18),"Patatas","123456789012");
		Product copia = original.clonar();
		Product otraCopia = original.clonar();
		assertTrue(original.equals(copia));
		assertTrue(copia.equals(otraCopia));
		assertTrue(original.equals(otraCopia));
	}
	
//public int hashCode()
	@Test
	public void testHashCodeMismoProductoCorrecto() {
		Product original = new Product(0.01,LocalDate.of(2021,11,18),"Patatas","123456789012");
		Product mismoProducto = original;
		
		assertEquals(original.hashCode(), mismoProducto.hashCode());		
	}
	
	@Test
	public void testHashCodeCopiaProductoCorrecto() {
		Product original = new Product(0.01,LocalDate.of(2021,11,18),"Patatas","123456789012");
		Product copia = original.clonar();
		
		assertEquals(original.hashCode(), copia.hashCode());
	}
	
	/* Técnicamente no es necesario que objetos distintos tengan hash codes distintos, pero nosotros
	 * lo comprobamos como garantía de un buen rendimiento en las hash tables (ver documentación de hashCode()
	 * en Object)
	 */
	
	@Test
	public void testHashCodePrecioDistinto() {
		Product original = new Product(0.01,LocalDate.of(2021,11,18),"Patatas","123456789012");
		Product otro = new Product(70,LocalDate.of(2021,11,18),"Patatas","123456789012");
		
		assertNotEquals(original.hashCode(), otro.hashCode());
	}
	
	@Test
	public void testHashCodeFechaDistinta() {
		Product original = new Product(0.01,LocalDate.of(2021,11,18),"Patatas","123456789012");
		Product otro = new Product(0.01,LocalDate.of(2021,11,17),"Patatas","123456789012");
		
		assertNotEquals(original.hashCode(), otro.hashCode());
	}
	
	@Test
	public void testHashCodeNombreDistinto() {
		Product original = new Product(0.01,LocalDate.of(2021,11,18),"Patatas","123456789012");
		Product otro = new Product(0.01,LocalDate.of(2021,11,18),"Fritas","123456789012");
		
		assertNotEquals(original.hashCode(), otro.hashCode());
	}
	
	@Test
	public void testHashCodeUPCDistinto() {
		Product original = new Product(0.01,LocalDate.of(2021,11,18),"Patatas","123456789012");
		Product otro = new Product(0.01,LocalDate.of(2021,11,18),"Patatas","465689768977");
		
		assertNotEquals(original.hashCode(), otro.hashCode());
	}
	
	//TEST ESPECIAL PARA HASHCODE()
	@Test
	public void testHashCodeReflexividadConsistencia() {
		Product original = new Product(0.01,LocalDate.of(2021,11,18),"Patatas","123456789012");
		assertEquals(original.hashCode(), original.hashCode());
	}
	
}

