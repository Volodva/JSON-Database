package server;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class Server {
    private static final int PORT = 22234;
    private static final String address = "127.0.0.1";
    static boolean isFinished = false;

    public static void main(String[] args) {

        DataBase data = new DataBase();
        ExecutorService executor = Executors.newSingleThreadExecutor();

        try (ServerSocket server = new ServerSocket(PORT, 50, InetAddress.getByName(address))) {

            System.out.println("Server started!");
            while (!isFinished) {

                Session session = new Session(server.accept(), data);
                session.start();
                session.join();
            }

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}