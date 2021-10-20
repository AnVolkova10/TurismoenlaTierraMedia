package dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import jdbc.ConnectionProvider;
import model.Usuario;

public class UserDAO implements GenericDAO<Usuario> {

	public List<Usuario> findAll() {
		try {
			String sql = "SELECT * FROM usuarios";
			Connection conn = ConnectionProvider.getConnection();

			PreparedStatement statement = conn.prepareStatement(sql);
			ResultSet results = statement.executeQuery();
			List<Usuario> users = new LinkedList<Usuario>();
			while (results.next()) {
				users.add(toUsuario(results));
			}
			
			return users;

		} catch (Exception e) {
			throw new MissingDataException(e);
		} 
	}
	
	public Usuario finByName (String name) {
		try {
			String sql = "SELECT * FROM usuarios WHERE nombre like ?";
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, name);
			ResultSet results = statement.executeQuery();
			Usuario nuevoUsuario = null;
			if (results.next()) {
				
				nuevoUsuario = toUsuario(results);
			}
			
			return nuevoUsuario;
			
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}
	
	public int countAll() {
		try {
			String sql = "SELECT COUNT(1) AS total FROM usuarios";
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

	public int insert(Usuario user) {
		try {
			String sql = "INSERT INTO usuarios (nombre, dinero, tiempo, preferencia) VALUES (?,?,?,?)";
			Connection conn = ConnectionProvider.getConnection();

			// Preparo la declaracion para SQL
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, user.getNombreDeUsuario());
			statement.setDouble(2, user.getDineroDisponible());
			statement.setDouble(3, user.getTiempoDisponible());
			statement.setString(4, user.getPreferencia().toString());
			int rows = statement.executeUpdate();
			
			return rows;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	public int delete(Usuario user) {
		try {
			String sql = "DELETE FROM usuarios WHERE nombre LIKE ?";
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, user.getNombreDeUsuario());
			int rows = statement.executeUpdate();
			
			return rows;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	public int update(Usuario user) {
		try {
			String sql = "UPDATE usuarios SET dinero = ?, tiempo = ?, preferencia = ? WHERE nombre LIKE ?";
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setDouble(1, user.getDineroDisponible());
			statement.setDouble(2, user.getTiempoDisponible());
			statement.setString(3, user.getPreferencia().toString());
			statement.setString(4, user.getNombreDeUsuario());			
			int rows = statement.executeUpdate();
			
			return rows;

		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}
	
	private Usuario toUsuario(ResultSet results) throws SQLException {
		return new Usuario(results.getString("nombre"), results.getDouble("dinero"), results.getDouble("tiempo"),
				results.getString("preferencia"));
	}
	
	
}
