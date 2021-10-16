package turismotierramedia;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class Sistema {

	// Se crea la lista de Usuarios EN BASE DE DATOS

	public static LinkedList<Usuario> getUsuario() throws SQLException {
		
		LinkedList<Usuario> usuario = new LinkedList<Usuario>();
			
		//declaro la base de datos
		String url="jdbc:sqlite:TierraMedia.db";
		Connection connection = DriverManager.getConnection(url);
		//declaro la tabla de usuarios
		String sql = "SELECT * FROM usuarios";
		PreparedStatement statement = connection.prepareStatement(sql);
		ResultSet usuariosdb= statement.executeQuery();
		//la linkedlist de usuarios esta declarada arriba
		while (usuariosdb.next()) {
			String nombre = usuariosdb.getString("nombre");
			int dinero= usuariosdb.getInt("dinero");
			double tiempo= usuariosdb.getDouble("tiempo");
			TipoAtraccion prefe= TipoAtraccion.valueOf(usuariosdb.getString("preferencia"));
			Usuario nuevoUsuario = new Usuario(nombre, dinero, tiempo, prefe);
			usuario.add(nuevoUsuario);
		}
		
		connection.close();
		// cierro archivo
		//connection.close();
		return usuario;
	}
	

			

	
	
	
	
	
	
	
	
	/*
	
	// Se crea la lista de Usuarios

	public static LinkedList<Usuario> getUsuario(String archivo) {
		LinkedList<Usuario> usuario = new LinkedList<Usuario>();
		Scanner sc = null;
		try {
			sc = new Scanner(new File(archivo));
			while (sc.hasNext()) {
				// Leo cada linea del archivo
				String linea = sc.nextLine();
				String datosDeUsuario[] = linea.split(",");
				// Creo un Usuario a partir de los datos leidos
				String nombre = String.valueOf(datosDeUsuario[0]);
				int dinero = Integer.parseInt(datosDeUsuario[1]);
				double tiempo = Double.parseDouble(datosDeUsuario[2]);
				TipoAtraccion prefe = TipoAtraccion.valueOf(datosDeUsuario[3]);
				Usuario nuevoUsuario = new Usuario(nombre, dinero, tiempo, prefe);
				// Agrego el usuario a la lista
				usuario.add(nuevoUsuario);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		// cierro archivo
		sc.close();
		return usuario;
	}
	*/
	

	// -------------------------------------------------
	// Abro archivos Atracciones

	public static LinkedList<Atracciones> getAtracciones(String archivo) {

		LinkedList<Atracciones> atracciones = new LinkedList<Atracciones>();

		Scanner sc = null;

		try {

			sc = new Scanner(new File(archivo));

			while (sc.hasNext()) {

				// Leo cada linea del archivo
				String linea = sc.nextLine();
				String datosAtracciones[] = linea.split(",");
				String nombreAtraccion = String.valueOf(datosAtracciones[0]);
				int costo = Integer.parseInt(datosAtracciones[1]);
				double tiempoAtraccion = Double.parseDouble(datosAtracciones[2]);
				int cupos = Integer.parseInt(datosAtracciones[3]);
				TipoAtraccion tipo = TipoAtraccion.valueOf(datosAtracciones[4]);

				Atracciones atraccion = new Atracciones(nombreAtraccion, costo, tiempoAtraccion, cupos, tipo);

				// Agrego las atracciones a la lista

				atracciones.add(atraccion);

			}
		} 
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		// cierro archivo
		sc.close();

		return atracciones;
	}

	// ---------------------------------------

	// ORDENAR PRODUCTOS POR PRECIO
	public static void ordenarPromosPorPrecio(List<Producto> producto) {
		Collections.sort(producto, new ProductosOrdenadosPrecio());
	}

	// --------------------
	// GRABAR COMPRAS

	public static void escribirCompras(String nombre, double tiempoTotal, double dineroTotal,
			List<Atracciones> producto, String file)

			throws IOException {

		// Se crea el printwriter
		PrintWriter salida = new PrintWriter(new FileWriter(file));

		// Escribe un encabezado con el nombre

		salida.println("Gracias : " + nombre + " por visitar Tierra Media");
		salida.println("---------------------------------------------");
		salida.println("Sus atracciones compradas son : ");
		salida.println("---------------------------------------------");

		// recorre la lista de compras que genero el usuario

		for (Atracciones compra : producto) {

			ArrayList<String> nombresAtrIncluidas = compra.getNombreAtracciones();

			for (int i = 0; i < nombresAtrIncluidas.size(); i++) {
				salida.println(nombresAtrIncluidas.get(i));
			}
		}

		// Escribe el pie con los totales de tiempo y dinero
		salida.println("---------------------------------------------");
		salida.println("Su gasto total es de " + dineroTotal + " monedas");
		salida.println("Su tiempo de permanencia " + tiempoTotal + " horas");

		salida.close();

	}

	// DEVUELVE productos QUE LE GUSTAN

	public static List<Producto> getSugerencias(List<Producto> productos, TipoAtraccion tipo) {

		List<Producto> sugerencias = new ArrayList<Producto>();
		List<Producto> queNoCoinciden = new ArrayList<Producto>();

		for (Producto ca : productos)

			if (ca.getTipoDeAtraccion() == tipo)
				sugerencias.add(ca);
			else
				queNoCoinciden.add(ca);

		// AGREGA LOS PRODUCTOS QUE NO COINCIDEN AL FINAL DE LA LISTA
		sugerencias.addAll(queNoCoinciden);

		return sugerencias;
	}

	// ---------------------------------------------------------------------------------
	// Abro archivo Promociones y creo Lista Producto con ellas

	public static LinkedList<Producto> getPromociones(String archivo) {

		LinkedList<Producto> productos = new LinkedList<Producto>();
		Scanner sc = null;

		try {
			sc = new Scanner(new File(archivo));

			while (sc.hasNext()) {

				// Leo cada linea del archivo
				String linea = sc.nextLine();

				String[] datosPromo = linea.split(",");

				String tipo = String.valueOf(datosPromo[0]);

				String nombrePromo = String.valueOf(datosPromo[1]);

				TipoAtraccion tipoP = TipoAtraccion.valueOf(datosPromo[2]);

				Producto prod = null;

				ArrayList<Atracciones> atracIncluidas = new ArrayList<>();

				// Agrega al array de atracciones aquellas incluidas en la Promo

				for (int i = 3; i < datosPromo.length - 1; i++) {
					atracIncluidas.add(obtenerAtraccion(datosPromo[i]));
				}

				if (tipo.equalsIgnoreCase("Por")) {
					/*
					 * PromoPorcentual la creo mandando el array atracciones en primer parametro y
					 * el dato del porcentaje de descuento como segundo mas nombre y tipo
					 */

					prod = new PromoPorcentaje(atracIncluidas,
							(double) Integer.parseInt(datosPromo[datosPromo.length - 1]), nombrePromo, tipoP);
				} else if (tipo.equalsIgnoreCase("AxB")) {
					/*
					 * PromoPorcentual la creo mandando el array atracciones en primer parametro y
					 * la atraccion que es gratuita en segundo mas nombre y tipo
					 */
					prod = new PromoAxB(atracIncluidas, obtenerAtraccion(datosPromo[datosPromo.length - 1]),
							nombrePromo, tipoP);
				} else if (tipo.equalsIgnoreCase("Abs")) {
					/*
					 * PromoPorcentual la creo mandando el array atracciones en primer parametro y
					 * el dato del costo final obtenido del archivo mas nombre y tipo
					 */
					prod = new PromoAbsoluta(atracIncluidas,
							(double) Integer.parseInt(datosPromo[datosPromo.length - 1]), nombrePromo, tipoP);
				}

				// Agrego la promo a la lista de productos
				productos.add(prod);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		// cierro archivo
		sc.close();

		return productos;
	}

	// Obtiene el objeto Atraccion a partir del String "nombreAtraccion" del archivo
	// promociones
	private static Atracciones obtenerAtraccion(String nombreAtraccion) {
		Atracciones atraccion = new Atracciones();

		// obtengo la lista a iterar
		LinkedList<Atracciones> lista = Sistema.getAtracciones("atracciones.in");

		if (atraccion != null) {
			// creo el objeto Iterator para recorrer la lista de Atracciones
			Iterator<Atracciones> atracIterar = lista.iterator();

			while (atracIterar.hasNext()) {
				Atracciones atrac = atracIterar.next();

				// si el nombre pasado como parametro coincide con el del iterador
				// devuelvo esa atraccion
				if (nombreAtraccion.equalsIgnoreCase(atrac.getNombreAtraccion())) {
					atraccion = atrac;
					break;
				}
			}
		}
		return atraccion;
	}

	// -------------------------------------------------------------------------------------

	// AGREGO LAS ATRACCIONES SIMPLES A LA LISTA PRODUCTO QUE TIENE LAS PROMOS
	// Recibe como parametro la lista tipo Producto con las promos

	public static List<Producto> getProductoFinal(List<Producto> listaProductos) {

		Producto productoAgregar = null;

		// obtengo la lista de atracciones
		LinkedList<Atracciones> lista = Sistema.getAtracciones("atracciones.in");

		// creo el objeto Iterator para recorrer la lista de Atracciones
		Iterator<Atracciones> Iterator = lista.iterator();

		while (Iterator.hasNext()) {
			Atracciones atrac = Iterator.next();

			// Creo el objeto a agregar
			// 14 del 10 avisar a delfi este constuctor etaba mal

			productoAgregar = new Atracciones(atrac.getNombreAtraccion(), atrac.getCostoAtraccion(),
					atrac.getDuracionAtraccion(), atrac.getCupoPersonas(), atrac.getTipoDeAtraccion());

			// Guarde la atraccion en la lista de tipo Producto
			listaProductos.add(productoAgregar);
		}

		return listaProductos;
	}
}