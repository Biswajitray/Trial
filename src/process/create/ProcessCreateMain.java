package process.create;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class ProcessCreateMain {
    public static void main(String s[])
    {
        int count = s.length;

        //Boolean.getBoolean()
        List<String> commandList = new ArrayList<>();
        for (int i = 0; i < count ; i++) {
            commandList.add(s[i]);
        }

        try {
            usingProcessBuilder(commandList);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void usingProcessBuilder(List<String> commandList) throws IOException {
        String processName = "processThreadDump";
        System.setProperty("out.path",".");
        Map envVariable = null;



        ProcessBuilder pb = new ProcessBuilder();

        if(processName != null && processName.length() > 0) {

            String outFilePath = processName + ".out";

            String dirName = System.getProperty("out.path",".."+ File.separatorChar +"log");
            if(new File(dirName).exists())
                outFilePath = dirName + File.separatorChar + outFilePath;

          //  pb = pb.redirectErrorStream(true);
            pb = pb.redirectOutput(ProcessBuilder.Redirect.appendTo(new File(outFilePath)));
         //   pb = pb.redirectError(ProcessBuilder.Redirect.appendTo(new File(outFilePath)));
        }
        if(envVariable != null) {
            System.out.println("Setting Environment variable");
            pb.environment().putAll(envVariable);
        }
        pb = pb.command(commandList);
        Process process = pb.start();

    }
}
