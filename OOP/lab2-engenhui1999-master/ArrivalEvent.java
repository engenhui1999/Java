class ArrivalEvent extends Event { 

  private Customer customer;
  private Shop shop;

  public ArrivalEvent(double time, Customer customer, Shop shop) {
    super(time);
    this.customer = customer;
    this.shop = shop;
  }

  @Override
  public String toString() {
    String str = "";
    str = String.format(": %s arrived %s", this.customer, this.shop.getQueue());
    return super.toString() + str;
  }

  @Override
  public Event[] simulate() {
    Counter counter = this.shop.findFirstAvailableCounter(); 
    if (counter == null) {
      if (this.shop.isFullQueue()) {
        return new Event[] {
          new DepartureEvent(this.getTime(), this.customer)
        };
      } else {  
        return new Event[] {
          new QueueEvent(this.getTime(), this.customer, this.shop)
        };
      }
    } else if (!this.shop.isEmptyQueue()) {
      Customer nextCustomer = this.shop.getNextCustomer();
      return new Event[] {
        new QueueEvent(this.getTime(), this.customer, this.shop),
        new ServiceBeginEvent(this.getTime(), nextCustomer, counter, this.shop)
      };
    } else {
      return new Event[] {
        new ServiceBeginEvent(this.getTime(), this.customer, counter, this.shop)
      };
    }
  }
}
