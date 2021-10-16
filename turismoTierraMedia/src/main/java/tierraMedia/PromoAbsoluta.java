package tierraMedia;

import java.util.ArrayList;

import tierraMedia.Atracciones;
import tierraMedia.TipoAtraccion;

public class PromoAbsoluta extends Producto{
	private Double precioFinal;
	protected ArrayList<Atracciones> atracciones;

	public PromoAbsoluta(ArrayList<Atracciones> atracciones, Double precioFinal, String nombre,
			TipoAtraccion tipoAtraccion) {
		super(atracciones, nombre, tipoAtraccion);
		this.setDescuentoAbsoluto(precioFinal);
		this.atracciones = atracciones;
	}

	private void setDescuentoAbsoluto(Double precio) {
		this.precioFinal = precio;
	}

	// Obtener precio CON descuento
	@Override
	public double getPrecioDescuento() {
		return this.precioFinal;
	}

	// Descuento un lugar al cupo de las atracciones que incluye esta promo
	@Override
	public void descontarCupoProducto() {
		for (int i = 0; i < this.atracciones.size(); i++) {
			this.atracciones.get(i).descontarCupoAtraccion();
		}
	}

	// Obtener el nombre de las atracciones
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
