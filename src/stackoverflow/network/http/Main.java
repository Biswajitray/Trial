package stackoverflow.network.http;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class Main  {
    public static void main(String[] args) throws IOException {
        long startTime = System.nanoTime();
        Helpers.get("www.google.com", 80); // works here

        /**
         * Creating socket here
         */
        String path = "test.txt";
        boolean append = true;
        for (int x = 0; x < 1; x++) {
            ArrayList<String> urls = new ArrayList<String>();
// when x = 0, y = 0 | 10 /\ when x = 1, y = 10 | 20
            for (int y= x * 10;y < ((x + 1) * 10); y++){
                urls.add(String.format("www.google%d.com/", y)); // doesn't work here
            }
            Thread thread = new Helpers(path, append, urls, 80);
            thread.start();
            thread.interrupt();
        }
        long endTime = System.nanoTime();
        long duration = TimeUnit.NANOSECONDS.toMillis(endTime - startTime);
        System.out.println(duration + " ms");
    }
}
