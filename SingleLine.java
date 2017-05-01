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


      @Override
      /**
       * Advances 1 time step through the simulation
       *
       * @post the simulation advances 1 time step
       * @return true if the simulation is over, false otherwise
       **/
      public boolean step(){
	  if(this.customers.isEmpty()){
	      for(int spot = 0; spot< this.tellerList.length; spot++){
		  if(this.tellerList[spot] != null){
		      this.time += this.tellerList[spot].serviceTime;
		  }
	      }
	      return true;
	  }
	  //initial filling
	  else if(this.time == 0){
	      for(int teller = 0; teller< this.tellerList.length; teller++){
		  this.tellerList[teller] = this.customers.getFirst();
		  --this.numCustomers;
		  this.customers.remove();
	      }
	  }
	  for(int teller = 0; teller < tellerList.length; teller++){
	      if(this.customers.isEmpty()){
		  for(int spot = 0; spot< this.tellerList.length; spot++){
		      this.time += this.tellerList[teller].serviceTime;
		  }		      
	      }
	      else{
		  this.tellerList[teller].serviceTime -= 1;
		  if(this.tellerList[teller].serviceTime == 0){
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
	  }
	  ++this.time;
	  return false;
      }
    
    


    /**
     * Advances timeSteps through the simulation
     *
     * @post the simulation advances 1 timeSteps
     * @return true if the simulation is over, false otherwise
     **/
    public boolean step(int timeSteps){
	if(this.customers.isEmpty()){
	    for(int spot = 0; spot< this.tellerList.length; spot++){
		if(this.tellerList[spot] != null){
		    this.time += this.tellerList[spot].serviceTime;
		}
	    }
	    return true;
	}
	//initial filling
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

    public String toString(){
	String tellers = "";
	for(int i = 0; i< this.tellerList.length; i++){
	    tellers += this.tellerList[i];
	}
	return "Customers at Teller:  " + tellers + "\n" + "Customers in Single Line: " + this.customers.toString();
    }
}
