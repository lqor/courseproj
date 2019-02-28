package Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(8000);

        while (true) {
            Socket client;
            try {
                client = serverSocket.accept();
                System.out.println("*** Client connected!");
                ThreadServer threadServer = new ThreadServer();
                threadServer.start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
