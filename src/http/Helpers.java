package http;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.net.UnknownHostException;

// Problem in stack overflow
public class Helpers {
    public static int get(String url){
        URL myURL = null;
        HttpURLConnection myUrlConn = null;
        try {
            myURL = new URL(url);
            myUrlConn = (HttpURLConnection) myURL.openConnection();
            myUrlConn.setConnectTimeout(400);
            myUrlConn.setReadTimeout(400);
            myUrlConn.setRequestMethod("GET");
            int status = myUrlConn.getResponseCode();
            return status;
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return 1;
        } catch (UnknownHostException e){
            return 0; // our special code for unknown host.
        } catch (ConnectException e){
            return 42; // our special code for connection refused.
        } catch (SocketTimeoutException e){
            return 24; // our special code for socket timeout.
        } catch (IOException e) {
            e.printStackTrace();
            return 3;
        }finally {
            if(myUrlConn != null)
                myUrlConn.disconnect();
        }
    }

    public static void writeToFile(String path, boolean append_to_file, String text) throws IOException {
        FileWriter write = new FileWriter( path , append_to_file);
        PrintWriter print_line = new PrintWriter(write);
        print_line.printf("%s\n", text);
        print_line.close();
    }
}
