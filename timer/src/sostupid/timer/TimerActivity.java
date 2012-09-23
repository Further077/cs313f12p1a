package sostupid.timer;

import android.app.Activity;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class TimerActivity extends Activity {
     
    TextView mButtonLabel;
   
    // Counter of time since app started ,a background task
    private long mStartTime = 0L;
    private TextView mTimeLabel,mTimerLabel;
   
    // Handler to handle the message to the timer task
    private Handler mHandler = new Handler();
          
    static final int UPDATE_INTERVAL = 1000;
   
    String timerStop1;
   
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
                                         
        mTimeLabel = (TextView) findViewById(R.id.countDownTv);
        mButtonLabel = (TextView) findViewById(R.id.countDown);
               
        mTimerLabel = (TextView) findViewById(R.id.textTimer);
        mButtonLabel = (TextView) findViewById(R.id.countDown);
       
       
        Button countDownButton = (Button) findViewById(R.id.countDown);      
        countDownButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view){
                                   
                  CountDownTimer timer1 = new CountDownTimer(99000,1000){

                        @Override
                        public void onFinish() {
                             
                              mTimeLabel.setText("Done!");
                        }

                        @Override
                        public void onTick(long millisUntilFinished) {                               
                             
                              int seconds = (int) (millisUntilFinished / 1000);
                              int minutes = seconds / 60;
                              seconds = seconds % 60;
                             
                              mTimeLabel.setText("" + minutes + ":"
                                                              + String.format("%02d", seconds));
                             
                        }          
                  }.start();
                 
            }
        });
              
       
        Button timerStartButton = (Button) findViewById(R.id.btnTimer);      
        timerStartButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view){
                 
                  if(mStartTime == 0L){
                  mStartTime = SystemClock.uptimeMillis();
                  mHandler.removeCallbacks(mUpdateTimeTask);
                  mHandler.postDelayed(mUpdateTimeTask, 100);
                 
                }                                          
            }
        });
       
        Button timerStopButton = (Button) findViewById(R.id.btnTimerStop);      
        timerStopButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view){
                             
                  mHandler.removeCallbacks(mUpdateTimeTask);
                 
                  mTimerLabel.setText(timerStop1);
                  mStartTime = 0L;
                                   
            }
        });
                    
    } // OnCreate
   
       
    private Runnable mUpdateTimeTask = new Runnable(){

            public void run() {
                 
                  final long start = mStartTime;
                  long millis = SystemClock.uptimeMillis()- start;
                             
                  int seconds = (int) (millis / 1000);
                  int minutes = seconds / 60;
                  seconds = seconds % 60;
                 
                  mTimerLabel.setText("" + minutes + ":"
                                                  + String.format("%02d", seconds));                             
                 
                  timerStop1 = minutes + ":"
                                + String.format("%02d", seconds);
                                               
                  mHandler.postDelayed(this, 200);               
                 
            }    
    };
       
    /*
    @Override
    protected void onPause() {
      mHandler.removeCallbacks(mUpdateTimeTask);
      super.onPause();
    }
    @Override
    protected void onResume() {
      super.onResume();
      mHandler.postDelayed(mUpdateTimeTask, 100);
    }
    */
   
} // class