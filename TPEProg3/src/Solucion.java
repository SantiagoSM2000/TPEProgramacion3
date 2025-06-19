import java.util.List;

//Clase sencilla para estandarizar la presentación de los datos
public class Solucion {
    private List<Maquina> secuencia;
    private int piezasProducidas;
    private int puestasEnFuncionamiento;
    private int metrica;

    public Solucion(List<Maquina> secuencia, int piezasProducidas, int puestasEnFuncionamiento, int metrica) {
        this.secuencia = secuencia;
        this.piezasProducidas = piezasProducidas;
        this.puestasEnFuncionamiento = puestasEnFuncionamiento;
        this.metrica = metrica;
    }

    public List<Maquina> getSecuencia() {
        return secuencia;
    }

    public int getPiezasProducidas() {
        return piezasProducidas;
    }

    public int getPuestasEnFuncionamiento() {
        return puestasEnFuncionamiento;
    }

    public int getMetrica() {
        return metrica;
    }
}
