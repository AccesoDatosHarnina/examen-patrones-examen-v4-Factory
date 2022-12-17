package modelo.soporte;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import modelo.insectos.Hormiga;

public class HormigaDataAdapter {
	
	//Es posible hacerlo no estatico
	public static HormigaData convert(Optional<List<Alimento>> alimento,Hormiga hormiga) {
		//si retorna null la hormiga creamos una lista vacia
		return new HormigaData(alimento, hormiga.getVida(), hormiga.getId());
	}
}
