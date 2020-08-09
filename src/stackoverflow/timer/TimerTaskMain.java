package stackoverflow.timer;

import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

public class TimerTaskMain {

    public static void main(String s[])
    {

    }

    private void scheduledAtMonthChange()
    {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.DAY_OF_MONTH , cal.getActualMaximum(Calendar.DAY_OF_MONTH));
        cal.set(Calendar.HOUR_OF_DAY,23);
        cal.set(Calendar.MINUTE,59);
        cal.set(Calendar.MILLISECOND,0);
        cal.set(Calendar.SECOND,59);

        long secsToNextMonth = cal.getTimeInMillis() - System.currentTimeMillis() + 2000;
        Timer nextMonthTime = new Timer();
        nextMonthTime.schedule(new TimerTask() {
            @Override
            public void run() {
                // Add your implementation here

                // Implementation end
                scheduledAtMonthChange();
            }
        }, secsToNextMonth );
    }
}
