package hola.newtimer.model;

public class DefaultCounter implements Counter{
  private  int status = 0;
  /**
   * The lower bound of the counter.
   */
  private final int min;

  /**
   * The upper bound of the counter.
   */
  private final int max;

  /**
   * The current value of the counter.
   */
  private int value;

  /**
   * Constructs a bounded counter with the default bounds.
   */
  public DefaultCounter() {
    this(0, 99);
  }

  /**
   * Constructs a bounded counter with the given bounds.
   * @param min the lower bound
   * @param max the upper bound
   * @throws IllegalArgumentException if min > max
   */
  public DefaultCounter(final int min, final int max) {
    if (min >= max) {
      throw new IllegalArgumentException("min >= max");
    }
    this.min = min;
    this.max = max;
    this.value = this.min;
  }

  /**
   * Indicates whether this counter currently satisfies its internal
   * data invariant: the counter value is within the bounds.
   * @return whether the data invariant is satisfied
   */
  protected boolean dataInvariant() {
    return min <= value && value <= max;
  }

  public void increment() {
    assert dataInvariant() && ! isFull();
    this.value ++;
    assert dataInvariant();
  }

  public void decrement() {
    assert dataInvariant() && ! isEmpty();
    this.value --;
    assert dataInvariant();
  }

  public void reset() {
    value = min;
    assert dataInvariant() && isEmpty();
  }

  public int getValue() {
    return value;
  }

  public boolean isFull() {
    return value >= max;
  }

  public boolean isEmpty() {
    return value <= min;
  }
  
  public int setValue(int number) {
	  return this.value=number;
  }
  
  // getStatus method returns the current state of the model
  public int getStatus(){
	return status;
  }
  
  // setStatus method used to change the state of the model
  public int setStatus(int number){
	  return this.status = number;
  }

}


