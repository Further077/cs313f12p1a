package sostupid.timer;

import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.CountDownTimer;
import android.os.SystemClock;
import android.os.Bundle;
import android.view.View.OnClickListener;




public class menu extends Activity{
	
	
		 private  static int NOTIFICATION_ID =1;

		private Timer timer = new Timer();

		 private int  maxTime =5,remainTime=5,counter=0,firstClick=0;
		 private Handler mHandler = new Handler();
	     private TextView timerLabel,buttonLabel;
	     private TimerTask beepTask;

	     
	     
		private void initNotification(){
	    	 String ns = Context.NOTIFICATION_SERVICE;
	    	 NotificationManager mNotificationManager = (NotificationManager) getSystemService(ns);
	    	 int icon = R.drawable.ic_launcher;
	    	 CharSequence tickerText = "3 Seconds elapse";
	    	 long when = System.currentTimeMillis();
	    	 Notification notification = new Notification(icon, tickerText, when);
	    	 notification.defaults |= Notification.DEFAULT_SOUND;
	    	 notification.defaults |= Notification.DEFAULT_VIBRATE;
	    	 
	    	 
	    	 notification.flags=Notification.FLAG_INSISTENT;
	    	 Context context = getApplicationContext();
	    	 CharSequence contentTitle = "My notification";
	    	 CharSequence contentText = "Hello World!";
	    	 Intent notificationIntent = new Intent(this, menu.class);
	    	 PendingIntent contentIntent = PendingIntent.getActivity(this, 0, notificationIntent, 0);

	    	 notification.setLatestEventInfo(context, contentTitle, contentText, contentIntent);
	    	 mNotificationManager.notify(NOTIFICATION_ID,notification);
		}
	     private void cancelNotification(){
	    	 String ns = Context.NOTIFICATION_SERVICE;
	    	 NotificationManager mNotificationManager = (NotificationManager) getSystemService(ns);
	    	 mNotificationManager.cancel(NOTIFICATION_ID);
	     }
	     
	     
	     public void onCreate(Bundle savedInstanceState) {
//	    	 public int onStartCommand(Intent intent, int flags, int startID);
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.main);
	   	 
	        buttonLabel = (TextView) findViewById(R.id.countDown);
	        timerLabel = (TextView) findViewById(R.id.textTimer);
	        Button timerStartButton = (Button) findViewById(R.id.btnTimer); 
	        
	       
	       
	        timerStartButton.setOnClickListener(new View.OnClickListener() {
	            public void onClick(View view){
	                 

	                  if(counter==0 ||remainTime==-1){

	  				

	                	  remainTime=maxTime;
	                	  counter=0;
	                	  mHandler.removeCallbacks(mUpdateTimeTask);

	                	  mHandler.postDelayed(mUpdateTimeTask,00);
	                	  
	                  }
	                  
	                  else if(remainTime>0){

	                	  counter=0;
	                	  timerLabel.setText(String.format("%02d", counter));
	                	  mHandler.removeCallbacks(mUpdateTimeTask);
	                	  remainTime=-1;
	                	  
	                  }
	                  
	                  
	             

	                	  
//	                  else if (remainTime>0)
	                	  
//            		  mHandler.removeCallbacks(mUpdateTimeTask);
	            	
	                	  
//	                	  if(remainTime>=0 && remainTime<=maxTime){
//	                		  mHandler.removeCallbacks(mUpdateTimeTask);

//	                	  }
	                	  

//	                	  else if (remainTime==0){
//	                		  counter=0;
//		                	  mHandler.postDelayed(mUpdateTimeTask, 0);
//
//	                 	}
//	                  
	                	  
	            }
	        });
	    }
	    
	    private final Runnable mUpdateTimeTask = new Runnable(){

            public void run() {
            	
            	if(remainTime>=0){
            		
            		if(counter==3){
            			
            			  timer.schedule(new TimerTask() {
 	  				    	 @Override
 	  				    	 public void run() {
 	  				                mHandler.post(new Runnable() {
 	  				                        public void run() {
 	  				                        	initNotification();

 	  				                        	cancelNotification();
 	  				                        	
 	  				                        }
 	  				               });
 	  				        }}, 0);	
            		if(remainTime==0){
            			
            		}
            			  
    	    		}
            	timerLabel.setText(String.format("%02d", remainTime));
	    		remainTime=maxTime-counter-1;
	    		counter++;
	    		
                mHandler.postDelayed(this, 1000);
            	}


        	    	


            }    
    };
    
    
    
    
   


 }
	    
	    

//	       
//	        Button timerStopButton = (Button) findViewById(R.id.btnTimerStop);      
//	        timerStopButton.setOnClickListener(new View.OnClickListener() {
//	            public void onClick(View view){
//	                             
//	                  mHandler.removeCallbacks(mUpdateTimeTask);
//	                 
//	                  timerLable.setText(String.format("%02d", counter));
//	                                   
//	            }
//	        });
//	                    
//	    } // OnCreate

	    
////	       
//	    private Runnable mUpdateTimeTask = new Runnable(){
//
//	            public void run() {
//	                 
////	         
//	                  
////	                  timerLable.setText(counter);     
////	                  if(counter <= maxTime ){
////	                	  timerStop1 = String.format("%02d", counter)+" remainTime:"+counter;    
//	   	                  mHandler.postDelayed(this, 0);   
////	                  }
//	                	  
//	                	  
//	                         
//	                 
//	            }    
//	    };
//	       
//	    /*
//	    @Override
//	    protected void onPause() {
//	      mHandler.removeCallbacks(mUpdateTimeTask);
//	      super.onPause();
//	    }
//	    @Override
//	    protected void onResume() {
//	      super.onResume();
//	      mHandler.postDelayed(mUpdateTimeTask, 100);
//	    }
//	    */

