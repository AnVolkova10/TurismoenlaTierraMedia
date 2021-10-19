package dao;
import java.util.List;
import model.Usuario;
public interface UserDAO extends GenericDAO<Usuario> {
	
	public abstract Usuario findByName(String name);
	public abstract List<Usuario> findByPreference (String preference);
	public abstract int updateDinero(Usuario user, double dinero);
	public abstract int updateTiempo(Usuario user, double tiempo);
	public abstract int updatePreferencia (Usuario user, String pref);

}
