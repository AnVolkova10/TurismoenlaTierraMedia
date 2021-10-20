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
	public void userTest() {
		UserDAO users = new UserDAO();
		System.out.println(users.countAll());
		System.out.println(users.findAll().toString());
		Usuario Carlos = new Usuario("Carlos",15,25,"DEGUSTACION");
		users.insert(Carlos);
		System.out.println(users.findByName("Sebastian").getNombreDeUsuario());
		Usuario angie = new Usuario("Angela", 10, 8, "AVENTURA");
		//users.update(angie);
		users.delete(Carlos);
		
		
	}
	
	@Test
	public void atraccionesTest() {
		AtraccionesDAO atrac = new AtraccionesDAO();
		atrac.countAll();
		assertEquals(14, atrac.countAll());
		System.out.println(atrac.findAll().toString());
		
		
		
	}
}
