class Departure extends Event {
  
  private Customer customer;

  public Departure(Customer customer){
    super(customer.getEndTime());
    this.customer = customer;
  }


  @Override
  public String toString() {
    String str = "";
    str = String.format(": Customer %d departed", this.customer.getId());
    return super.toString() + str;
  }

  @Override
  public Event[] simulate(){
    return new Event[] {};
  }
}
