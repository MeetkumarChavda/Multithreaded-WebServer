import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public void run() throws IOException {
        int port = 8010;
        ServerSocket socket = new ServerSocket(port);
        socket.setSoTimeout(10000);
        while (true) {
            try {
                System.out.println("Server is Listen on " + port);
                Socket accaeptedConnection = socket.accept();
                System.out.println("connected to " + accaeptedConnection.getRemoteSocketAddress());
                PrintWriter toClient = new PrintWriter(accaeptedConnection.getOutputStream());
                BufferedReader fromClient = new BufferedReader(
                        new InputStreamReader(accaeptedConnection.getInputStream()));
                toClient.println("Hello from the Server!");
                toClient.close();
                fromClient.close();
                accaeptedConnection.close();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        Server server = new Server();
        try {
            server.run();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}