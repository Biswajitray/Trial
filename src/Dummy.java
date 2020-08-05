import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class Dummy {
    public static void main(String s[])
    {
        Optional<String> data = Optional.of("TrialValue");
        System.out.println(data.get());
        data = Optional.empty();
        System.out.println(data.get());
       // checkString();
        //executeThreads();
        //checkUmodifiable();
        
    }

    private static void checkString()
    {
        String temp = "asdfsdaf=1234";
        System.out.println(temp.split(",").length > 0 ? temp.split(",")[0] : 1);
    }

    private static void checkUmodifiable()
    {
        List<String> origDataList = new ArrayList<>();
        origDataList.add("asdfsadf");
        List<String> dataList = Collections.unmodifiableList(origDataList);
        dataList.add("12312312");
    }
    private static void executeThreads()
    {

        System.setErr(null);
        Runnable runnable = () -> {
            int count = 0;
            while(true)
            {
                System.out.println(count);
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                count++;
            }
        };


        Thread th1 = new Thread(runnable);
        th1.start();
        Thread th2 = new Thread(runnable);
        th2.start();
        Thread th3 = new Thread(runnable);
        th3.start();
    }
}
