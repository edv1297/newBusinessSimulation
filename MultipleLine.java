//Konnor A. Herbst (HackerMan)
//Eddy D. Varela (Deputy)

import java.util.Random;
import structure5.*;

public class MultipleLine extends BusinessSimulation{

  //structure for tellers final because these should not change once established
  final Customer[] tellerList;

  /**
  * structure for the lines which is essentially a 2D array but VectorHeaps
  * allow us to not have to manage the the other customers after we pop them
  * off of the line.
  **/
  Vector<VectorHeap<Customer>> superMarket;

  //Structure that holds all the customers and the getFirst() method is very useful
  PriorityQueue<Customer> customers;

  public MultipleLine(int numCustomers, int numServicePoints,
  int maxEventStart, int seed){

    super(numCustomers, numServicePoints, maxEventStart, seed );
    this.tellerList = new Customer[numServicePoints];
    this.customers = generateCustomerSequence(numCustomers, maxEventStart, seed);
    this.superMarket = this.makeLines();

  }
  /**
  * @pre Generates a seqeunce of lines that correspond to every teller
  * these sequences are "ordered" like an matrix with cols = # tellers
  * and everytime we reach the last column we go to the next row using the
  * modular arithmetic and restart from column 0 filling our lines from our
  * customerSequence
  *
  * @post lines with customers(in order by their arrival time) corresponding to
  * our number of tellers.
  **/
  public Vector<VectorHeap<Customer>> makeLines(){

    //since we already know the size, get rid of the doubling cost.
    Vector<VectorHeap<Customer>> lines =
    new Vector<VectorHeap<Customer>>(this.tellerList.length);

    for(int q = 0; q< this.tellerList.length; ++q){
      VectorHeap<Customer> line = new VectorHeap<Customer>();
      lines.add(line);
    }
    int temp = 0;
    while(!this.customers.isEmpty()){
      lines.elementAt(temp%this.tellerList.length).add(this.customers.remove());
      ++temp;
    }
    return lines;
  }
  @Override
  /**
  * Advances 1 time step through the simulation
  *
  * @post the simulation has advances 1 time step
  * @return true if the simulation is over, false otherwise
  **/
  public boolean step(){

    //initial filling
    if(this.time == 0){
      for(int teller = 0; teller< this.tellerList.length; teller++){
        this.tellerList[teller] = this.superMarket.elementAt(teller).remove();
        --this.numCustomers;
      }
    }
    //check if it's empty
    if(this.isEmpty()){
      for(int spot = 0; spot<this.tellerList.length; spot++){
        if(this.tellerList[spot] != null){
          this.time += this.tellerList[spot].serviceTime;
        }
      }
      return true;
    }

    else{
      for(int line =0; line <this.tellerList.length; line++){
        if(this.tellerList[line] != null){
          this.tellerList[line].serviceTime -= 1;

          //if my customer is finished at my teller and there are more customers
          if(this.tellerList[line].serviceTime == 0 && (!this.superMarket.elementAt(line).isEmpty())){
            this.tellerList[line] = this.superMarket.elementAt(line).remove();

            //if there are no more customers
          }else if(this.tellerList[line].serviceTime == 0 && (this.superMarket.elementAt(line).isEmpty())){
            this.tellerList[line] = null;
          }
        }
      }
      ++this.time;
    }
    return false;
  }

  @Override
  /**
  * Advances (timeSteps) through the simulation
  *
  * @post the simulation has advanced 1 timeSteps, our highest serviceTime in our
  * tellerList is at least timeSteps
  * @return true if the simulation is over, false otherwise
  **/
  public boolean step(int timeSteps){




    //return true if supermarket is empty
    if(this.isEmpty()){
      for(int spot = 0; spot<this.tellerList.length; spot++){
        if(this.tellerList[spot] != null){
          this.time += this.tellerList[spot].serviceTime;
        }
      }
      return true;
    }

    //initial filling only when time is 0.
    if(this.time == 0){
      for(int teller = 0; teller< this.tellerList.length; teller++){
        this.tellerList[teller] = this.superMarket.elementAt(teller).remove();
        --this.numCustomers;
      }
    }

    else{
      for(int line =0; line <this.tellerList.length; line++){
        if(this.tellerList[line] != null){
          this.tellerList[line].serviceTime -= timeSteps;

          //if my customer is finished at my teller and there are more customers
          if(this.tellerList[line].serviceTime <= 0 && (!this.superMarket.elementAt(line).isEmpty())){
            this.tellerList[line] = this.superMarket.elementAt(line).remove();

            //if my customer is finished at my teller and there are no more customers
          }else if(this.tellerList[line].serviceTime <= 0 && (this.superMarket.elementAt(line).isEmpty())){
            this.tellerList[line] = null;
          }
        }
      }
      this.time += timeSteps;
    }
    return false;
  }

  /**
  * checks to see if our lines are all empty
  * @return true if all of our lines are empty, false otherwise
  **/
  public boolean isEmpty(){
    boolean truthVal = true;
    int count = 0;
    for(int line=0; line<this.tellerList.length; line++){
      if(!this.superMarket.elementAt(line).isEmpty()){
        return false;
      }
    }
    return truthVal;
  }

  public String toString(){
    String finLines = "";
    for(int teller = 0; teller<this.tellerList.length; teller++){
      finLines += "Customers at Teller " + (teller + 1) + "\n" + "In Corresponding Line: ";
      finLines += this.superMarket.elementAt(teller).toString();
      finLines += "\n -------------------------------- \n";
    }
    return finLines;

  }
}
