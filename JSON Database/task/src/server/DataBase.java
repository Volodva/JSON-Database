package server;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class DataBase {
    private static final String FILENAME = System.getProperty("user.dir") + "/src/server/data/db.json";
    private Map<String, String> data = new HashMap<>();
    private ReadWriteLock lock = new ReentrantReadWriteLock();
    private Lock readLock = lock.readLock();
    private Lock writeLock = lock.writeLock();
    private Response response = new Response();
    private BufferedWriter writer;

     {
        try{
            writer = new BufferedWriter(new FileWriter(FILENAME));
        } catch (IOException e) {
        }
    }


    public void setData(String key, String value) {
        clearResponse();

        data.put(key, value);
        response.setResponse("OK");

    }

    public void getData(String key) {
        clearResponse();

        if (data.containsKey(key)) {
            response.setResponse("OK");
            response.setValue(data.get(key));
        } else {
            response.setResponse("ERROR");
            response.setReason("No such key");
        }
    }

    public void deleteData(String key) {
        clearResponse();

        if (data.containsKey(key)) {
            data.remove(key);
            response.setResponse("OK");
        } else {
            response.setResponse("ERROR");
            response.setReason("No such key");
        }
    }

    public void exitData() {
        clearResponse();

        response.setResponse("OK");
        System.exit(0);
    }

    private void clearResponse() {
        response.setResponse(null);
        response.setReason(null);
        response.setValue(null);
    }

    public Response getResponse() {
        return response;
    }
}
