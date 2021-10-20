package turismotierramedia;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import dao.*;

public class AppTierraMedia {

	public static void main(String[] args)  {
		
		//Conecta a la Base de datos
		UserDAO usercon = DAOFactory.getUserDAO();
		
		System.out.println("App Tierra Media");
		System.out.println("-------------------------");
		
		//Solicita el ingreso de usuario
		
		System.out.println("Ingrese su usuario : ");
		Scanner userIng = new Scanner(System.in);
		String opt = userIng.next();
		Usuario user = usercon.findByName(opt);

		System.out.println(user.getNombreDeUsuario()+" bienvenido a Tierra Media");
		
		//System.out.println("Su saldo es: " + user.getDineroDisponible() + ". Su tiempo disponible es: "
			//	+ user.getTiempoDisponible());
		
		
		//Se crea lista de atracciones	
		AtraccionesDAO atrDAO = new AtraccionesDAO();
		List<Atracciones> atracciones2 = atrDAO.findAll();
		
		System.out.println("mostrar atracciones");
		System.out.println(atracciones2);
		
		
		//Crear Lista de Promociones
		//List<Producto> promociones = Sistema.getPromociones("promociones.txt");
		PromocionesDAO proDAO = new PromocionesDAO();
		List<Producto> promociones = proDAO.findAll();
		
		System.out.println("ver promociones");
		System.out.println(promociones);
		
		//Se crea lista de Sugerencias
		LinkedList<Producto> sugerencias = new LinkedList<Producto>();
		
		//agrego atracciones a sugerencias
		sugerencias.addAll(atracciones2);
	
		
		
		
		
		
		

		/*List<Producto> promociones = Sistema.getPromociones("promociones.txt");

		// Crea una lista tipo Producto con todos los productos finales
		List<Producto> productosFinales = Sistema.getProductoFinal(promociones);

		Sistema.ordenarPromosPorPrecio(productosFinales);

		// ------------------------------------------------------------------------------------------------------
*/
	
			double dineroTotal = 0;
			double tiempoTotal = 0;
/*
			LinkedList<Producto> sugerencias = new LinkedList<Producto>();

			sugerencias.addAll(Sistema.getSugerencias(productosFinales, user.getPreferencia()));

			*/
			
			// SE CREA LISTA PARA GUARDAR ITINERARIO
			LinkedList<Atracciones> itinerario = new LinkedList<Atracciones>();

			/*System.out.println(user.getNombreDeUsuario() + " Bienvenido/a Tierra Media");
			System.out.println("--------------------------------------------");
			System.out.println("Tenemos las siguientes Atracciones para vos!");

			// >>> PRIMERO SUGIERE SEGUN SU PREFERENCIA DE TIPO DE ATRACCION
			// >>> LUEGO OTRAS OPCIONES
		*/

			for (Producto producto : sugerencias) {

				// SI PUDE COMPRAR, NO LA COMPRO AUN Y TIENE CUPO SUGIERE
				if (user.puedeComprar(producto) && producto.noEstaEnItinerario(itinerario)
						&& producto.tieneCupo()) {

					//este array me da errorrrrrrrrr
					//ArrayList<String> nombresAtrIncluidas = producto.getNombreAtracciones();
					ArrayList<String> nombresAtrIncluidas = producto.getNombreAtracEnPromo();
					
					// IMPRIME POR CONSOLA SALDO Y TIEMPO DIPONIBLE
					System.out.println("Su saldo es: " + user.getDineroDisponible() + ". Su tiempo disponible es: "
							+ user.getTiempoDisponible());

					System.out.println(">>>--------------------------------------------<<<");

					System.out.println("Nuestra sugerencia es: " + producto.getNombreProducto());

					/*System.out.println("Que incluye las atracciones:");

					for (int i = 0; i < nombresAtrIncluidas.size(); i++) {
						System.out.println(nombresAtrIncluidas.get(i));
					}*/

					System.out.println("A un precio de " + producto.getPrecioDescuento() + " Monedas");
					System.out.println("La duracion en horas es : " + producto.getDuracionTotal());

					// Si no es una atraccion simple es promo y se ahorra monedas
					if (producto.getCostoTotal() != producto.getPrecioDescuento()) {
						double ahorro = Math.round((producto.getCostoTotal() - producto.getPrecioDescuento()) * 100)
								/ 100d;
						System.out.println("Comprando este pack se ahorra un total de " + ahorro + " Monedas");
					}

					// Solicita si quiere comprar esa Atraccion
					Scanner opcion = new Scanner(System.in);
					System.out.println("Desea comprar esta oferta S/N");
					String opt2 = opcion.next();

					System.out.println(opt2);

					if (opt.equals("S") || opt2.equals("s")) {

						System.out.println(">>> GRACIAS POR SU COMPRA ");
						System.out.println("-----------------------------------------");
						System.out.println();

						// descuenta CUPO en atracciones
						producto.descontarCupoProducto();

						// Descuenta tiempo y dinero en usuario
						user.descontarDineroDisponible(producto.getPrecioDescuento());
						user.descontarTiempoDisponible(producto.getDuracionTotal());

						// contadores para totalizar
						dineroTotal += producto.getPrecioDescuento();
						tiempoTotal += producto.getDuracionTotal();

						/* agrega al itinerario la compra
						ArrayList<Atracciones> atrac = producto.getAtracciones();
						for (int i = 0; i < atrac.size(); i++) {
							itinerario.add(atrac.get(i));*/
						
						}
					} // CIERRA EL IF
				} // CIERRA EL IF SI TIENE DINERO
			}

			//System.out.println(" ");

			//String archSalida = ("Itinerario" + user.getNombreDeUsuario() + ".out");

			//Sistema.escribirCompras(user.getNombreDeUsuario(), tiempoTotal, dineroTotal, itinerario, archSalida);

		} // TERMINA FOR

	

