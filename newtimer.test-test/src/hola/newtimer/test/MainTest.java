package hola.newtimer.test;

import hola.newtimer.Main;
import android.os.CountDownTimer;
import android.test.ActivityInstrumentationTestCase2;
import android.test.UiThreadTest;
import android.widget.Button;
import android.widget.TextView;
import hola.newtimer.R;
/**
 * GUI-level test of several essential click-counter scenarios.
 *
 * @author laufer
 * @see http://developer.android.com/tools/testing/activity_testing.html
 */
public class MainTest extends ActivityInstrumentationTestCase2<Main> {

    /**
     * Creates an {@link ActivityInstrumentationTestCase2} for the {@link SkeletonActivity}
     * activity.
     */
	public MainTest() {
		super(Main.class);
	}

    /**
     * Verifies that the activity under test can be launched.
     */
    public void testActivityTestCaseSetUpProperly() {
        assertNotNull("activity should be launched successfully", getActivity());
    }
//
    
    
    ///press---3.5sec---press
    @UiThreadTest
    public void test1() {
    	assertEquals(0, getDisplayedValue());
    	assertTrue(pressTheOnlyButton().isEnabled());
    	
    	

    	assertTrue(pressTheOnlyButton().performClick());
    	
    	assertEquals(1, getDisplayedValue());
    	assertTrue(pressTheOnlyButton().isEnabled());
    	
    	afterSecondsToClick(3500).start();
    	////////////////////////////should be 0/////////////////
    	assertEquals(1, getDisplayedValue());
    	assertTrue(pressTheOnlyButton().isEnabled());
    }

    

    @UiThreadTest
    public void test2() {
    	int counter=1;
    	assertEquals(0, getDisplayedValue());
    	assertTrue(pressTheOnlyButton().isEnabled());
    	
    	while(counter<=99){    	
    		assertTrue(pressTheOnlyButton().performClick());
        	assertEquals(counter, getDisplayedValue());
    		counter++;
    		
}
    	
    	assertFalse(pressTheOnlyButton().isEnabled());

    	afterSecondsToClick(1000000).start();
    	////////////////////////////should be 0/////////////////
    	assertEquals(99, getDisplayedValue());
//    	assertTrue(pressTheOnlyButton().isEnabled());
    	
    }
    
//    The @UiThreadTest annotation tells Android to build this method so that it runs on the UI thread. This allows the method to change the state of the spinner widget in the application under test. This use of @UiThreadTest shows that, if necessary, you can run an entire method on the UI thread.


    @UiThreadTest
    public void test3() {
    	int counter=1;
    	assertEquals(0, getDisplayedValue());
    	assertTrue(pressTheOnlyButton().isEnabled());
    	
    	while(counter<=13){    	
    		assertTrue(pressTheOnlyButton().performClick());
        	assertEquals(counter, getDisplayedValue());
    		counter++;
    		
}
    	
    	assertTrue(pressTheOnlyButton().isEnabled());

    	afterSecondsToClick(13500).start();
    	////////////////////////////should be 0/////////////////
    	assertEquals(13, getDisplayedValue());
    	assertTrue(pressTheOnlyButton().isEnabled());
    }

    // auxiliary methods for easy access to UI widgets

    protected int getDisplayedValue() {
    	final TextView t = (TextView) getActivity().findViewById(R.id.textTimer);
    	return Integer.parseInt(t.getText().toString().trim());
    }

    protected Button pressTheOnlyButton() {
    	return (Button) getActivity().findViewById(R.id.theOnlyButton);
    }
    
    protected CountDownTimer afterSecondsToClick(final long seconds){
        CountDownTimer counDownTimer = new CountDownTimer(seconds, 1000){

    		@Override
    		public void onFinish() {
    	    	assertTrue(pressTheOnlyButton().performClick());
    	    	    assertEquals(seconds, getDisplayedValue());


    			// TODO Auto-generated method stub
    			
    		}

    		@Override
    		public void onTick(long millisUntilFinished) {
    			// TODO Auto-generated method stub
    			
    		}};
		return counDownTimer;};

   
}