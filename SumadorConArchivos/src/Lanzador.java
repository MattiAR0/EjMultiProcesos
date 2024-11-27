import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Lanzador {

    // Método para crear el archivo de entrada con los rangos
    public void crearArchivoEntrada(String archivoEntrada, List<int[]> rangos) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(archivoEntrada))) {
            for (int[] rango : rangos) {
                writer.write(rango[0] + " " + rango[1]);
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Método para lanzar el proceso Sumador
    public void lanzarProceso(String archivoEntrada, String archivoSalida) {
        ProcessBuilder pb = new ProcessBuilder("java", "Sumador", archivoEntrada, archivoSalida);
        pb.directory(new File(".")); // Usar el directorio actual como base

        try {
            Process proceso = pb.start();
            proceso.waitFor(); // Esperar a que el proceso termine
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Lanzador lanzador = new Lanzador();

        // Definir los rangos
        List<int[]> rangos = new ArrayList<>();
        int rangoPorProceso = 10;
        int inicioRango = 1;

        for (int i = 0; i < 5; i++) {
            int finRango = inicioRango + rangoPorProceso - 1;
            rangos.add(new int[]{inicioRango, finRango});
            inicioRango = finRango + 1;
        }

        // Crear archivo de entrada
        String archivoEntrada = "entrada.txt";
        String archivoSalida = "salida.txt";
        lanzador.crearArchivoEntrada(archivoEntrada, rangos);

        // Lanzar el proceso Sumador
        lanzador.lanzarProceso(archivoEntrada, archivoSalida);

        // Leer y mostrar el resultado desde salida.txt
        try (BufferedReader reader = new BufferedReader(new FileReader(archivoSalida))) {
            String resultado = reader.readLine();
            System.out.println("La suma total de los números especificados es: " + resultado);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
