import java.util.Random;
import structure5.*;

public class SingleLine extends BusinessSimulation{

  PriorityQueue<Customer> customers;
  Customer[] tellerList;

  public SingleLine(int numCustomers, int numServicePoints, int maxEventStart, int seed){
    super(numCustomers, numServicePoints, maxEventStart, seed);
    this.tellerList = new Customer[numServicePoints];
    this.customers = generateCustomerSequence(numCustomers, maxEventStart, seed);
  }

  //single step
  @Override
  public boolean step(){
    if(this.customers.isEmpty()){
      return true;
    }
    else if(this.time == 0){
      for(int teller = 0; teller< this.tellerList.length; teller++){
        this.tellerList[teller] = this.customers.getFirst();
        --this.numCustomers;
        this.customers.remove();
      }
    }
    for(int teller = 0; teller < tellerList.length; teller++){
      this.tellerList[teller].serviceTime -= 1;
      if(this.tellerList[teller].serviceTime == 0){
        //this is tricky I need to make sure that when there is only one customer left that I am only
        if(this.numCustomers != 0){
          this.tellerList[teller] = customers.getFirst();
          --numCustomers;
          this.customers.remove();
        }
        else{
          this.tellerList[teller] = null;
        }
      }
    }
    ++this.time;
    return false;
  }


  //I need to make sure that I am updating my teller service times for each step
  //arbitrary amount of steps

  public boolean step(int timeSteps){
    if(this.customers.isEmpty()){
      return true;
    }
    else if(this.time == 0){
      for(int teller = 0; teller< this.tellerList.length; teller++){
        this.tellerList[teller] = this.customers.getFirst();
        --this.numCustomers;
        this.customers.remove();
      }
    }
    for(int teller = 0; teller < tellerList.length; teller++){
      this.tellerList[teller].serviceTime -= timeSteps;
      if(this.tellerList[teller].serviceTime == 0){
        //this is tricky I need to make sure that when there is only one customer left that I am only
        if(this.numCustomers != 0){
          this.tellerList[teller] = customers.getFirst();
          --numCustomers;
          this.customers.remove();
        }
        else{
          this.tellerList[teller] = null;
        }
      }
    }
    this.time += timeSteps;
    return false;
  }

  //represent the number of customers left in the line after each step
  public String toString(){
    String tellers = "";
    for(int i = 0; i< this.tellerList.length; i++){
      tellers += this.tellerList[i];
    }




    return "Tellers/Customers currently at Tellers: " + tellers + "\n" + "Customers in line: " + this.customers.toString();
  }
  public static void main(String[] args){    
    SingleLine line = new SingleLine(9, 2, 10, 5);
    //System.out.println(line.customers);
    //    line.step();
    // System.out.println(line.toString());
    // line.step(9);
    // System.out.println(line.toString());
    	while(!line.step()){
    	    System.out.println(line.toString());
    	    System.out.println("--------");
    	    }
    //I want to find a way to actually get through the customers at the teller their time is up
    //I want to add the last customers serviceTime to this total
    // System.out.println("The total time it took to process our customers was: " + line.time);
  }
}

//figure out the the queue situation for tellers
// figure out the popping situation for customers
//conqeur the comp sci lab
//get money


//the only problem here is the toString prints null instead of "no customer in line"
