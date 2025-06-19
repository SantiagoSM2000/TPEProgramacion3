import java.util.List;

/*
Estrategia de resolución Greedy:

- Qué es Greedy?
  Es una técnica que toma siempre la mejor opción disponible en cada paso, sin mirar al futuro.
  Siempre se toma la mejor decision local sin pensar en un contexto global

- Cómo se aplica aquí?
  Queremos producir la cantidad de piezas necesaria usando la menor cantidad de maquinas.
  En cada paso elegimos la máquina que más piezas produce sin pasarnos del total que sobra.

- Cómo funciona el algoritmo?
  1. Ordenamos las máquinas de mayor a menor según la cantidad de piezas que producen.
  2. Mientras falten piezas por producir:
     a. Buscamos la máquina más grande que no se pase del total que falta.
     b. Si encontramos una, la usamos y restamos sus piezas al total que falta.
     c. Si no hay ninguna máquina que entre, no hay solución (no se puede llegar exacto).
  3. Guardamos la secuencia de máquinas usadas.

- Cuales son los candidatos?
  Cada máquina que se evalúa para ver si puede ser usada en el paso actual.

- Estrategia para elegir candidatos: Elegir el mejor candidato local
  (el que mas piezas produce sin pasarme del total)

- Consideraciones respecto a encontrar o no solución:
    El algoritmo greedy no asegura encontrar una solución óptima ya que al tomar decisiones locales,
    puede fallar aunque exista una solución.
    Ejemplo: para producir 10 piezas con máquinas de [4, 4, 3, 3], puede elegir [4, 4] (suma 8) y no llegar al
    total, aunque [4, 3, 3] sí es solución.

- Qué es la métrica candidatos considerados?
  Es la cantidad total de veces que el algoritmo evalúa una máquina como posible candidata en todos los pasos.

- Ejemplo:
  Si tenemos máquinas [M1: 7, M2: 3, M3: 4, M4: 1] y queremos producir 12 piezas,
  el algoritmo primero elige M1 (7 piezas), luego M3 (4 piezas), luego M4 (1 pieza), sumando 12.
*/
public class Greedy {
    public Solucion resolver(int piezasTotales, List<Maquina> maquinas) {
        List<Maquina> secuencia = new java.util.ArrayList<>();
        int piezasActuales = 0;
        int puestas = 0;
        int candidatosConsiderados = 0;
        List<Maquina> maquinasOrdenadas = new java.util.ArrayList<>(maquinas);
        // Ordenar de mayor a menor por cantidad de piezas
        maquinasOrdenadas.sort(java.util.Comparator.comparingInt(Maquina::getPiezas).reversed());
        while (piezasActuales < piezasTotales) {
            boolean encontrado = false;
            for (Maquina m : maquinasOrdenadas) {
                candidatosConsiderados++;
                if (piezasActuales + m.getPiezas() <= piezasTotales) {
                    secuencia.add(m);
                    piezasActuales += m.getPiezas();
                    puestas++;
                    encontrado = true;
                    break;
                }
            }
            if (!encontrado) break; // No se puede avanzar más
        }
        if (piezasActuales == piezasTotales) {
            return new Solucion(secuencia, piezasActuales, puestas, candidatosConsiderados);
        } else {
            return null;
        }
    }
}
