package TurismoenlaTierraMedia;

public class PromoAbsoluta extends Producto {
	private Double precioFinal;

	public PromoAbsoluta(Atracciones[] atracciones, Double precioFinal) {
		super(atracciones);
		this.setDescuentoAbsoluto(precioFinal);
	}

	private void setDescuentoAbsoluto(Double precio) {
		this.precioFinal = precio;
	}

	// Obtener precio CON descuento
	@Override
	public Double getPrecioDescuento() {
		return this.precioFinal;
	}


}