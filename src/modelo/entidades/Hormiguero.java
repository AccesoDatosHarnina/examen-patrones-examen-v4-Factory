package modelo.entidades;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.stream.Stream;

import modelo.insectos.GuerreraCreator;
import modelo.insectos.Hormiga;
import modelo.insectos.RecolectoraCreator;
import modelo.soporte.HormigaData;
import modelo.soporte.Statistics;

public class Hormiguero implements PropertyChangeListener, Observer {
	Statistics statistics;
	public final int cantidadHormigasTotal = 30;
	public final int cantidadHormigasGuerreras = 15;
	private List<Hormiga> hormigas;
	//porque no podemos borrar mientras recorremos
	private List<Hormiga> hormigasMuertas=new ArrayList<Hormiga>();;
	private static long id = 1;

	public Hormiguero() {
		super();
		hormigas = new ArrayList<Hormiga>();
		statistics = new Statistics();
	}

	@Override
	public void update(Observable o, Object arg) {
		Boolean respuesta = (Boolean) arg;
		if (respuesta) {
			convertirHormigasGuerra(respuesta);
		} else {
			convertirHormigasPaz();
		}

	}

	public void funciona() {
		Historia historia = new Historia();
		do {
			historia.pasaHistoria();
			// hay un problema porque la tarea puede borrar hormigas
			hormigas.forEach((hormiga)->{hormiga.hacerTarea();});
			enterrarHormigas();
			crearHormigas(historia.isEnGuerra());
			System.out.println(historia.historia);
		} while (!historia.isAcabada());
		System.out.println(statistics.getCurrentMediaAlimento());
	}

	private void enterrarHormigas() {
		hormigas.removeAll(hormigasMuertas);
		hormigasMuertas=new ArrayList<Hormiga>();
	}

	public List<Hormiga> getHormigas() {
		return hormigas;
	}

	public void setHormigas(List<Hormiga> hormigas) {
		this.hormigas = hormigas;
	}

	public void convertirHormigasGuerra(boolean inGuerra) {
		int guerreras = 0;
		if (inGuerra) {
			guerreras = cantidadHormigasGuerreras - contarHormigasGuerreras();
		}
		int index = 0;
		while (guerreras > 0) {
			Hormiga hormiga = hormigas.get(index++);
			if (!hormiga.isGuerrera()) {
				hormiga.setComportamiento(new GuerreraCreator().factoryMethod(hormiga));
				guerreras--;
			}
		}
	}

	public void convertirHormigasPaz() {
		hormigas.stream().filter((hormiga) -> hormiga.isGuerrera()).forEach((hormiga) -> {
			hormiga.setComportamiento(new RecolectoraCreator().factoryMethod(hormiga));
		});

	}

	private int contarHormigasGuerreras() {
		return (int) hormigas.stream().filter((hormiga) -> hormiga.isGuerrera()).count();
	}

	private void crearHormigas(boolean inGuerra) {
		int contador = 0;
		for (int i = hormigas.size(); i < cantidadHormigasTotal; i++) {
			hormigas.add(new Hormiga(id++, this));
			contador++;
		}
		convertirHormigasGuerra(inGuerra);
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		HormigaData hormiga = (HormigaData) evt.getNewValue();
		statistics.addData(hormiga);
		hormigasMuertas.add((Hormiga) evt.getOldValue());
	}

}