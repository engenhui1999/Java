class ServiceBegin extends Event {
  
  private Customer customer;
  private Counter counter;

  public ServiceBegin(Customer customer, Counter counter) {
    super(customer.getArrivalTime());
    this.customer = customer;
    this.counter = counter;
  }

  @Override
  public String toString() {
    String str = "";
    str = String.format(": Customer %d service begin (by Counter %d)", this.customer.getId(), this.counter.getCounterId());
    return super.toString() + str;
  }

  @Override
  public Event[] simulate(){
    this.counter.startServing(customer);

    return new Event[] {
      new ServiceEnd(this.customer, this.counter)
    };
  }
}
