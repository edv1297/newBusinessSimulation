/**
*
* Konnor A. Herbst (HackerMan)
* Eddy D. Varela (Deputy)
*
* Thought Questions:
*  1. After running several tests, the queue strategy that runs the fastest
* was the single line queue. With a small amout of cusomters, it wasn't noticeable
* which queue strategy was faster but after using a larger set of customrs, it was
* that the single line implementation was fastest.
*
* 2. Based on the implementations, it does not seem that there is a difference in
* the average wait time per customer. However, the problem with the multiple line
* implementation arises when there is an empty teller. At that point there is an
* inefficient teller that is not doing anything so the the single line implementation
* would be more efficient because that teller does not stop working.
*
* 3. Yes this would have to change the underlying structure to something that can allow
* the last customers in to be able to be moved around pretty easily. A stack might be
* useful in order to get the customer at the end of the line and move them into another
* stack but we would not be able to get the first person in the line. A DoublyLinkedList
* might be useful in getting the first/last customers in the line and move them around
* until all the lines are balanced.
*
* 4.If there were lines for shorter timed customers then, customers that had lower serviceTimes
* would be able to get attended quickly and not have to wait in line behind someone that
* is going to take a really long time. In the single line implementation if the tellers were
* partitioned to take only the specific times then this may be inefficient because what if
* all the tellers that take long service times are occupied and there is one long serviceTime
* customer in front of several short serviceTime customers? This would be very annoying and pretty
* inefficient. In the multiple line implementation, if everyone is sorted by their service times,
* into respective lines, the lower bound of the average wait time will be significantly shorter
* than the previous fastest time and so the average will be lower than before so this implementation
* will be more efficient thus favorable.
*
**/

import java.util.Vector;
import java.util.Random;
import structure5.*;

public abstract class BusinessSimulation {

  /* sequence of customers, prioritized by randomly-generated event time */
  PriorityQueue<Customer> eventQueue;

  /* series of service points where customers queue and are served */
  Vector<Queue<Customer>> servicePoints;

  /* current time step in the simulation */
  int time;

  /* seed for Random() so that simulation is repeatable */
  int seed;

  int numCustomers;

  int numTellers;

  int maxEventStart;

  /* Used to bound the range of service times that Customers require */
  static final int MIN_SERVICE_TIME = 1; //TODO: set appropraitely
  static final int MAX_SERVICE_TIME = 10; //TODO: set appropriately

  /**
  * Creates a BusinessSimulation.
  * @post the step() function may be called.
  *
  * @numCustomers number of random customers that appear in the simulation
  * @numServicePoints number of tellers in this simulation
  * @maxEventStart latest timeStep that a Customer may appear in the simulation
  * @seed used to seed a Random() so that simulation is repeatable.
  */
  //numServicePoints --> tellers
  public BusinessSimulation(int numCustomers, int numServicePoints,
  int maxEventStart, int seed) {
    VectorHeap<Customer>customers = new VectorHeap<Customer>();
    this.time = 0;
    this.seed = seed;
    this.maxEventStart = maxEventStart;
    this.numCustomers = numCustomers;
    this.numTellers = numServicePoints;
  }

  /**
  * Generates a sequence of Customer objects, stored in a PriorityQueue.
  * Customer priority is determined by arrival time
  * @arg numCustomers number of customers to simulate
  * @arg maxEventStart maximum timeStep that a customer arrives
  *      in @eventQueue
  * @arg seed use Random(seed) to make customer sequence repeatable
  * @return A PriorityQueue that represents Customer arrivals,
  *         ordered by Customer arrival time
  */
  public static PriorityQueue<Customer> generateCustomerSequence(int numCustomers,
  int maxEventStart,
  int seed) {
    Random generator = new Random(seed);
    VectorHeap<Customer> evQ = new VectorHeap<Customer>();
    for(int customer = 1; customer <= numCustomers; customer++){
      evQ.add(new Customer(generator.nextInt(maxEventStart), generator.nextInt(MAX_SERVICE_TIME) + MIN_SERVICE_TIME));
    }
    return evQ;
  }

  /**
  * Advances @timeSteps time steps through the simulation.
  *
  * @post the simulation has advanced @timeSteps time steps
  * @return true if the simulation is over, false otherwise
  */
  abstract public boolean step(int timeSteps);

  /**
  * Advances 1 time step through the simulation.
  *
  * @post the simulation has advanced 1 time step
  * @return true if the simulation is over, false otherwise
  */
  abstract public boolean step();

  /**
  * @return a string representation of the simulation
  */
  public String toString() {
    // TODO: modify if needed.

    String str = "Time: " + time + "\n";

    str = str + "Event Queue: ";
    if (eventQueue != null) {
      str = str + eventQueue.toString();
    }
    str = str + "\n";
    if (servicePoints != null)  {
      for (Queue<Customer> sp : servicePoints) {
        str = str + "Service Point: " + sp.toString() + "\n";
      }
    }
    return str;
  }
}
