package uva.poo.tests;

import java.time.LocalDate;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import uva.poo.vending.Pack;
import uva.poo.vending.Product;

/**
 * Clase para hacer pruebas unitarias de caja negra de la clase Vendible.
 * Aquí se prueban sólo los métodos implementados, que son los que tienen que ver con los nombres.
 * @author juagonz0
 * @author sanbarb
 */
public class VendibleTest {		
		
	// ---------------------------------------------- TEST CON PRODUCT ---------------------------------------------- \\
	
	// Constructor: Test de nombre inválido (nombre válido ya se prueba en ProductTest)

	@Test (expected=NullPointerException.class)
	public void testProductConstructorNombreNullIncorrecto() {
		double precio = 5.2;
		LocalDate fecha = LocalDate.of(2021, 11, 18);
		String nombre = null;
		String upc = "123456789012";
		@SuppressWarnings("unused")
		Product i = new Product(precio, fecha, nombre, upc);
	}

	
	@Test (expected=IllegalArgumentException.class )
	public void testProductConstructorNombreVacioIncorrecto() {
		LocalDate fecha = LocalDate.of(2021, 11, 18);
		String nombre = "";
		String upc = "123456789012";
		double precio = 5.2;
		@SuppressWarnings("unused")
		Product i = new Product(precio, fecha, nombre, upc);
	}
	
	// public void setNombre(String nombre)
	
	@Test
	public void testProductSetNombreCorrecto() {
		double precio = 5.2;
		LocalDate fecha = LocalDate.of(2021,11,18);
		String nombre = "Patatas";
		String upc = "123456789012";
		Product i = new Product(precio, fecha, nombre, upc);
		i.setNombre("Pipas");
		assertEquals("Pipas",i.getNombre());
	}
	
	
	@Test (expected=NullPointerException.class)
	public void testProductSetNombreNullIncorrecto() {
		double precio = 5.2;
		LocalDate fecha = LocalDate.of(2021,11,18);
		String nombre = "Patatas";
		String upc = "123456789012";
		Product i = new Product(precio, fecha, nombre, upc);
		String nuevoNombre = null;
		i.setNombre(nuevoNombre);
	}
	

	@Test (expected=IllegalArgumentException.class )
	public void testProductSetNombreVacioIncorrecto() {
		LocalDate fecha = LocalDate.of(2021,11,18);
		String nombre = "Patatas";
		String upc = "123456789012";
		double precio = 5.2;
		Product i = new Product(precio, fecha, nombre, upc);
		i.setNombre("");
	}
		
	// ---------------------------------------------- TEST CON PACK ---------------------------------------------- \\
	
	// Constructor: Test de nombre inválido (nombre válido ya se prueba en PackTest)
	
	@Test (expected=NullPointerException.class)
	public void testPackConstructorNombreNullIncorrecto() {
		Product [] contenido = new Product[3];
		contenido[0] = new Product(5.5, LocalDate.of(2021,11,18), "Patatas", "123456789012");
		contenido[1] = new Product(4, LocalDate.of(2021,10,20), "Palomitas", "109876543212");;
		contenido[2] = new Product(7, LocalDate.of(2021,10,10), "Aceitunas", "654017891238");
		@SuppressWarnings("unused")
		Pack paquete = new Pack(contenido,null,"50");
	}

	
	@Test (expected=IllegalArgumentException.class )
	public void testPackConstructorNombreVacioIncorrecto() {
		Product [] contenido = new Product[3];
		contenido[0] = new Product(5.5, LocalDate.of(2021,11,18), "Patatas", "123456789012");
		contenido[1] = new Product(4, LocalDate.of(2021,10,20), "Palomitas", "109876543212");;
		contenido[2] = new Product(7, LocalDate.of(2021,10,10), "Aceitunas", "654017891238");
		@SuppressWarnings("unused")
		Pack paquete = new Pack(contenido,"","50");
	}
	
	// public void setNombre(String nombre)
	
	@Test
	public void testPackSetNombreCorrecto() {
		Product [] contenido = new Product[3];
		contenido[0] = new Product(5.5, LocalDate.of(2021,11,18), "Patatas", "123456789012");
		contenido[1] = new Product(4, LocalDate.of(2021,10,20), "Palomitas", "109876543212");;
		contenido[2] = new Product(7, LocalDate.of(2021,10,10), "Aceitunas", "654017891238");
		@SuppressWarnings("unused")
		Pack paquete = new Pack(contenido,"Desayuno Muy Vendible","344564B");
		paquete.setNombre("Pipas Pack Supremo");
		assertEquals("Pipas Pack Supremo", paquete.getNombre());
	}
	
	
	@Test (expected=NullPointerException.class)
	public void testPackSetNombreNullIncorrecto() {
		Product [] contenido = new Product[3];
		contenido[0] = new Product(5.5, LocalDate.of(2021,11,18), "Patatas", "123456789012");
		contenido[1] = new Product(4, LocalDate.of(2021,10,20), "Palomitas", "109876543212");;
		contenido[2] = new Product(7, LocalDate.of(2021,10,10), "Aceitunas", "654017891238");
		Pack paquete = new Pack(contenido,"Desayuno Muy Vendible","344564B");
		paquete.setNombre(null);
	}
	

	@Test (expected=IllegalArgumentException.class )
	public void testPackSetNombreVacioIncorrecto() {
		Product [] contenido = new Product[3];
		contenido[0] = new Product(5.5, LocalDate.of(2021,11,18), "Patatas", "123456789012");
		contenido[1] = new Product(4, LocalDate.of(2021,10,20), "Palomitas", "109876543212");;
		contenido[2] = new Product(7, LocalDate.of(2021,10,10), "Aceitunas", "654017891238");
		Pack paquete = new Pack(contenido,"Desayuno Muy Vendible","344564B");
		paquete.setNombre("");
	}
}
