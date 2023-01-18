class ServiceEndEvent extends Event {
  
  private Customer customer;
  private Counter counter;
  private Shop shop;

  public ServiceEndEvent(double time, Customer customer, Counter counter, Shop shop) {
    super(time);
    this.customer = customer;
    this.counter = counter;
    this.shop = shop;
  }


  @Override
  public String toString() {
    String str = "";
    str = String.format(": %s service done (by %s)", this.customer, this.counter);
    return super.toString() + str;
  }

  @Override
  public Event[] simulate() {
    this.counter.stopServing(this.customer);


    if (this.shop.isEmptyQueue()) {
      return new Event[] {        
        new DepartureEvent(this.getTime(), this.customer)
      };
    } else {
      Customer nextCustomer = (Customer) this.shop.getNextCustomer();
      return new Event[] {
        new DepartureEvent(this.getTime(), this.customer),
        new ServiceBeginEvent(this.getTime(), nextCustomer, this.counter, this.shop)
      };
    }
  }
}
