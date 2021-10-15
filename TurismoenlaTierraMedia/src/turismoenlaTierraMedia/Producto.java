package turismoenlaTierraMedia;

import java.util.ArrayList;
import java.util.LinkedList;

public abstract class Producto {

	protected String nombreProducto;
	protected TipoAtraccion tipoAtraccion;
	protected double costoTotal;
	protected double duracionTotal;

	// Constructor para Producto con Promos
	public Producto(ArrayList<Atracciones> atracciones, String nombreProducto, TipoAtraccion tipoAtraccion) {
		this.setCostoTotal(atracciones);
		this.setDuracionTotal(atracciones);
		this.nombreProducto = nombreProducto;
		this.tipoAtraccion = tipoAtraccion;
	}

	// Constructor para Producto con Atracciones
	public Producto(double costo, double duracion, String nombreAtraccion, TipoAtraccion tipoDeAtraccion) {
		this.costoTotal = costo;
		this.duracionTotal = duracion;
		this.nombreProducto = nombreAtraccion;
		this.tipoAtraccion = tipoDeAtraccion;
	}

	// Constructor para Producto con Atracciones
	public Producto(double costo, double duracion, String nombreAtraccion) {
		this.costoTotal = costo;
		this.duracionTotal = duracion;
		this.nombreProducto = nombreAtraccion;
	}

	public Producto() {
	}

	public abstract ArrayList<String> getNombreAtracEnPromo();

	// Set el precio SIN el descuento
	public void setCostoTotal(ArrayList<Atracciones> atrIncluidas) {
		double costo = 0;
		for (int i = 0; i < atrIncluidas.size(); i++) {
			costo += atrIncluidas.get(i).getCostoAtraccion();
		}
		this.costoTotal = costo;
	}

	// Obtener el precio SIN descuento
	public Double getCostoTotal() {
		return this.costoTotal;
	}

	// Obtenga precio con descuento
	public abstract double getPrecioDescuento();

	// Set el tiempo total
	public void setDuracionTotal(ArrayList<Atracciones> atrIncluidas) {
		double tiempo = 0;
		for (int i = 0; i < atrIncluidas.size(); i++) {
			tiempo += atrIncluidas.get(i).getDuracionAtraccion();
		}
		this.duracionTotal = tiempo;
	}

	// descontar cupo
	public abstract void descontarCupoProducto();

	// Obtener duracion total
	public double getDuracionTotal() {
		return this.duracionTotal;
	}

	// obtenertipoAtraccion
	public TipoAtraccion getTipoDeAtraccion() {
		return this.tipoAtraccion;
	}

	// ve si no esta en itinerario
	public boolean noEstaEnItinerario(LinkedList<Atracciones> itinerario) {
		boolean noEncontrado = true;

		if (this.esPromo()) {
			ArrayList<String> nombresAtrIncluidas = this.getNombreAtracEnPromo();

			// if itinerario tiene alguna de las atracciones de la promo
			for (int j = 0; j < nombresAtrIncluidas.size(); j++) {
				for (int i = 0; i < itinerario.size(); i++) {
					if (itinerario.get(i).getNombreAtraccion().equals(nombresAtrIncluidas.get(j)))
						return false;
				}
			}
		} // SI no es promo se fija si contiene el nombre de la atraccion simple
		else {
			for (int i = 0; i < itinerario.size(); i++) {
				if (itinerario.get(i).getNombreAtraccion().equals(this.nombreProducto))
					return false;
			}
		}

		return noEncontrado;
	}

	// chequea si tiene cupo la promo o la atraccion
	public boolean tieneCupo() {
		boolean hayCupo = true;

		// si es promo chequea cada una de las atracciones que la componen
		if (this.esPromo()) {
			ArrayList<Atracciones> atrac = this.getAtraccionesPromo();
			for (int i = 0; i < atrac.size(); i++) {
				if (!atrac.get(i).tieneCupo())
					return false;
			}
		} // SI es atraccion se fija solo en esa
		else {
			if (!this.tieneCupo())
				return false;
		}

		return hayCupo;
	}

	// devuelve si es promo o no
	public abstract boolean esPromo();

	// si es promo obtiene las atracciones que incluye, si es atraccion no hace nada
	protected abstract ArrayList<Atracciones> getAtraccionesPromo();

	// si es atraccion obtiene a ella misma, si es promo no hace nada
	protected abstract Atracciones getAtraccion();
}
