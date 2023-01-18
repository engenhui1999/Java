/**
 * A generic box storing an item.
 * CS2030S Lab 4
 * AY21/22 Semester 2
 *
 * @author Eng En Hui (10J)
 */

public class Box<T> {

  private final T contents;
  private static final Box<?> EMPTY_BOX = new Box<>(null);

  private Box(T contents) {
    this.contents = contents;
  }
  
  public static <T> Box<T> of(T value) {
    if (value == null) {
      return empty();
    }
    return new Box<T>(value);
  }
  
  public static <T> Box<T> ofNullable(T input) {
    if (input == null) {
      return empty();
    }
    return new Box<T>(input);
  }

  public static <T> Box<T> empty() {
    @SuppressWarnings("unchecked")
    Box<T> temp = (Box<T>) EMPTY_BOX;
    return temp;
  }

  public boolean isPresent() {
    return !(this.contents == null);
  }

  public Box<T> filter(BooleanCondition<? super T> condition) {
    if (!this.isPresent()) {
      return empty();
    }
    if (!condition.test(this.contents)) {
      return empty();
    }
    return this;
  }

  public <U> Box<U> map(Transformer<? super T, ? extends U> transformer) {
    if (this == EMPTY_BOX) {
      return empty();
    }
    return ofNullable(transformer.transform(this.contents));
  }

  @Override
  public String toString() {
    if (this == EMPTY_BOX) {
      return "[]";
    }
    return "[" + this.contents + "]";
  }

  @Override
  public boolean equals(Object other) {
    if (this == other) {
      return true;
    }

    if (other instanceof Box<?>) {
      Box<?> otherBox = (Box<?>) other;
      if (this.contents == otherBox.contents) {
        return true;
      }

      if (this.contents == null || otherBox.contents == null) {
        return false;
      }
      
      return this.contents.equals(otherBox.contents);
    }

    return false;
  }
}
