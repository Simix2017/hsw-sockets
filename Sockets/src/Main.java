import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Main {

    public static void main(String[] args) throws Exception {
        System.out.println("CLIENT");
        final var socket = new Socket("localhost", 10_005);
        final var output = new PrintWriter(socket.getOutputStream());
        final var input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        System.out.println(input.readLine());

        // prime
        output.println("1"); // Generate prime
        output.flush();
        System.out.println(input.readLine());
        System.out.println(input.readLine());

        // prime 2
        output.println("1"); // Generate prime
        output.flush();
        System.out.println(input.readLine());
        System.out.println(input.readLine());

        // close
        output.println("0");
        output.flush();
        System.out.println(input.readLine());
        System.out.println(input.readLine());
    }

}