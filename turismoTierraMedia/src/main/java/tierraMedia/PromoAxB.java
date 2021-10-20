package tierraMedia;

import java.util.ArrayList;
import java.util.List;

import tierraMedia.Atracciones;
import tierraMedia.TipoAtraccion;

public class PromoAxB extends Promocion{
	private Atracciones atrGratis;
	

	public PromoAxB(ArrayList<Atracciones> atracciones, Atracciones atrGratis, String nombre,
			TipoAtraccion tipoAtraccion) {
		super(atracciones, nombre, tipoAtraccion);
		this.atrGratis = atrGratis;
		this.agregarAtraccion(atrGratis);
	}
	
	public PromoAxB(ArrayList<Atracciones> atracciones, Atracciones atrGratis, String nombre,
			String tipoAtraccion) {
		super(atracciones, nombre, tipoAtraccion);
		this.atrGratis = atrGratis;
		this.agregarAtraccion(atrGratis);
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
		return super.getCostoTotal() + atrGratis.getCostoAtraccion();
	}

	// Obtener duracion sumando el tiempo de la Atraccion gratis
	public double getDuracionTotal() {
		return super.getDuracionTotal() + atrGratis.getDuracionAtraccion();
	}

	@Override
	public void descontarCupoProducto() {
		for (int i = 0; i < this.getAtracciones().size(); i++) {
			this.getAtracciones().get(i).descontarCupoAtraccion();
		}
	}

	// obtener nombre atracciones inlcuidas
	@Override
	public ArrayList<String> getNombreAtracEnPromo() {
		ArrayList<String> nombres = new ArrayList<String>();

		for (int i = 0; i < this.getAtracciones().size(); i++) {
			nombres.add(this.getAtracciones().get(i).getNombreAtraccion());
		}
		return nombres;
	}

	@Override
	public boolean esPromo() {
		return true;
	}

	@Override
	public ArrayList<Atracciones> getAtraccionesPromo() {
		return this.getAtracciones();
	}

	
	public Atracciones getAtraccionGratuita() {
		return this.atrGratis;
	}

	@Override
	public Atracciones getAtraccion() {
		return this.getAtraccion();
		
	}

}
