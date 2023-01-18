class ServiceEndEvent extends Event {
  
  private Customer customer;
  private Counter counter;
  private Shop shop;
  private Customer nextCustomer;
  private Customer customerMoveToCounter;

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
    
    if (this.counter.isEmptyQueue() && this.shop.isEmptyQueue()) {
      return new Event[] {        
        new DepartureEvent(this.getTime(), this.customer)
      };
    } else if (this.counter.isEmptyQueue()) {
      this.nextCustomer = this.shop.getNextCustomer();
      return new Event[] {
        new DepartureEvent(this.getTime(), this.customer),
        new ServiceBeginEvent(this.getTime(), this.nextCustomer, this.counter, this.shop)
      };
    } else if (this.shop.isEmptyQueue()) {
      this.nextCustomer = this.counter.getNextCustomer();
      return new Event[] {
        new DepartureEvent(this.getTime(), this.customer),
        new ServiceBeginEvent(this.getTime(), this.nextCustomer, this.counter, this.shop)
      };
    } else {
      this.nextCustomer = this.counter.getNextCustomer();
      this.customerMoveToCounter = this.shop.getNextCustomer();
      return new Event[] {
        new DepartureEvent(this.getTime(), this.customer),
        new ServiceBeginEvent(this.getTime(), this.nextCustomer, this.counter, this.shop),
        new CounterQueueEvent(this.getTime(), this.customerMoveToCounter, this.counter)
      };
    }
  }
}
