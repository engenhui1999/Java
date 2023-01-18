class Arrival extends Event { 
  
  private Customer customer;
  private Shop shop;

  public Arrival(Customer customer, Shop shop) {
    super(customer.getArrivalTime());
    this.customer = customer;
    this.shop = shop;
  }

  @Override
  public String toString() {
    String str = "";
    str = String.format(": Customer %d arrives", this.customer.getId());
    return super.toString() + str;
  }

  @Override
  public Event[] simulate(){
    Counter counter = this.shop.findFirstAvailableCounter(); 
    if (counter.getCounterId() == -1) {
      return new Event[] {
        new Departure(this.customer)
      };
    } else {
      return new Event[] {
        new ServiceBegin(this.customer, counter)
      };
    }

  }

}
