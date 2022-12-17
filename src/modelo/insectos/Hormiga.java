package modelo.insectos;

import java.beans.PropertyChangeSupport;
import java.util.Random;

import modelo.entidades.Hormiguero;
import modelo.soporte.HormigaDataAdapter;

public class Hormiga {
	PropertyChangeSupport pcs;
	private final int maxima = 50;
	private int vida = new Random().nextInt(maxima) + 1;
	private int edad = 0;
	public long id;
	protected int incrementoVidaPorDefecto = 1;
	Comportamiento comportamiento;

	public Hormiga(long id,Hormiguero hormiguero) {
		super();
		this.id = id;
		pcs = new PropertyChangeSupport(this);
		comportamiento = new Recolectora(this);
		pcs.addPropertyChangeListener(hormiguero);
	}

	public boolean isGuerrera() {
		return comportamiento.isGuerrera();
	}

	public void setComportamiento(Comportamiento comportamiento) {
		this.comportamiento=comportamiento;
	}

	public int getVida() {
		return vida;
	}

	public void setVida(int vida) {
		this.vida = vida;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void hacerTarea() {
		if (this.isAlive()) {
			comportamiento.actua();
		} else {
			pcs.firePropertyChange("muerte", this, HormigaDataAdapter.convert(this.comportamiento.getAlimentos(),this));
		}
	}

	public boolean isAlive() {
		return vida > edad;
	}

	protected void incrementaEdad(int i) {
		this.edad += i;
	}

}
