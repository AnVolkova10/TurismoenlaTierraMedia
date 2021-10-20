package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import jdbc.ConnectionProvider;
import model.Usuario;
import tierraMedia.Atracciones;
import tierraMedia.Producto;
import tierraMedia.PromoAbsoluta;
import tierraMedia.PromoAxB;
import tierraMedia.PromoPorcentaje;

public class PromocionesDAO implements GenericDAO<Producto> {

	public List<Producto> findAll() {
		try {
			String sql = "SELECT * FROM promociones";
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);
			ResultSet results = statement.executeQuery();
			List<Producto> promos = new LinkedList<Producto>();
			while (results.next()) {
				promos.add(toPromocion(results));
			}
			return promos;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	public int countAll() {
		try {
			String sql = "SELECT COUNT(1) AS total FROM promociones";
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);
			ResultSet results = statement.executeQuery();
			results.next();
			int total = results.getInt("total");
			return total;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	public int insert(PromoAbsoluta promo) {
		try {
			String sql = "INSERT INTO promociones (nombre_promo, tipo_atraccion, incluye1, incluye2, descuento_absoluto) VALUES (?,?,?,?,?)";
			Connection conn = ConnectionProvider.getConnection();

			// Preparo la declaracion para SQL
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, promo.getNombreProducto());
			statement.setString(2, promo.getTipoDeAtraccion().toString());
			statement.setString(3, promo.getAtracciones().get(0).getNombreAtraccion());
			statement.setString(4, promo.getAtracciones().get(1).getNombreAtraccion());
			statement.setDouble(5, promo.getPrecioDescuento());

			int rows = statement.executeUpdate();
			return rows;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}
	
	public int insert(PromoPorcentaje promo) {
		try {
			String sql = "INSERT INTO promociones (nombre_promo, tipo_atraccion, incluye1, incluye2, porcentaje_descuento) VALUES (?,?,?,?,?)";
			Connection conn = ConnectionProvider.getConnection();

			// Preparo la declaracion para SQL
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, promo.getNombreProducto());
			statement.setString(2, promo.getTipoDeAtraccion().toString());
			statement.setString(3, promo.getAtracciones().get(0).getNombreAtraccion());
			statement.setString(4, promo.getAtracciones().get(1).getNombreAtraccion());
			statement.setDouble(5, promo.getPorcentajeDescuento());

			int rows = statement.executeUpdate();
			return rows;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}
	
	public int insert(PromoAxB promo) {
		try {
			String sql = "INSERT INTO promociones (nombre_promo, tipo_atraccion, incluye1, incluye2, gratis) VALUES (?,?,?,?,?)";
			Connection conn = ConnectionProvider.getConnection();

			// Preparo la declaracion para SQL
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, promo.getNombreProducto());
			statement.setString(2, promo.getTipoDeAtraccion().toString());
			statement.setString(3, promo.getAtracciones().get(0).getNombreAtraccion());
			statement.setString(4, promo.getAtracciones().get(1).getNombreAtraccion());
			statement.setString(5, promo.getAtraccionGratuita().getNombreAtraccion());

			int rows = statement.executeUpdate();
			return rows;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	public int delete(Producto promo) {
		try {
			String sql = "DELETE FROM promociones WHERE nombre_promo LIKE ?";
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, promo.getNombreProducto());
			int rows = statement.executeUpdate();
			return rows;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	public int update(Producto promo) {
		return 0;
	}
	
	private Producto toPromocion(ResultSet results) throws SQLException {
		if (results.getString(1)== "AxB") {			
			ArrayList<Atracciones> atr = new ArrayList<Atracciones>();
			AtraccionesDAO dao1= new AtraccionesDAO();
			Atracciones atr1= dao1.findByName(results.getString(4));
			Atracciones atr2= dao1.findByName(results.getString(5));
			atr.add(atr1);
			atr.add(atr2);
			Atracciones atrG= dao1.findByName(results.getString(6));
			Producto axb = new PromoAxB(atr, atrG, results.getString(2),results.getString(3));
			return axb;
		}
		else if (results.getString(1)== "Por") {			
			ArrayList<Atracciones> atr = new ArrayList<Atracciones>();
			AtraccionesDAO dao1= new AtraccionesDAO();
			Atracciones atr1= dao1.findByName(results.getString(4));
			Atracciones atr2= dao1.findByName(results.getString(5));
			atr.add(atr1);
			atr.add(atr2);
			
			Producto por = new PromoPorcentaje(atr, results.getDouble(7), results.getString(2),results.getString(3));
			return por;
		}else if (results.getString(1)== "Abs") {			
			ArrayList<Atracciones> atr = new ArrayList<Atracciones>();
			AtraccionesDAO dao1= new AtraccionesDAO();
			Atracciones atr1= dao1.findByName(results.getString(4));
			Atracciones atr2= dao1.findByName(results.getString(5));
			atr.add(atr1);
			atr.add(atr2);
			
			Producto abs = new PromoAbsoluta(atr, results.getDouble(8), results.getString(2),results.getString(3));
			return abs;
			}
		return null;
		
	}

	

	public Producto findByName(String name) {
		try {
			String sql = "SELECT * FROM promociones WHERE nombre_promo like ?";
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, name);
			ResultSet results = statement.executeQuery();
			Producto nuevaPromo = null;
			if (results.next()) {
				
				nuevaPromo = toPromocion(results);
			}
			
			return nuevaPromo;
			
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	public int insert(Producto t) {
		// TODO Auto-generated method stub
		return 0;
	}

	

}
