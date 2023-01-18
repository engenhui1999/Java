class ServiceEnd extends Event {
  
  private Customer customer;
  private Counter counter;

  public ServiceEnd(Customer customer, Counter counter) {
    super(customer.getEndTime());
    this.customer = customer;
    this.counter = counter;
  }


  @Override
  public String toString() {
    String str = "";
    str = String.format(": Customer %d service done (by Counter %d)", this.customer.getId(), this.counter.getCounterId());
    return super.toString() + str;
  }

  @Override
  public Event[] simulate(){
    this.counter.stopServing(this.customer);

    return new Event[] {        
      new Departure(this.customer)
    };
  }
}
