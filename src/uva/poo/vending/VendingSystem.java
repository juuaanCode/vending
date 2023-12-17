package uva.poo.vending;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Implementación de un sistema de máquinas de <i>vending</i> a nivel nacional. 
 * Proporciona herramientas que facilitan la gestión.
 * @author juagonz0
 * @author sanbarb
 */
public class VendingSystem {
	private VendingCity[] conjunto;
	
	/**
	 * Inicializar el sistema vacio
	 */
	public VendingSystem() {
		conjunto = new VendingCity[52];
	}
	
	
	/**
	 * Clona el sistema generando una copia en profundidad.
	 * @return Sistema clonado en profundidad.
	 */
	public VendingSystem clonar(){
		VendingSystem nuevoSistema = new VendingSystem();
		
		for (VendingCity provincia : conjunto) {
			if (provincia != null) {
				nuevoSistema.anadirSede(provincia.clonar());
			}
		}
		return nuevoSistema;
	}
	
	
	/**
	 * Añade una nueva sede provincial al sistema.
	 * @param sede Sede que se añade al sistema.
	 * @throws NullPointerException La sede es null.
	 * @throws IllegalArgumentException Ya existe una sede en esa provincia.
	 */
	public void anadirSede (VendingCity sede) {
		if (sede == null) {
			throw new NullPointerException("Sede == null");
		}
		
		if (conjunto[sede.getCodigoProvincia() - 1] != null){
			throw new IllegalArgumentException("Ya hay una sede de esta provincia");
		}
		
		conjunto[sede.getCodigoProvincia() - 1] = sede;
	}
	
	
	/** 
	 * Elimina una sede del sistema según el código de la proovincia.
	 * @param codigoProvincia Codigo de la provincia cuya sede se desea eliminar.
	 * @throws IllegalArgumentException El código de la provincia no es válido.
	 * @throws IllegalArgumentException La provincia no tiene sede.
	 */
	public void eliminarSede (int codigoProvincia) {
		if (codigoProvincia < 1 ||codigoProvincia > 52) {
			throw new IllegalArgumentException("Codigo de provincia no válido");
		}
		
		if (conjunto[codigoProvincia - 1] == null) {
			throw new IllegalArgumentException("Esta provincia no tiene sede");
		}
		conjunto[codigoProvincia - 1] = null;
	}
	
	
	/**
	 * Devuelve las sedes que forman parte del sistema, ordenadas según el número de provincia.
	 * Si en una provincia no hay sede, es null.
	 * @return Array con las sedes.
	 */
	public VendingCity[] getSedes() {
		return Arrays.copyOf(conjunto, 52);
	}
	
	
	/**
	 * Devuelve el número de maquinas de una sede.
	 * @param codigoProvincia Código de la provincia. 
	 * @return Numero de máquinas.
	 * @throws IllegalArgumentException El código de la provincia no es válido.
	 * @throws IllegalArgumentException La provincia no tiene sede.
	 */
	public int getNumeroMaquinas(int codigoProvincia) {
		if (codigoProvincia< 1 ||codigoProvincia > 52) {
			throw new IllegalArgumentException("Codigo de provincia no válido");
		}
		
		if (conjunto[codigoProvincia - 1] == null) {
			throw new IllegalArgumentException("Esta provincia no tiene sede");
		}
		
		return conjunto[codigoProvincia - 1].getListado().size();
	}
	
	
	/**
	 * Devuelve una lista con todas las máquinas <i>VendingMachine</i> de una provincia.
	 * @param codigoProvincia Código de la provincia.
	 * @return Lista con las máquinas de la provincia.
	 * @throws IllegalArgumentException El código de la provincia no es válido.
	 * @throws IllegalArgumentException La provincia no tiene sede.
	 */
	public List<VendingMachine> getListadoMaquinas(int codigoProvincia) {
		if (codigoProvincia< 1 ||codigoProvincia > 52) {
			throw new IllegalArgumentException("Codigo de provincia no válido");
		}
		
		if (conjunto[codigoProvincia - 1] == null) {
			throw new IllegalArgumentException("Esta provincia no tiene sede");
		}
		return conjunto[codigoProvincia - 1].getListado();
	}
	
	
	/**
	 * Devuelve el numero de provincias que se gestionan.
	 * @return Número de provincias.
	 */
	public int getNumeroProvincias() {
		int numeroProvinciasGestionadas = 0;
		for (VendingCity ciudad : conjunto) {
			if (ciudad != null) {
				numeroProvinciasGestionadas++;
			}
		}
		return numeroProvinciasGestionadas;
		
	}

	
	/**
	 * Devuelve los  nombres de todas las provincias donde hay una sede.
	 * @return Lista con todos los nombres.
	 */
	public List<String> listarNombresProvinciasConSede(){
		List<String> listado = new ArrayList<>();
		
		for (VendingCity ciudad : conjunto) {
			if (ciudad != null) {
				listado.add(ciudad.getNombreProvincia());
			}
		}
		return listado;
	}
	
	
	/**
	 * Devuelve una lista con el número de maquinas que se gestionan en cada sede.
	 * @return Lista con la cantidad de máquinas en cada sede, en orden de número de provincia.
	 */
	public List<Integer> listarCantidadMaquinasPorSede(){
		List<Integer> listado = new ArrayList<>();
		for (VendingCity ciudad : conjunto) {
			if (ciudad == null) {
				listado.add(0);
			} else {
				listado.add(ciudad.getNumeroMaquinas());
			}
		}
		return listado;
	}

	
	/**
	 * Devuelve un hash code para el sistema.
	 * @return Valor del hash code de este sistema.
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(getSedes());
		return result;
	}

	
	/**
	 * Compara este objeto con otro. El resultado es true si y solo si el argumento 
	 * no es null y es el mismo sistema u otro con los parámetros iguales.
	 * @return true si los objetos son iguales, false si no.
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		VendingSystem other = (VendingSystem) obj;
		return Arrays.equals(getSedes(), other.getSedes());
	}
}
