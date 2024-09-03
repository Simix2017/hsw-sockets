import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.net.ServerSocket;
import java.net.Socket;
import java.security.SecureRandom;

public class Main {

    public static void main(String[] args) throws Exception {
        System.out.println("SERVER");
        final var serverSocket = new ServerSocket(10_005);
        while (true) {
            final var socket = serverSocket.accept();
            final Runnable process = () -> {
                try {
                    processSocket(socket);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            };
            final var thread = new Thread(process);
            thread.start();
        }
    }

    private static void processSocket(Socket socket) throws IOException, InterruptedException {
        final var output = new PrintWriter(socket.getOutputStream());
        final var input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        do {
            output.println("Hallo, ich bin der Server! 0 = Abbruch, 1 = Zufällige Primzahl");
            output.flush();
            final var command = input.readLine();
            System.out.println("command: " + command);
            if ("1".equals(command)) {
                final var prime = BigInteger.probablePrime(100, new SecureRandom());
                Thread.sleep(10_000);
                output.println(prime);
                output.flush();
            } else if ("0".equals(command)) {
                output.println("Bye bye!");
                output.flush();
                socket.close();
                break;
            }
        } while (true);
    }

}