package uva.poo.vending;

import java.util.Arrays;
import fabricante.externo.tarjetas.TarjetaMonedero;

/**
 * Implementa la funcionalidad de una máquina de <i>vending</i>.
 * Formada de lineas (en profundidad) con vendibles, permite comprar vendibles con una tarjeta monedero. <br>
 * Se considera que una máquina no puede cambiar de dimensiones una vez creada.<br>
 * No se pueden realizar operaciones en una máquina fuera de servicio, salvo recabar información y cambiar de estado.<br>
 * El número de filas puede ser mucho mayor que el de líneas, en ese caso se considera que las filas necesarias más abajo quedarían vacías. De esta forma se 
 * permite almacenar las dimensiones de la máquina sin que genere ningún problema en el uso de la máquina.
 * <br><br>
 * EJEMPLO:<br>
 * Dada una máquina de 10 líneas y de 4 filas de altura, los identificadores se asignan de la 
 * siguiente forma:<br>
 * 	| 0 | 1 | 2 |<br>
 *  | 3 | 4 | 5 |<br>
 * 	| 6 | 7 | 8 |<br>
 * 	| 9 |<br>
 * El número de columnas es 3 y no se puede modificar la máquina para usar más huecos.
 *
 * @author juagonz0
 * @author sanbarb
 */
public class VendingMachine {
	
	private int identificador;
	private Vendible[] lineas;
	private int[] cantidades;
	private boolean estado;
	private int numeroFilas;
	
	
	/**
	 * Inicializa la maquina con un identificador, un número de líneas, su estado y el número de filas de altura.
	 * @param identificador Número que identifica a la máquina.
	 * @param numeroLineas Número de líneas de la máquina. Debe ser mayor que 0.
	 * @param estado Estado inicial de la máquina. True significa operativa.
	 * @param filas Nuevo número de filas (altura). Debe ser mayor que 0.
	 * @throws IllegalArgumentException El número de líneas es menor o igual que 0.
	 * @throws IllegalArgumentException El número de filas es menor o igual que 0.
	 */
	public VendingMachine(int identificador, int numeroLineas, boolean estado, int filas){
		if (numeroLineas <= 0) {
			throw new IllegalArgumentException("Número de líneas <= 0");
		}
		if (filas <=0) {
			throw new IllegalArgumentException("Numero de filas <= 0");
		}
		this.identificador = identificador;
		lineas = new Vendible[numeroLineas];
		cantidades = new int[numeroLineas];
		this.estado = estado;
		numeroFilas = filas;
	}
	
	/**
	 * Clona la máquina generando una copia en profundidad.
	 * @return Máquina clonada en profundidad.
	 */
	public VendingMachine clonar(){
		VendingMachine nuevaMaquina = new VendingMachine(getIdentificador(), getNumeroLineas(), getEstado(), getNumeroFilas());
		
		for (int i = 0; i < getNumeroLineas(); i++) {
			if (getLineas()[i] != null) {
				nuevaMaquina.asignarLinea(getLineas()[i].clonar(), getCantidades()[i], i);
			}
		}
		return nuevaMaquina;
	}
	
	
	/**
	 * Devuelve el identificador único de la máquina.
	 * @return El identificador.
	 */
	public int getIdentificador() {
		return identificador;
	}

	
	/**
	 * Asigna un nuevo identificador a la máquina.
	 * @param nuevoIdentificador Identificador de la máquina.
	 * @throws IllegalStateException Máquina fuera de servicio.
	 */
	public void setIdentificador(int nuevoIdentificador) {
		if (!getEstado()) {
			throw new IllegalStateException("Máquina fuera de servicio");
		}
		identificador = nuevoIdentificador;
	}
	
	
	/**
	 * Devuelve un array con los vendibles de la máquina, en el orden de las líneas.
	 * Las modificaciones en el <i>array</i> no afectarán a la máquina, pero a los vendibles sí.
	 * @return Array de vendibles de la máquina.
	 */
	public Vendible[] getLineas() {
		return Arrays.copyOf(lineas, lineas.length);
	}
	
	
	/**
	 * Devuelve un array con las cantidades de los vendibles de la máquina, en el orden de las líneas.
	 * En las líneas sin asignar no se especifica un valor concreto.
	 * Las modificaciones en el <i>array</i> no afectarán a la máquina, pero a las líneas sí.
	 * @return Array de cantidades de la máquina.
	 */
	public int[] getCantidades() {
		return Arrays.copyOf(cantidades, cantidades.length);
	}
	
	
	/**
	 * Comprueba si la máquina está operativa o no.
	 * @return True si está operativa, false en caso contrario.
	 */
	public boolean getEstado() {
		return estado;
	}

	
	/**
	 * Pone la máquina en estado operativo.
	 * @throws IllegalStateException La máquina ya está operativa.
	 */
	public void ponerOperativa(){
		if (estado) {
			throw new IllegalStateException("Máquina ya encendida");
		}
		estado = true;
	}
	
	
	/**
	 * Pone la máquina fuera de servicio.
	 * @throws IllegalStateException La máquina ya está fuera de servicio.
	 */
	public void fueraDeServicio(){
		if (!estado) {
			throw new IllegalStateException("Máquina ya fuera de servicio");
		}
		estado = false;
	}
	
	
	/**
	 * Devuelve el número de filas (altura) de la máquina.
	 * @return El número de filas de la máquina.
	 */
	public int getNumeroFilas() {
		return numeroFilas;
	}
	
	
	/**
	 * Devuelve el número de columnas (anchura) de la máquina.
	 * @return El número de columnas de la máquina.
	 */
	public int getNumeroColumnas() {
		return (int) Math.ceil((float) getNumeroLineas()/getNumeroFilas());
	}
	
	
	/**
	 * Devuelve el numero de líneas de la máquina.
	 * @return El número de líneas.
	 */
	public int getNumeroLineas() {
		return lineas.length;
	}
	
	/**
	 * Comprueba si la máquina tiene alguna de sus líneas vacías.
	 * @return True si hay alguna vacía, false en caso contrario.
	 */
	public boolean tieneAlgunaLineaVacia() {
		boolean encontradaVacia = false;
		int indice = 0;
		
		while (indice<lineas.length && !encontradaVacia) {
			if (lineas[indice] == null) {
				encontradaVacia = true;
			} else if (cantidades[indice] == 0) {
				encontradaVacia = true;
			} else {
				indice++;
			}
		}
		return encontradaVacia;
	}
	
	
	/**
	 * Vacía todas las líneas de la máquina.
	 * @throws IllegalStateException Máquina fuera de servicio.
	 */
	public void resetearMaquina(){
		if (!getEstado()) {
			throw new IllegalStateException("Máquina fuera de servicio");
		}
		lineas = new Vendible[getNumeroLineas()];
		cantidades = new int[getNumeroLineas()];
	}
	
	
	/**
	 * Desasigna una línea de la máquina, dejándola vacía.
	 * @param numLinea Identificador de la línea que se quiere vaciar.
	 * @throws IllegalStateException La línea ya estaba sin asignar a un vendible.
	 * @throws IllegalStateException Máquina fuera de servicio.
	 * @throws IndexOutOfBoundsException El número de línea es mayor que el tamaño de la máquina.
	 */
	public void desasignarLinea(int numLinea){
		if (!getEstado()) {
			throw new IllegalStateException("Máquina fuera de servicio");
		}
		if (numLinea >= lineas.length) {
			throw new IndexOutOfBoundsException("La línea no existe");
		}
		
		if (lineas[numLinea] != null) {
			lineas[numLinea] = null;
		} else {
			throw new IllegalStateException("La linea ya está sin asignar");
		}
	}
	
	
	/**
	 * Asigna un vendible a la máquina en una posición sin asignar.
	 * Se asigna ese mismo vendible (no una copia).
	 * @param vendible Vendible que se debe añadir.
	 * @param cantidad Cantidad del vendible que se debe añadir. No puede ser menor que 0.
	 * @param numLinea Número de línea de la máquina a la que se debe añadir. Debe estar sin asignar.
	 * @throws IllegalArgumentException Cantidad menor que 0.
	 * @throws IllegalStateException La línea está asignada.
	 * @throws IllegalStateException Máquina fuera de servicio.
	 * @throws NullPointerException El vendible es null.
	 * @throws IndexOutOfBoundsException El número de línea es mayor que el tamaño de la máquina o menor que 0.
	 */
	public void asignarLinea(Vendible vendible, int cantidad, int numLinea){
		if (!getEstado()) {
			throw new IllegalStateException("Máquina fuera de servicio");
		}
		if (numLinea >= lineas.length || numLinea < 0) {
			throw new IndexOutOfBoundsException("La línea no existe");
		} 
		if (vendible == null) {
			throw new NullPointerException("Vendible == null");
		}
		if (lineas[numLinea]!=null) {
			throw new IllegalStateException("La línea está asignada");
		}
		if (cantidad < 0) {
			throw new IllegalArgumentException("Cantidad < 0");
		}
		lineas[numLinea] = vendible;
		cantidades[numLinea] = cantidad;
	}
	
	
	/**
	 * Asigna un vendible a la máquina en una posición concreta.
	 * Se asigna ese mismo vendible (no una copia).
	 * @param vendible Vendible que reemplaza al actual
	 * @param cantidad Cantidad del vendible que se debe añadir. No puede ser menor que 0.
	 * @param numLinea Número de línea de la máquina a la que se debe añadir.
	 * @throws IllegalArgumentException Cantidad menor que 0.
	 * @throws IllegalStateException Máquina fuera de servicio.
	 * @throws NullPointerException El vendible es null.
	 * @throws IndexOutOfBoundsException El número de línea es mayor que el tamaño de la máquina o menor que 0.
	 */
	public void reemplazarLinea(Vendible vendible, int cantidad, int numLinea){
		if (!getEstado()) {
			throw new IllegalStateException("Máquina fuera de servicio");
		}
		if (vendible == null) {
			throw new NullPointerException("Producto == null");
		}
		if (numLinea >= lineas.length || numLinea < 0) {
			throw new IndexOutOfBoundsException("La línea no existe");
		}
		if (cantidad < 0) {
			throw new IllegalArgumentException("Cantidad < 0");
		}
		lineas[numLinea] = vendible;
		cantidades[numLinea] = cantidad;
	}
	
	
	/**
	 * Añade o resta el numero de items indicado del vendible de la linea.
	 * @param numLinea Número de la línea en la que se encuentra el vendible.
	 * @param cambio Cantidad sumada o restada. No se debe restar más cantidad de lo que hay.
	 * @throws IllegalArgumentException Se está restando más cantidad de la que hay.
	 * @throws IllegalStateException Máquina fuera de servicio.
	 */
	public void cambiarCantidadSuma(int numLinea, int cambio) {
		if (!getEstado()) {
			throw new IllegalStateException("Máquina fuera de servicio");
		}
		if (numLinea >= lineas.length) {
			throw new IndexOutOfBoundsException("La línea no existe");
		}
		if (lineas[numLinea] == null) {
			throw new IllegalStateException("La linea está sin asignar");
		}
		if (cambio < 0 && cantidades[numLinea] < -cambio) {
			throw new IllegalArgumentException("Se está restando más cantidad de la que hay");
		}
		cantidades[numLinea] += cambio;
	}

	
	/**
	 * Compra de la máquina una cantidad concreta de un vendible de una línea,
	 * dada una tarjeta monedero. No se puede comprar en una máquina fuera de servicio.
	 * @param tarjeta Tarjeta monedero. Debe tener saldo suficiente.
	 * @param linea Identificador de la línea de la que se quiere extraer el vendible.
	 * @throws IllegalArgumentException No hay ningún vendible asignado a esa línea
	 * @throws IllegalArgumentException No hay suficiente cantidad de vendibles en esa línea.
	 * @throws IllegalArgumentException No hay saldo suficiente en la tarjeta.
	 * @throws NullPointerException Tarjeta es null.
	 * @throws IllegalStateException Máquina fuera de servicio.
	 */
	public void comprarItem(TarjetaMonedero tarjeta, int linea){
		if (!getEstado()) {
			throw new IllegalStateException("Máquina fuera de servicio");
		}
		if (tarjeta == null) {
			throw new NullPointerException("Tarjeta == null");
		}
		if (lineas[linea] == null) {
			throw new IllegalArgumentException("No hay un vendible asignado");
		}
		if (cantidades[linea] < 1) {
			throw new IllegalArgumentException("No hay suficiente vendible");
		}
		if (lineas[linea].getPrecio() > tarjeta.getSaldoActual()){
			throw new IllegalArgumentException("No hay saldo suficiente");
		}
		tarjeta.descontarDelSaldo("6Z1y00Nm31aA-571", lineas[linea].getPrecio());
		cantidades[linea] -= 1;
	}

	
	/**
	 * Devuelve un hash code para la máquina.
	 * @return Valor del hash code de esta máquina.
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(getCantidades());
		result = prime * result + (getEstado() ? 1231 : 1237);
		result = prime * result + getIdentificador();
		result = prime * result + Arrays.hashCode(getLineas());
		result = prime * result + getNumeroFilas();
		return result;
	}

	
	/**
	 * Compara este objeto con otro. El resultado es true si y solo si el argumento 
	 * no es null y es la misma máquina u otra con los parámetros iguales.
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
		VendingMachine other = (VendingMachine) obj;
		return (Arrays.equals(getCantidades(), other.getCantidades()) && getEstado() == other.getEstado() && 
				getIdentificador() == other.getIdentificador() && Arrays.equals(getLineas(), other.getLineas()) && getNumeroFilas() == other.getNumeroFilas());
	}
}