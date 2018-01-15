/**
 * Java. Level 2. Lesson 6
 * Simple chat client
 *
 * @author Maksim Shelepov
 * @version 1.0 dated Jan 15, 2018
 * @link https://github.com/Loredan15/HW6
 */
import java.io.*;
import java.net.*;
import java.util.*;

class SimpleClient {

    final String SERVER_ADDR = "127.0.0.1"; // or "localhost"
    final int SERVER_PORT = 2048;
    final String CLIENT_PROMPT = "$ ";
    final String CONNECT_TO_SERVER = "Connection to server established.";
    final String CONNECT_CLOSED = "Connection closed.";
    final String EXIT_COMMAND = "exit"; // command for exit

    public static void main(String[] args) {
        new SimpleClient();
    }

    SimpleClient() {
        String message;
        try (Socket socket = new Socket(SERVER_ADDR, SERVER_PORT);
            PrintWriter writer = new PrintWriter(socket.getOutputStream());
            Scanner scanner = new Scanner(System.in)) {
            System.out.println(CONNECT_TO_SERVER);
            BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            do {
                System.out.print(CLIENT_PROMPT);
                message = scanner.nextLine();
                writer.println(message);
                writer.flush();
                System.out.println(br.readLine());
            } while (!message.equals(EXIT_COMMAND));
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        System.out.println(CONNECT_CLOSED);
    }
}