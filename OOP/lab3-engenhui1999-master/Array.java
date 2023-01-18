/**
 * The Array for CS2030S 
 *
 * @author XXX
 * @version CS2030S AY21/22 Semester 2
 */
class Array<T extends Comparable<T>> { // TODO: Change to bounded type parameter
  private T[] array;

  Array(int size) {
    // TODO
    @SuppressWarnings({"rawtypes", "unchecked"})
    T[] temp = (T[]) new Comparable[size];
    this.array = temp;

  }

  public void set(int index, T item) {
    // TODO
    this.array[index] = item;
  }

  public T get(int index) {
    // TODO
    return this.array[index];
  }

  public T min() {
    // TODO
    T minItem = this.array[0];
    for (int i = 1; i < this.array.length; i++) {
      if (minItem.compareTo(this.array[i]) > 0) {
        minItem = this.array[i];
      }
    }
    return minItem;
  }

  public int length() {
    return this.array.length;
  }

  @Override
  public String toString() {
    StringBuilder s = new StringBuilder("[ ");
    for (int i = 0; i < array.length; i++) {
      s.append(i + ":" + array[i]);
      if (i != array.length - 1) {
        s.append(", ");
      }
    }
    return s.append(" ]").toString();
  }
}
