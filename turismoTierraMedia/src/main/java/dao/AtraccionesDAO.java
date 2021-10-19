package dao;

import java.util.List;

import tierraMedia.Atracciones;

public interface AtraccionesDAO extends GenericDAO<Atracciones> {
	
	public abstract Atracciones findByName(String name);
	public abstract List<Atracciones> findByType (String Type);
	public abstract int updateCosto(Atracciones atraccion,double costo);
	public abstract int updateTiempo(Atracciones atraccioness,double tiempo);
	public abstract int updateType (Atracciones atraccion, String tipo);
	public abstract int updateCupo (Atracciones atraccion, int cupo);

}