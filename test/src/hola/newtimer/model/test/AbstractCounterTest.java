package hola.newtimer.model.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import hola.newtimer.model.Counter;

import org.junit.Test;

/**
 * Testcase superclass for the counter abstraction.
 * @author laufer
 * @see http://xunitpatterns.com/Testcase%20Superclass.html
 */
public abstract class AbstractCounterTest {

	protected Counter counter;

	/**
	 * Setter for dependency injection. Usually invoked by concrete testcase
	 * subclass.
	 * 
	 * @param counter
	 */
	protected void setCounter(final Counter counter) {
		this.counter = counter;
	}

	@Test
	public void testPreconditions() {
		// this counter has at least two different values
		assertFalse(counter.isEmpty() && counter.isFull());
	}

	@Test
	public void testIncrement() {
		decrementIfFull();
		assertFalse(counter.isFull());
		final int v = counter.getValue();
		counter.increment();
		assertEquals(v + 1, counter.getValue());
	}

	@Test
	public void testDecrement() {
		incrementIfEmpty();
		assertFalse(counter.isEmpty());
		final int v = counter.getValue();
		counter.decrement();
		assertEquals(v - 1, counter.getValue());
	}

	@Test
	public void testReset() {
		incrementIfEmpty();
		assertFalse(counter.isEmpty());
		counter.reset();
		assertTrue(counter.isEmpty());
	}

	@Test
	public void testGet() {
		// cannot check much other than consistency across invocations
		final int v = counter.getValue();
		assertEquals(v, counter.getValue());
	}

	@Test
	public void testIsFull() {
		decrementIfFull();
		assertFalse(counter.isFull());
	}

	@Test
	public void testIsEmpty() {
		incrementIfEmpty();
		assertFalse(counter.isEmpty());
	}
	
	// Test for model value of Counter object
	@Test
	public void testgetValue() {
		final int v = counter.getValue();
		assertEquals(v, counter.getValue());
	}
	
	// Test for model status method getStatus
	@Test
	public void testgetStatus() {
		final int v = counter.getStatus();
		assertEquals(v, counter.getStatus());
	}
	
	
	// Test for model status after altering model state
	@Test
	public void testSetStatus() {
		incrementIfEmpty();
		assertFalse(counter.isEmpty());
		final int v = counter.getStatus();
		counter.setStatus(v);
		assertEquals(counter.getStatus(),v);
	}
 
	protected void decrementIfFull() {
		if (counter.isFull()){ counter.decrement(); }
	}

	protected void incrementIfEmpty() {
		if (counter.isEmpty()) { counter.increment(); }
	}
}