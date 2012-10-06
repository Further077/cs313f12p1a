package sostupid.timer;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;

public class MainActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loading);
        Thread logoTimer = new Thread(){
        	public void run(){
        		try{
        			sleep(1000);
        			Intent menuIntent = new Intent("sostupid.timer.MENU");
        			startActivity(menuIntent);
        			
        		} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
        		
        		finally{
        			finish();
        			
        		}
        	}
        	
        };
        logoTimer.start();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
}
