package modelo.soporte;

import java.util.List;
import java.util.Optional;

public class HormigaData {
	Optional<List<Alimento>> alimentos;
	int vida;
	long id;

	public HormigaData(Optional<List<Alimento>> alimentos, int vida, long id) {
		this.alimentos = alimentos;
		this.vida = vida;
		this.id = id;
	}

	public Double getMediaAlimentos() {
		double respuesta = 0;
		if (alimentos.isPresent()) {
			respuesta = (alimentos.get().stream().mapToInt((alimento) -> {
				return alimento.getPower();
			}).average().getAsDouble());
		}
		return respuesta;
	}

	public float getIndiceProductividad() {
		float respuesta = 0;
		if (alimentos.isPresent()) {
			respuesta = (alimentos.get().stream().mapToInt((alimento) -> {
				return alimento.getPower();
			}).sum() / (float) vida);
		}
		return respuesta;
	}
}
