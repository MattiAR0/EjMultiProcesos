import java.io.*;
import java.net.*;

public class ServidorSumador {
    public static void main(String[] args) {
        int puerto = 12345; // Puerto donde escucha el servidor

        try (ServerSocket servidor = new ServerSocket(puerto)) {
            System.out.println("ServidorSumador escuchando en el puerto " + puerto);

            while (true) {
                // Aceptar una conexión de cliente
                Socket cliente = servidor.accept();
                System.out.println("Cliente conectado: " + cliente.getInetAddress());

                // Manejar al cliente en un hilo separado
                new Thread(() -> manejarCliente(cliente)).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void manejarCliente(Socket cliente) {
        try (
                DataInputStream entrada = new DataInputStream(cliente.getInputStream());
                DataOutputStream salida = new DataOutputStream(cliente.getOutputStream())
        ) {
            // Leer los números enviados por el cliente
            int inicio = entrada.readInt();
            int fin = entrada.readInt();

            // Calcular la suma
            int suma = 0;
            for (int i = inicio; i <= fin; i++) {
                suma += i;
            }

            // Enviar la suma de vuelta al cliente
            salida.writeInt(suma);

            System.out.println("Suma enviada al cliente: " + suma);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                cliente.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
