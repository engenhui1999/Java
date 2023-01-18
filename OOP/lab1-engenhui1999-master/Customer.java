class Customer {

  private static int n = 0;
  private int id;
  private double arrivalTime;
  private double serviceTime;
  private boolean customerServed = false;

  public Customer(double arrivalTime, double serviceTime) {
    this.id = n;
    this.arrivalTime = arrivalTime;
    this.serviceTime = serviceTime;
    n++;
  }

  public double getArrivalTime() {
    return this.arrivalTime;
  }

  public double getServiceTime() {
    return this.serviceTime;
  }

  public void serveCustomer() {
    customerServed = true;
  }

  public double getEndTime() {
    if (customerServed) {
      return this.arrivalTime + this.serviceTime;
    }
    return arrivalTime;
  }

  public int getId() {
    return this.id;
  }
}

