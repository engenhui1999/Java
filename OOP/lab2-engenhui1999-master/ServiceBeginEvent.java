class ServiceBeginEvent extends Event {

  private Customer customer;
  private Counter counter;
  private Shop shop;

  public ServiceBeginEvent(double time, Customer customer, Counter counter, Shop shop) {
    super(time);
    this.customer = customer;
    this.counter = counter;
    this.shop = shop;
  }

  @Override
  public String toString() {
    String str = "";
    str = String.format(": %s service begin (by %s)", this.customer, this.counter);
    return super.toString() + str;
  }

  @Override
  public Event[] simulate() {
    this.counter.startServing(customer);
    double endTime = this.customer.getEndTime(this.getTime());
    return new Event[] {
      new ServiceEndEvent(endTime, this.customer, this.counter, this.shop)
    };
  }
}
