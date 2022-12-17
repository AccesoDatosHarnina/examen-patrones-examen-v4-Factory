package control;

import modelo.entidades.Hormiguero;
import modelo.insectos.Hormiga;

public class App {

	public static void main(String[] args) {
		Hormiguero hormiguero=new Hormiguero();
        for (int i = 0; i < 30; i++) {
        	hormiguero.getHormigas().add(new Hormiga(i,hormiguero));
		}
        hormiguero.funciona();
	}

}
