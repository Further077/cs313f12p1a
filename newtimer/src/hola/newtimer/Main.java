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
	int min =0, max=99,counter=0,allowCountDown=0,beep=0,count=0,startAdding=0; double endTime=0,startTime=0;
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


    //Instance a runnable to  run every one second passed through handler.
    private final Runnable countDownTask = new Runnable() {
		@Override
		public void run() {
			if(model.getValue()>=0&&model.getStatus()==2){
				updateView();
				handler.postDelayed(this, 1000);
				model.decrement();
//				 TextView valueView = (TextView) findViewById(R.id.textTimer);
//					valueView.setText(("hello"+(int)model.getValue()));
			}
			else if(model.getValue()==-1&&model.getStatus()==2){
				model.reset();
				updateView();
				initNotification("time is up!",0);
				beep=1;
			}

		}};
	
		
	//Instance a CountDownTimer used when the timer first ever starts.
	private CountDownTimer threeSeconds = new CountDownTimer(3000, 1000){
    	
	    @Override     
	    public void onFinish() {  
	    	initNotification("falling down",1);
	    	handler.postDelayed(countDownTask, 0);
	    	model.setStatus(2);

	    	
	    }     
	    @Override     
	    public void onTick(long millisUntilFinished) {  
	    	
	    	}};	
	    	
	    	
	/**
	* Instantiates model and connects listeners to views.
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
		final Button theOnlyButton = (Button)findViewById(R.id.theOnlyButton);
		
		//Set up works after clicking theOnlyButton.
		theOnlyButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(final View v) {	
				threeSeconds.cancel();
				if(model.getStatus()==0||model.getStatus()==1)
				threeSeconds.start();
				//initiate state
				if(model.getStatus()==0){model.increment();updateView();model.setStatus(1);}
				//incrementing
				else if(model.getStatus()==1){model.increment();updateView();}
				else if(model.getStatus()==2){
					//cancle
					if(beep==0){model.setStatus(0);model.reset();updateView();}
					//stop
					else {cancelNotification();model.reset();updateView();model.setStatus(0);}
				}

				
				
				
				
				
				
				
		    		}
		    
		});
		}

	/**
	 * Updates the view from the model.
	 */
	private void updateView() {
		 TextView valueView = (TextView) findViewById(R.id.textTimer);
		valueView.setText(String.format("%02d", (model.getValue())));
		// afford controls according to model state
		((Button) findViewById(R.id.theOnlyButton)).setEnabled(!model
				.isFull());
//		((Button) findViewById(R.id.button_decrement)).setEnabled(!model
//				.isEmpty());
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
        CharSequence contentTitle = "My notification";
        CharSequence contentText = "Hello World!";
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


