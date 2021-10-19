package dao;

public class DAOFactory {
	public static UserDAO getUserDAO() {
		return new UserDAOImpl();
		
	}
	public static AtraccionesDAO getAtraccionDAO() {
		return new AtraccionesDAOImpl();
	}

}
