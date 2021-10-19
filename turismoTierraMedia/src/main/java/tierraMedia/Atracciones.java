package tierraMedia;

import java.util.ArrayList;

import tierraMedia.Atracciones;
import tierraMedia.Producto;
import tierraMedia.TipoAtraccion;

public class Atracciones extends Producto implements Comparable<Atracciones>{
	private int cupoPersonas;

	public Atracciones(String nombreAtraccion, double costoAtraccion, double duracionAtraccion, int cupoPersonas,
			TipoAtraccion tipoDeAtraccion) {
		super(costoAtraccion, duracionAtraccion, nombreAtraccion, tipoDeAtraccion);
		this.cupoPersonas = cupoPersonas;
	}
	public Atracciones(String nombreAtraccion, double costoAtraccion, double duracionAtraccion, int cupoPersonas,
			String tipoDeAtraccion) {
		super(costoAtraccion, duracionAtraccion, nombreAtraccion, tipoDeAtraccion);
		this.cupoPersonas = cupoPersonas;
	}

	public Atracciones() {
		super();
	}

	// informa el nombre de la atraccion
	public String getNombreAtraccion() {
		return super.nombreProducto;
	}

	// informa el costo de la atraccion.
	public double getCostoAtraccion() {
		return super.costoTotal;
	}

	// informa la duracion de la atraccion.
	public double getDuracionAtraccion() {
		return super.duracionTotal;
	}

	// informa el cupo de personas que queda.
	public int getCupoPersonas() {
		return cupoPersonas;
	}

	public boolean tieneCupo() {
		return this.getCupoPersonas() > 0;
	}

	// Descuenta 1 persona al total
	public void descontarCupoAtraccion() {
		this.cupoPersonas -= 1;
	}

	// Descuenta 1 persona al total
	@Override
	public void descontarCupoProducto() {
		this.descontarCupoAtraccion();
	}

	// Para la lista producto
	@Override
	public double getPrecioDescuento() {
		return super.costoTotal;
	}

	@Override
	public ArrayList<String> getNombreAtracEnPromo() {
		return null;
	}

	
	public int compareTo(Atracciones o) {
		return super.nombreProducto.compareTo(o.nombreProducto);

	}

	@Override
	public boolean esPromo() {
		return false;
	}

	@Override
	public Atracciones getAtraccion() {
		return this;
	}

	@Override
	public ArrayList<Atracciones> getAtraccionesPromo() {
		return null;
	}

}
