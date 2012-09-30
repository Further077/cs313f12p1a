package hola.newtimer.test;

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import hola.newtimer.model.DefaultCounter;


public class DefaultCounterTest extends AbstractCounterTest {

	private final static int MIN = 0;
	private final static int MAX = 99;
	
	@Before
	public void setUp() throws Exception {
		setCounter(new DefaultCounter(MIN,MAX));
	}

	@After
	public void tearDown() throws Exception {
		setCounter(null);
	}

	@Test
	public void testIntialMinValue() {
		assertEquals(MIN,counter.getValue());
	}
	
	@Test
	public void testMaxValue(){
		while(!counter.isFull())
		{
			counter.increment();
		}
		assertEquals(MIN,counter.getValue());
	}

}
