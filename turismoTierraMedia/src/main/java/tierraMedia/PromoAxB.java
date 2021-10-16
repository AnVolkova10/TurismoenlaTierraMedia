package tierraMedia;

import java.util.ArrayList;

import tierraMedia.Atracciones;
import tierraMedia.TipoAtraccion;

public class PromoAxB extends Producto{
	private Atracciones atrGratis;
	protected ArrayList<Atracciones> atracciones;

	public PromoAxB(ArrayList<Atracciones> atracciones, Atracciones atrGratis, String nombre,
			TipoAtraccion tipoAtraccion) {
		super(atracciones, nombre, tipoAtraccion);
		this.atrGratis = atrGratis;
		this.atracciones = atracciones;
		this.atracciones.add(atrGratis);
	}

	// Obtener precio CON descuento
	@Override
	public double getPrecioDescuento() {
		return this.getCostoTotal() - this.getDescuento();
	}

	public double getDescuento() {
		double descuento = this.atrGratis.getCostoAtraccion();
		return descuento;
	}

	// Obtener precio SIN descuento
	@Override
	public Double getCostoTotal() {
		return super.costoTotal + atrGratis.getCostoAtraccion();
	}

	// Obtener duracion sumando el tiempo de la Atraccion gratis
	public double getDuracionTotal() {
		return super.duracionTotal + atrGratis.getDuracionAtraccion();
	}

	@Override
	public void descontarCupoProducto() {
		for (int i = 0; i < this.atracciones.size(); i++) {
			this.atracciones.get(i).descontarCupoAtraccion();
		}
	}

	// obtener nombre atracciones inlcuidas
	@Override
	public ArrayList<String> getNombreAtracEnPromo() {
		ArrayList<String> nombres = new ArrayList<String>();

		for (int i = 0; i < this.atracciones.size(); i++) {
			nombres.add(this.atracciones.get(i).getNombreAtraccion());
		}
		return nombres;
	}

	@Override
	public boolean esPromo() {
		return true;
	}

	@Override
	public ArrayList<Atracciones> getAtraccionesPromo() {
		return this.atracciones;
	}

	@Override
	public Atracciones getAtraccion() {
		return null;
	}

}
