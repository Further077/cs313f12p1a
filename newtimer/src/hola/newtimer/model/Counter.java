package hola.newtimer.model;

public interface Counter {

	  /**
	   * Increments the counter value.
	   * Precondition: counter is not full.
	   */
	  void increment(int value);

	  /**
	   * Decrements the counter value.
	   * Precondition: counter is not empty.
	   */
	  void decrement(int value);

	  /**
	   * Resets the counter value.
	   * Precondition: true.
	   * Postcondition: counter is not empty.
	   */
	  void reset();

	  /**
	   * Returns the current counter value.
	   * @return the current counter value
	   */
	  int getValue();
	  

	  /**
	   * Indicates whether the counter is full (at its maximum).
	   * @return whether the counter is full
	   */
	  boolean isFull();

	  /**
	   * Indicates whether the counter is empty (at its minimum).
	   * @return whether the counter is empty
	   */
	  boolean isEmpty();
	  int setValue(int number);

}
