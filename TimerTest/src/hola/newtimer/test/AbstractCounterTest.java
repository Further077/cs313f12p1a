package hola.newtimer.test;

import static org.junit.Assert.*;
import org.junit.Test;
import hola.newtimer.model.Counter;

public abstract class AbstractCounterTest {

	//public AbstractCounterTest(){
	
	//Counter object used in unit tests
	protected Counter counter;

	protected void setCounter(final Counter counter) 
	{
		this.counter = counter;
	}
	
	@Test
	public void firstTest(){
		assertFalse(counter.isEmpty()&& counter.isFull());
	}
	
	@Test 
	public void testIncrement(){
		decrementIfFull();
		assertFalse(counter.isFull());
		final int i = counter.getValue();
		counter.increment();
		assertEquals(i + 1, counter.getValue());	
	}
	
	@Test
	public void testDecrement(){
		incrementIfEmpty();
		assertFalse(counter.isEmpty());
		final int i = counter.getValue();
		counter.decrement();
		assertEquals(i - 1, counter.getValue());
	}
	
	@Test 
	public void testReset(){
		//incrementIfEmpty();
		assertFalse(counter.isEmpty());
		counter.reset();
		assertTrue(counter.isEmpty());
	}
	
	@Test
	public void testGetValue(){
		final int i = counter.getValue();
		assertEquals(i,counter.getValue());
	}
	
	@Test
	public void testIsEmpty(){
		incrementIfEmpty();
		assertFalse(counter.isEmpty());
	}
	
	protected void decrementIfFull(){
		if(counter.isFull())
		{
			counter.decrement();
		}
		}
	
	protected void incrementIfEmpty(){
		if (counter.isEmpty())
		{
			counter.increment();
		}
		}
}
