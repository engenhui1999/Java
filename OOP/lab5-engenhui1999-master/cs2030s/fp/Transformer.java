/**
 * CS2030S Lab 5
 * AY20/21 Semester 2
 *
 * @author Put Your Name (Lab Group)
 */

package cs2030s.fp;

public interface Transformer<T, U> {
  abstract U transform(T input);
}
