import java.io.*;
import java.util.Scanner;

public class Sumador {

    public static void main(String[] args) {
        if (args.length < 2) {
            System.out.println("Por favor, ingrese los nombres de archivo de entrada y salida.");
            return;
        }

        String archivoEntrada = args[0];
        String archivoSalida = args[1];

        int sumaTotal = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(archivoEntrada))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                // Leer los valores de inicio y fin
                Scanner scanner = new Scanner(linea);
                int inicio = scanner.nextInt();
                int fin = scanner.nextInt();
                scanner.close();

                // Calcular la suma para el rango
                for (int i = inicio; i <= fin; i++) {
                    sumaTotal += i;
                }
            }

            // Escribir el resultado total en el archivo de salida
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(archivoSalida))) {
                writer.write(String.valueOf(sumaTotal));
            }

        } catch (IOException | NumberFormatException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
