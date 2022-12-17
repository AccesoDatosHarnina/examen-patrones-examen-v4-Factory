package modelo.soporte;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Statistics {
	private List<HormigaData> datos;
	int empty=0;

	public Statistics() {
		this.datos = new ArrayList<HormigaData>();
	}

	public void addData(HormigaData hormigaData) {

		datos.add(hormigaData);
	}

	public Double getCurrentMediaAlimento() {
		return datos.stream().mapToDouble((data) -> {
			return data.getMediaAlimentos();
		}).average().getAsDouble();
	}

	public double getCurrentIndiceGlobal() {
		return datos.stream().mapToDouble((data) -> {
			return data.getIndiceProductividad();
		}).average().getAsDouble();
	}
}
