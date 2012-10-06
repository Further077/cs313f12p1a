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

    @UiThreadTest
    public  void test1() throws InterruptedException {
    	
    	assertEquals(0, getDisplayedValue());
    	assertTrue(pressTheOnlyButton().isEnabled());
    	assertTrue(pressTheOnlyButton().performClick());
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
    		counter++;

    	}
    	assertFalse(pressTheOnlyButton().isEnabled());
    	assertEquals(99, getDisplayedValue());//99 max
    }
    
    
//    The @UiThreadTest annotation tells Android to build this method so that
//    it runs on the UI thread. This allows the method to change the state of the 
//    spinner widget in the application under test. 
//    This use of @UiThreadTest shows that, if necessary, you can run an entire
//    method on the UI thread.

    // auxiliary methods for easy access to UI widgets
    protected int getDisplayedValue() {
    	final TextView t = (TextView) getActivity().findViewById(R.id.textTimer);
    	return Integer.parseInt(t.getText().toString().trim());
    }

    protected  Button pressTheOnlyButton() {
    	
    	return (Button) getActivity().findViewById(R.id.theOnlyButton);
    }

    /**Testing the countdownTimer was unsuccessful due to the implementation of a thread to 
     * represent the 3 seconds countdown. The UI ThreadTest was our best attempt at creating the test
     */
    //    
//          @UiThreadTest
//          private Thread thread;
//          Runnable runnable = new Runnable() {
//    		@Override
//    		public void run() {
//            	afterSeconds(3000,1);
//    		}
//    	};
//	
//	
//        public MainTest(final Runnable runnable){
//        	
//    		super(Main.class);
//
//            thread = new Thread(new Runnable(){
//            	
//                public void run(){
////                    try{            
//                        runnable.run();
////                    }catch(AssertionError e){
////                        exc = e;
////                    }
//                }
//            });
//        }

/**Another attempt at testing 3 second countdown via thread
 * 
 */
//    protected CountDownTimer afterSeconds( long seconds, final int doClick){
//        CountDownTimer counDownTimer = new CountDownTimer(seconds, 1000){
//
//            @UiThreadTest
//            @Override
//    		public void onFinish() {
//    			if(doClick!=0)
//    	    	assertTrue(pressTheOnlyButton().performClick());
//
//
//    			// TODO Auto-generated method stub
//    			
//    		}
//
//    		@Override
//    		public void onTick(long millisUntilFinished) {
////		    	assertEquals((int)millisUntilFinished/1000, getDisplayedValue());
//
//    			// TODO Auto-generated method stub
//    			
//    		}};
//		return counDownTimer;
//		
//    };
		
	
   
}