package Server;

import java.io.*;
import java.net.Socket;

public class ThreadServer extends Thread {
    private Socket client;

    public ThreadServer(Socket client) {
        this.client = client;
    }

    @Override
    public void run() {
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
            PrintWriter out = new PrintWriter(new OutputStreamWriter(client.getOutputStream()));

            out.close();
            client.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
