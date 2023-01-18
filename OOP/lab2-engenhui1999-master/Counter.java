
class Counter {
  
  private int id;
  private boolean isAvailable = true;  

  public Counter(int id) {
    this.id = id;
  }

  public boolean isAvailable() {
    return isAvailable;
  }
  
  public int getCounterId() {
    return this.id;
  }

  public void startServing(Customer customer) {
    isAvailable = false;
  }

  public void stopServing(Customer customer) {
    isAvailable = true;
  }

  @Override
  public String toString() {
    return "S" + this.id;
  }
}
