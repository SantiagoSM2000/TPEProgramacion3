import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Verifica que se pase el path del archivo de configuración como argumento (opcional)
        /*
        if (args.length < 1) {
            System.out.println("Debe indicar el path del archivo de configuración.");
            return;
        }
        */
        //Sirve para ingresar el path mediante argumento
        //String path = args[0];
        String path = "ejemplo.txt";
        try {
            // Lee la cantidad total de piezas y la lista de máquinas desde el archivo
            int piezasTotales = LectorArchivo.leerPiezasTotales(path);
            List<Maquina> maquinas = LectorArchivo.leerMaquinas(path);

            // Ejecuta el algoritmo Backtracking
            Backtracking backtracking = new Backtracking();
            Solucion solucionBack = backtracking.resolver(piezasTotales, maquinas);
            System.out.println("Backtracking:");
            mostrarSolucion(solucionBack);

            // Ejecuta el algoritmo Greedy
            Greedy greedy = new Greedy();
            Solucion solucionGreedy = greedy.resolver(piezasTotales, maquinas);
            System.out.println("Greedy:");
            mostrarSolucion(solucionGreedy);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    // Muestra la solución que se consigue con cada algoritmo
    private static void mostrarSolucion(Solucion solucion) {
        if (solucion == null) {
            System.out.println("No se encontró solución.");
            return;
        }
        System.out.print("Secuencia de máquinas: ");
        for (Maquina m : solucion.getSecuencia()) {
            System.out.print(m.getNombre() + " ");
        }
        System.out.println();
        System.out.println("Piezas producidas: " + solucion.getPiezasProducidas());
        System.out.println("Puestas en funcionamiento: " + solucion.getPuestasEnFuncionamiento());
        System.out.println("Métrica: " + solucion.getMetrica());
    }
}
