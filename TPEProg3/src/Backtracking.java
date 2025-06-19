import java.util.List;

/*
Estrategia de resolución Backtracking:

- Qué es Backtracking?
  Es una técnica que prueba todas las combinaciones posibles para encontrar la mejor solución a un problema.

- Cómo se aplica al problema?
  Queremos encontrar la secuencia de máquinas que la suma de sus piezas sea igual
  al objetivo de piezas usando la menor cantidad de máquinas posibles (M1-M3-M4) o (M3-M3-M3)

- Cómo funciona el algoritmo?
  1. Partimos de cero piezas producidas y una secuencia vacía
  2. En cada paso, probamos agregar cada máquina posible a la secuencia (aunque ya la usamos antes)
  3. Restamos la cantidad de piezas que produce la máquina elegida al total que nos falta producir
  4. Si llegamos exactamente a cero piezas restantes, guardamos la secuencia si es la mejor encontrada hasta ahora
  5. Si nos pasamos (piezasRestantes < 0), cortamos esa rama poda1
  6. Si hay una solución y la secuencia actual es igual o más larga cortamos esa rama poda2
  7. Si todavía faltan piezas, seguimos probando con todas las máquinas (esto genera un árbol de decisiones donde
     cada rama es una posible secuencia)
  8. Al terminar, nos quedamos con la mejor secuencia encontrada

- Estados finales: Son las hojas del árbol de exploración que se producen al no tener más decisiones disponibles.
  No todos los estados finales son estados solución

- Estados solución: Son los estados finales que cumplen con todas las condiciones del problema

- Ejemplo:
  Si tenemos las máquinas [M1: 7, M2: 3, M3: 4, M4: 1] y queremos hacer 12 piezas,
  el algoritmo prueba combinaciones como [M1, M3, M4], [M3, M3, M3], [M2, M2, M2, M2], etc
  y se va a quedar con la que menos máquinas use

*/

public class Backtracking {
    public Solucion resolver(int piezasTotales, List<Maquina> maquinas) {
        ResultadoBacktracking resultado = new ResultadoBacktracking();
        backtrack(maquinas, piezasTotales, new java.util.ArrayList<>(), resultado);
        if (resultado.mejorSecuencia != null) {
            return new Solucion(resultado.mejorSecuencia, piezasTotales, resultado.mejorSecuencia.size(), resultado.estadosGenerados);
        } else {
            return null;
        }
    }

    private void backtrack(List<Maquina> maquinas, int piezasRestantes, List<Maquina> actual, ResultadoBacktracking resultado) {
        resultado.estadosGenerados++;
        if (piezasRestantes == 0) {
            if (resultado.mejorSecuencia == null || actual.size() < resultado.mejorSecuencia.size()) {
                resultado.mejorSecuencia = new java.util.ArrayList<>(actual);
            }
            return;
        }
        if (piezasRestantes < 0) {
            return; // Poda por exceso (Si la suma de piezas se pasó del total pedido)
        }
        if (resultado.mejorSecuencia != null && actual.size() >= resultado.mejorSecuencia.size()) {
            return; // Poda por tamaño (Si ya hay una solución y la secuencia actual es igual o más larga que la mejor encontrada)
        }
        // Damos por sentado por el enunciado que se pueden repetir las máquinas
        for (Maquina m : maquinas) {
            actual.add(m);
            backtrack(maquinas, piezasRestantes - m.getPiezas(), actual, resultado);
            actual.remove(actual.size() - 1);
        }
    }

    private static class ResultadoBacktracking {
        List<Maquina> mejorSecuencia = null;
        int estadosGenerados = 0;
    }
}
