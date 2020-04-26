package concurrent.executor;

import java.util.concurrent.*;

public class ExecutorMain {

    public static void main(String s[])
    {
        /**
        long startTimeinMillis = System.currentTimeMillis();
        try {
            executorTimeOut();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        long endTimeinMills = System.currentTimeMillis();

        System.out.println("Time : "+(endTimeinMills - startTimeinMillis) / 1000);
         */
        try {
            futureTaskInThread();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void futureTaskInThread() throws ExecutionException, InterruptedException {

          }

    private static void executorTimeOut() throws InterruptedException, ExecutionException {

        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);
        for (int i = 0; i < 10; i++) {
            TimeOutCallableTask timeOutCallableTask = new TimeOutCallableTask( i+ 1);
            boolean execStatus = false;
            try {
                execStatus = executorService.
                        schedule(timeOutCallableTask,0,TimeUnit.SECONDS).get(20,TimeUnit.SECONDS);
            } catch (TimeoutException e) {
                // e.printStackTrace();
            }
            System.out.println(" ExecStatus : "+execStatus + " , for  "+(i+1));
        }

        System.out.println("Before shutdown now");
       // executorService.shutdownNow();
    }

    private static class TimeOutCallableTask implements Callable<Boolean> {

        private int i;

        public TimeOutCallableTask(int i)
        {
            this.i = i;
        }
        @Override
        public Boolean call() {
            System.out.println("Waiting for 10 sec : " +i );
            try {
                Thread.sleep((10000 * i));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return false;
        }
    }

}
