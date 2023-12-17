package uva.poo.vending;

import java.time.LocalDate;

/**
 * Implementación de un producto. Almacena la información sobre su precio, su fecha de consumo
 * preferente, su nombre y su <i>Universal Product Code</i> (UPC).
 * @author juagonz0
 * @author sanbarb
 */
public class Product extends Vendible{
	private double  precio;	
	private LocalDate fechaConsumoPreferente;
	private String upc;
	
	
	/**
	 * Inicializa el producto con un precio, una fecha de consumo preferente, un nombre
	 * y un UPC.
	 * @param precio Precio en euros del producto. No puede ser menor o igual que 0.
	 * @param caducidad Fecha de consumo preferente.
	 * @param nombre Nombre del producto. No puede ser un nombre vacío.
	 * @param upc <i>Universal Product Code</i>. Debe cumplir las condiciones del dígito de control.
	 * @throws IllegalArgumentException El precio es menor o igual que 0.
	 * @throws IllegalArgumentException El UPC no cumple las condiciones.
	 * @throws IllegalArgumentException El nombre está vacío.
	 * @throws NullPointerException La fecha es null.
	 * @throws NullPointerException El nombre es null.
	 * @throws NullPointerException El UPC es null.
	 */
	public Product(double precio, LocalDate caducidad, String nombre, String upc){
		super(nombre);
		if (precio<=0) {
			throw new IllegalArgumentException("El precio es <= 0");
		}
		if (caducidad == null) {
			throw new NullPointerException("Caducidad == null");
		} 
		if (upc == null) {
			throw new NullPointerException("UPC == null");
		}
		if (!comprobarUPC(upc)) {
			throw new IllegalArgumentException("UPC no válido");
		} 
		
		this.precio = precio;
		fechaConsumoPreferente = caducidad;
		this.upc = upc;
	}

	
	/**
	 * Clona el producto generando una copia.
	 * @return Producto clonado.
	 */
	public Product clonar() {
	    return new Product(getPrecio(), getFechaConsumoPreferente(), getNombre(), getIdentificador());
	}
	
	
	/**
	 * Devuelve el precio del producto.
	 * @return Precio del producto.
	 */
	public double getPrecio() {
		return precio;
	}

	
	/**
	 * Asigna un nuevo precio al producto
	 * @param nuevoPrecio Precio en euros del producto. No puede ser menor o igual que 0.
	 * @throws IllegalArgumentException El precio es menor o igual que 0.
	 */
	public void setPrecio(double nuevoPrecio){
		if (nuevoPrecio<=0) {
			throw new IllegalArgumentException("El precio es <= 0");
		} 
		precio = nuevoPrecio;
	}
	

	/**
	 * Devuelve la fecha de caducidad del producto.
	 * @return Fecha de caducidad del producto.
	 */
	public LocalDate getFechaConsumoPreferente() {
		return fechaConsumoPreferente; //La fecha es inmutable, podemos devolverla tal cual sin peligro
	}
	
	
	/**
	 * Asigna una nueva fecha de caducidad al producto.
	 * @param nuevaCaducidad Fecha de consumo preferente que se quiere asignar.
	 */
	public void setFechaConsumoPreferente(LocalDate nuevaCaducidad) {
		if (nuevaCaducidad == null) {
			throw new NullPointerException("Caducidad == null");
		} 
		fechaConsumoPreferente = nuevaCaducidad;
	}

	
	/**
	 * Devuelve el UPC del producto.
	 * @return Universal Product Code.
	 */
	public String getIdentificador() {
		return upc;
	}
	
	
	/**
	 * Asigna un nuevo identificador al producto. Debe cumplir la especificación del UPC.
	 * @param nuevoIdentificador Identificador del producto. No puede estar vacío y debe ser un UPC válido.
	 * @throws IllegalArgumentException El UPC no cumple las condiciones.
	 * @throws NullPointerException El UPC es null.
	 */
	public void setIdentificador(String nuevoIdentificador) {
		if (nuevoIdentificador == null) {
			throw new NullPointerException("UPC == null");
		}
		if (!comprobarUPC(nuevoIdentificador)) {
			throw new IllegalArgumentException("UPC no válido");
		}
		upc = nuevoIdentificador;
	}
	
	
	/**
	 * Comprueba la validez de un <i>Universal Product Code</i> de 12 dígitos.
	 * @param upc El número UPC que se desea comprobar.
	 * @return true si es válido, false si no lo es.
	 */
	private static boolean comprobarUPC(String upc) {
		boolean resultado = false;
		if (upc.length() == 12) { //Tiene que estar en el intervalo
			char[] digitos = upc.toCharArray();
			long suma = 0;
			for (int i = 0; i<11; i++) {
				int digito = Character.getNumericValue(digitos[i]);
				if (digito < 0 || digito > 9) return false;
				
				suma += (i%2 == 0) ? digito*3 : digito;
			}
			long m = (suma % 10 == 0) ? suma : suma + 10 - (suma % 10);
			
			if ((int) Math.abs(suma - m) == Character.getNumericValue(digitos[11])) {
				resultado = true;
			}
		}
		return resultado;
	}


	/**
	 * Devuelve un hash code para el producto.
	 * @return Valor del hash code de este producto.
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = getNombre().hashCode();
		result = prime * result + getFechaConsumoPreferente().hashCode();
		long temp;
		temp = Double.doubleToLongBits(precio);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + getIdentificador().hashCode();
		return result;
	}


	/**
	 * Compara este objeto con otro. El resultado es true si y solo si el argumento 
	 * no es null y es el mismo produto u otro con los parámetros iguales.
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
		Product other = (Product) obj;
		return (getNombre().equals(other.getNombre()) && getIdentificador().equals(other.getIdentificador()) && 
				Double.doubleToLongBits(getPrecio()) == Double.doubleToLongBits(other.getPrecio()) &&
				getFechaConsumoPreferente().equals(other.getFechaConsumoPreferente()));
	}
}