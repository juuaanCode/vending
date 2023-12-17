package uva.poo.vending;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Implementación de una sede provincial de máquinas de <i>vending</i>.
 * Permite operaciones sencillas de gestión de estas máquinas. No se permite el cambio de provincia.
 * @author juagonz0
 * @author sanbarb
 */
public class VendingCity {
	private ArrayList<VendingMachine> listado;
	private int codigoProvincia;
	
	private static final HashMap<Integer, String> provincias = crearMapa();
	
	/**
	 * Método privado dedicado a la creación del mapa para las provincias.
	 * Asocia el número con el nombre de cada provincia.
	 * @return Mapa con las claves siendo los números y los valores los nombres.
	 */
	private static HashMap<Integer, String> crearMapa(){
		HashMap<Integer, String> mapa = new HashMap<>();
		mapa.put(1, "Álava");
		mapa.put(2, "Albacete");
		mapa.put(3, "Alicante");
		mapa.put(4, "Almería");
		mapa.put(5, "Ávila");
		mapa.put(6, "Badajoz");
		mapa.put(7, "Baleares");
		mapa.put(8, "Barcelona");
		mapa.put(9, "Burgos");
		mapa.put(10, "Cáceres");
		mapa.put(11, "Cádiz");
		mapa.put(12, "Castellón");
		mapa.put(13, "Ciudad Real");
		mapa.put(14, "Córdoba");
		mapa.put(15, "La Coruña");
		mapa.put(16, "Cuenca");
		mapa.put(17, "Gerona");
		mapa.put(18, "Granada");
		mapa.put(19, "Guadalajara");
		mapa.put(20, "Guipúzcoa");
		mapa.put(21, "Huelva");
		mapa.put(22, "Huesca");
		mapa.put(23, "Jaén");
		mapa.put(24, "León");
		mapa.put(25, "Lérida");
		mapa.put(26, "La Rioja");
		mapa.put(27, "Lugo");
		mapa.put(28, "Madrid");
		mapa.put(29, "Málaga");
		mapa.put(30, "Murcia");
		mapa.put(31, "Navarra");
		mapa.put(32, "Orense");
		mapa.put(33, "Asturias");
		mapa.put(34, "Palencia");
		mapa.put(35, "Las Palmas");
		mapa.put(36, "Pontevedra");
		mapa.put(37, "Salamanca");
		mapa.put(38, "Santa Cruz de Tenerife");
		mapa.put(39, "Cantabria");
		mapa.put(40, "Segovia");
		mapa.put(41, "Sevilla");
		mapa.put(42, "Soria");
		mapa.put(43, "Tarragona");
		mapa.put(44, "Teruel");
		mapa.put(45, "Toledo");
		mapa.put(46, "Valencia");
		mapa.put(47, "Valladolid");
		mapa.put(48, "Vizcaya");
		mapa.put(49, "Zamora");
		mapa.put(50, "Zaragoza");
		mapa.put(51, "Ceuta");
		mapa.put(52, "Melilla");	
		return mapa;
	}
	
	
	/**
	 * Inicializa la provincia.
	 * @param codigo Código de la provincia en la que se encuentra la sede.
	 * @throws IllegalArgumentException El código de la provincia no existe en España.
	 */
	public VendingCity(int codigo) {
		if (codigo < 1 || codigo > 52) {
			throw new IllegalArgumentException("Codigo de provincia no válido");
		}
		listado = new ArrayList<>();
		codigoProvincia = codigo;
	}
	
	
	/**
	 * Clona la provincia generando una copia en profundidad.
	 * @return VendingCity clonada en profundidad.
	 */
	public VendingCity clonar(){
		VendingCity nuevaCiudad = new VendingCity(getCodigoProvincia());
		
		for (VendingMachine maquina : getListado()) {
			nuevaCiudad.anadirMaquina(maquina.clonar());
		}
		return nuevaCiudad;
	}
	
	
	/**
	 * Añade una maquina al sistema, siguiendo el orden del identificador.
	 * Se asigna esa misma máquina (no una copia).
	 * @param maquina Maquina que se debe añadir al sistema, con un identificador único (al menos en el sistema).
	 * @throws NullPointerException La máquina es null.
	 * @throws IllegalArgumentException Ya existe una máquina con ese identificador.
	 */
	public void anadirMaquina(VendingMachine maquina) {
		if (maquina == null) 
			throw new NullPointerException("Maquina == null");
		
		for (int i = 0; i<listado.size(); i++) {
			if (listado.get(i).getIdentificador() == maquina.getIdentificador())
				throw new IllegalArgumentException("Ya hay una maquina con ese identificador");
		}
		if (listado.isEmpty()) {
			listado.add(maquina);
		} else {
			boolean anadido = false;
			int pos = 0;
			while (pos < listado.size() && !anadido) {
				if (listado.get(pos) == null || listado.get(pos).getIdentificador() > maquina.getIdentificador()){
					listado.add(pos, maquina);
					anadido = true;
				} else {
					pos++;
				}
			}
			if (!anadido) { //Lo metemos al final
				listado.add(maquina);
			}
		}
	}

	
	
	/**
	 * Elimina una máquina del sistema a partir de su identificador
	 * @param identificador Identificador de la maquina a eliminar del sistema.
	 * @throws IllegalArgumentException No existe esa máquina.
	 */
	public void eliminarMaquina(long identificador){
		int i = 0;
		boolean borrado = false;
		while(i<listado.size() && !borrado) {
			if (listado.get(i).getIdentificador() == identificador) {
				listado.remove(i);
				borrado = true;
			}
			i++;
		}
		if (!borrado) {
			throw new IllegalArgumentException("Máquina no presente en el sistema");
		}
	}
	
	
	/**
	 * Devuelve una lista con todas las máquinas <i>VendingMachine</i> de la sede, por orden de identificador.<br>
	 * Las modificaciones en el array no afectarán al sistema, pero a las máquinas sí. De esta forma se permite
	 * la gestión de las máquinas del sistema de forma sencilla.
	 * @return Lista con todas las maquinas VendingMachine del sistema. Si no hay máquinas, estará vacía.
	 */
	public List<VendingMachine> getListado() {
		return new ArrayList<>(listado);
	}
	
	
	/**
	 * Devuelve el código de provincia de la que la ciudad es sede.
	 * @return Código de provincia de la sede.
	 */
	public int getCodigoProvincia() {
		return codigoProvincia;
	}
	
	
	/**
	 * Devuelve el nombre de la provincia en la que se encuentra la sede.
	 * @return Nombre de la provincia.
	 */
	public String getNombreProvincia() {
		return provincias.get(getCodigoProvincia());
	}
	
	
	/**
	 * Devuelve la máquina buscando el identificador.
	 * @param identificador Número único que identifica a esa máquina.
	 * @return Máquina (si se ha encontrado)
	 * @throws IllegalArgumentException Esa máquina no está en el sistema.
	 * @throws IllegalArgumentException El sistema está vacío.
	 */
	public VendingMachine getMaquina(int identificador){
		if (listado.isEmpty()) {
			throw new IllegalArgumentException("El sistema está vacío");
		}
		VendingMachine resultado = null;
		int i = 0;
		boolean encontrado = false;
		while(i<listado.size() && !encontrado) {
			if (listado.get(i).getIdentificador() == identificador) {
				resultado = listado.get(i);
				encontrado = true;
			}
			i++;
		}
		if (!encontrado) {
			throw new IllegalArgumentException("Máquina no presente en el sistema");
		}
		return resultado;
	}
	
	
	/**
	 * Devuelve el número de máquinas operativas en el sistema.
	 * @return Numero de maquinas operativas.
	 */
	public int getNumeroMaquinasOperativas() {
		int numeroOperativas = 0;
		for (int i = 0; i<listado.size(); i++) {
			if (listado.get(i).getEstado()) {
				numeroOperativas++;
			}
		}
		return numeroOperativas;
	}
	
	
	/**
	 * Devuelve el número de máquinas presentes en la provincia.
	 * @return Número de máquinas de la provincia.
	 */
	public int getNumeroMaquinas() {
		return listado.size();
	}
	
	/**
	 * Devuelve una lista con todas las máquinas <i>VendingMachine</i> que tienen alguna 
	 * línea vacía.
	 * @return Lista con todas las maquinas que tengan alguna línea vacía. Si no hay máquinas, estará vacía.
	 */
	public List<VendingMachine> listarMaquinasConAlgunaLineaVacia() {
		List<VendingMachine> nuevoListado = new ArrayList<>();
		
		for (VendingMachine maquina : listado) {
			if (maquina.tieneAlgunaLineaVacia()) {
				nuevoListado.add(maquina);
			}
		}
		return nuevoListado;
	}


	/**
	 * Devuelve un hash code para la provincia.
	 * @return Valor del hash code de esta provincia.
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + getCodigoProvincia();
		result = prime * result + getListado().hashCode();
		return result;
	}


	/**
	 * Compara este objeto con otro. El resultado es true si y solo si el argumento 
	 * no es null y es la misma VendingCity u otra con los parámetros iguales.
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
		VendingCity other = (VendingCity) obj;
		return (getCodigoProvincia() == other.getCodigoProvincia() && getListado().equals(other.getListado()));
	}
}
