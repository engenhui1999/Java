/**
 * CS2030S Lab 5.
 * AY20/21 Semester 2
 *
 * @author Put Your Name (Lab Group)
 */

package cs2030s.fp;

import java.util.NoSuchElementException;

public abstract class Maybe<T> {

  protected abstract T get();

  public abstract Maybe<T> filter(BooleanCondition<? super T> condition);
  
  public abstract <U> Maybe<U> map(Transformer<? super T, ? extends U> transformer);
  
  public abstract <U> Maybe<U> flatMap(
      Transformer<? super T, ? extends Maybe<? extends U>> transformer);
  
  public abstract <U extends T> T orElse(U input);
  
  public abstract T orElseGet(Producer<? extends T> producer);

  public abstract void consumeWith(Consumer<? super T> consumer);

  private static class None extends Maybe<Object> {
    private static final Maybe<?> NONE = new None();

    @Override
    public String toString() {
      return "[]";
    }

    @Override
    public boolean equals(Object other) {
      return other instanceof None;
    }

    @Override
    protected Object get() {
      throw new NoSuchElementException();
    }

    @Override
    public Maybe<Object> filter(BooleanCondition<? super Object> condition) {
      return Maybe.none();
    }

    @Override
    public <U> Maybe<U> map(Transformer<? super Object, ? extends U> transformer) { 
      return Maybe.none();
    }

    @Override
    public <U> Maybe<U> flatMap(
        Transformer<? super Object, ? extends Maybe<? extends U>> transformer) {
      return Maybe.none();
    }

    @Override
    public <U> Object orElse(U input) {
      return input;
    }

    @Override
    public Object orElseGet(Producer<? extends Object> producer) {
      return producer.produce();
    }

    @Override
    public void consumeWith(Consumer<? super Object> consumer) {
    }
  }

  private static class Some<T> extends Maybe<T> { 
    private final T t;
    
    protected Some(T t) {
      this.t = t;
    }

    @Override
    public String toString() {
      return "[" + this.get() + "]";
    }

    @Override
    public boolean equals(Object other) {
      if (this == other) {
        return true;
      }

      if (other instanceof Some<?>) {
        @SuppressWarnings("unchecked")
        Some<?> otherSome = (Some<T>) other;
        if (this.get() == otherSome.get()) {
          return true;
        }

        if (this.get() == null || otherSome.get() == null) {
          return false;
        }

        return this.get().equals(otherSome.get());
      }

      return false;
    }

    @Override
    protected T get() {
      return this.t;
    }

    @Override
    public Maybe<T> filter(BooleanCondition<? super T> condition) {
      if (this.t != null && !condition.test(this.t)) {
        return Maybe.none();
      }
      return this;
    }

    @Override
    public <U> Maybe<U> map(Transformer<? super T, ? extends U> transformer) {
      return Maybe.some(transformer.transform(this.get()));
    }

    @Override
    public <U> Maybe<U> flatMap(Transformer<? super T, ? extends Maybe<? extends U>> transformer) {
      @SuppressWarnings("unchecked")
      Maybe<U> maybe = (Maybe<U>) transformer.transform(this.get());
      return maybe;
    }

    @Override
    public <U extends T> T orElse(U input) {
      return this.get();
    }

    @Override
    public T orElseGet(Producer<? extends T> producer) {
      return this.get();
    }

    @Override
    public void consumeWith(Consumer<? super T> consumer) {
      consumer.consume(this.get());
    }
  }

  public static <T> Maybe<T> none() {
    @SuppressWarnings("unchecked")
    Maybe<T> maybe = (Maybe<T>) None.NONE;
    return maybe;
  }

  public static <T> Maybe<T> some(T t) {
    return new Some<T>(t);
  }

  public static <T> Maybe<T> of(T t) {
    if (t == null) {
      return none();
    }
    return some(t);
  }
}
