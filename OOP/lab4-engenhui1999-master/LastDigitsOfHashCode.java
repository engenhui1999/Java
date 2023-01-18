/**
 * A transformer with a parameter k that takes in an object x 
 * and outputs the last k digits of the hash value of x.
 * CS2030S Lab 4
 * AY21/22 Semester 2
 *
 * @author Put Your Name (Lab Group)
 */

public class LastDigitsOfHashCode implements Transformer<Object, Integer> {
  private int digits;

  public LastDigitsOfHashCode(int digits) {
    this.digits = digits;
  }

  @Override
  public Integer transform(Object input) {
    double hashNum = input.hashCode();
    int output = (int) (hashNum % (Math.pow(10, this.digits)));
    return Math.abs(output);
  }

}
