package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import jdbc.ConnectionProvider;
import tierraMedia.PromoAbsoluta;
import tierraMedia.PromoAxB;
import tierraMedia.PromoPorcentaje;
import tierraMedia.Promocion;

public class PromocionesDAO implements GenericDAO<Promocion> {

	public List<Promocion> findAll() {
		try {
			String sql = "SELECT * FROM promociones";
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);
			ResultSet results = statement.executeQuery();
			List<Promocion> promos = new LinkedList<Promocion>();
			while (results.next()) {
				promos.add(results);
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

	public int delete(Promocion promo) {
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

	public int update(Promocion t) {
		// TODO Auto-generated method stub
		return 0;
	}
	private Promocion toPromocion(ResultSet results) throws SQLException {
		if (results.getString(1)== "AxB") {
			return new PromoAxB(results.getString(2), results.getString)
		}
		
	}

	public int insert(Promocion t) {
		// TODO Auto-generated method stub
		return 0;
	}

}
