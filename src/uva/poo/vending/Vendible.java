package uva.poo.vending;

import java.time.LocalDate;

/**
 * Abstracción de un vendible de una VendingMachine. Implementa el control del nombre, y el resto de métodos 
 * se dejan como abstractos.
 * @author juagonz0
 * @author sanbarb
 */
public abstract class Vendible {

	private String nombre;
	
	
	/**
	 * Devuelve el precio del vendible.
	 * @return Precio del vendible.
	 */
	public abstract double getPrecio();
	
	
	/**
	 * Devuelve el identificador del vendible. Algunas implementaciones pueden imponer
	 * ciertas restricciones sobre el tipo de identificadores que se pueden usar.
	 * @return Identificador del vendible.
	 */
	public abstract String getIdentificador();
	
	
	/**
	 * Asigna un nuevo identificador al vendible. Algunas implementaciones pueden imponer
	 * ciertas restricciones sobre el tipo de identificadores que se pueden usar. 
	 * @param nuevoIdentificador Identificador del vendible.
	 */
	public abstract void setIdentificador(String nuevoIdentificador);
	
	
	/**
	 * Devuelve la fecha de caducidad del vendible.
	 * @return Fecha de caducidad.
	 */
	public abstract LocalDate getFechaConsumoPreferente();
	
	
	/**
	 * Clona el vendible generando una copia.
	 * @return Vendible clonado.
	 */
	public abstract Vendible clonar();
	
	
	/**
	 * Inicializa el vendible con un nombre, que no puede estar vacío.
	 * @param nombre Nombre del vendible. No puede ser un nombre vacío.
	 * @throws IllegalArgumentException El nombre está vacío.
	 * @throws NullPointerException El nombre es null.
	 */
	protected Vendible(String nombre) {
		if (nombre == null) {
			throw new NullPointerException("Nombre == null");
		}
		if (nombre.equals("")) {
			throw new IllegalArgumentException("Nombre está vacío");
		}
		this.nombre = nombre;
	}
	
	
	/**
	 * Devuelve el nombre del vendible.
	 * @return El nombre del vendible.
	 */
	public String getNombre() {
		return nombre;
	}
	
	
	/**
	 * Asigna un nuevo nombre al vendible.
	 * @param nuevoNombre Nombre del vendible. No puede ser un nombre vacío.
	 * @throws IllegalArgumentException El nombre está vacío.
	 * @throws NullPointerException El nombre es null.
	 */
	public void setNombre(String nuevoNombre) {
		if (nuevoNombre == null) {
			throw new NullPointerException("Nombre == null");
		}
		if (nuevoNombre.equals("")) {
			throw new IllegalArgumentException("Nombre está vacío");
		}
		nombre = nuevoNombre;
	}
}