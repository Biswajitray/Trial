package stackoverflow.network.http;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
public class Helpers extends Thread {
    public Helpers(String path, boolean append, ArrayList<String> urls, int port) throws IOException {
        this.run(path, append, urls, port);
    }
    public void run(String path, boolean append, ArrayList<String> urls, int port) throws IOException {
        for (String url : urls) {
            String status = Helpers.get(url, port);
            //Helpers.writeToFile(path, append, status);
            System.out.println(status);
        }
    }
    public static String get(String url, int port) throws IOException {
        try {
            Socket conn = new Socket(url, 80);
            conn.close();
            return url + " | Success";
        } catch (UnknownHostException error) {
            return url + " | Failed";
        }
    }


}
