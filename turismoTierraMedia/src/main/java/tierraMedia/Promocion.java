package tierraMedia;

import java.util.ArrayList;
import java.util.List;

public abstract class Promocion extends Producto {
	private ArrayList<Atracciones> atracciones = new ArrayList<Atracciones>();

	// Constructor para Producto con Promos
	public Promocion(ArrayList<Atracciones> atracciones, String nombreProducto, TipoAtraccion tipoAtraccion) {
			this.setCostoTotal(atracciones);
			this.setDuracionTotal(atracciones);
			this.setNombreProducto(nombreProducto);
			this.setTipoAtraccion(tipoAtraccion);
			this.atracciones=atracciones;
		}
	public Promocion(ArrayList<Atracciones> atracciones, String nombreProducto, String tipoAtraccion) {
		this.setCostoTotal(atracciones);
		this.setDuracionTotal(atracciones);
		this.setNombreProducto(nombreProducto);
		TipoAtraccion stringToEnum = TipoAtraccion.valueOf(tipoAtraccion);
		this.setTipoAtraccion(stringToEnum);
		this.atracciones=atracciones;
	}

	public Promocion(ArrayList<Atracciones> atracciones, double costo, double duracion, String nombreProd,
			TipoAtraccion claseAtraccion) {
		super(costo, duracion, nombreProd, claseAtraccion);
	}

	public Promocion(ArrayList<Atracciones> atracciones, double costo, double duracion, String nombreProd,
			String claseAtraccion) {
		super(costo, duracion, nombreProd, claseAtraccion);
	}
	
	public void agregarAtraccion (Atracciones atraccion) {
		this.atracciones.add(atraccion);
	}
	
	public ArrayList<Atracciones> getAtracciones(){
		return this.atracciones;
	}

}
