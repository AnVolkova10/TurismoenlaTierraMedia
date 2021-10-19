package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import jdbc.ConnectionProvider;
import model.Usuario;
import tierraMedia.Atracciones;

public class AtraccionesDAOImpl implements AtraccionesDAO {

	public List<Atracciones> findAll() {
		try {
			String sql = "SELECT * FROM atracciones";
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);
			ResultSet results = statement.executeQuery();
			List<Atracciones> atracciones = new LinkedList<Atracciones>();
			while (results.next()) {
				atracciones.add(toAtraccion(results));
			}
			return atracciones;
		}catch (Exception e){
			throw new MissingDataException (e);
		}
	}

	public int countAll() {
		try {
			String sql = "SELECT COUNT(1) AS total FROM atracciones";
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

	public int insert(Atracciones atraccion) {
		try {
			String sql = "INSERT INTO atracciones (nombre_atraccion, costo, tiempo_recorrido, cupo_personas, tipo_atraccion) VALUES (?,?,?,?,?)";
			Connection conn = ConnectionProvider.getConnection();

			// Preparo la declaracion para SQL
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, atraccion.getNombreAtraccion());
			statement.setDouble(2, atraccion.getCostoTotal());
			statement.setDouble(3, atraccion.getDuracionAtraccion());
			statement.setInt(4, atraccion.getCupoPersonas());
			statement.setString(5, atraccion.getTipoDeAtraccion().toString());
			int rows = statement.executeUpdate();
			return rows;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}

	}

	public int delete(Atracciones atraccion) {
		try {
			String sql = "DELETE FROM atracciones WHERE nombre_atraccion LIKE ?";
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, atraccion.getNombreAtraccion());
			int rows = statement.executeUpdate();
			return rows;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	public Atracciones findByName(String name) {
		try {
			String sql = "SELECT * FROM atracciones WHERE nombre_atraccion LIKE ?";
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, name);
			ResultSet results = statement.executeQuery();
			Atracciones atraccion = null;
			if (results.next()) {
				atraccion = toAtraccion(results);
			}
			return atraccion;

		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	public List<Atracciones> findByType(String type) {
		try {
			String sql = "SELECT * FROM atracciones WHERE tipo_atraccion LIKE ?";
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(5, type);
			ResultSet results = statement.executeQuery();
			List<Atracciones> atracciones = new LinkedList<Atracciones>();
			while (results.next()) {
				atracciones.add(toAtraccion(results));
			
		}
			return atracciones;
			
		}catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	public int updateCosto(Atracciones atraccion, double costo) {
		try {
			String sql ="UPDATE atracciones SET costo = ? WHERE nombre_atraccion LIKE ?";
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement statement= conn.prepareStatement(sql);
			statement.setDouble(2, costo);
			statement.setString(1, atraccion.getNombreAtraccion());
			int rows = statement.executeUpdate();
			return rows;
			
		}catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	public int updateTiempo(Atracciones atraccion, double tiempo) {
		try {
			String sql ="UPDATE atracciones SET tiempo_recorrido = ? WHERE nombre_atraccion LIKE ?";
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement statement= conn.prepareStatement(sql);
			statement.setDouble(3, tiempo);
			statement.setString(1, atraccion.getNombreAtraccion());
			int rows = statement.executeUpdate();
			return rows;
			
		}catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	public int updateType(Atracciones atraccion, String tipo) {
		try {
			String sql ="UPDATE atracciones SET tipo_atraccion = ? WHERE nombre_atraccion LIKE ?";
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement statement= conn.prepareStatement(sql);
			statement.setString(5, tipo);
			statement.setString(1, atraccion.getNombreAtraccion());
			int rows = statement.executeUpdate();
			return rows;
			
		}catch (Exception e) {
			throw new MissingDataException(e);
		}
	}
	
	private Atracciones toAtraccion(ResultSet results) throws SQLException {
		return new Atracciones(results.getString(1), results.getDouble(2), results.getDouble(3),
				results.getInt(4), results.getString(5));
	}

	public int updateCupo(Atracciones atraccion, int cupo) {
		try {
			String sql ="UPDATE atracciones SET cupo_personas = ? WHERE nombre_atraccion LIKE ?";
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement statement= conn.prepareStatement(sql);
			statement.setInt(4, cupo);
			statement.setString(1, atraccion.getNombreAtraccion());
			int rows = statement.executeUpdate();
			return rows;
			
		}catch (Exception e) {
			throw new MissingDataException(e);
		}
	}


}
