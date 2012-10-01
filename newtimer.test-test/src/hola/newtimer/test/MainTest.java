package hola.newtimer.test;

import hola.newtimer.Main;
import android.test.ActivityInstrumentationTestCase2;
import android.test.UiThreadTest;
import android.widget.Button;
import android.widget.TextView;

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
//    @UiThreadTest
//    public void testActivityScenarioIncReset() {
//    	assertEquals(0, getDisplayedValue());
//    	assertTrue(getIncButton().isEnabled());
//    	assertFalse(getDecButton().isEnabled());
//    	assertTrue(getResetButton().isEnabled());
//    	assertTrue(getIncButton().performClick());
//    	assertEquals(1, getDisplayedValue());
//    	assertTrue(getIncButton().isEnabled());
//    	assertTrue(getDecButton().isEnabled());
//    	assertTrue(getResetButton().isEnabled());
//    	assertTrue(getResetButton().performClick());
//    	assertEquals(0, getDisplayedValue());
//    	assertTrue(getIncButton().isEnabled());
//    	assertFalse(getDecButton().isEnabled());
//    	assertTrue(getResetButton().isEnabled());
//    }
//
//    @UiThreadTest
//    public void testActivityScenarioIncUntilFull() {
//    	assertEquals(0, getDisplayedValue());
//    	assertTrue(getIncButton().isEnabled());
//    	assertFalse(getDecButton().isEnabled());
//    	assertTrue(getResetButton().isEnabled());
//    	while (getIncButton().isEnabled()) {
//    		final int v = getDisplayedValue();
//        	assertTrue(getIncButton().performClick());
//        	assertEquals(v + 1, getDisplayedValue());
//    	}
//    	assertFalse(getIncButton().isEnabled());
//    	assertTrue(getDecButton().isEnabled());
//    	assertTrue(getResetButton().isEnabled());
//    }
//
//    // auxiliary methods for easy access to UI widgets
//
//    protected int getDisplayedValue() {
//    	final TextView t = (TextView) getActivity().findViewById(R.id.textTimer);
//    	return Integer.parseInt(t.getText().toString().trim());
//    }
//
//    protected Button getIncButton() {
//    	return (Button) getActivity().findViewById(R.id.theOnlyButton);
//    }
//
//    protected Button getDecButton() {
//    	return (Button) getActivity().findViewById(R.id.theOnlyButton);
//    }
//
//    protected Button getResetButton() {
//    	return (Button) getActivity().findViewById(R.id.theOnlyButton);
//    }
}