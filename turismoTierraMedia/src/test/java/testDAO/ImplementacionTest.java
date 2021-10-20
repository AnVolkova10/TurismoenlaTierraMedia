package testDAO;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;

import org.junit.Test;
import dao.*;
import tierraMedia.*;
import jdbc.ConnectionProvider;
import model.Usuario;

public class ImplementacionTest {

	@Test
	public void getUserTest() {
		UserDAO users = new UserDAO();
		System.out.println(users.countAll());
		System.out.println(users.findAll().toString());
		Usuario Carlos = new Usuario("Carlos",15,25,"DEGUSTACION");
		//users.insert(Carlos);
		System.out.println(users.finByName("Sebastian").getNombreDeUsuario());
		Usuario angie = new Usuario("Angela", 10, 8, "AVENTURA");
		users.update(angie);
		users.delete(Carlos);
		
		
	}
	

}
