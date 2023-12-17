package uva.poo.vending;

import java.util.Arrays;
import fabricante.externo.tarjetas.TarjetaMonedero;

/**
 * Implementa la funcionalidad de una m�quina de <i>vending</i>.
 * Formada de lineas (en profundidad) con vendibles, permite comprar vendibles con una tarjeta monedero. <br>
 * Se considera que una m�quina no puede cambiar de dimensiones una vez creada.<br>
 * No se pueden realizar operaciones en una m�quina fuera de servicio, salvo recabar informaci�n y cambiar de estado.<br>
 * El n�mero de filas puede ser mucho mayor que el de l�neas, en ese caso se considera que las filas necesarias m�s abajo quedar�an vac�as. De esta forma se 
 * permite almacenar las dimensiones de la m�quina sin que genere ning�n problema en el uso de la m�quina.
 * <br><br>
 * EJEMPLO:<br>
 * Dada una m�quina de 10 l�neas y de 4 filas de altura, los identificadores se asignan de la 
 * siguiente forma:<br>
 * 	| 0 | 1 | 2 |<br>
 *  | 3 | 4 | 5 |<br>
 * 	| 6 | 7 | 8 |<br>
 * 	| 9 |<br>
 * El n�mero de columnas es 3 y no se puede modificar la m�quina para usar m�s huecos.
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
	 * Inicializa la maquina con un identificador, un n�mero de l�neas, su estado y el n�mero de filas de altura.
	 * @param identificador N�mero que identifica a la m�quina.
	 * @param numeroLineas N�mero de l�neas de la m�quina. Debe ser mayor que 0.
	 * @param estado Estado inicial de la m�quina. True significa operativa.
	 * @param filas Nuevo n�mero de filas (altura). Debe ser mayor que 0.
	 * @throws IllegalArgumentException El n�mero de l�neas es menor o igual que 0.
	 * @throws IllegalArgumentException El n�mero de filas es menor o igual que 0.
	 */
	public VendingMachine(int identificador, int numeroLineas, boolean estado, int filas){
		if (numeroLineas <= 0) {
			throw new IllegalArgumentException("N�mero de l�neas <= 0");
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
	 * Clona la m�quina generando una copia en profundidad.
	 * @return M�quina clonada en profundidad.
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
	 * Devuelve el identificador �nico de la m�quina.
	 * @return El identificador.
	 */
	public int getIdentificador() {
		return identificador;
	}

	
	/**
	 * Asigna un nuevo identificador a la m�quina.
	 * @param nuevoIdentificador Identificador de la m�quina.
	 * @throws IllegalStateException M�quina fuera de servicio.
	 */
	public void setIdentificador(int nuevoIdentificador) {
		if (!getEstado()) {
			throw new IllegalStateException("M�quina fuera de servicio");
		}
		identificador = nuevoIdentificador;
	}
	
	
	/**
	 * Devuelve un array con los vendibles de la m�quina, en el orden de las l�neas.
	 * Las modificaciones en el <i>array</i> no afectar�n a la m�quina, pero a los vendibles s�.
	 * @return Array de vendibles de la m�quina.
	 */
	public Vendible[] getLineas() {
		return Arrays.copyOf(lineas, lineas.length);
	}
	
	
	/**
	 * Devuelve un array con las cantidades de los vendibles de la m�quina, en el orden de las l�neas.
	 * En las l�neas sin asignar no se especifica un valor concreto.
	 * Las modificaciones en el <i>array</i> no afectar�n a la m�quina, pero a las l�neas s�.
	 * @return Array de cantidades de la m�quina.
	 */
	public int[] getCantidades() {
		return Arrays.copyOf(cantidades, cantidades.length);
	}
	
	
	/**
	 * Comprueba si la m�quina est� operativa o no.
	 * @return True si est� operativa, false en caso contrario.
	 */
	public boolean getEstado() {
		return estado;
	}

	
	/**
	 * Pone la m�quina en estado operativo.
	 * @throws IllegalStateException La m�quina ya est� operativa.
	 */
	public void ponerOperativa(){
		if (estado) {
			throw new IllegalStateException("M�quina ya encendida");
		}
		estado = true;
	}
	
	
	/**
	 * Pone la m�quina fuera de servicio.
	 * @throws IllegalStateException La m�quina ya est� fuera de servicio.
	 */
	public void fueraDeServicio(){
		if (!estado) {
			throw new IllegalStateException("M�quina ya fuera de servicio");
		}
		estado = false;
	}
	
	
	/**
	 * Devuelve el n�mero de filas (altura) de la m�quina.
	 * @return El n�mero de filas de la m�quina.
	 */
	public int getNumeroFilas() {
		return numeroFilas;
	}
	
	
	/**
	 * Devuelve el n�mero de columnas (anchura) de la m�quina.
	 * @return El n�mero de columnas de la m�quina.
	 */
	public int getNumeroColumnas() {
		return (int) Math.ceil((float) getNumeroLineas()/getNumeroFilas());
	}
	
	
	/**
	 * Devuelve el numero de l�neas de la m�quina.
	 * @return El n�mero de l�neas.
	 */
	public int getNumeroLineas() {
		return lineas.length;
	}
	
	/**
	 * Comprueba si la m�quina tiene alguna de sus l�neas vac�as.
	 * @return True si hay alguna vac�a, false en caso contrario.
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
	 * Vac�a todas las l�neas de la m�quina.
	 * @throws IllegalStateException M�quina fuera de servicio.
	 */
	public void resetearMaquina(){
		if (!getEstado()) {
			throw new IllegalStateException("M�quina fuera de servicio");
		}
		lineas = new Vendible[getNumeroLineas()];
		cantidades = new int[getNumeroLineas()];
	}
	
	
	/**
	 * Desasigna una l�nea de la m�quina, dej�ndola vac�a.
	 * @param numLinea Identificador de la l�nea que se quiere vaciar.
	 * @throws IllegalStateException La l�nea ya estaba sin asignar a un vendible.
	 * @throws IllegalStateException M�quina fuera de servicio.
	 * @throws IndexOutOfBoundsException El n�mero de l�nea es mayor que el tama�o de la m�quina.
	 */
	public void desasignarLinea(int numLinea){
		if (!getEstado()) {
			throw new IllegalStateException("M�quina fuera de servicio");
		}
		if (numLinea >= lineas.length) {
			throw new IndexOutOfBoundsException("La l�nea no existe");
		}
		
		if (lineas[numLinea] != null) {
			lineas[numLinea] = null;
		} else {
			throw new IllegalStateException("La linea ya est� sin asignar");
		}
	}
	
	
	/**
	 * Asigna un vendible a la m�quina en una posici�n sin asignar.
	 * Se asigna ese mismo vendible (no una copia).
	 * @param vendible Vendible que se debe a�adir.
	 * @param cantidad Cantidad del vendible que se debe a�adir. No puede ser menor que 0.
	 * @param numLinea N�mero de l�nea de la m�quina a la que se debe a�adir. Debe estar sin asignar.
	 * @throws IllegalArgumentException Cantidad menor que 0.
	 * @throws IllegalStateException La l�nea est� asignada.
	 * @throws IllegalStateException M�quina fuera de servicio.
	 * @throws NullPointerException El vendible es null.
	 * @throws IndexOutOfBoundsException El n�mero de l�nea es mayor que el tama�o de la m�quina o menor que 0.
	 */
	public void asignarLinea(Vendible vendible, int cantidad, int numLinea){
		if (!getEstado()) {
			throw new IllegalStateException("M�quina fuera de servicio");
		}
		if (numLinea >= lineas.length || numLinea < 0) {
			throw new IndexOutOfBoundsException("La l�nea no existe");
		} 
		if (vendible == null) {
			throw new NullPointerException("Vendible == null");
		}
		if (lineas[numLinea]!=null) {
			throw new IllegalStateException("La l�nea est� asignada");
		}
		if (cantidad < 0) {
			throw new IllegalArgumentException("Cantidad < 0");
		}
		lineas[numLinea] = vendible;
		cantidades[numLinea] = cantidad;
	}
	
	
	/**
	 * Asigna un vendible a la m�quina en una posici�n concreta.
	 * Se asigna ese mismo vendible (no una copia).
	 * @param vendible Vendible que reemplaza al actual
	 * @param cantidad Cantidad del vendible que se debe a�adir. No puede ser menor que 0.
	 * @param numLinea N�mero de l�nea de la m�quina a la que se debe a�adir.
	 * @throws IllegalArgumentException Cantidad menor que 0.
	 * @throws IllegalStateException M�quina fuera de servicio.
	 * @throws NullPointerException El vendible es null.
	 * @throws IndexOutOfBoundsException El n�mero de l�nea es mayor que el tama�o de la m�quina o menor que 0.
	 */
	public void reemplazarLinea(Vendible vendible, int cantidad, int numLinea){
		if (!getEstado()) {
			throw new IllegalStateException("M�quina fuera de servicio");
		}
		if (vendible == null) {
			throw new NullPointerException("Producto == null");
		}
		if (numLinea >= lineas.length || numLinea < 0) {
			throw new IndexOutOfBoundsException("La l�nea no existe");
		}
		if (cantidad < 0) {
			throw new IllegalArgumentException("Cantidad < 0");
		}
		lineas[numLinea] = vendible;
		cantidades[numLinea] = cantidad;
	}
	
	
	/**
	 * A�ade o resta el numero de items indicado del vendible de la linea.
	 * @param numLinea N�mero de la l�nea en la que se encuentra el vendible.
	 * @param cambio Cantidad sumada o restada. No se debe restar m�s cantidad de lo que hay.
	 * @throws IllegalArgumentException Se est� restando m�s cantidad de la que hay.
	 * @throws IllegalStateException M�quina fuera de servicio.
	 */
	public void cambiarCantidadSuma(int numLinea, int cambio) {
		if (!getEstado()) {
			throw new IllegalStateException("M�quina fuera de servicio");
		}
		if (numLinea >= lineas.length) {
			throw new IndexOutOfBoundsException("La l�nea no existe");
		}
		if (lineas[numLinea] == null) {
			throw new IllegalStateException("La linea est� sin asignar");
		}
		if (cambio < 0 && cantidades[numLinea] < -cambio) {
			throw new IllegalArgumentException("Se est� restando m�s cantidad de la que hay");
		}
		cantidades[numLinea] += cambio;
	}

	
	/**
	 * Compra de la m�quina una cantidad concreta de un vendible de una l�nea,
	 * dada una tarjeta monedero. No se puede comprar en una m�quina fuera de servicio.
	 * @param tarjeta Tarjeta monedero. Debe tener saldo suficiente.
	 * @param linea Identificador de la l�nea de la que se quiere extraer el vendible.
	 * @throws IllegalArgumentException No hay ning�n vendible asignado a esa l�nea
	 * @throws IllegalArgumentException No hay suficiente cantidad de vendibles en esa l�nea.
	 * @throws IllegalArgumentException No hay saldo suficiente en la tarjeta.
	 * @throws NullPointerException Tarjeta es null.
	 * @throws IllegalStateException M�quina fuera de servicio.
	 */
	public void comprarItem(TarjetaMonedero tarjeta, int linea){
		if (!getEstado()) {
			throw new IllegalStateException("M�quina fuera de servicio");
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
	 * Devuelve un hash code para la m�quina.
	 * @return Valor del hash code de esta m�quina.
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
	 * no es null y es la misma m�quina u otra con los par�metros iguales.
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