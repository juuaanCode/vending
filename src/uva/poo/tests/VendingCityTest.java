package uva.poo.tests;

import static org.junit.Assert.*;

import java.time.LocalDate;


import org.junit.Before;
import org.junit.Test;
import uva.poo.vending.VendingCity;
import uva.poo.vending.Product;
import uva.poo.vending.VendingMachine;


/**
 * Clase para hacer pruebas unitarias de caja negra de la clase VendingCity.
 * @author juagonz0
 * @author sanbarb
 */
public class VendingCityTest {
	private VendingMachine maquina1,maquina2,maquina3;
	private Product productoValido1, productoValido2, productoValido3; 
	
	
	
	@Before
	public void setUp() throws Exception {	
		productoValido1 = new Product(5.5, LocalDate.of(2021,11,18), "Patatas", "123456789012");
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
		}

//public VendingCity()
	@Test 
	public void testConstructorCorrecto() {
		VendingCity grupo = new VendingCity(33);
		assertEquals(33, grupo.getCodigoProvincia());
		assertEquals(0, grupo.getNumeroMaquinas());
	}
	
	@Test 
	public void testConstructorCorrectoAVLInferior() {
		VendingCity grupo = new VendingCity(1);
		assertEquals(1,grupo.getCodigoProvincia());
		assertEquals(0, grupo.getNumeroMaquinas());
	}
	
	@Test 
	public void testConstructorCorrectoAVLSuperior() {
		VendingCity grupo = new VendingCity(52);;
		assertEquals(52,grupo.getCodigoProvincia());
		assertEquals(0, grupo.getNumeroMaquinas());
	}
	
	
	@Test (expected=IllegalArgumentException.class)
	public void testConstructorIncorrectoAVLInferior() {
		VendingCity grupo = new VendingCity(0);
		assertEquals(0,grupo.getCodigoProvincia());
		assertEquals(0, grupo.getNumeroMaquinas());
	}
	
	@Test (expected=IllegalArgumentException.class)
	public void testConstructorInorrectoAVLSuperior() {
		VendingCity grupo = new VendingCity(53);
		assertEquals(53,grupo.getCodigoProvincia());
		assertEquals(0, grupo.getNumeroMaquinas());
	}
	
//public VendingCity clonar()
	@Test
	public void testClonarCorrecto() {
		VendingCity grupo = new VendingCity(33);
		grupo.anadirMaquina(maquina2);
		
		VendingCity copia = grupo.clonar();
		
		assertNotNull(copia);
		assertEquals(grupo, copia); //Deben ser iguales, pero tener copia en profundidad de todo
		assertNotSame(grupo, copia);
		for (int numMaquina = 0; numMaquina < grupo.getListado().size(); numMaquina++) {
			VendingMachine maquinaOriginal = grupo.getListado().get(numMaquina);
			VendingMachine maquinaCopia = copia.getListado().get(numMaquina);
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

	
//public void anadirMaquina(VendingMachine maquina)
	@Test 
	public void testAnadirMaquinaCorrecto() {
		VendingCity grupo = new VendingCity(33);
		grupo.anadirMaquina(maquina2);
		int id = maquina2.getIdentificador();
		assertSame(maquina2,grupo.getMaquina(id));
		assertSame(maquina2, grupo.getListado().get(0)); //Debe ponerla en el primer lugar
	}
	
	@Test 
	public void testAnadirMaquinaCiudadNoVaciaCorrecto() {
		VendingCity grupo = new VendingCity(33);
		grupo.anadirMaquina(maquina2);
		grupo.anadirMaquina(maquina1);
		assertSame(maquina1, grupo.getMaquina(maquina1.getIdentificador()));
	}
	
	@Test (expected=NullPointerException.class)
	public void testAnadirMaquinaMaquinaNull() {
		VendingCity grupo = new VendingCity(33);
		grupo.anadirMaquina(null);
	}
	
	@Test (expected=IllegalArgumentException.class)
	public void testAnadirMaquinaMismoIdentificador() {
		VendingCity grupo = new VendingCity(47);
		grupo.anadirMaquina(maquina2);
		grupo.anadirMaquina(maquina3);
	}
	
//public void eliminarMaquina(long identificador)
	@Test
	public void testEliminarMaquinaCorrecto() {
		VendingCity grupo = new VendingCity(33);
		grupo.anadirMaquina(maquina1);
		grupo.anadirMaquina(maquina2);
		long id =maquina2.getIdentificador();
		grupo.eliminarMaquina(id);
		int lon =grupo.getListado().size();
		for (int i=0;i<lon;i++) {
			assertNotEquals(maquina2,grupo.getListado().get(i));
		}
	}
	
	@Test (expected=IllegalArgumentException.class)
	public void testEliminarMaquinaIncorrecto() {
		VendingCity grupo = new VendingCity(33);
		grupo.anadirMaquina(maquina1);
		long id =maquina2.getIdentificador();
		grupo.eliminarMaquina(id);
	}
	
	@Test (expected=IllegalArgumentException.class)
	public void testEliminarMaquinaIncorrectoSistemaVacio() {
		VendingCity grupo = new VendingCity(33);
		long id =maquina2.getIdentificador();
		grupo.eliminarMaquina(id);
	}
//public List<VendingMachine> getListado() 
	@Test
	public void testGetListado() {
		VendingCity grupo = new VendingCity(47);
		grupo.anadirMaquina(maquina1);
		grupo.anadirMaquina(maquina2);
		assertEquals(2,grupo.getListado().size());
		assertSame(maquina2, grupo.getListado().get(0));
		assertSame(maquina1, grupo.getListado().get(1));
	}
	@Test
	public void testGetListadoVacioAVL() {
		VendingCity grupo = new VendingCity(47);
		assertEquals(0,grupo.getListado().size());
	}
	
// public String getNombreProvincia() - Hay que probar las 52
	
	@Test
	public void testGetNombreProvincia1() {
		VendingCity grupo = new VendingCity(1);
		assertEquals("Álava", grupo.getNombreProvincia());
	}
	
	@Test
	public void testGetNombreProvincia2() {
		VendingCity grupo = new VendingCity(2);
		assertEquals("Albacete", grupo.getNombreProvincia());
	}
	
	@Test
	public void testGetNombreProvincia3() {
		VendingCity grupo = new VendingCity(3);
		assertEquals("Alicante", grupo.getNombreProvincia());
	}
	
	@Test
	public void testGetNombreProvincia4() {
		VendingCity grupo = new VendingCity(4);
		assertEquals("Almería", grupo.getNombreProvincia());
	}
	
	@Test
	public void testGetNombreProvincia5() {
		VendingCity grupo = new VendingCity(5);
		assertEquals("Ávila", grupo.getNombreProvincia());
	}
	
	@Test
	public void testGetNombreProvincia6() {
		VendingCity grupo = new VendingCity(6);
		assertEquals("Badajoz", grupo.getNombreProvincia());
	}
	
	@Test
	public void testGetNombreProvincia7() {
		VendingCity grupo = new VendingCity(7);
		assertEquals("Baleares", grupo.getNombreProvincia());
	}
	
	@Test
	public void testGetNombreProvincia8() {
		VendingCity grupo = new VendingCity(8);
		assertEquals("Barcelona", grupo.getNombreProvincia());
	}
	
	@Test
	public void testGetNombreProvincia9() {
		VendingCity grupo = new VendingCity(9);
		assertEquals("Burgos", grupo.getNombreProvincia());
	}
	
	@Test
	public void testGetNombreProvincia10() {
		VendingCity grupo = new VendingCity(10);
		assertEquals("Cáceres", grupo.getNombreProvincia());
	}
	
	@Test
	public void testGetNombreProvincia11() {
		VendingCity grupo = new VendingCity(11);
		assertEquals("Cádiz", grupo.getNombreProvincia());
	}
	
	@Test
	public void testGetNombreProvincia12() {
		VendingCity grupo = new VendingCity(12);
		assertEquals("Castellón", grupo.getNombreProvincia());
	}
	
	@Test
	public void testGetNombreProvincia13() {
		VendingCity grupo = new VendingCity(13);
		assertEquals("Ciudad Real", grupo.getNombreProvincia());
	}
	
	@Test
	public void testGetNombreProvincia14() {
		VendingCity grupo = new VendingCity(14);
		assertEquals("Córdoba", grupo.getNombreProvincia());
	}
	
	@Test
	public void testGetNombreProvincia15() {
		VendingCity grupo = new VendingCity(15);
		assertEquals("La Coruña", grupo.getNombreProvincia());
	}
	
	@Test
	public void testGetNombreProvincia16() {
		VendingCity grupo = new VendingCity(16);
		assertEquals("Cuenca", grupo.getNombreProvincia());
	}
	
	@Test
	public void testGetNombreProvincia17() {
		VendingCity grupo = new VendingCity(17);
		assertEquals("Gerona", grupo.getNombreProvincia());
	}
	
	@Test
	public void testGetNombreProvincia18() {
		VendingCity grupo = new VendingCity(18);
		assertEquals("Granada", grupo.getNombreProvincia());
	}
	
	@Test
	public void testGetNombreProvincia19() {
		VendingCity grupo = new VendingCity(19);
		assertEquals("Guadalajara", grupo.getNombreProvincia());
	}
	
	@Test
	public void testGetNombreProvincia20() {
		VendingCity grupo = new VendingCity(20);
		assertEquals("Guipúzcoa", grupo.getNombreProvincia());
	}
	
	@Test
	public void testGetNombreProvincia21() {
		VendingCity grupo = new VendingCity(21);
		assertEquals("Huelva", grupo.getNombreProvincia());
	}
	
	@Test
	public void testGetNombreProvincia22() {
		VendingCity grupo = new VendingCity(22);
		assertEquals("Huesca", grupo.getNombreProvincia());
	}
	
	@Test
	public void testGetNombreProvincia23() {
		VendingCity grupo = new VendingCity(23);
		assertEquals("Jaén", grupo.getNombreProvincia());
	}
	
	@Test
	public void testGetNombreProvincia24() {
		VendingCity grupo = new VendingCity(24);
		assertEquals("León", grupo.getNombreProvincia());
	}
	
	@Test
	public void testGetNombreProvincia25() {
		VendingCity grupo = new VendingCity(25);
		assertEquals("Lérida", grupo.getNombreProvincia());
	}
	
	@Test
	public void testGetNombreProvincia26() {
		VendingCity grupo = new VendingCity(26);
		assertEquals("La Rioja", grupo.getNombreProvincia());
	}
	
	@Test
	public void testGetNombreProvincia27() {
		VendingCity grupo = new VendingCity(27);
		assertEquals("Lugo", grupo.getNombreProvincia());
	}
	
	@Test
	public void testGetNombreProvincia28() {
		VendingCity grupo = new VendingCity(28);
		assertEquals("Madrid", grupo.getNombreProvincia());
	}
	
	@Test
	public void testGetNombreProvincia29() {
		VendingCity grupo = new VendingCity(29);
		assertEquals("Málaga", grupo.getNombreProvincia());
	}
	
	@Test
	public void testGetNombreProvincia30() {
		VendingCity grupo = new VendingCity(30);
		assertEquals("Murcia", grupo.getNombreProvincia());
	}
	
	@Test
	public void testGetNombreProvincia31() {
		VendingCity grupo = new VendingCity(31);
		assertEquals("Navarra", grupo.getNombreProvincia());
	}
	
	@Test
	public void testGetNombreProvincia32() {
		VendingCity grupo = new VendingCity(32);
		assertEquals("Orense", grupo.getNombreProvincia());
	}
	
	@Test
	public void testGetNombreProvincia33() {
		VendingCity grupo = new VendingCity(33);
		assertEquals("Asturias", grupo.getNombreProvincia());
	}
	
	@Test
	public void testGetNombreProvincia34() {
		VendingCity grupo = new VendingCity(34);
		assertEquals("Palencia", grupo.getNombreProvincia());
	}
	
	@Test
	public void testGetNombreProvincia35() {
		VendingCity grupo = new VendingCity(35);
		assertEquals("Las Palmas", grupo.getNombreProvincia());
	}
	
	@Test
	public void testGetNombreProvincia36() {
		VendingCity grupo = new VendingCity(36);
		assertEquals("Pontevedra", grupo.getNombreProvincia());
	}
	
	@Test
	public void testGetNombreProvincia37() {
		VendingCity grupo = new VendingCity(37);
		assertEquals("Salamanca", grupo.getNombreProvincia());
	}
	
	@Test
	public void testGetNombreProvincia38() {
		VendingCity grupo = new VendingCity(38);
		assertEquals("Santa Cruz de Tenerife", grupo.getNombreProvincia());
	}
	
	@Test
	public void testGetNombreProvincia39() {
		VendingCity grupo = new VendingCity(39);
		assertEquals("Cantabria", grupo.getNombreProvincia());
	}
	
	@Test
	public void testGetNombreProvincia40() {
		VendingCity grupo = new VendingCity(40);
		assertEquals("Segovia", grupo.getNombreProvincia());
	}
	
	@Test
	public void testGetNombreProvincia41() {
		VendingCity grupo = new VendingCity(41);
		assertEquals("Sevilla", grupo.getNombreProvincia());
	}
	
	@Test
	public void testGetNombreProvincia42() {
		VendingCity grupo = new VendingCity(42);
		assertEquals("Soria", grupo.getNombreProvincia());
	}
	
	@Test
	public void testGetNombreProvincia43() {
		VendingCity grupo = new VendingCity(43);
		assertEquals("Tarragona", grupo.getNombreProvincia());
	}
	
	@Test
	public void testGetNombreProvincia44() {
		VendingCity grupo = new VendingCity(44);
		assertEquals("Teruel", grupo.getNombreProvincia());
	}
	
	@Test
	public void testGetNombreProvincia45() {
		VendingCity grupo = new VendingCity(45);
		assertEquals("Toledo", grupo.getNombreProvincia());
	}
	
	@Test
	public void testGetNombreProvincia46() {
		VendingCity grupo = new VendingCity(46);
		assertEquals("Valencia", grupo.getNombreProvincia());
	}
	
	@Test
	public void testGetNombreProvincia47() {
		VendingCity grupo = new VendingCity(47);
		assertEquals("Valladolid", grupo.getNombreProvincia());
	}
	
	@Test
	public void testGetNombreProvincia48() {
		VendingCity grupo = new VendingCity(48);
		assertEquals("Vizcaya", grupo.getNombreProvincia());
	}
	
	@Test
	public void testGetNombreProvincia49() {
		VendingCity grupo = new VendingCity(49);
		assertEquals("Zamora", grupo.getNombreProvincia());
	}
	
	@Test
	public void testGetNombreProvincia50() {
		VendingCity grupo = new VendingCity(50);
		assertEquals("Zaragoza", grupo.getNombreProvincia());
	}
	
	@Test
	public void testGetNombreProvincia51() {
		VendingCity grupo = new VendingCity(51);
		assertEquals("Ceuta", grupo.getNombreProvincia());
	}
	
	@Test
	public void testGetNombreProvincia52() {
		VendingCity grupo = new VendingCity(52);
		assertEquals("Melilla", grupo.getNombreProvincia());
	}
	
	
// public VendingMachine getMaquina(long identificador)
	@Test 
	public void testGetMaquina() {
		VendingCity grupo = new VendingCity(47);
		grupo.anadirMaquina(maquina1);
		int id = maquina1.getIdentificador();
		assertEquals(maquina1,grupo.getMaquina(id));
	}
	@Test (expected=IllegalArgumentException.class)
	public  void testGetMaquinaIdentificadorIncorrecto() {
		VendingCity grupo = new VendingCity(47);
		grupo.anadirMaquina(maquina1);
		int id = maquina2.getIdentificador();
		grupo.getMaquina(id);	
	}
	
	@Test (expected=IllegalArgumentException.class)
	public  void testGetMaquniaSistemaVacio() {
		VendingCity grupo = new VendingCity(47);
		int id = maquina2.getIdentificador();
		grupo.getMaquina(id);	
	}

//public int numeroMaquinasOperativas()
	@Test 
	public void testNumeroMaquinasOperativas () {
		VendingCity grupo = new VendingCity(47);
		grupo.anadirMaquina(maquina2);
		assertEquals(1,grupo.getNumeroMaquinasOperativas());
	}
	
	@Test 
	public void testNumeroMaquinasOperativasAVL () {
		VendingCity grupo = new VendingCity(47);
		assertEquals(0,grupo.getNumeroMaquinasOperativas());
	}

// public int getNumeroMaquinas() 
	@Test 
	public void testNumeroMaquinas() {
		VendingCity grupo = new VendingCity(47);
		grupo.anadirMaquina(maquina1);
		grupo.anadirMaquina(maquina2);
		assertEquals(2,grupo.getNumeroMaquinas());
	}
	
	@Test 
	public void testNumeroMaquinasAVL () {
		VendingCity grupo = new VendingCity(47);
		assertEquals(0,grupo.getNumeroMaquinas());
	}
	
//public ArrayList<VendingMachine> listarMaquinasConAlgunaLineaVacia()
	@Test
	public void testListarMaquinasConAlgunaLineaVacia() {
		VendingCity grupo = new VendingCity(47);
		grupo.anadirMaquina(maquina1);
		grupo.anadirMaquina(maquina2);
		maquina1.desasignarLinea(1);
		int lon =grupo.listarMaquinasConAlgunaLineaVacia().size();
		assertNotEquals(1,lon);
	}
	
	@Test
	public void testListarMaquinasSistemaVacio() {
		VendingCity grupo = new VendingCity(47);
		assertEquals(0,grupo.listarMaquinasConAlgunaLineaVacia().size());
	}

	@Test
	public void testListarMaquinasNoHayMaquinasVacias() {
		VendingCity grupo = new VendingCity(47);
		grupo.anadirMaquina(maquina1);
		assertEquals(0,grupo.listarMaquinasConAlgunaLineaVacia().size());
	}
	
	
//public boolean equals(Object obj)
	@Test
	public void testEqualsMismaSedeCorrecto() {
		VendingCity original = new VendingCity(47);
		original.anadirMaquina(maquina1);
		original.anadirMaquina(maquina2);
		VendingCity mismoSistema = original;
		
		assertTrue(original.equals(mismoSistema));		
	}
	
	@Test
	public void testEqualsCopiaSedeCorrecto() {
		VendingCity original = new VendingCity(47);
		original.anadirMaquina(maquina2);
		VendingCity copia = original.clonar();
		assertTrue(original.equals(copia));
	}
	
	@Test
	public void testEqualsConNull() {
		VendingCity original = new VendingCity(47);
		original.anadirMaquina(maquina1);
		assertFalse(original.equals(null));
	}
	
	@Test
	public void testEqualsNumeroMaquinasDistinto() {
		VendingCity original = new VendingCity(47);
		original.anadirMaquina(maquina1);
		original.anadirMaquina(maquina2);
		VendingCity otro = new VendingCity(47);
		otro.anadirMaquina(maquina1);
		
		assertFalse(original.equals(otro));
	}
	
	@Test
	public void testEqualsNumeroMaquinasIgualPeroDistintas() {
		VendingCity original = new VendingCity(50);
		original.anadirMaquina(maquina1);
		VendingCity otro = new VendingCity(50);
		original.anadirMaquina(maquina2);
		
		assertFalse(original.equals(otro));
	}
	
	//Test ESPECIALES PARA EQUALS()
	@SuppressWarnings("unlikely-arg-type")
	@Test
	public void testEqualsOtraClase() {
		VendingCity original = new VendingCity(20);
		assertFalse(original.equals(new String("jaaj")));
	}
	
	@Test
	public void testEqualsReflexividad() {
		VendingCity original = new VendingCity(50);
		assertTrue(original.equals(original));
	}
	@Test
	public void testEqualsSimetria() {
		VendingCity original = new VendingCity(50);
		original.anadirMaquina(maquina1);
		VendingCity copia = original.clonar();
		assertTrue(original.equals(copia));
		assertTrue(copia.equals(original));
	}
	@Test
	public void testEqualsTransitividad() {
		VendingCity original = new VendingCity(20);
		original.anadirMaquina(maquina1);
		VendingCity copia = original.clonar();
		VendingCity otraCopia = original.clonar();
		assertTrue(original.equals(copia));
		assertTrue(copia.equals(otraCopia));
		assertTrue(original.equals(otraCopia));
	}
	
//public int hashCode()
	@Test
	public void testHashCodeMismaSedeCorrecto() {
		VendingCity original = new VendingCity(47);
		original.anadirMaquina(maquina1);
		original.anadirMaquina(maquina2);
		VendingCity mismoSistema = original;
		
		assertEquals(original.hashCode(), mismoSistema.hashCode());		
	}
	
	@Test
	public void testHashCodeCopiaSedeCorrecto() {
		VendingCity original = new VendingCity(47);
		original.anadirMaquina(maquina2);
		VendingCity copia = original.clonar();
		assertTrue(original.equals(copia));
		
		assertEquals(original.hashCode(), copia.hashCode());	
	}
	
	/* Técnicamente no es necesario que objetos distintos tengan hash codes distintos, pero nosotros
	 * lo comprobamos como garantía de un buen rendimiento en las hash tables (ver documentación de hashCode()
	 * en Object)
	 */
	
	@Test
	public void testHashCodeNumeroMaquinasDistinto() {
		VendingCity original = new VendingCity(47);
		original.anadirMaquina(maquina1);
		original.anadirMaquina(maquina2);
		VendingCity otro = new VendingCity(47);
		otro.anadirMaquina(maquina1);
		
		assertNotEquals(original.hashCode(), otro.hashCode());
	}
	
	@Test
	public void testHashCodeNumeroMaquinasIgualPeroDistintas() {
		VendingCity original = new VendingCity(50);
		original.anadirMaquina(maquina1);
		VendingCity otro = new VendingCity(50);
		original.anadirMaquina(maquina2);
		
		assertNotEquals(original.hashCode(), otro.hashCode());
	}
	
	//TEST ESPECIAL PARA HASHCODE()
	@Test
	public void testHashCodeReflexividadConsistencia() {
		VendingCity original = new VendingCity(50);
		original.anadirMaquina(maquina1);
		assertEquals(original.hashCode(), original.hashCode());
	}
	
}

