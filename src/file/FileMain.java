package file;

import java.io.File;

public class FileMain {

    public static void main(String s[])
    {
     checkMemory();
    }
    private static void checkMemory()
    {
        File file = new File(System.getProperty("app.root"));
        long totalMem = file.getTotalSpace() ;
        long freeMem = file.getFreeSpace() ;

        int totalMValue = 0;
        totalMValue = (int) (totalMem >>> 30);

        int totalFValue = 0;
        totalFValue = (int) (freeMem >>> 30);

        System.out.println("Total Memory in GB : " + totalMValue);
        System.out.println("Free Memory in GB : " + totalFValue);
    }
}
