package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.LinkedList;
import model.Usuario;
import jdbc.ConnectionProvider;
import java.sql.SQLException;

public class UserDAOImpl implements UserDAO {

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
		// intento insertar, en caso contrario arroja Exception
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

	public Usuario findByName(String name) {
		try {
			String sql = "SELECT * FROM usuarios WHERE nombre LIKE ?";
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, name);
			ResultSet results = statement.executeQuery();
			Usuario user = null;
			if (results.next()) {
				user = toUsuario(results);
			}
			return user;

		} catch (Exception e) {
			throw new MissingDataException(e);
		}

	}

	public List<Usuario> findByPreference(String preference) {
		try {
			String sql = "SELECT * FROM usuarios WHERE prefererencia LIKE ?";
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(4, preference);
			ResultSet results = statement.executeQuery();
			List<Usuario> users = new LinkedList<Usuario>();
			while (results.next()) {
				users.add(toUsuario(results));
			
		}
			return users;
			
		}catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	public int updateDinero(Usuario user, double dinero) {
		try {
			String sql ="UPDATE usuarios SET dinero = ? WHERE nombre LIKE ?";
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement statement= conn.prepareStatement(sql);
			statement.setDouble(2, dinero);
			statement.setString(1, user.getNombreDeUsuario());
			int rows = statement.executeUpdate();
			return rows;
			
		}catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	public int updateTiempo(Usuario user, double tiempo) {
		try {
			String sql ="UPDATE usuarios SET tiempo = ? WHERE nombre LIKE ?";
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement statement= conn.prepareStatement(sql);
			statement.setDouble(3, tiempo);
			statement.setString(1, user.getNombreDeUsuario());
			int rows = statement.executeUpdate();
			return rows;
			
		}catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	public int updatePreferencia(Usuario user, String pref) {
		try {
			String sql ="UPDATE usuarios SET preferencia = ? WHERE nombre LIKE ?";
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement statement= conn.prepareStatement(sql);
			statement.setString(4, pref);
			statement.setString(1, user.getNombreDeUsuario());
			int rows = statement.executeUpdate();
			return rows;
			
		}catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	private Usuario toUsuario(ResultSet results) throws SQLException {
		return new Usuario(results.getString("nombre"), results.getDouble("dinero"), results.getDouble("tiempo"),
				results.getString("preferencia"));
	}

}
