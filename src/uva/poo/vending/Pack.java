package uva.poo.vending;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementación de un pack, formado por al menos dos productos.<br>
 * El precio del pack se calcula como la suma de los productos contenidos con un descuento del 20%.<br>
 * La fecha de caducidad del pack es la del producto que primero caduque.
 * @author juagonz0
 * @author sanbarb
 */
public class Pack extends Vendible{
	private ArrayList<Product> contenido;
	private String identificador;
	
	/**
	 * Inicializa el pack con una serie de productos, un nombre y un identificador.
	 * No hay restricciones en el identificador, a parte de no ser null o una cadena vacía.
	 * @param productos Array de productos del pack. Debe contener al menos dos productos y no se pueden repetir iguales.
	 * El array debe ser de la capacidad justa.
	 * @param nombre Nombre del pack. No puede ser un nombre vacío.
	 * @param identificador Identificador del pack. No puede ser un identificador vacío.
	 * @throws IllegalArgumentException Hay menos de 2 productos.
	 * @throws IllegalArgumentException Hay productos repetidos.
	 * @throws IllegalArgumentException El identificador está vacío.
	 * @throws IllegalArgumentException El nombre está vacío.
	 * @throws NullPointerException Hay algún producto null.
	 * @throws NullPointerException El identificador es null.
	 * @throws NullPointerException El nombre es null.
	 */
	public Pack(Product[] productos, String nombre, String identificador) {
		super(nombre);
		
		if (productos.length < 2) {
			throw new IllegalArgumentException("No hay suficientes productos");
		}
		contenido = new ArrayList<>();
		for (Product producto : productos) {
			if (producto == null) {
				throw new NullPointerException("Hay producto == null");
			}
			if (contenido.contains(producto)) {
				throw new IllegalArgumentException("Productos repetidos");
			}
			contenido.add(producto);
		}
		
		if (identificador == null) {
			throw new NullPointerException("Identificador == null");
		}
		if (identificador.equals("")) {
			throw new IllegalArgumentException("Identificador está vacío");
		}
		this.identificador = identificador;
	}

	
	/**
	 * Clona el pack generando una copia en profundidad.
	 * @return Pack clonado en profundidad.
	 */
	public Pack clonar(){
		Product[] nuevoContenido = new Product[getCantidadProductos()];
		for (int i = 0; i<getCantidadProductos(); i++) {
			nuevoContenido[i] = getProductos().get(i).clonar();
		}
		return new Pack(nuevoContenido, getNombre(), getIdentificador());
	}

	
	/**
	 * Devuelve el precio del pack. Consiste en la suma de los precios de los productos 
	 * con un 20% de descuento.
	 * @return Precio del pack.
	 */
	public double getPrecio() {
		double calculo = 0;
		for (Product producto : contenido) {
			calculo += producto.getPrecio();
		}
		return calculo*0.8;
	}

	
	/**
	 * Devuelve el identificador del pack.
	 * @return Identificador del pack.
	 */
	public String getIdentificador() {
		return identificador;
	}

	
	/**
	 * Asigna un nuevo identificador al pack.
	 * @param nuevoIdentificador Identificador del pack. No puede estar vacío.
	 * @throws IllegalArgumentException El identificador está vacío.
	 * @throws NullPointerException El identificador es null.
	 */
	public void setIdentificador(String nuevoIdentificador) {
		if (nuevoIdentificador == null) {
			throw new NullPointerException("Identificador == null");
		}
		if (nuevoIdentificador.equals("")) {
			throw new IllegalArgumentException("Identificador está vacío");
		}
		identificador = nuevoIdentificador;
	}
	
	
	
	/**
	 * Devuelve la fecha de caducidad del pack, que es la del producto que antes caduque.
	 * @return Fecha de caducidad del pack.
	 */
	public LocalDate getFechaConsumoPreferente() {
		LocalDate fechaConsumoPack = contenido.get(0).getFechaConsumoPreferente();
		for (Product producto : contenido) {
			if (producto.getFechaConsumoPreferente().isBefore(fechaConsumoPack)) {
				fechaConsumoPack = producto.getFechaConsumoPreferente();
			}
		}
		return fechaConsumoPack;
	}
	
	
	/**
	 * Devuelve la cantidad de productos contenidos en el pack.
	 * @return Cantidad de productos del pack.
	 */
	public int getCantidadProductos() {
		return contenido.size();
	}
	
	
	/**
	 * Comprueba si un producto forma parte del pack, o uno igual que él.
	 * @param producto Producto que se debe comprobar si pertenece al pack (o uno igual).
	 * @return true si está contenido, false si no.
	 * @throws NullPointerException El producto es null.
	 */
	public boolean contiene(Product producto) {
		if (producto == null) {
			throw new NullPointerException("Producto == null");
		}
		return contenido.contains(producto);
	}
	
	
	/**
	 * Devuelve un ArrayList con los productos contenidos.
	 * Las modificaciones en el <i>array</i> no afectarán al pack, pero a los productos sí.
	 * @return ArrayList con los productos del pack.
	 */
	public List<Product> getProductos() {
		return new ArrayList<>(contenido);
	}
	
	
	/**
	 * Añade un producto al pack. No puede haber un producto igual en el pack.
	 * @param producto Producto que se debe añadir. No puede ser null ni estar repetido.
	 * @throws IllegalArgumentException El producto está repetido (hay un producto igual).
	 * @throws NullPointerException El producto es null.
	 */
	public void anadirProducto(Product producto) {
		if (producto == null) {
			throw new NullPointerException("Producto == null");
		}
		if (contiene(producto)) {
			throw new IllegalArgumentException("Producto repetido");
		}
		contenido.add(producto);
	}
	
	
	/**
	 * Elimina un producto del pack. Deben quedar al menos dos productos.
	 * @param producto Producto igual al que se debe eliminar del pack.
	 * @throws IllegalArgumentException El producto no está presente en el pack.
	 * @throws IllegalArgumentException El pack ya contiene el mínimo de productos (2).
	 * @throws NullPointerException El producto es null.
	 */
	public void eliminarProducto(Product producto) {
		if (producto == null) {
			throw new NullPointerException("Producto == null");
		}
		if (!contenido.contains(producto)) {
			throw new IllegalArgumentException("Producto no existente en el pack");
		}
		if (contenido.size() == 2) {
			throw new IllegalStateException("El pack está en el mínimo de cantidad");
		}
		contenido.remove(producto);
	}


	/**
	 * Devuelve un hash code para el pack.
	 * @return Valor del hash code de este pack.
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = getNombre().hashCode();
		result = prime * result + getProductos().hashCode();
		result = prime * result + getIdentificador().hashCode();
		return result;
	}


	/**
	 * Compara este objeto con otro. El resultado es true si y solo si el argumento 
	 * no es null y es el mismo pack u otro con los parámetros iguales.
	 * El orden en el que se añaden los productos no afecta.
	 * @return true si los objetos son iguales, false si no.
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (this == obj) {
			return true;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Pack other = (Pack) obj;
		
		if (getCantidadProductos() != other.getCantidadProductos()) {
			return false;
		} else {
			// Dos packs son iguales independientemente del orden
			for (int i = 0; i<getProductos().size(); i++) {
				if (!other.getProductos().contains(getProductos().get(i))) {
					return false;
				}
			}
		}
		return (getNombre().equals(other.getNombre()) && getIdentificador().equals(other.getIdentificador()));
	}
	
	
	
	
}
