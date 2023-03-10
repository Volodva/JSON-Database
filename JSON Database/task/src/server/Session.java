package server;

import com.google.gson.Gson;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Session extends Thread {
    private final Socket socket;
    private DataBase dataBase;

    public Session(Socket socketForClient, DataBase dataBase) {
        this.socket = socketForClient;
        this.dataBase = dataBase;
    }

    @Override
    public void run() {
        try (
                DataInputStream input = new DataInputStream(socket.getInputStream());
                DataOutputStream output = new DataOutputStream(socket.getOutputStream())
        ) {


            String msgFromClient = input.readUTF();
            Request request = new Gson().fromJson(msgFromClient, Request.class);


            switch (request.getType()) {
                case "set":
                    dataBase.setData(request.getKey(), request.getValue());
                    break;
                case "get":
                    dataBase.getData(request.getKey());
                    break;
                case "delete":
                    dataBase.deleteData(request.getKey());
                    break;
                case "exit": {
                    Server.isFinished = true;
                    dataBase.exitData();
                }
                break;
            }
            String msgFromServer = new Gson().toJson(dataBase.getResponse());
            output.writeUTF(msgFromServer);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

