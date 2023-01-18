
class Counter {
  
  private int id;
  private boolean available = true;  
  private String str = "";

  public Counter(int id) {
    this.id = id;
  }

  public boolean isAvailable() {
    return available;
  }
  
  public int getCounterId(){
    return this.id;
  }

  public void startServing(Customer customer) {
    available = false;
    customer.serveCustomer();
  }

  public void stopServing(Customer customer) {
    available = true;
  }
}
