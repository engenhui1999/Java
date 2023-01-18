/**
 * A boolean condition with parameter x that can be applied to
 * a string.  Returns true if the string is longer than x; false
 * otherwise.
 * CS2030S Lab 4
 * AY21/22 Semester 2
 *
 * @author Put Your Name (Lab Group)
 */

public class LongerThan implements BooleanCondition<String> {
  private int givenLimit;

  public LongerThan(int givenLimit) {
    this.givenLimit = givenLimit;
  }

  @Override
  public boolean test(String input) {
    return (input.length() > this.givenLimit);
  }  
}
