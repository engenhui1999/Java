class Counter implements Comparable<Counter> {
  
  private int id;
  private boolean isAvailable = true; 
  private Queue<Customer> queue;

  public Counter(int id, int queueSize) {
    this.id = id;
    this.queue = new Queue<Customer>(queueSize);
  }

  public boolean isAvailable() {
    return this.isAvailable;
  }
  
  public int getCounterId() {
    return this.id;
  }

  public void startServing(Customer customer) {
    this.isAvailable = false;
  }

  public void stopServing(Customer customer) {
    this.isAvailable = true;
  }

  public boolean isFullQueue() {
    return this.queue.isFull();
  }

  public boolean isEmptyQueue() {
    return this.queue.isEmpty();
  }
  
  public void addCustomerToQueue(Customer customer) {
    this.queue.enq(customer);
  }

  /**
  * Returns the next customer in queue.
  */
  public Customer getNextCustomer() {
    return this.queue.deq();
  }

  public String getQueueString() {
    return this.queue.toString();
  }

  public int queueLength() {
    return this.queue.length();
  }

  @Override
  public String toString() {
    return "S" + this.id + " " + this.queue;
  }

  @Override
  public int compareTo(Counter counter) {
    if (this.isAvailable) {
      return -1;
    } else if (counter.isAvailable()) {
      return 1;
    } else if (this.queueLength() < counter.queueLength()) {
      return -1;
    } else if (this.queueLength() > counter.queueLength()) {
      return 1;
    } else {
      return 0;
    }
  }
}
