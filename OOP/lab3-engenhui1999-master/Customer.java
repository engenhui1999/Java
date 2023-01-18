class Customer {

  private static int n = 0;
  private int id;
  private double serviceTime;

  public Customer(double serviceTime) {
    this.id = n;
    this.serviceTime = serviceTime;
    n++;
  }

  public double getEndTime(double currentTime) {
    return currentTime + this.serviceTime;
  }

  public int getId() {
    return this.id;
  }

  @Override
  public String toString() {
    return "C" + this.id; 
  }
}

