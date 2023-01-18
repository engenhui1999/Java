class ShopQueueEvent extends Event {

  private Customer customer;
  private Shop shop;

  public ShopQueueEvent(double time, Customer customer, Shop shop) {
    super(time);
    this.customer = customer;
    this.shop = shop;
  }

  @Override
  public String toString() {
    String str =  "";
    str = String.format(": %s joined shop queue %s", this.customer, this.shop.getQueueString());
    return super.toString() + str;
  }

  @Override
  public Event[] simulate() {
    this.shop.addCustomerToQueue(this.customer);
    return new Event[] {};
  }
}
