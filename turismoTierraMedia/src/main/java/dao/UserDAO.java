package dao;
import java.util.List;
import model.Usuario;
public interface UserDAO extends GenericDAO<Usuario> {
	
	public abstract Usuario findByUsername(String username);
	public abstract List<Usuario> findByPreference (String preference);
	public abstract int updateDinero(Usuario user);
	public abstract int updateTiempo(Usuario user);
	public abstract int updatePreferencia (Usuario user);

}
