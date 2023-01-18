import java.util.Scanner;

class Shop {

  public int numOfCounters;
  private Counter[] counters;

  public Shop(int numOfCounters) {
    this.numOfCounters = numOfCounters;
    counters = new Counter[numOfCounters];
    for (int i = 0; i < numOfCounters; i++) {
      counters[i] = new Counter(i); 
    }
  }

  public Counter findFirstAvailableCounter(){
    int counter = -1;
    for (int i = 0; i < numOfCounters; i++) {
      if (this.counters[i].isAvailable()) {
       	counter = i;
	break;
      }
    }
    if (counter == -1) {
      return new Counter(-1); 
    }
    return counters[counter];
  }
}
