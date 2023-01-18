import java.util.Scanner;

class Shop {

  private Array<Counter> counters;
  private Queue<Customer> queue;

  public Shop(int numOfCounters, int counterQueueLength, int entranceQueueLength) {
    this.queue = new Queue<Customer>(entranceQueueLength);
    this.counters = new Array<Counter>(numOfCounters);
    for (int i = 0; i < numOfCounters; i++) {
      this.counters.set(i, new Counter(i, counterQueueLength)); 
    }
  }

  public Counter findCounter() {
    return this.counters.min();
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
}
