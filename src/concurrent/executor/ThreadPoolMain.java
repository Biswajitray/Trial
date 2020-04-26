package concurrent.executor;

import concurrent.ReturnData1;
import concurrent.ReturnData2;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ThreadPoolMain {

    public static void main(String s[])
    {
        System.out.println(System.currentTimeMillis());
        cachedThreadPool();
        System.out.println(System.currentTimeMillis());
        dataExecutor.shutdown();
        System.out.println(System.currentTimeMillis());

    }

    private static ExecutorService dataExecutor;
    private static void cachedThreadPool()
    {
        dataExecutor = Executors.newCachedThreadPool();
        Future<ReturnData1> returnData1Future = null;
        Future<ReturnData2> returnData2Future = null;
        returnData1Future = dataExecutor.submit(
                () ->
                {
                    Thread.sleep(2000);
                    return new ReturnData1();
                }
        );

        returnData2Future = dataExecutor.submit(
                () ->
                {
                    Thread.sleep(2000);
                    return new ReturnData2();
                }
        );
        //dataExecutor.invokeAll()
        returnData1Future.isDone();
        try {
            ReturnData1 returnData1 = returnData1Future.get();
            ReturnData2 returnData2 = returnData2Future.get();

            System.out.println(returnData1);
            System.out.println(returnData2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

    }
}

