import java.io.*;
import java.net.*;
import java.util.Scanner;

public class ClienteLanzador {
    public static void main(String[] args) {
        String host = "localhost"; // Dirección del servidor
        int puerto = 12345;        // Puerto del servidor

        try (Socket socket = new Socket(host, puerto);
             DataOutputStream salida = new DataOutputStream(socket.getOutputStream());
             DataInputStream entrada = new DataInputStream(socket.getInputStream())) {

            // Leer los rangos desde el usuario
            Scanner scanner = new Scanner(System.in);
            System.out.print("Ingrese el inicio del rango: ");
            int inicio = scanner.nextInt();
            System.out.print("Ingrese el fin del rango: ");
            int fin = scanner.nextInt();

            // Enviar los números al servidor
            salida.writeInt(inicio);
            salida.writeInt(fin);

            // Recibir el resultado del servidor
            int suma = entrada.readInt();
            System.out.println("La suma de los números del rango es: " + suma);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
