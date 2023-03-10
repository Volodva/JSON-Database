package client;

import com.google.gson.Gson;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;

public class Client {

    private static final String SERVER_ADDRESS = "127.0.0.1";
    private static final int SERVER_PORT = 22234;
    private static final String FILE_REQUEST_PATH_TEST_ENVIRONMENT = System.getProperty("user.dir") + "/src/client/data/";
    private static Request request = new Request();


    public static void main(String[] args) {
        parseArgs(args);
        System.out.println("Client started!");
        String msg;

        try (
                Socket socket = new Socket(InetAddress.getByName(SERVER_ADDRESS), SERVER_PORT);
                DataInputStream input = new DataInputStream(socket.getInputStream());
                DataOutputStream output = new DataOutputStream(socket.getOutputStream())
        ) {

            if (request.getType() == null) {
                try(BufferedReader reader = new BufferedReader(new FileReader(FILE_REQUEST_PATH_TEST_ENVIRONMENT + request.getFileName()))) {
                    msg = reader.readLine();
                }
            } else {
                msg = new Gson().toJson(request);
            }

            output.writeUTF(msg);
            System.out.println("Sent: " + msg);

            String receivedMsg = input.readUTF();
            System.out.println("Received: " + receivedMsg);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void parseArgs(String[] args) {
        for (int i = 0; i < args.length; i++) {
            switch (args[i]) {
                case "-t":
                    request.setType(args[i + 1]);
                    break;
                case "-k":
                    request.setKey(args[i + 1]);
                    break;
                case "-v":
                    request.setValue(args[i + 1]);
                    break;
                case "-in" :
                    request.setFileName(args[i + 1]);
                    break;
            }
        }
    }
}
