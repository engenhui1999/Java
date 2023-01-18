import java.util.Scanner;

class Shop {

  private Counter[] counters;
  private Queue queue;

  public Shop(int numOfCounters, int maxQueue) {
    queue = new Queue(maxQueue);
    counters = new Counter[numOfCounters];
    for (int i = 0; i < numOfCounters; i++) {
      counters[i] = new Counter(i); 
    }
  }

  public Counter findFirstAvailableCounter() {
    int counter = -1;
    for (int i = 0; i < counters.length; i++) {
      if (this.counters[i].isAvailable()) {
        counter = i;
        break;
      }
    }
    if (counter == -1) {
      return null;
    }
    return counters[counter];
  }

  public boolean isFullQueue() {
    return queue.isFull();
  }

  public boolean isEmptyQueue() {
    return queue.isEmpty();
  }
  
  public void addCustomerToQueue(Customer customer) {
    queue.enq(customer);
  }

  /**
  * Returns the next customer in queue.
  */
  public Customer getNextCustomer() {
    return (Customer) queue.deq();
  }

  public Queue getQueue() {
    return queue;
  }
}
