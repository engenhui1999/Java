package cs2030s.fp;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * A class that builds a possibly infinite number of
 * elements with the help of lazy evaluation.
 *
 * @author Eng En Hui (10J)
 * @version CS2030S AY 21/22 Sem 2
 *
 */

public class InfiniteList<T> {
  /**
   * A static class that contains nothing is used to
   * mark the end of a list, changing an infinite list
   * to a finite list.
   */
  private static class Sentinel extends InfiniteList<Object> {
    private static final InfiniteList<?> SENTINEL = new Sentinel();

    /**
     * If this method is called within the Sentinel class,
     * then a NoSuchElementException will be thrown.
     *
     * @throws NoSuchElementException This will be thrown if
     the method is called within Sentinel as Sentinel contains
     nothing.
     */
    @Override
    public Object head() {
      throw new NoSuchElementException();
    }

    /**
     * If this method is called within the Sentinel class,
     * then a NoSuchElementException will be thrown.
     *
     * @throws NoSuchElementException This will be thrown if
     the method is called within Sentinel as Sentinel contains
     nothing.
     */
    @Override
    public InfiniteList<Object> tail() {
      throw new NoSuchElementException();
    }

    /**
     * Returns Sentinel as there will be no elements to map to.
     *
     * @param <R> the type of the returned InfiniteList.
     * @param mapper The transformer that returns an InfiniteList
     of type 'R'.
     * @return The Sentinel.
     */
    @Override
    public <R> InfiniteList<R> map(Transformer<? super Object, ? extends R> mapper) {
      return InfiniteList.sentinel();
    }

    /**
     * Returns Sentinel as there will be no elements to filter.
     *
     * @param predicate The BooleanCondition given.
     * @return The Sentinel.
     */
    @Override
    public InfiniteList<Object> filter(BooleanCondition<? super Object> predicate) {
      return InfiniteList.sentinel();
    }
  
    /**
     * Returns Sentinel as there will be no elements to limit. 
     *
     * @param n The number of elements.
     * @return The Sentinel.
     */
    @Override
    public InfiniteList<Object> limit(long n) {
      return InfiniteList.sentinel();
    }
    
    /**
     * Returns an empty ArrayList as there are no elements.
     *
     * @return An empty ArrayList.
     */
    @Override
    public List<Object> toList() {
      return new ArrayList<>();
    }

    /**
     * Returns Sentinel as there will be no elements to takeWhile.
     *
     * @param predicate The given BooleanCondition.
     * @return The Sentinel.
     */
    @Override
    public InfiniteList<Object> takeWhile(BooleanCondition<? super Object> predicate) {
      return InfiniteList.sentinel();
    }

    /**
     * Returns identity as there are no elements to reduce.
     *
     * @param <U> The type of the final result.
     * @param identity The initial value.
     * @param accumulator The given Combiner.
     * @return The identity.
     */
    @Override
    public <U> U reduce(U identity, Combiner<U, ? super Object, U> accumulator) {
      return identity;
    }

    /**
     * Returns 0 as there are no elements in Sentinel.
     *
     * @return 0.
     */
    @Override
    public long count() {
      return 0;
    }

    /**
     * Returns the string representation of the Sentinel.
     *
     * @return The string representation of the Sentinel.
     */
    @Override
    public String toString() {
      return "-";
    }
  }
  
  /** The head of the InfiniteList. */
  private final Lazy<Maybe<T>> head;
  /** The tail of the InfiniteList. */
  private final Lazy<InfiniteList<T>> tail;

  /**
   * A private constructor to initialise the InfiniteList
   * class with the head and tail being null.
   */
  private InfiniteList() { 
    this.head = null; 
    this.tail = null;
  }
 
  /**
   * A private constructor to initialise the InfiniteList
   * class with the given head and tail.
   *
   * @param head The given head to be cached.
   * @param tail The given producer to produce the tail.
   */
  private InfiniteList(T head, Producer<InfiniteList<T>> tail) {
    this.head = Lazy.<Maybe<T>>of(Maybe.<T>some(head));
    this.tail = Lazy.<InfiniteList<T>>of(tail);
  }

  /**
   * A private constructor to initialise the InfiniteList
   * class with the given head and tail.
   *
   * @param head The given head.
   * @param tail The given tail.
   */
  private InfiniteList(Lazy<Maybe<T>> head, Lazy<InfiniteList<T>> tail) {
    this.head = head;
    this.tail = tail;
  }

  /**
   * Create an InfiniteList with all of its elements being
   * the value produced from the given producer.
   *
   * @param <T> The type of the producer given.
   * @param producer The producer that produces a value.
   * @return The created InfiniteList.
   */
  public static <T> InfiniteList<T> generate(Producer<T> producer) {
    return new InfiniteList<T>(
    Lazy.<Maybe<T>>of(() -> Maybe.<T>some(producer.produce())), 
    Lazy.<InfiniteList<T>>of(() -> generate(producer))
    );
  }
  
  /**
   * Create an InfiniteList with the first element being
   * the initial value and its subsequent elements being
   * transformed by the transformer.
   *
   * @param <T> The type of the transformer given.
   * @param seed The initial value.
   * @param next The transformer to transform the next element.
   * @return The created InfiniteList.
   */
  public static <T> InfiniteList<T> iterate(T seed, Transformer<T, T> next) {
    return new InfiniteList<T>(
    Lazy.<Maybe<T>>of(Maybe.<T>some(seed)),
    Lazy.<InfiniteList<T>>of(() -> iterate(next.transform(seed), next))
    );
  }
  
  /**
   * Return the head of the InfiniteList.
   *
   * @return The head of the InfiniteList.
   */
  public T head() {
    return this.head.get().orElseGet(() -> this.tail.get().head());
  }

  /**
   * Return the tail of the InfiniteList.
   *
   * @return The tail of the InfiniteList.
   */
  public InfiniteList<T> tail() {
    return this.head.get().map(x -> this.tail.get()).orElseGet(() -> this.tail.get().tail());
  }

  /**
   * Transforms the InfiniteList of type 'T' into an
   * InfiniteList of type 'R'. The method should lazily
   * apply the given transformation to each element.
   *
   * @param <R> the type of the returned InfiniteList.
   * @param mapper The transformer that returns an InfiniteList
   of type 'R'.
   * @return The transformed InfiniteList.
   */
  public <R> InfiniteList<R> map(Transformer<? super T, ? extends R> mapper) {
    return new InfiniteList<R>(
    Lazy.<Maybe<R>>of(() -> Maybe.<R>some(mapper.transform(this.head()))), 
    Lazy.<InfiniteList<R>>of(() -> this.tail().map(mapper))
    );
  }

  /**
   * Test if elements passes the predicate given. The elements
   * that fails the predicate will be marked as Maybe.none().
   * This process will also be a lazy evaluation.
   *
   * @param predicate The BooleanCondition given.
   * @return An InfiniteList with its elements being filtered.
   */
  public InfiniteList<T> filter(BooleanCondition<? super T> predicate) {
    return new InfiniteList<T>(
    Lazy.<Maybe<T>>of(() -> this.head.get().filter(predicate)),
    Lazy.<InfiniteList<T>>of(() -> this.tail.get().filter(predicate))
    );
  }

  /**
   * Method to create the Sentinel class. Sentinel contains
   * nothing and is used to mark the end of an InfiniteList.
   *
   * @param <T> The type of the Sentinel class.
   * @return The created Sentinel.
   */
  public static <T> InfiniteList<T> sentinel() {
    @SuppressWarnings("unchecked")
    InfiniteList<T> sentinel = (InfiniteList<T>) Sentinel.SENTINEL;
    return sentinel;
  }
  
  /**
   * Test if the list is an instance of 'Sentinel'. Returns
   * true if the list is an instance of 'Sentinel' and false
   * otherwise.
   *
   * @return A boolean value.
   */
  public boolean isSentinel() {
    return this instanceof Sentinel;
  }

  /**
   * Terminates an InfiniteList into a finite List with at
   * most 'n' number of elements. This method truncates the
   * InfiniteList and marks the end of the list with a
   * Sentinel.
   *
   * @param n The number of elements.
   * @return The truncated InfiniteList.
   */
  public InfiniteList<T> limit(long n) {
    return n <= 0 ? this.sentinel() : new InfiniteList<T>(
    this.head,
    Lazy.<InfiniteList<T>>of(
      () -> this.head.get()
      .map(head -> this.tail.get().limit(n - 1))
      .orElseGet(() -> this.tail.get().limit(n))
      )
    );
  }

  /**
   * Collects the elements in the InfiniteList into a
   * 'java.util.List'.
   *
   * @return The List representation of the InfiniteList.
   */
  public List<T> toList() {
    return this.reduce(new ArrayList<T>(), (x, y) -> {
      x.add(y);
      return x;
    });
  }

  /**
   * Truncates the InfiniteList as soon as there is an element
   * that fails the predicate.
   *
   * @param predicate The given BooleanCondition.
   * @return The truncated InfiniteList.
   */
  public InfiniteList<T> takeWhile(BooleanCondition<? super T> predicate) {
    Lazy<Maybe<T>> h = this.head.map(head -> head.filter(predicate));
    Lazy<InfiniteList<T>> t = this.tail.map(tail -> tail.takeWhile(predicate));
    return new InfiniteList<T>(
      h,
      Lazy.<InfiniteList<T>>of(
        () -> this.head.get()
        .map(h1 -> h.get().map(h2 -> t.get()).orElseGet(() -> this.sentinel()))
        .orElseGet(() -> t.get())
      )
    );
  }

  /**
   * Performs a reduction on the elements of the InfiniteList,
   * using an accumulator to produce a final result.
   *
   * @param <U> The type of the final result.
   * @param identity The initial value.
   * @param accumulator The given Combiner.
   * @return The final result.
   */
  public <U> U reduce(U identity, Combiner<U, ? super T, U> accumulator) {
    return this.head.get()
      .map(x -> this.tail.get().reduce(accumulator.combine(identity, x), accumulator))
      .orElseGet(() -> this.tail.get().reduce(identity, accumulator));
  }

  /**
   * Returns the number of elements in the InfiniteList.
   * The InfiniteList should have already been truncated,
   * otherwise this will run into an error.
   *
   * @return The number of elements.
   */
  public long count() {
    return this.reduce(0L, (x, y) -> x + 1L);
  }

  /**
   * Returns the string representation of the InfiniteList.
   *
   * @return The string representation of the InfiniteList.
   */
  public String toString() {
    return "[" + this.head + " " + this.tail + "]";
  }
}
