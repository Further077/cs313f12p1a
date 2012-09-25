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
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;


public class Main extends Activity {
	int min =0, max=5,counter=0,allowCountDown=0,beep=0,count=0,startAdding=0;
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
	 * Instantiates model and connects listeners to views.
	 */

    
    private final Runnable countDownTask = new Runnable() {

		@Override
		public void run() {
			if(model.getValue()>0){
				handler.postDelayed(this, 1000);
				updateView();
				model.decrement(1);
				updateView();
			}
			else{
				initNotification("time is up!",0);
				beep=2;
			}
		}};

	private CountDownTimer threeSeconds = new CountDownTimer(3000, 1000){
    	
	    @Override     
	    public void onFinish() {  
	    	initNotification("done",1);
	    	beep=1;
	    	allowCountDown=1;
	    }     
	    @Override     
	    public void onTick(long millisUntilFinished) {  
	    	
	    	}};
    
	    	
//	     public void countButton(final View v){
//	        	
//	        	if(model.getValue()>=0&&allowCountDown==0&beep==0){ 
//	        		counter++;
//	        		model.increment(1);
//	        		updateView();
//	        	}
//	        }   	
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

		
		
		final Button theOnlyButton = (Button)findViewById(R.id.theOnlyButton);
		
		theOnlyButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(final View v) {
				// TODO Auto-generated method stub
	

				//init button
		    	if(model.getValue()==0&&allowCountDown==0&&beep==0&&startAdding==0){ 
		    		threeSeconds.start();
		    		startAdding=1;
		   	    	model.increment(1);
		    		updateView();
		    		counter=0;
		    	}
		    	
		    	//add button
		    	else if(model.getValue()>0&&allowCountDown==0&&beep==0&&startAdding==1){ 
		   	    	model.increment(1);
		    		updateView();
		    	}
		    	
		    	
		    	
		    	// countdown button
		    	if(model.getValue()>0&&allowCountDown==1&&beep==1&&startAdding==1&&counter==0){
		    		
		    		 CountDownTimer countDown = new CountDownTimer(model.getValue()*1000,1000){

		    			@Override
		    			public void onFinish() {
		    				model.setValue(0);
		    				updateView();
		    				initNotification("End!!!!",0);
		    				beep=2;
		    			}

		    			@Override
		    			public void onTick(long millisUntilFinished) {
		    				counter=1;
		    				updateView(String.valueOf((int)Math.floor((millisUntilFinished+.3)/1000)));
//		    				updateView(String.valueOf(millisUntilFinished));
		    			}};
		    			countDown.start();
		    		
//		    		cancle button
		    		if(counter==1){
		    			countDown.cancel();
		    		model.setValue(0);
		    		updateView();
		    		allowCountDown=0;
		    		beep=0;
		    		startAdding=0;
		    		}
		    		}
	
		    	//   stop button
		    	if(model.getValue()>=0&&allowCountDown<=1&&beep>=1){
		    		cancelNotification();
		    		allowCountDown=0;
		    		beep=0;
		    		startAdding=0;
		    		counter=0;
		    		model.setValue(0);
		    	}
		    	

		    	
		    	    
		 
				
			}});
		


	
	}

//		final OnClickListener buttontListener = new OnClickListener() {
//			
//			
//			public void onClick( View v) {
//				
//				/**
//				 * Count Down Method.
//				 */
				        

//				/**
//				 * click buttons
//				 */
//				counter=model.getValue();
//				
//			
//
//			}
//		};
//		findViewById(R.id.theOnlyButton).setOnClickListener(
//				buttontListener);
		
		
	

	/**
	 * Updates the view from the model.
	 */
	protected void updateView() {
		final TextView valueView = (TextView) findViewById(R.id.textTimer);
		valueView.setText(Integer.toString(model.getValue()));
		// afford controls according to model state
		((Button) findViewById(R.id.theOnlyButton)).setEnabled(!model
				.isFull());
//		((Button) findViewById(R.id.button_decrement)).setEnabled(!model
//				.isEmpty());
	}
	
	protected void updateView(String string) {
		final TextView valueView = (TextView) findViewById(R.id.textTimer);
		valueView.setText(string);
		// afford controls according to model state
		((Button) findViewById(R.id.theOnlyButton)).setEnabled(!model
				.isFull());
	}

	
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
    private void cancelNotification() {
        String ns = Context.NOTIFICATION_SERVICE;
        NotificationManager mNotificationManager = (NotificationManager) getSystemService(ns);
        mNotificationManager.cancel(NOTIFICATION_ID);
    }
	 
}



