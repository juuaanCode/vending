package uva.poo.vending;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Implementaci�n de una sede provincial de m�quinas de <i>vending</i>.
 * Permite operaciones sencillas de gesti�n de estas m�quinas. No se permite el cambio de provincia.
 * @author juagonz0
 * @author sanbarb
 */
public class VendingCity {
	private ArrayList<VendingMachine> listado;
	private int codigoProvincia;
	
	private static final HashMap<Integer, String> provincias = crearMapa();
	
	/**
	 * M�todo privado dedicado a la creaci�n del mapa para las provincias.
	 * Asocia el n�mero con el nombre de cada provincia.
	 * @return Mapa con las claves siendo los n�meros y los valores los nombres.
	 */
	private static HashMap<Integer, String> crearMapa(){
		HashMap<Integer, String> mapa = new HashMap<>();
		mapa.put(1, "�lava");
		mapa.put(2, "Albacete");
		mapa.put(3, "Alicante");
		mapa.put(4, "Almer�a");
		mapa.put(5, "�vila");
		mapa.put(6, "Badajoz");
		mapa.put(7, "Baleares");
		mapa.put(8, "Barcelona");
		mapa.put(9, "Burgos");
		mapa.put(10, "C�ceres");
		mapa.put(11, "C�diz");
		mapa.put(12, "Castell�n");
		mapa.put(13, "Ciudad Real");
		mapa.put(14, "C�rdoba");
		mapa.put(15, "La Coru�a");
		mapa.put(16, "Cuenca");
		mapa.put(17, "Gerona");
		mapa.put(18, "Granada");
		mapa.put(19, "Guadalajara");
		mapa.put(20, "Guip�zcoa");
		mapa.put(21, "Huelva");
		mapa.put(22, "Huesca");
		mapa.put(23, "Ja�n");
		mapa.put(24, "Le�n");
		mapa.put(25, "L�rida");
		mapa.put(26, "La Rioja");
		mapa.put(27, "Lugo");
		mapa.put(28, "Madrid");
		mapa.put(29, "M�laga");
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
	 * @param codigo C�digo de la provincia en la que se encuentra la sede.
	 * @throws IllegalArgumentException El c�digo de la provincia no existe en Espa�a.
	 */
	public VendingCity(int codigo) {
		if (codigo < 1 || codigo > 52) {
			throw new IllegalArgumentException("Codigo de provincia no v�lido");
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
	 * A�ade una maquina al sistema, siguiendo el orden del identificador.
	 * Se asigna esa misma m�quina (no una copia).
	 * @param maquina Maquina que se debe a�adir al sistema, con un identificador �nico (al menos en el sistema).
	 * @throws NullPointerException La m�quina es null.
	 * @throws IllegalArgumentException Ya existe una m�quina con ese identificador.
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
	 * Elimina una m�quina del sistema a partir de su identificador
	 * @param identificador Identificador de la maquina a eliminar del sistema.
	 * @throws IllegalArgumentException No existe esa m�quina.
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
			throw new IllegalArgumentException("M�quina no presente en el sistema");
		}
	}
	
	
	/**
	 * Devuelve una lista con todas las m�quinas <i>VendingMachine</i> de la sede, por orden de identificador.<br>
	 * Las modificaciones en el array no afectar�n al sistema, pero a las m�quinas s�. De esta forma se permite
	 * la gesti�n de las m�quinas del sistema de forma sencilla.
	 * @return Lista con todas las maquinas VendingMachine del sistema. Si no hay m�quinas, estar� vac�a.
	 */
	public List<VendingMachine> getListado() {
		return new ArrayList<>(listado);
	}
	
	
	/**
	 * Devuelve el c�digo de provincia de la que la ciudad es sede.
	 * @return C�digo de provincia de la sede.
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
	 * Devuelve la m�quina buscando el identificador.
	 * @param identificador N�mero �nico que identifica a esa m�quina.
	 * @return M�quina (si se ha encontrado)
	 * @throws IllegalArgumentException Esa m�quina no est� en el sistema.
	 * @throws IllegalArgumentException El sistema est� vac�o.
	 */
	public VendingMachine getMaquina(int identificador){
		if (listado.isEmpty()) {
			throw new IllegalArgumentException("El sistema est� vac�o");
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
			throw new IllegalArgumentException("M�quina no presente en el sistema");
		}
		return resultado;
	}
	
	
	/**
	 * Devuelve el n�mero de m�quinas operativas en el sistema.
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
	 * Devuelve el n�mero de m�quinas presentes en la provincia.
	 * @return N�mero de m�quinas de la provincia.
	 */
	public int getNumeroMaquinas() {
		return listado.size();
	}
	
	/**
	 * Devuelve una lista con todas las m�quinas <i>VendingMachine</i> que tienen alguna 
	 * l�nea vac�a.
	 * @return Lista con todas las maquinas que tengan alguna l�nea vac�a. Si no hay m�quinas, estar� vac�a.
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
	 * no es null y es la misma VendingCity u otra con los par�metros iguales.
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
