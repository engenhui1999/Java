class CounterQueueEvent extends Event {

  private Customer customer;
  private Counter counter;

  public CounterQueueEvent(double time, Customer customer, Counter counter) {
    super(time);
    this.customer = customer;
    this.counter = counter;
  }

  @Override
  public String toString() {
    String str =  "";
    str = String.format(": %s joined counter queue (at %s)", this.customer, this.counter);
    return super.toString() + str;
  }

  @Override
  public Event[] simulate() {
    this.counter.addCustomerToQueue(this.customer);
    return new Event[] {};
  }
}
