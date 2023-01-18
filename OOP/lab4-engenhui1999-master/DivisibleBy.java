/**
 * A boolean condition with an integer parameter y that can be 
 * apply to another integer x.  Returns true if x is divisible 
 * by y, false otherwise.
 * CS2030S Lab 4
 * AY21/22 Semester 2
 *
 * @author Put Your Name (Lab Group)
 */

public class DivisibleBy implements BooleanCondition<Integer> {
  private int number;

  public DivisibleBy(Integer number) {
    this.number = number;
  }

  @Override
  public boolean test(Integer input) {
    return (input % this.number == 0);
  }
}
