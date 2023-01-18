class ArrivalEvent extends Event { 

  private Customer customer;
  private Shop shop;
  private Counter counter;

  public ArrivalEvent(double time, Customer customer, Shop shop) {
    super(time);
    this.customer = customer;
    this.shop = shop;
  }

  @Override
  public String toString() {
    String str = "";
    str = String.format(": %s arrived %s", this.customer, this.shop.getQueueString());
    return super.toString() + str;
  }

  @Override
  public Event[] simulate() {
    this.counter = this.shop.findCounter(); 
    if (this.counter.isAvailable()) {
      return new Event[] {
        new ServiceBeginEvent(this.getTime(), this.customer, this.counter, this.shop)
      };
    } else if (!this.counter.isFullQueue()) {
      return new Event[] {
        new CounterQueueEvent(this.getTime(), this.customer, this.counter)
      };
    } else if (!this.shop.isFullQueue()) {
      return new Event[] {
        new ShopQueueEvent(this.getTime(), this.customer, this.shop)
      };
    } else {
      return new Event[] {
        new DepartureEvent(this.getTime(), this.customer)
      };
    }
  }
}
