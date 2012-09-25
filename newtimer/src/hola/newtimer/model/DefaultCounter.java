package hola.newtimer.model;


public class DefaultCounter implements Counter{


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
    this(0, 30);
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

  public void increment(int number) {
    assert dataInvariant() && ! isFull();
    this.value =value+number;
    assert dataInvariant();
  }

  public void decrement(int number) {
    assert dataInvariant() && ! isEmpty();
    this.value =value-number;
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
  

}


