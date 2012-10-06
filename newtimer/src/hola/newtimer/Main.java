package hola.newtimer;

import hola.newtimer.model.DefaultCounter;
import hola.newtimer.model.Counter;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class Main extends Activity {
	private int min =0, max=99,beep=0;
    private Handler handler = new Handler();
	private Counter model= new DefaultCounter(min, max);
    private static int NOTIFICATION_ID = 1;
	private static String TAG = "project1a-clickcounter-android";

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.i(TAG, "onCreate");
		configureApp();
	}

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }

    @Override
	protected void onStart() {
		super.onStart();
		Log.i(TAG, "onstartAdding");
		updateView();
	}
/**
 * The model's state is represented by one of the following integer values:
 * 0: represents the initial state of the Counter where value = 0 
 * 1: represents the state of the model while incrementing
 * -1: represents the state of the model while decrementing
 * 
 */
    
    
    //Instance of a runnable object to pass methods to handler
    private final Runnable countDownTask = new Runnable() {
		@Override
		public void run() {
			if(model.getValue()>=0&&model.getStatus()==-1){
				updateView();
				handler.postDelayed(this, 1000);
				model.decrement();
			}
			else if(model.getValue()==-1&&model.getStatus()==-1){
				model.reset();
				updateView();
				initNotification("Time's Up!",0);
				beep=1;
			}

		}};
	
		
	//Instance of a CountDownTimer object used when timer initially starts
	private CountDownTimer threeSecondCountdown = new CountDownTimer(3000, 1000){
    	
	    @Override     
	    public void onFinish() {  
	    	initNotification("Counting Down",1);
	    	handler.postDelayed(countDownTask, 0);
	    	model.setStatus(-1);
	    }     
	    @Override     
	    public void onTick(long millisUntilFinished) {  
	    	
	    	}};	
	    	
	    	
	/**
	* Instantiates model and connects listeners to views
	*/
	protected void configureApp() {
		setContentView(R.layout.activity_main);

		// catch checked exceptions
		try {
			// for flexibility, instantiate model based on externally configured
			// class name
			model = Class
					.forName(getResources().getString(R.string.model_class))
					.asSubclass(Counter.class).newInstance();
		} catch (final Throwable ex) {
			Log.d(TAG, "checked exception while instantiating model", ex);
			// re-throw as unchecked exception
			throw new RuntimeException(ex);
		}

		
		//Instance theOnlyButton
		final Button theSingleButton = (Button)findViewById(R.id.theOnlyButton);
		
		//Set up works after clicking theOnlyButton.
		theSingleButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(final View v) {	
				threeSecondCountdown.cancel();
				if(model.getStatus()==0||model.getStatus()==1)
				threeSecondCountdown.start();
				//initiate state
				if(model.getStatus()==0){model.increment();updateView();model.setStatus(1);}
				//incrementing
				else if(model.getStatus()==1){model.increment();updateView();}
				else if(model.getStatus()==-1){
					//cancel
					if(beep==0){model.setStatus(0);model.reset();updateView();}
					//stop
					else {cancelNotification();model.reset();updateView();model.setStatus(0);}
				}
		    		}
		    
		});
		}

	/**
	 * Updates the view based on state of model
	 */
	private void updateView() {
		TextView valueView = (TextView) findViewById(R.id.textTimer);
		valueView.setText(String.format("%02d", (model.getValue())));
		((Button) findViewById(R.id.theOnlyButton)).setEnabled(!model
				.isFull());
	}

	
	//initialize a notification with (text showed up, beep options) 
	
    @SuppressWarnings("deprecation")
	private void initNotification(CharSequence tickerText, int beepTime) {
        String ns = Context.NOTIFICATION_SERVICE;
        NotificationManager mNotificationManager = (NotificationManager) getSystemService(ns);
        int icon = R.drawable.ic_launcher;

        long when = System.currentTimeMillis();
        Notification notification = new Notification(icon, tickerText, when);
        notification.defaults |= Notification.DEFAULT_SOUND;
        if (beepTime == 1) notification.flags = Notification.FLAG_ONLY_ALERT_ONCE;
        else notification.flags = Notification.FLAG_INSISTENT;
        Context context = getApplicationContext();
        CharSequence contentTitle = "Timer";
        CharSequence contentText = "Alert";
        Intent notificationIntent = new Intent(this, Main.class);
        PendingIntent contentIntent = PendingIntent.getActivity(this, 0, notificationIntent, 0);

        notification.setLatestEventInfo(context, contentTitle, contentText, contentIntent);
        mNotificationManager.notify(NOTIFICATION_ID, notification);
    }
    
    //cancel current notification
    private void cancelNotification() {
        String ns = Context.NOTIFICATION_SERVICE;
        NotificationManager mNotificationManager = (NotificationManager) getSystemService(ns);
        mNotificationManager.cancel(NOTIFICATION_ID);
    }
	 
}


