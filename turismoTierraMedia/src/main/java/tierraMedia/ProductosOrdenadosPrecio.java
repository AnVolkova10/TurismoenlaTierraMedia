package tierraMedia;

import java.util.Comparator;

import tierraMedia.Producto;

public class ProductosOrdenadosPrecio implements Comparator <Producto> {
	public int compare (Producto prod1, Producto prod2) {
		
		return (prod1.getCostoTotal().compareTo(prod2.getCostoTotal()))*-1;
				
		
	}

}
