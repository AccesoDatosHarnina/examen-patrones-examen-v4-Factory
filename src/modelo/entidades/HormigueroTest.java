package modelo.entidades;

import org.junit.jupiter.api.Test;

import modelo.insectos.Hormiga;

class HormigueroTest {

    @Test
    void test(){
        Hormiguero hormiguero=new Hormiguero();
        for (int i = 0; i < 30; i++) {
        	hormiguero.getHormigas().add(new Hormiga(i,hormiguero));
		}
        hormiguero.convertirHormigasGuerra(true);
        hormiguero.convertirHormigasPaz();
        hormiguero.funciona();
    }
}