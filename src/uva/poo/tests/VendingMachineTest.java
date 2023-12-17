package uva.poo.tests;

import static org.junit.Assert.*;


import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;

import fabricante.externo.tarjetas.TarjetaMonedero;
import uva.poo.vending.Product;
import uva.poo.vending.Vendible;
import uva.poo.vending.VendingMachine;


/**
 * Clase para hacer pruebas unitarias de caja negra de la clase VendingMachine.
 * @author juagonz0
 * @author sanbarb
 */
public class VendingMachineTest {
	private Product productoValido1, productoValido2, productoValido3;
	
	@Before
	public void setUp() throws Exception {	
        productoValido1 = new Product(5, LocalDate.of(2021,11,18), "Patatas", "123456789012");
        productoValido2 = new Product(4, LocalDate.of(2021,10,20), "Palomitas", "109876543212");
        productoValido3 = new Product(7, LocalDate.of(2021,10,10), "Aceitunas", "654017891238");
        
	}

//public VendingMachine(int identificador, int numeroLineas, boolean estado, int filas)
	@Test
	public void testConstructorVendingMachineAVLTodoCorrecto() {
		VendingMachine maquina = new VendingMachine(1,1,true,1);
		assertNotNull(maquina);
		assertEquals(1,maquina.getIdentificador());
		assertEquals(1,maquina.getNumeroLineas());
		for (int i = 0; i < maquina.getLineas().length; i++) {
			assertNull(maquina.getLineas()[i]);
		}
		assertEquals(true,maquina.getEstado());
		assertEquals(1,maquina.getNumeroFilas());
		assertEquals(1, maquina.getNumeroColumnas());
	}
	@Test
	public void testConstructorVendingMachineTodoCorrecto() {
		VendingMachine maquina = new VendingMachine(5679,20,false,3);
		assertNotNull(maquina);
		assertEquals(5679,maquina.getIdentificador());
		assertEquals(20,maquina.getNumeroLineas());
		for (int i = 0; i < maquina.getLineas().length; i++) {
			assertNull(maquina.getLineas()[i]);
		}
		assertEquals(false,maquina.getEstado());
		assertEquals(3,maquina.getNumeroFilas());
		assertEquals(7, maquina.getNumeroColumnas());
	}
	
	@Test
	public void testConstructorVendingMachineTodoCorrectoEspecial() {
		VendingMachine maquina = new VendingMachine(-47,20,false,3);
		assertNotNull(maquina);
		assertEquals(-47,maquina.getIdentificador());
		assertEquals(20,maquina.getNumeroLineas());
		for (int i = 0; i < maquina.getLineas().length; i++) {
			assertNull(maquina.getLineas()[i]);
		}
		assertEquals(false,maquina.getEstado());
		assertEquals(3,maquina.getNumeroFilas());
		assertEquals(7, maquina.getNumeroColumnas());
	}
	
	
	@Test (expected=IllegalArgumentException.class)
	public void testConstructorVendingMachineAVLNumeroLineasIncorrecto() {
		@SuppressWarnings("unused")
		VendingMachine maquina = new VendingMachine(650,0,true,20); 
	}
	
	@Test (expected=IllegalArgumentException.class)
	public void testConstructorVendingMachineAVLNumeroFilasIncorrecto() {
		@SuppressWarnings("unused")
		VendingMachine maquina = new VendingMachine(650,20,true,0);
	}
	
	@Test (expected=IllegalArgumentException.class)
	public void testConstructorVendingMachineNumeroLineasIncorrecto() {
		@SuppressWarnings("unused")
		VendingMachine maquina = new VendingMachine(650,-20,true,20);
	}
	
	@Test (expected=IllegalArgumentException.class)
	public void testConstructorVendingMachineNumeroFilasIncorrecto() {
		@SuppressWarnings("unused")
		VendingMachine maquina = new VendingMachine(650,20,true,-5);
	}
	

//public VendingMachine clonar()
	@Test
	public void testClonarCorrecto() {
		VendingMachine maquina = new VendingMachine(650,20,true,5);
		maquina.asignarLinea(productoValido1, 5, 0);
		maquina.asignarLinea(productoValido2, 3, 1);

		VendingMachine copia = maquina.clonar();
		assertNotNull(copia);
		assertEquals(maquina, copia); //Deben ser iguales, pero tener copia en profundidad de todo
		assertNotSame(maquina, copia);
		for (int i = 0; i < maquina.getLineas().length; i++) {
			//Lineas iguales, pero no las mismas
			if (maquina.getLineas()[i] == null || copia.getLineas() == null) {
				assertEquals(maquina.getLineas()[i], copia.getLineas()[i]);
			} else {
				//Con productos iguales, pero no los mismos
				assertEquals(maquina.getLineas()[i], copia.getLineas()[i]);
				assertNotSame(maquina.getLineas()[i], copia.getLineas()[i]);
			}
		}
	}

	
// public void setIdentificador(int nuevoIdentificador)
	@Test 
	public void testSetIdentificadorCorrecto() {
		VendingMachine maquina = new VendingMachine(650,20,true,5);
		int nuevoId = 50;
		maquina.setIdentificador(nuevoId);
		assertEquals(nuevoId, maquina.getIdentificador());
	}
	
	@Test (expected=IllegalStateException.class)
	public void testSetIdentificadorMaquinaFueraServicio() {
		VendingMachine maquina = new VendingMachine(650,20,false,5);
		int nuevoId = 50;
		maquina.setIdentificador(nuevoId);
	}

	
// public Vendible[] getLineas()
	@Test
	public void testGetLineasAVLVacio() {
		VendingMachine maquina = new VendingMachine(650,3,true,2);
		assertEquals(3,maquina.getLineas().length);
		for (int i = 0; i<3; i++) {
			assertNull(maquina.getLineas()[i]);
		}
	}
	
	@Test
	public void testGetLineasAVLLleno() {
		VendingMachine maquina = new VendingMachine(650,3,true,2);
		maquina.asignarLinea(productoValido1, 5, 0);
		maquina.asignarLinea(productoValido2, 4, 1);
		maquina.asignarLinea(productoValido3, 6, 2);
		assertEquals(3,maquina.getLineas().length);
		assertEquals(productoValido1, maquina.getLineas()[0]);
		assertEquals(productoValido2, maquina.getLineas()[1]);
		assertEquals(productoValido3, maquina.getLineas()[2]);
	}
	
	@Test
	public void testGetLineas() {
		VendingMachine maquina = new VendingMachine(650,5,true,2);
		maquina.asignarLinea(productoValido1, 5, 0);
		maquina.asignarLinea(productoValido2, 4, 1);
		maquina.asignarLinea(productoValido3, 6, 2);
		assertEquals(5,maquina.getLineas().length);
		assertEquals(productoValido1, maquina.getLineas()[0]);
		assertEquals(productoValido2, maquina.getLineas()[1]);
		assertEquals(productoValido3, maquina.getLineas()[2]);
		assertNull(maquina.getLineas()[3]);
		assertNull(maquina.getLineas()[4]);
	}

// public int[] getCantidades() 
	@Test 
	public void testGetCantidadesAVLVacio() {
		VendingMachine maquina = new VendingMachine(650,3,true,2);
		assertEquals(3,maquina.getCantidades().length);
		//No se especifica valores para líneas no asignadas
	}
	
	@Test 
	public void testGetCantidadesAVLLleno() {
		VendingMachine maquina = new VendingMachine(650,3,true,2);
		maquina.asignarLinea(productoValido1, 5, 0);
		maquina.asignarLinea(productoValido2, 4, 1);
		maquina.asignarLinea(productoValido3, 6, 2);
		assertEquals(3,maquina.getCantidades().length);
		assertEquals(5, maquina.getCantidades()[0]);
		assertEquals(4, maquina.getCantidades()[1]);
		assertEquals(6, maquina.getCantidades()[2]);
	}
	
	@Test 
	public void testGetCantidades() {
		VendingMachine maquina = new VendingMachine(650,5,true,2);
		maquina.asignarLinea(productoValido1, 5, 0);
		maquina.asignarLinea(productoValido2, 4, 1);
		maquina.asignarLinea(productoValido3, 6, 2);
		assertEquals(5,maquina.getCantidades().length);
		assertEquals(5, maquina.getCantidades()[0]);
		assertEquals(4, maquina.getCantidades()[1]);
		assertEquals(6, maquina.getCantidades()[2]);
		assertNull(maquina.getLineas()[3]);
		assertNull(maquina.getLineas()[4]);
	}
	
//public void ponerOperativa()
	@Test
	public void testPonerOperativa() {
		VendingMachine maquina = new VendingMachine(650,20,false,5);
		maquina.ponerOperativa();
		assertTrue(maquina.getEstado());
	}
	
	@Test  (expected=IllegalStateException.class)
	public void testPonerOperativaIncorrecto() {
		VendingMachine maquina = new VendingMachine(650,20,true,5);
		maquina.ponerOperativa();
	}

//public void fueraDeServicio()	
	@Test
	public void testFueraDeServicio() {
		VendingMachine maquina = new VendingMachine(650,20,true,5);
		maquina.fueraDeServicio();
		assertFalse(maquina.getEstado());	
	}

	@Test  (expected=IllegalStateException.class)
	public void testFueraDeServicioIncorrecto() {
		VendingMachine maquina = new VendingMachine(650,20,false,5);
		maquina.fueraDeServicio();
	}
	
//public boolean tieneAlgunaLineaVacia()	
	@Test 
	public void testTieneAlgunaLineaVaciaTodasNoAsignadas() {
		VendingMachine maquina = new VendingMachine(650,3,true,2);
		assertTrue(maquina.tieneAlgunaLineaVacia());
	}
	
	@Test 
	public void testTieneAlgunaLineaVaciaTodasAsignadasCantidadCero() {
		VendingMachine maquina = new VendingMachine(650,3,true,2);
		maquina.asignarLinea(productoValido1, 0, 0);
		maquina.asignarLinea(productoValido2, 0, 1);
		maquina.asignarLinea(productoValido3, 0, 2);
		assertTrue(maquina.tieneAlgunaLineaVacia());
	}
	

	@Test 
	public void testTieneAlgunaLineaVaciaCorrecto() {
		VendingMachine maquina = new VendingMachine(650,3,true,2);
		maquina.asignarLinea(productoValido1, 6, 0);
		maquina.asignarLinea(productoValido2, 5, 1);
		assertTrue(maquina.tieneAlgunaLineaVacia());
	}
	
	
	@Test
	public void testTieneAlgunaLineaVaciaSinLineasVacias() {
		VendingMachine maquina = new VendingMachine(650,3,true,2);
		maquina.asignarLinea(productoValido1, 8, 0);
		maquina.asignarLinea(productoValido2, 6, 1);
		maquina.asignarLinea(productoValido3, 4, 2);
		assertFalse(maquina.tieneAlgunaLineaVacia());
	}
	
	
//public void resetearMaquina()
	@Test 
	public void testResetearMaquina() {
		VendingMachine maquina = new VendingMachine(650,3,true,2);
		maquina.asignarLinea(productoValido1, 8, 0);
		maquina.asignarLinea(productoValido2, 6, 1);
		maquina.asignarLinea(productoValido3, 4, 2);
		maquina.resetearMaquina();
		for (Vendible vendible : maquina.getLineas()) {
			assertNull(vendible);
		}
	}
	
	@Test 
	public void testResetearMaquinaYaVacia() {
		VendingMachine maquina = new VendingMachine(650,3,true,2);
		maquina.resetearMaquina();
		for (Vendible vendible : maquina.getLineas()) {
			assertNull(vendible);
		}
	}
	
	@Test (expected=IllegalStateException.class)
	public void testResetearMaquinaIncorrectoMaquinaNoOperativa() {
		VendingMachine maquina = new VendingMachine(650,3,true,2);
		maquina.fueraDeServicio();
		maquina.resetearMaquina();
	}
	
//public void desasignarLinea(int numLinea)
	@Test 
	public void testDesasignarLineaCorrecto() {
		VendingMachine maquina = new VendingMachine(650,3,true,2);
		maquina.asignarLinea(productoValido1, 8, 0);
		maquina.desasignarLinea(0);
		assertNull(maquina.getLineas()[0]);
	}
	
	@Test (expected=IllegalStateException.class)
	public void testDesasignarLineaIncorrectoMaquinaNoOperativa() {
		VendingMachine maquina = new VendingMachine(650,3,true,2);
		maquina.asignarLinea(productoValido1, 8, 0);
		maquina.fueraDeServicio();
		maquina.desasignarLinea(1);
	}
	
	@Test (expected=IndexOutOfBoundsException.class)
	public void testDesasignarLineaInorrectoNumLineaNoExiste() {
		VendingMachine maquina = new VendingMachine(650,3,true,2);
		maquina.desasignarLinea(5);
		
	}
	
	@Test (expected=IllegalStateException.class)
	public void testDesasignarLineaInorrectoLineaYaVacia() {
		VendingMachine maquina = new VendingMachine(650,3,true,2);
		maquina.desasignarLinea(0);
	}
	
//public void asignarLinea(LineaMaquina linea, int numLinea)
	@Test 
	public void testAsignarLineaCorrectoAVLInicio() {
		VendingMachine maquina = new VendingMachine(650,3,true,2);	
		maquina.asignarLinea(productoValido1, 8, 0);
		assertSame(productoValido1, maquina.getLineas()[0]);
	}

	@Test 
	public void testAsignarLineaCorrectoAVLFinal() {
		VendingMachine maquina = new VendingMachine(650,3,true,2);
		maquina.asignarLinea(productoValido3, 4, 2); 
		assertSame(productoValido3, maquina.getLineas()[2]);
	}
	@Test 
	public void testAsignarLineaCorrecto() {
		VendingMachine maquina = new VendingMachine(650,3,true,2);
		maquina.asignarLinea(productoValido3, 4, 1); 
		assertSame(productoValido3, maquina.getLineas()[1]);
	}
	
	@Test (expected=NullPointerException.class)
	public void testAsignarLineaIncorrectoLineaNull () {
		VendingMachine maquina = new VendingMachine(650,3,true,2);
		maquina.asignarLinea(null, 4, 1);
		
	}
	@Test  (expected=IndexOutOfBoundsException.class)
	public void testAsignarLineaIncorrectoNumLineaMayor() {
		VendingMachine maquina = new VendingMachine(650,3,true,2);
		maquina.asignarLinea(productoValido3, 4, 8);
	
	}
	@Test  (expected=IndexOutOfBoundsException.class)
	public void testAsignarLineaIncorrectoNumLineaMayorAVL() {
		VendingMachine maquina = new VendingMachine(650,3,true,2);
		maquina.asignarLinea(productoValido3, 4, 3);
	
	}
	
	@Test  (expected=IndexOutOfBoundsException.class)
	public void testAsignarLineaIncorrectoNumLineaMenorAVL() {
		VendingMachine maquina = new VendingMachine(650,3,true,2);
		maquina.asignarLinea(productoValido3, 4, -1);
	}
	
	@Test  (expected=IndexOutOfBoundsException.class)
	public void testAsignarLineaIncorrectoNumLineaMenor() {
		VendingMachine maquina = new VendingMachine(650,3,true,2);
		maquina.asignarLinea(productoValido3, 4,-51);
	}
	
	@Test  (expected=IllegalStateException.class)
	public void testAsignarLineaIncorrectoLineaYaAsignada() {
		VendingMachine maquina = new VendingMachine(650,3,true,2);
		maquina.asignarLinea(productoValido3, 4, 1);
		maquina.asignarLinea(productoValido2, 4, 1);
	}
	
	@Test  (expected=IllegalArgumentException.class)
	public void testAsignarLineaIncorrectoCantidad() {
		VendingMachine maquina = new VendingMachine(650,3,true,2);
		maquina.asignarLinea(productoValido1, -2, 2);
	}
	
	@Test  (expected=IllegalArgumentException.class)
	public void testAsignarLineaIncorrectoCantidadAVL() {
		VendingMachine maquina = new VendingMachine(650,3,true,2);
		maquina.asignarLinea(productoValido1, -1, 2);
	}
	
	@Test  (expected=IllegalStateException.class)
	public void testAsignarLineaIncorrectoMaquinaNoOperativa() {
		VendingMachine maquina = new VendingMachine(650,3,true,2);
		maquina.fueraDeServicio();
		maquina.asignarLinea(productoValido2, 1, 1);
	}

//public void reemplazarLinea(LineaMaquina linea, int numLinea)
	@Test 
	public void testReemplazarLineaCorrectoAVLInicioEstandoNull() {
		VendingMachine maquina = new VendingMachine(650,3,true,2);	
		maquina.reemplazarLinea(productoValido1, 5, 0);
		assertSame(productoValido1, maquina.getLineas()[0]);
		assertEquals(5, maquina.getCantidades()[0]);
	}

	@Test 
	public void testReemplazarLineaCorrectoAVLInicioEstandoOcupada() {
		VendingMachine maquina = new VendingMachine(650,3,true,2);
		maquina.asignarLinea(productoValido2, 4, 0);
		maquina.reemplazarLinea(productoValido1, 5, 0);
		assertEquals(productoValido1, maquina.getLineas()[0]);
		assertEquals(5, maquina.getCantidades()[0]);
	}

	@Test 
	public void testReemplazarLineaCorrectoAVLFinalEstandoNull() {
		VendingMachine maquina = new VendingMachine(650,3,true,2);
		maquina.reemplazarLinea(productoValido1, 5, 2);
		assertEquals(productoValido1, maquina.getLineas()[2]);
		assertEquals(5, maquina.getCantidades()[2]);
	}
	
	@Test 
	public void testReemplazarLineaCorrectoAVLFinalEstandoOcupada() {
		VendingMachine maquina = new VendingMachine(650,3,true,2);
		maquina.asignarLinea(productoValido2, 4, 2);
		maquina.reemplazarLinea(productoValido1, 5, 2);
		assertEquals(productoValido1, maquina.getLineas()[2]);
		assertEquals(5, maquina.getCantidades()[2]);
	}
	
	@Test 
	public void testReemplazarLineaCorrectoEstandoNull() {
		VendingMachine maquina = new VendingMachine(650,3,true,2);
		maquina.reemplazarLinea(productoValido1, 5, 1);
		assertEquals(productoValido1, maquina.getLineas()[1]);
		assertEquals(5, maquina.getCantidades()[1]);
		}
	
	@Test 
	public void testReemplazarLineaCorrectoEstandoOcupada() {
		VendingMachine maquina = new VendingMachine(650,3,true,2);
		maquina.asignarLinea(productoValido2, 4,1);
		maquina.reemplazarLinea(productoValido1, 5, 1);
		assertEquals(productoValido1, maquina.getLineas()[1]);
		assertEquals(5, maquina.getCantidades()[1]);
	}
	
	@Test (expected=NullPointerException.class)
	public void testReemplazarLineaIncorrectoLineaNull () {
		VendingMachine maquina = new VendingMachine(650,3,true,2);
		maquina.reemplazarLinea(null, 5, 1);
	}
	@Test  (expected=IndexOutOfBoundsException.class)
	public void testReemplazarLineaIncorrectoNumLineaMayor() {
		VendingMachine maquina = new VendingMachine(650,3,true,2);
		maquina.reemplazarLinea(productoValido1, 5, 6);
	
	}
	@Test  (expected=IndexOutOfBoundsException.class)
	public void testReemplazarLineaIncorrectoNumLineaMayorAVL() {
		VendingMachine maquina = new VendingMachine(650,3,true,2);
		maquina.reemplazarLinea(productoValido1, 5, 3);
	
	}
	
	@Test  (expected=IndexOutOfBoundsException.class)
	public void testReemplazarLineaIncorrectoNumLineaMenorAVL() {
		VendingMachine maquina = new VendingMachine(650,3,true,2);
		maquina.reemplazarLinea(productoValido1, 5, -1);
	}
	
	@Test  (expected=IndexOutOfBoundsException.class)
	public void testReemplazarLineaIncorrectoNumLineaMenor() {
		VendingMachine maquina = new VendingMachine(650,3,true,2);
		maquina.reemplazarLinea(productoValido1, 5, -500);
	}
	
	@Test  (expected=IllegalArgumentException.class)
	public void testReemplazarLineaIncorrectoCantidad() {
		VendingMachine maquina = new VendingMachine(650,3,true,2);
		maquina.reemplazarLinea(productoValido1, -2, 2);
	}
	
	@Test  (expected=IllegalArgumentException.class)
	public void testReemplazarLineaIncorrectoCantidadAVL() {
		VendingMachine maquina = new VendingMachine(650,3,true,2);
		maquina.reemplazarLinea(productoValido1, -1, 1);
	}
	
	@Test  (expected=IllegalStateException.class)
	public void testReemplazarLineaIncorrectoMaquinaNoOperativa() {
		VendingMachine maquina = new VendingMachine(650,3,true,2);
		maquina.asignarLinea(productoValido2, 4,1);
		maquina.fueraDeServicio();
		maquina.reemplazarLinea(productoValido1, 5, 1);
	}

//public void cambiarCantidadSuma(int numLinea, int cambio) 
	@Test 
	public void testCambiarSumaCorrecto() {
		VendingMachine maquina = new VendingMachine(650,3,true,2);
		maquina.asignarLinea(productoValido1, 8, 0);
		maquina.asignarLinea(productoValido2, 6, 1);
		maquina.asignarLinea(productoValido3, 4, 2);
		maquina.cambiarCantidadSuma(0,2);
		assertEquals(10,maquina.getCantidades()[0]);
	}
	@Test 
	public void testCambiarSumaCorrectoNegativo() {
		VendingMachine maquina = new VendingMachine(650,3,true,2);
		maquina.asignarLinea(productoValido1, 8, 0);
		maquina.asignarLinea(productoValido2, 6, 1);
		maquina.asignarLinea(productoValido3, 4, 2);
		maquina.cambiarCantidadSuma(0,-5);
		assertEquals(3,maquina.getCantidades()[0]);
	}
	@Test 
	public void testCambiarSumaCorrectoAVL() {
		VendingMachine maquina = new VendingMachine(650,3,true,2);
		maquina.asignarLinea(productoValido1, 8, 0);
		maquina.asignarLinea(productoValido2, 6, 1);
		maquina.asignarLinea(productoValido3, 4, 2);
		maquina.cambiarCantidadSuma(0,0);
		assertEquals(8,maquina.getCantidades()[0]);
	}
	
	@Test (expected=IllegalStateException.class)
	public void testCambiarSumaMaquinaFueraServicio() {
		VendingMachine maquina = new VendingMachine(650,3,true,2);
		maquina.asignarLinea(productoValido1, 8, 0);
		maquina.asignarLinea(productoValido2, 6, 1);
		maquina.asignarLinea(productoValido3, 4, 2);
		maquina.fueraDeServicio();
		maquina.cambiarCantidadSuma(0,2);
	}
	
	@Test (expected=IndexOutOfBoundsException.class)
	public void testCambiarSumaLineaNoExistente() {
		VendingMachine maquina = new VendingMachine(650,3,true,2);
		maquina.asignarLinea(productoValido1, 8, 0);
		maquina.asignarLinea(productoValido2, 6, 1);
		maquina.asignarLinea(productoValido3, 4, 2);
		maquina.cambiarCantidadSuma(5,2);
	}
	@Test (expected=IndexOutOfBoundsException.class)
	public void testCambiarSumaLineaNoExistenteAVL() {
		VendingMachine maquina = new VendingMachine(650,3,true,2);
		maquina.asignarLinea(productoValido1, 8, 0);
		maquina.asignarLinea(productoValido2, 6, 1);
		maquina.asignarLinea(productoValido3, 4, 2);
		maquina.cambiarCantidadSuma(3,2);
	}
	@Test (expected=IndexOutOfBoundsException.class)
	public void testCambiarSumaLineaNoExistenteAVLNegativa() {
		VendingMachine maquina = new VendingMachine(650,3,true,2);
		maquina.asignarLinea(productoValido1, 8, 0);
		maquina.asignarLinea(productoValido2, 6, 1);
		maquina.asignarLinea(productoValido3, 4, 2);
		maquina.cambiarCantidadSuma(-1,2);
	}
	@Test (expected=IndexOutOfBoundsException.class)
	public void testCambiarSumaLineaNoExistentNegativa() {
		VendingMachine maquina = new VendingMachine(650,3,true,2);
		maquina.asignarLinea(productoValido1, 8, 0);
		maquina.asignarLinea(productoValido2, 6, 1);
		maquina.asignarLinea(productoValido3, 4, 2);
		maquina.cambiarCantidadSuma(-150,2);
	}
	@Test (expected=IllegalStateException.class)
	public void testCambiarSumaLineaSinAisgnar() {
		VendingMachine maquina = new VendingMachine(650,3,true,2);
		maquina.cambiarCantidadSuma(0,5);
	}
	
	@Test (expected=IllegalArgumentException.class)
	public void testCambiarSumaAVLNegativoIncorrecto() {
		VendingMachine maquina = new VendingMachine(650,3,true,2);
		maquina.asignarLinea(productoValido1, 8, 0);
		maquina.asignarLinea(productoValido2, 6, 1);
		maquina.asignarLinea(productoValido3, 4, 2);
		maquina.cambiarCantidadSuma(1, -7);
	}
	@Test (expected=IllegalArgumentException.class)
	public void testCambiarSumaNegativoIncorrecto() {
		VendingMachine maquina = new VendingMachine(650,3,true,2);
		maquina.asignarLinea(productoValido1, 8, 0);
		maquina.asignarLinea(productoValido2, 6, 1);
		maquina.asignarLinea(productoValido3, 4, 2);
		maquina.cambiarCantidadSuma(1, -50);
	}
	
//public void comprarItem(TarjetaMonedero tarjeta, int linea)
	@Test
	public void testTarjetasMonederoCorrectoAVLSaldoYCantidad() {
		VendingMachine maquina = new VendingMachine(650,3,true,2);
		maquina.asignarLinea(productoValido1, 1, 0);
		maquina.asignarLinea(productoValido2, 1, 1);
		maquina.asignarLinea(productoValido3, 1, 2);
		double saldo = productoValido1.getPrecio();
		TarjetaMonedero tarjeta = new TarjetaMonedero("A156Bv09_1zXo894", saldo);
		maquina.comprarItem(tarjeta, 0);
		
		assertEquals(tarjeta.getSaldoActual(),0,0.01);	
		assertEquals(0, maquina.getCantidades()[0]);	
	}

	@Test
	public void testTarjetasMonederoCorrecto() {
		VendingMachine maquina = new VendingMachine(650,3,true,2);
		int cantidadOriginal = 8;
		maquina.asignarLinea(productoValido1, 8, 0);
		maquina.asignarLinea(productoValido2, 6, 1);
		maquina.asignarLinea(productoValido3, 4, 2);
		double precio = productoValido1.getPrecio();
		TarjetaMonedero tarjeta = new TarjetaMonedero("A156Bv09_1zXo894", 50000);
		maquina.comprarItem(tarjeta, 0);
		
		assertEquals(tarjeta.getSaldoActual(), 50000-precio, 0.01);
		assertEquals(cantidadOriginal-1, maquina.getCantidades()[0]);
	}
	
	@Test (expected=IllegalArgumentException.class)
	public void testTarjetasMonederoIncorrectoLineaNoAsignada() {
		VendingMachine maquina = new VendingMachine(650,3,true,2);
		maquina.asignarLinea(productoValido2, 6, 1);
		maquina.asignarLinea(productoValido3, 4, 2);
		TarjetaMonedero tarjeta = new TarjetaMonedero("A156Bv09_1zXo894", 50000);
		maquina.comprarItem(tarjeta, 0);	
	}
	
	@Test (expected=IllegalArgumentException.class)
	public void testTarjetasMonederoIncorrectoCantidadInsuficiente() {
		VendingMachine maquina = new VendingMachine(650,3,true,2);
		maquina.asignarLinea(productoValido1, 8, 0);
		maquina.asignarLinea(productoValido2, 6, 1);
		maquina.asignarLinea(productoValido3, 0, 2);
		TarjetaMonedero tarjeta = new TarjetaMonedero("A156Bv09_1zXo894", 50000);
		maquina.comprarItem(tarjeta, 2);	
	}
	
	@Test (expected=IllegalArgumentException.class)
	public void testTarjetasMonederoIncorrectoTarjetaSinSaldo() {
		VendingMachine maquina = new VendingMachine(650,3,true,2);
		maquina.asignarLinea(productoValido1, 8, 0);
		maquina.asignarLinea(productoValido2, 6, 1);
		maquina.asignarLinea(productoValido3, 4, 2);
		TarjetaMonedero tarjeta = new TarjetaMonedero("A156Bv09_1zXo894", 0);
		maquina.comprarItem(tarjeta, 0);	
	}
	
	@Test (expected=IllegalArgumentException.class)
	public void testTarjetasMonederoIncorrectoSaldoInsuficienteAVL() {
		VendingMachine maquina = new VendingMachine(650,3,true,2);
		maquina.asignarLinea(productoValido1, 8, 0);
		maquina.asignarLinea(productoValido2, 6, 1);
		maquina.asignarLinea(productoValido3, 4, 2);
		double precio = productoValido1.getPrecio();
		TarjetaMonedero tarjeta = new TarjetaMonedero("A156Bv09_1zXo894", precio-0.01);
		maquina.comprarItem(tarjeta, 0);	
	}
	
	@Test (expected=NullPointerException.class)
	public void testTarjetasMonederoIncorrectoTarjetaNull() {
		VendingMachine maquina = new VendingMachine(650,3,true,2);
		maquina.asignarLinea(productoValido1, 8, 0);
		maquina.asignarLinea(productoValido2, 6, 1);
		maquina.asignarLinea(productoValido3, 4, 2);
		TarjetaMonedero tarjeta = null;
		maquina.comprarItem(tarjeta, 0);	
	}
	
	@Test (expected=IllegalStateException.class)
	public void testTarjetasMonederoIncorrectoMaquinaFueraDeServicio() {
		VendingMachine maquina = new VendingMachine(650,3,true,2);
		maquina.fueraDeServicio();
		TarjetaMonedero tarjeta = new TarjetaMonedero("A156Bv09_1zXo894", 0.01);
		maquina.comprarItem(tarjeta, 0);	
	
	}
	
//public boolean equals(Object obj)
	@Test
	public void testEqualsMismaMaquinaCorrecto() {
		VendingMachine original = new VendingMachine(650,3,true,2);
		original.asignarLinea(productoValido1, 8, 0);
		original.asignarLinea(productoValido2, 6, 1);
		original.asignarLinea(productoValido3, 4, 2);
		VendingMachine mismaMaquina = original;
		
		assertTrue(original.equals(mismaMaquina));		
	}
	
	@Test
	public void testEqualsCopiaMaquinaCorrecto() {
		VendingMachine original = new VendingMachine(650,3,true,2);
		original.asignarLinea(productoValido1, 8, 0);
		original.asignarLinea(productoValido2, 6, 1);
		original.asignarLinea(productoValido3, 4, 2);
		VendingMachine copia = original.clonar();
		
		assertTrue(original.equals(copia));
	}
	
	@Test
	public void testEqualsConNull() {
		VendingMachine original = new VendingMachine(650,3,true,2);
		
		assertFalse(original.equals(null));
	}
	
	@Test
	public void testEqualsIdentificadorDistinto() {
		VendingMachine original = new VendingMachine(650,3,true,2);
		VendingMachine otra = new VendingMachine(7500,3,true,2);
		
		assertFalse(original.equals(otra));
	}
	
	@Test
	public void testEqualsEstadoDistinto() {
		VendingMachine original = new VendingMachine(650,3,true,2);
		VendingMachine otra = new VendingMachine(650,3,false,2);
		
		assertFalse(original.equals(otra));
	}
	
	@Test
	public void testEqualsNumeroFilasDistinto() {
		VendingMachine original = new VendingMachine(650,3,true,2);
		VendingMachine otra = new VendingMachine(650,3,true,3);
		
		assertFalse(original.equals(otra));
	}
	
	@Test
	public void testEqualsNumeroLineasDistinto() {
		VendingMachine original = new VendingMachine(650,3,true,2);
		VendingMachine otra = new VendingMachine(650,6,true,2);
		
		assertFalse(original.equals(otra));
	}
	
	@Test
	public void testEqualsNumeroLineasIgualDistintasAsignaciones() {
		VendingMachine original = new VendingMachine(650,3,true,2);
		original.asignarLinea(productoValido1, 8, 0);
		VendingMachine otra = new VendingMachine(650,6,true,2);
		
		assertFalse(original.equals(otra));
	}
	
	@Test
	public void testEqualsNumeroLineasIgualDistintosProductos() {
		VendingMachine original = new VendingMachine(650,3,true,2);
		original.asignarLinea(productoValido1, 8, 0);
		VendingMachine otra = new VendingMachine(650,6,true,2);
		otra.asignarLinea(productoValido2, 8, 0);
		
		assertFalse(original.equals(otra));
	}
	
	@Test
	public void testEqualsNumeroLineasIgualDistintasCantidades() {
		VendingMachine original = new VendingMachine(650,3,true,2);
		original.asignarLinea(productoValido1, 8, 0);
		VendingMachine otra = new VendingMachine(650,6,true,2);
		otra.asignarLinea(productoValido1, 7, 0);
		
		assertFalse(original.equals(otra));
	}
	
	@Test
	public void testEqualsNumeroLineasIgualDistintasColocaciones() {
		VendingMachine original = new VendingMachine(650,3,true,2);
		original.asignarLinea(productoValido1, 8, 0);
		VendingMachine otra = new VendingMachine(650,6,true,2);
		otra.asignarLinea(productoValido1, 8, 1);
		
		assertFalse(original.equals(otra));
	}
	
	//Test ESPECIALES PARA EQUALS()
	@SuppressWarnings("unlikely-arg-type")
	@Test
	public void testEqualsOtraClase() {
		VendingMachine original = new VendingMachine(650,3,true,2);
		assertFalse(original.equals(new String("jaaj")));
	}
	
	@Test
	public void testEqualsReflexividad() {
		VendingMachine original = new VendingMachine(650,3,true,2);
		assertTrue(original.equals(original));
	}
	@Test
	public void testEqualsSimetria() {
		VendingMachine original = new VendingMachine(650,3,true,2);
		VendingMachine copia = original.clonar();
		assertTrue(original.equals(copia));
		assertTrue(copia.equals(original));
	}
	@Test
	public void testEqualsTransitividad() {
		VendingMachine original = new VendingMachine(650,3,true,2);
		VendingMachine copia = original.clonar();
		VendingMachine otraCopia = original.clonar();
		assertTrue(original.equals(copia));
		assertTrue(copia.equals(otraCopia));
		assertTrue(original.equals(otraCopia));
	}
	
//public int hashCode()
	@Test
	public void testHashCodeMismaMaquinaCorrecto() {
		VendingMachine original = new VendingMachine(650,3,true,2);
		original.asignarLinea(productoValido1, 8, 0);
		original.asignarLinea(productoValido2, 6, 1);
		VendingMachine mismaMaquina = original;
		
		assertEquals(original.hashCode(), mismaMaquina.hashCode());		
	}
	
	@Test
	public void testHashCodeCopiaMaquinaCorrecto() {
		VendingMachine original = new VendingMachine(650,3,true,2);
		original.asignarLinea(productoValido1, 8, 0);
		original.asignarLinea(productoValido2, 6, 1);
		VendingMachine copia = original.clonar();
		
		assertEquals(original.hashCode(), copia.hashCode());	
	}
	
	/* Técnicamente no es necesario que objetos distintos tengan hash codes distintos, pero nosotros
	 * lo comprobamos como garantía de un buen rendimiento en las hash tables (ver documentación de hashCode()
	 * en Object)
	 */

	@Test
	public void testHashCodeIdentificadorDistinto() {
		VendingMachine original = new VendingMachine(650,3,true,2);
		VendingMachine otra = new VendingMachine(7500,3,true,2);
		
		assertNotEquals(original.hashCode(), otra.hashCode());
	}
	
	@Test
	public void testHashCodeEstadoDistinto() {
		VendingMachine original = new VendingMachine(650,3,true,2);
		VendingMachine otra = new VendingMachine(650,3,false,2);
		
		assertNotEquals(original.hashCode(), otra.hashCode());
	}
	
	@Test
	public void testHashCodeNumeroLineasDistinto() {
		VendingMachine original = new VendingMachine(650,3,true,2);
		VendingMachine otra = new VendingMachine(650,3,true,3);
		
		assertNotEquals(original.hashCode(), otra.hashCode());
	}
	
	@Test
	public void testHashCodeNumeroLineasIgualDistintasAsignaciones() {
		VendingMachine original = new VendingMachine(650,3,true,2);
		original.asignarLinea(productoValido1, 8, 0);
		VendingMachine otra = new VendingMachine(650,6,true,2);
		
		assertNotEquals(original.hashCode(), otra.hashCode());
	}
	
	@Test
	public void testHashCodeNumeroLineasIgualDistintosProductos() {
		VendingMachine original = new VendingMachine(650,3,true,2);
		original.asignarLinea(productoValido1, 8, 0);
		VendingMachine otra = new VendingMachine(650,6,true,2);
		otra.asignarLinea(productoValido2, 8, 0);
		
		assertNotEquals(original.hashCode(), otra.hashCode());
	}
	
	@Test
	public void testHashCodeNumeroLineasIgualDistintasCantidades() {
		VendingMachine original = new VendingMachine(650,3,true,2);
		original.asignarLinea(productoValido1, 8, 0);
		VendingMachine otra = new VendingMachine(650,6,true,2);
		otra.asignarLinea(productoValido1, 7, 0);
		
		assertNotEquals(original.hashCode(), otra.hashCode());
	}
	
	@Test
	public void testHashCodeNumeroLineasIgualDistintasColocaciones() {
		VendingMachine original = new VendingMachine(650,3,true,2);
		original.asignarLinea(productoValido1, 8, 0);
		VendingMachine otra = new VendingMachine(650,6,true,2);
		otra.asignarLinea(productoValido1, 8, 1);
		
		assertNotEquals(original.hashCode(), otra.hashCode());
	}
	
//TEST ESPECIAL PARA HASHCODE()
	@Test
	public void testHashCodeReflexividadConsistencia() {
		VendingMachine original = new VendingMachine(650,3,true,2);
		assertEquals(original.hashCode(), original.hashCode());
	}

}

