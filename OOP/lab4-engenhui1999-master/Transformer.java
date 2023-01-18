/**
 * The Transformer interface that can transform a type T
 * to type U.
 * CS2030S Lab 4
 * AY21/22 Semester 2
 *
 * @author Put Your Name (Lab Group)
 */

interface Transformer<T, U> {
  abstract U transform(T input);
}

