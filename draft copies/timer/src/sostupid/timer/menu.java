
package sostupid.timer;

import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;




public class menu extends Activity {


    private static int NOTIFICATION_ID = 1;

    private Timer timer = new Timer();

    private int maxTime = 5, remainTime = 5, counter = 0;
    private Handler handler = new Handler();
    private TextView timerLabel, buttonLabel;
    private boolean beep = false;



    private void initNotification(CharSequence tickerText, int beepTime) {
        String ns = Context.NOTIFICATION_SERVICE;
        NotificationManager mNotificationManager = (NotificationManager) getSystemService(ns);
        int icon = R.drawable.ic_launcher;

        long when = System.currentTimeMillis();
        Notification notification = new Notification(icon, tickerText, when);
        notification.defaults |= Notification.DEFAULT_SOUND;
        if (beepTime == 1) notification.flags = Notification.FLAG_ONGOING_EVENT;
        else notification.flags = Notification.FLAG_INSISTENT;
        Context context = getApplicationContext();
        CharSequence contentTitle = "My notification";
        CharSequence contentText = "Hello World!";
        Intent notificationIntent = new Intent(this, menu.class);
        PendingIntent contentIntent = PendingIntent.getActivity(this, 0, notificationIntent, 0);

        notification.setLatestEventInfo(context, contentTitle, contentText, contentIntent);
        mNotificationManager.notify(NOTIFICATION_ID, notification);
    }
    private void cancelNotification() {
        String ns = Context.NOTIFICATION_SERVICE;
        NotificationManager mNotificationManager = (NotificationManager) getSystemService(ns);
        mNotificationManager.cancel(NOTIFICATION_ID);
    }


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        timerLabel = (TextView) findViewById(R.id.textTimer);
        Button timerStartButton = (Button) findViewById(R.id.btnTimer);



        timerStartButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {

                //Start Button
                if (counter == 0 && beep == false) {
                    //	                	  remainTime=maxTime;
                    handler.removeCallbacks(mUpdateTimeTask);
                    handler.postDelayed(mUpdateTimeTask, 0);


                }
                //Cancel Button
                else if (counter != 0) {
                    timer.cancel();
                    cancelNotification();
                    handler.removeCallbacks(mUpdateTimeTask);
                    timerLabel.setText(String.format("%02d", 0));
                    counter = 0;
                    remainTime = maxTime;
                    beep = false;
                }
                // Stop Beep
                if (remainTime == -1 && beep == true) {
                    cancelNotification();
                    timerLabel.setText(String.format("%02d", 0));
                    remainTime = maxTime;
                    counter = 0;
                    beep = false;
                }


            }
        });
    }


    private final Runnable mUpdateTimeTask = new Runnable() {

        public void run() {
            if (remainTime >= 0) {
                if (counter == 3) {

                    initNotification("3 senconds elapsed!", 1);
                    beep = true;

                }

                timerLabel.setText(String.format("%02d", remainTime));
                counter++;
                remainTime = maxTime - counter;
                handler.postDelayed(this, 1000);

            }
            if (remainTime == -1) timer.schedule(new TimerTask() {

                @Override
                public void run() {
                    handler.post(new Runnable() {
                        public void run() {
                            initNotification("time is up!", 0);
                        }
                    });
                }
            }, 0, 500);

        }
    };
}


//	       
//	    /*
//	    @Override
//	    protected void onPause() {
//	      handler.removeCallbacks(mUpdateTimeTask);
//	      super.onPause();
//	    }
//	    @Override
//	    protected void onResume() {
//	      super.onResume();
//	      handler.postDelayed(mUpdateTimeTask, 100);
//	    }
//	    */