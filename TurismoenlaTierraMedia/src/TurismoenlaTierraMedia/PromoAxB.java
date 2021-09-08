package TurismoenlaTierraMedia;

public class PromoAxB extends Producto{
	
	private Atracciones atrGratis;
	protected Atracciones [] atracciones;

	public PromoAxB(Atracciones[] atracciones, Atracciones atrGratis) {
		super(atracciones);
		this.atrGratis = atrGratis;
		this.atracciones = atracciones;
	}

	// Obtener precio CON descuento
	@Override
	public Double getPrecioDescuento() {
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
	public Double getDuracionTotal() {
		return super.duracionTotal + atrGratis.getDuracionAtraccion();
	}
	 
	
	// Para que guarde en atracciones incluidas la atraccion Gratis
	public Atracciones[] getAtracciones() {
		Atracciones [] atrIncluidas = new Atracciones[3];
		for(int i = 0; i < this.atracciones.length + 1; i++) {
			if(i == this.atracciones.length) {
				atrIncluidas[i] = this.atrGratis;
			}
			else {
				atrIncluidas[i] = this.atracciones[i];
			}
		}
		return atrIncluidas;
	}
	
	// obtener nombre atracciones
	@Override
	public String[] getNombreAtracciones() {
		String [] nombres = new String [3];
		Atracciones [] atracciones = this.getAtracciones();
		for (int i = 0; i < atracciones.length; i++) {
			nombres[i] = atracciones[i].getNombreAtraccion();
		}
		return nombres;
	}
}
