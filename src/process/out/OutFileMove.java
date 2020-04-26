package process.out;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Calendar;

public class OutFileMove {

    public static void main(String s[])
    {

        Calendar cal = Calendar.getInstance();
        System.out.println(cal.get(Calendar.SUNDAY));
        //String fileName = s[0];
        //System.out.println("File Name "+fileName);
        //changeAndCreate(fileName);
    }

    private static void changeAndCreate(String fileName)
    {

        try {
            String[] filePath = fileName.split("\\.");
            String newFileName = filePath[0] + "_1."+filePath[1];
            Files.copy(Paths.get(fileName), new FileOutputStream(newFileName) );
            FileOutputStream foos = new FileOutputStream(fileName);
            foos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
