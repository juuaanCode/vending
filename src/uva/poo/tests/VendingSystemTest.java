package uva.poo.tests;

import static org.junit.Assert.*;

import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;

import uva.poo.vending.Product;
import uva.poo.vending.VendingCity;
import uva.poo.vending.VendingSystem;
import uva.poo.vending.VendingMachine;

/**
 * Clase para hacer pruebas unitarias de caja negra de la clase VendingSystem.
 * @author juagonz0
 * @author sanbarb
 */
public class VendingSystemTest {
	private VendingMachine maquina1,maquina2,maquina3;
	private Product productoValido1, productoValido2, productoValido3;
	private VendingCity sede33, sede47, sedeNull;
	@Before
	public void setUp() throws Exception {	
        productoValido1 = new Product(5, LocalDate.of(2021,11,18), "Patatas", "123456789012");
        productoValido2 = new Product(4, LocalDate.of(2021,10,20), "Palomitas", "109876543212");
        productoValido3 = new Product(7, LocalDate.of(2021,10,10), "Aceitunas", "654017891238");

		maquina1 = new VendingMachine(650,3,true,2);
		maquina2 = new VendingMachine(50,3,true,2);
		maquina3 = new VendingMachine(50,3,true,2);
		//Llenamos la máquina 1
		maquina1.asignarLinea(productoValido1, 5, 0);
		maquina1.asignarLinea(productoValido2, 4, 1);
		maquina1.asignarLinea(productoValido3, 8, 2);
		//Y parcialmente la máquina 2
		maquina2.asignarLinea(productoValido1, 4, 0);
		maquina2.asignarLinea(productoValido2, 5, 1);
		sede47 = new VendingCity(47);
		sede33 = new VendingCity(33);
		sedeNull =null;
	}
// public VendingSystem() 
	@Test 
	public void testConstructor() {
		VendingSystem conjunto = new VendingSystem();
		assertNotNull(conjunto);
		assertEquals(0, conjunto.getNumeroProvincias());
	}
	
// public VendingSystem clonar()
	@Test
	public void testClonarCorrecto() {
		VendingSystem sistema = new VendingSystem();
		sistema.anadirSede(sede47);
		sistema.anadirSede(sede33);
		
		VendingSystem copia = sistema.clonar();
		
		assertNotNull(copia);
		assertEquals(sistema, copia); //Deben ser iguales, pero tener copia en profundidad de todo
		assertNotSame(sistema, copia);
		for (int numSede = 0; numSede < sistema.getNumeroProvincias(); numSede++) {
			if (sistema.getSedes()[numSede] == null || copia.getSedes()[numSede] == null) {
				assertEquals(sistema.getSedes()[numSede], copia.getSedes()[numSede]);
			} else {
				for (int numMaquina = 0; numMaquina < sistema.getSedes()[numSede].getListado().size(); numMaquina++) {
					VendingMachine maquinaOriginal = sistema.getSedes()[numSede].getListado().get(numMaquina);
					VendingMachine maquinaCopia = copia.getSedes()[numSede].getListado().get(numMaquina);
					assertEquals(maquinaOriginal, maquinaCopia);
					assertNotSame(maquinaOriginal, maquinaCopia);
					
					//Por cada par de máquinas hay que probar que los productos son iguales, pero no las mismas
					for (int numLinea = 0; numLinea < maquinaOriginal.getLineas().length; numLinea++) {
						if (maquinaOriginal.getLineas()[numLinea] == null || maquinaCopia.getLineas()[numLinea] == null) {
							assertEquals(maquinaOriginal.getLineas()[numLinea], maquinaCopia.getLineas()[numLinea]);
						} else {
							assertNotSame(maquinaOriginal.getLineas()[numLinea], maquinaCopia.getLineas()[numLinea]);
							//Con productos iguales, pero no los mismos
						}
					}
				}
			}
		}
		
		
		
	}
	
// public void anadirSede (VendingCity sede) 
	@Test
	public void testAnadirSedeCorrecto() {
		VendingSystem conjunto = new VendingSystem();
		conjunto.anadirSede(sede33);
		assertEquals(sede33,conjunto.getSedes()[32]);
	}
	
	@Test
	public void testAnadirSedeCorrectoAVLSuperior() {
		VendingSystem conjunto = new VendingSystem();
		VendingCity sede = new VendingCity(52);
		conjunto.anadirSede(sede);
		assertEquals(sede,conjunto.getSedes()[51]);
	}
	
	@Test
	public void testAnadirSedeCorrectoAVLInferior() {
		VendingSystem conjunto = new VendingSystem();
		VendingCity sede = new VendingCity(1);
		conjunto.anadirSede(sede);
		assertEquals(sede,conjunto.getSedes()[0]);
	}
	
	@Test (expected=IllegalArgumentException.class)
	public void testAnadirSedeYaExistente() {
		VendingSystem conjunto = new VendingSystem();
		conjunto.anadirSede(sede47);
		VendingCity nuevasede = new VendingCity(47);
		conjunto.anadirSede(nuevasede);
	}
	
	@Test (expected=NullPointerException.class)
	public void testAnadirSedeNull() {
		VendingSystem conjunto = new VendingSystem();
		conjunto.anadirSede(sedeNull);
	}
	
// public void eliminarSede (int codigoProvincia) 
	@Test 
	public void testEliminarSedeCorrecto() {
		VendingSystem conjunto = new VendingSystem();
		conjunto.anadirSede(sede47);
		conjunto.eliminarSede(47);
		assertEquals(null, conjunto.getSedes()[46]);
	}
	
	@Test 
	public void testEliminarSedeCorrectoAVLSuperior() {
		VendingSystem conjunto = new VendingSystem();
		VendingCity sede = new VendingCity(52);
		conjunto.anadirSede(sede);
		conjunto.eliminarSede(52);
		assertEquals(null, conjunto.getSedes()[51]);
	}
	
	@Test 
	public void testEliminarSedeCorrectoAVLInferior() {
		VendingSystem conjunto = new VendingSystem();
		VendingCity sede = new VendingCity(1);
		conjunto.anadirSede(sede);
		conjunto.eliminarSede(1);
		assertEquals(null, conjunto.getSedes()[0]);
	}
	
	
	@Test (expected=IllegalArgumentException.class)
	public void testEliminarSedeCodigoIncorrectoAVLSuperior() {
		VendingSystem conjunto = new VendingSystem();
		conjunto.eliminarSede(53);
	}
	@Test (expected=IllegalArgumentException.class)
	public void testEliminarSedeCodigoIncorrectoSuperior() {
		VendingSystem conjunto = new VendingSystem();
		conjunto.eliminarSede(100);
	}
	@Test (expected=IllegalArgumentException.class)
	public void testEliminarSedeCodigoIncorrectoAVLInferior() {
		VendingSystem conjunto = new VendingSystem();
		conjunto.eliminarSede(0);
	}
	@Test (expected=IllegalArgumentException.class)
	public void testEliminarSedeCodigoIncorrectoInferior() {
		VendingSystem conjunto = new VendingSystem();
		conjunto.eliminarSede(-20);
	}
	@Test (expected=IllegalArgumentException.class)
	public void testEliminarSedeNoExistenteIncorrecto() {
		VendingSystem conjunto = new VendingSystem();
		conjunto.eliminarSede(47);
	}
	
// public int getNumeroMaquinas(int codigoProvincia) 
	@Test
	public void testNumeroMaquinasCorrectoAVLmin() {
		VendingSystem conjunto = new VendingSystem();
		conjunto.anadirSede(sede47);
		assertEquals(0,conjunto.getNumeroMaquinas(47));
	}
	@Test
	public void testNumeroMaquinasCorrecto() {
		VendingSystem conjunto = new VendingSystem();
		conjunto.anadirSede(sede47);
		sede47.anadirMaquina(maquina1);
		sede47.anadirMaquina(maquina2);
		assertEquals(2,conjunto.getNumeroMaquinas(47));
	}
	@Test (expected=IllegalArgumentException.class)
	public void testnumeroMaquinasSedeCodigoAVLmaxIncorrecto() {
		VendingSystem conjunto = new VendingSystem();
		conjunto.getNumeroMaquinas(53);
	}
	@Test (expected=IllegalArgumentException.class)
	public void testnumeroMaquinasCodigoMAXIncorrecto() {
		VendingSystem conjunto = new VendingSystem();
		conjunto.eliminarSede(100);
	}
	@Test (expected=IllegalArgumentException.class)
	public void testnumeroMaquinasCodigoAVLminIncorrecto() {
		VendingSystem conjunto = new VendingSystem();
		conjunto.getNumeroMaquinas(0);
	}
	@Test (expected=IllegalArgumentException.class)
	public void testnumeroMaquinasCodigoMinIncorrecto() {
		VendingSystem conjunto = new VendingSystem();
		conjunto.getNumeroMaquinas(-20);
	}
	@Test (expected=IllegalArgumentException.class)
	public void testnumeroMaquinasCodigoNoExistenteIncorrecto() {
		VendingSystem conjunto = new VendingSystem();
		conjunto.getNumeroMaquinas(47);
	}

// public List<VendingMachine> getListado(int codigoProvincia) 
	@Test 
	public void testGetListadoCorrecto() {
		VendingSystem conjunto = new VendingSystem();
		conjunto.anadirSede(sede47);
		sede47.anadirMaquina(maquina1);
		sede47.anadirMaquina(maquina2);
		assertEquals(2,conjunto.getListadoMaquinas(47).size());
		for (int i =0;i <sede47.getListado().size(); i++) {
			assertEquals(sede47.getListado().get(i),conjunto.getListadoMaquinas(47).get(i));
		}
	}
	@Test 
	public void testGetListadoCorrectoVacio() {
		VendingSystem conjunto = new VendingSystem();
		conjunto.anadirSede(sede47);
		assertEquals(0, conjunto.getListadoMaquinas(47).size());
	}
	@Test (expected=IllegalArgumentException.class)
	public void testgetListadoSedeCodigoAVLmaxIncorrecto() {
		VendingSystem conjunto = new VendingSystem();
		conjunto.getListadoMaquinas(53);
	}
	@Test (expected=IllegalArgumentException.class)
	public void testgetListadoCodigoMAXIncorrecto() {
		VendingSystem conjunto = new VendingSystem();
		conjunto.getListadoMaquinas(100);
	}
	@Test (expected=IllegalArgumentException.class)
	public void testgetListadoCodigoAVLminIncorrecto() {
		VendingSystem conjunto = new VendingSystem();
		conjunto.getListadoMaquinas(0);
	}
	@Test (expected=IllegalArgumentException.class)
	public void testgetListadoCodigoMinIncorrecto() {
		VendingSystem conjunto = new VendingSystem();
		conjunto.getListadoMaquinas(-20);
	}
	@Test (expected=IllegalArgumentException.class)
	public void testgetListadoCodigoNoExistenteIncorrecto() {
		VendingSystem conjunto = new VendingSystem();
		conjunto.getListadoMaquinas(47);
	}

// public int numeroProvincias () 
	@Test 
	public void testNumeroPorovincias(){
		VendingSystem conjunto = new VendingSystem();
		conjunto.anadirSede(sede47);
		conjunto.anadirSede(sede33);
		assertEquals(2,conjunto.getNumeroProvincias());
	}
	@Test 
	public void testNumeroPorovinciasAVL(){
		VendingSystem conjunto = new VendingSystem();
		assertEquals(0,conjunto.getNumeroProvincias());
	}

// public List<String> listarProvinciasConSede()
	@Test 
	public void testListarProvincias() {
		VendingSystem conjunto = new VendingSystem();
		conjunto.anadirSede(sede47);
		conjunto.anadirSede(sede33);
		assertEquals(2,conjunto.listarNombresProvinciasConSede().size());
		assertTrue(conjunto.listarNombresProvinciasConSede().contains(sede47.getNombreProvincia()));
		assertTrue(conjunto.listarNombresProvinciasConSede().contains(sede33.getNombreProvincia()));
	}
	
	@Test 
	public void testListarProvinciasAVL() {
		VendingSystem conjunto = new VendingSystem();
		assertEquals(0,conjunto.listarNombresProvinciasConSede().size());
	}
	
//public List<Integer> listarCantidadMaquinasPorSede()
	@Test
	public void testListarCantidadMaquinasPorSede() {
		VendingSystem conjunto = new VendingSystem();
		conjunto.anadirSede(sede47);
		conjunto.anadirSede(sede33);
		sede47.anadirMaquina(maquina1);
		sede47.anadirMaquina(maquina2);
		sede33.anadirMaquina(maquina3);
		for (int i = 0; i<52; i++) {
			if (i == 46) {
				assertEquals(new Integer(2), conjunto.listarCantidadMaquinasPorSede().get(i));
			} else if (i == 32) {
				assertEquals(new Integer(1), conjunto.listarCantidadMaquinasPorSede().get(i));
			} else {
				assertEquals(new Integer(0), conjunto.listarCantidadMaquinasPorSede().get(i));
			}
		}
	}
	@Test
	public void testListarCantidadMaquinasPorSedeAVL() {
		VendingSystem conjunto = new VendingSystem();
		conjunto.anadirSede(sede47);
		conjunto.anadirSede(sede33);
		for (int i = 0; i<52; i++) {
			assertEquals(new Integer(0), conjunto.listarCantidadMaquinasPorSede().get(i));
		}
	}
	
//public boolean equals(Object obj)
	@Test
	public void testEqualsMismoSistemaCorrecto() {
		VendingSystem original = new VendingSystem();
		original.anadirSede(sede47);
		original.anadirSede(sede33);
		VendingSystem mismoSistema = original;
		
		assertTrue(original.equals(mismoSistema));		
	}
	
	@Test
	public void testEqualsCopiaSistemaCorrecto() {
		VendingSystem original = new VendingSystem();
		original.anadirSede(sede47);
		original.anadirSede(sede33);
		VendingSystem copia = original.clonar();
		assertTrue(original.equals(copia));
	}
	
	@Test
	public void testEqualsConNull() {
		VendingSystem original = new VendingSystem();
		original.anadirSede(sede47);
		assertFalse(original.equals(null));
	}
	
	@Test
	public void testEqualsNumeroSedesDistinto() {
		VendingSystem original = new VendingSystem();
		original.anadirSede(sede47);
		VendingSystem otro = new VendingSystem();
		otro.anadirSede(sede47);
		otro.anadirSede(sede33);
		
		assertFalse(original.equals(otro));
	}
	
	@Test
	public void testEqualsNumeroSedesIgualPeroDistintas() {
		VendingSystem original = new VendingSystem();
		original.anadirSede(sede47);
		VendingSystem otro = new VendingSystem();
		otro.anadirSede(sede33);
		
		assertFalse(original.equals(otro));
	}
	
	//Test ESPECIALES PARA EQUALS()
	@SuppressWarnings("unlikely-arg-type")
	@Test
	public void testEqualsOtraClase() {
		VendingSystem original = new VendingSystem();
		assertFalse(original.equals(new String("jaaj")));
	}
	
	@Test
	public void testEqualsReflexividad() {
		VendingSystem original = new VendingSystem();
		assertTrue(original.equals(original));
	}
	@Test
	public void testEqualsSimetria() {
		VendingSystem original = new VendingSystem();
		original.anadirSede(sede47);
		VendingSystem copia = original.clonar();
		assertTrue(original.equals(copia));
		assertTrue(copia.equals(original));
	}
	@Test
	public void testEqualsTransitividad() {
		VendingSystem original = new VendingSystem();
		original.anadirSede(sede47);
		VendingSystem copia = original.clonar();
		VendingSystem otraCopia = original.clonar();
		assertTrue(original.equals(copia));
		assertTrue(copia.equals(otraCopia));
		assertTrue(original.equals(otraCopia));
	}
	
//public int hashCode()
	@Test
	public void testHashCodeMismoSistemaCorrecto() {
		VendingSystem original = new VendingSystem();
		original.anadirSede(sede47);
		original.anadirSede(sede33);
		VendingSystem mismoSistema = original;
		
		assertEquals(original.hashCode(), mismoSistema.hashCode());		
	}
	
	@Test
	public void testHashCodeCopiaSistemaCorrecto() {
		VendingSystem original = new VendingSystem();
		original.anadirSede(sede47);
		original.anadirSede(sede33);
		VendingSystem copia = original.clonar();
		
		assertEquals(original.hashCode(), copia.hashCode());	
	}
	
	/* Técnicamente no es necesario que objetos distintos tengan hash codes distintos, pero nosotros
	 * lo comprobamos como garantía de un buen rendimiento en las hash tables (ver documentación de hashCode()
	 * en Object)
	 */
	
	@Test
	public void testHashCodeNumeroSedesDistinto() {
		VendingSystem original = new VendingSystem();
		original.anadirSede(sede47);
		VendingSystem otro = new VendingSystem();
		otro.anadirSede(sede47);
		otro.anadirSede(sede33);
		
		assertNotEquals(original.hashCode(), otro.hashCode());
	}
	
	@Test
	public void testHashCodeNumeroSedesIgualPeroDistintas() {
		VendingSystem original = new VendingSystem();
		original.anadirSede(sede47);
		VendingSystem otro = new VendingSystem();
		otro.anadirSede(sede33);
		
		assertNotEquals(original.hashCode(), otro.hashCode());
	}
	
	//TEST ESPECIAL PARA HASHCODE()
	@Test
	public void testHashCodeReflexividadConsistencia() {
		VendingSystem original = new VendingSystem();
		original.anadirSede(sede47);
		assertEquals(original.hashCode(), original.hashCode());
	}
	
}
