package cs2030s.fp;

/**
 * A class that delays the evaluation of an
 * expression until the value is needed.
 *
 * @author Eng En Hui (10J)
 * @version CS2030S AY 21/22 Sem 2
 *
 */
public class Lazy<T> {
  /** The producer that produces the value when needed. */ 
  private Producer<? extends T> producer;
  /** The value that is cached within the Lazy class. */
  private Maybe<T> value;
  

  /**
   * A private constructor to initialise the Lazy class
   * with the given value.
   *
   * @param v The given value to be cached.
   */
  private Lazy(T v) {
    this.value = Maybe.<T>some(v);
  }

  /**
   * A private constructor to initialise the Lazy class
   * with the given producer.
   *
   * @param s The given producer.
   */
  private Lazy(Producer<? extends T> s) {
    this.producer = s;
    this.value = Maybe.<T>none();
  }

  /**
   * Create a Lazy class with the given value.
   *
   * @param <T> The type of the value given.
   * @param v The given value to be cached.
   * @return The created Lazy.
   */
  public static <T> Lazy<T> of(T v) {
    return new Lazy<T>(v);
  }

  /**
   * Create a Lazy class with the given producer.
   *
   * @param <T> The type of the producer given.
   * @param s The producer that produces a value when needed.
   * @return The created Lazy.
   */
  public static <T> Lazy<T> of(Producer<? extends T> s) {
    return new Lazy<T>(s);
  }

  /**
   * Method that is called when the value is needed and
   * will produce the value if it is not available. If
   * the is already available, return that value. Otherwise
   * the value will be computed and returned. The computation
   * will only be done once for the same value.
   *
   * @return The value.
   */
  public T get() {
    T val = this.value.orElseGet(this.producer);
    this.value = Maybe.some(val);
    return val;
  }

  /**
   * Return the string representation of the list.
   *
   * @return The string representation of the list.
   */
  @Override
  public String toString() {
    return this.value.map((Object x) -> String.valueOf(x)).orElse("?");
  }
 
  /**
   * Transforms the Lazy of type 'T' into a value of type 'U'.
   * The transformer passed into the 'map' method should not be evaluated
   * until get() is called, and will only be evaluated once.
   *
   * @param <U> The type of the returned Lazy.
   * @param transformer The transformer that returns a value of type 'U'.
   * @return The transformed Lazy.
   */
  public <U> Lazy<U> map(Transformer<? super T, ? extends U> transformer) {
    return Lazy.<U>of(() -> transformer.transform(this.get()));
  }

  /**
   * Transform the Lazy of type 'T' into a value of type 'U'.
   * The transformer passed into the 'flatMap' method should not be
   * evaluated until get() is called, and will only be evaluated once.
   *
   * @param <U> The type of the returned Lazy
   * @param transformer The transformer that returns a Lazy of type 'U'.
   * @return The transformed Lazy.
   */
  public <U> Lazy<U> flatMap(Transformer<? super T, ? extends Lazy<? extends U>> transformer) {
    Producer<U> p = () -> transformer.transform(this.get()).get();
    return Lazy.<U>of(p);
  }
  
  /**
   * Test if the value passes the condition given. The BooleanCondition
   * should only be executed at most once.
   *
   * @param condition The BooleanCondition given.
   * @return A Lazy with the boolean value.
   */
  public Lazy<Boolean> filter(BooleanCondition<? super T> condition) {
    return Lazy.<Boolean>of(() -> condition.test(this.get()));
  }

  /**
   * Method to test whether both objects are equal. The method will only
   * return true only if both objects being compared are 'Lazy' and the
   * value contained within it are equal (according to their 'equals()').
   *
   * @param other The object to be compared to.
   * @return A boolean value.
   */
  @Override
  public boolean equals(Object other) {
    if (this == other) {
      return true;
    }
    if (other instanceof Lazy<?>) {
      @SuppressWarnings("unchecked")
      Lazy<?> otherLazy = (Lazy<?>) other;
      if (this.get() == otherLazy.get()) {
        return true;
      } else if (this.get() == null || otherLazy.get() == null) {
        return false;
      } 
      return this.get().equals(otherLazy.get());
    }
    return false;
  }

  /**
   * Method that combines two 'Lazy' into one 'Lazy'. The two 'Lazy'
   * objects may contain values of different types.
   *
   * @param <U> The type of the other 'Lazy' object to be combined.
   * @param <R> The type of the returned 'Lazy' object.
   * @param lazy the 'Lazy' object to be combined.
   * @param combiner The given combiner.
   * @return A 'Lazy' created from combining the two 'Lazy' objects.
   */
  public <U, R> Lazy<R> combine(
      Lazy<U> lazy, Combiner<? super T, ? super U, ? extends R> combiner) {
    Producer<R> r = () -> combiner.combine(this.get(), lazy.get());
    return Lazy.<R>of(r);
  }  
}
