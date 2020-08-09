package stackoverflow.network.http;

import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Inet4Address;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

public class MakingGetCall {

    public static void main(String []s) throws UnknownHostException {

        String hostdomain = "www.w3.org";
        String host = Inet4Address.getByName(hostdomain).getHostAddress();
        int port = 443;

        System.out.println("host : " + host);

        List<String> urlList = new ArrayList<>();
        urlList.add("/Protocols/rfc2616/rfc2616-sec8.html");
        urlList.add("/Protocols/rfc2616/rfc2616.html");

        try {
            makingHTTPCall(hostdomain ,urlList);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * hostname of the webserver e.g. www.w3.org
     * @param hostname
     * @param urlList
     * @throws IOException
     */
    public static void makingHTTPCall( String hostname , List<String> urlList) throws IOException {

        SSLSocketFactory factory = (SSLSocketFactory)SSLSocketFactory.getDefault();
        SSLSocket socket =
                (SSLSocket)factory.createSocket(hostname, 443);


        BufferedReader in
                = new BufferedReader(
                new InputStreamReader(socket.getInputStream()));
        PrintWriter out
                = new PrintWriter(socket.getOutputStream(), true);

        /**
         *  if required create different url List and pass those list to separate thread for better performance
         */

        urlList.forEach(
                url -> {
                    System.out.println("Making call to url " + url);
                    out.println("HEAD "  + url + " HTTP/1.1");
                    out.println();
                    out.flush();

                    String line = "";

                    try {
                        while ((line = in.readLine()) != null) {
                            System.out.println("Response" + line);
                            break;
                        }

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
        );

        try {
                in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        out.close();

    }


}
