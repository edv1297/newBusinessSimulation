import java.util.Random;
import structure5.*;

public class MultipleLine extends BusinessSimulation{

    //all the customers
    Customer[] tellerList;
    Vector<VectorHeap<Customer>> superMarket;
    PriorityQueue<Customer> customers;


    //stores the number of tellers for conveneince
    public MultipleLine(int numCustomers, int numServicePoints,
			int maxEventStart, int seed){
	super(numCustomers, numServicePoints, maxEventStart, seed );
	this.tellerList = new Customer[numServicePoints];
	this.customers = generateCustomerSequence(numCustomers, maxEventStart, seed);
	this.superMarket = makeLines();
    }

    /**
     * Generates a seqeunce of lines that correspond to every teller
     * these sequences are "ordered" like an matrix with cols = # tellers
     * and we everytime we reach the last column we go to the next row and restart
     * from column 0 with filling our lines from our customerSequence
     * @post lines with customers(in order by their orrival time) corresponding to our number of tellers
     **/
    protected Vector<VectorHeap<Customer>> makeLines(){
	int temp = 0;
	Vector<VectorHeap<Customer>> lines = new Vector<VectorHeap<Customer>>();
	for(int q = 0; q< this.tellerList.length; q++){
	    VectorHeap<Customer> line = new VectorHeap<Customer>();
	    lines.add(line);
	}
	while(!this.customers.isEmpty()){
	    if(temp%(this.tellerList.length-1) == 1){
		lines.elementAt(this.tellerList.length - 1).add(this.customers.remove());
	    }
	    else{
		lines.elementAt(temp%(this.tellerList.length)).add(this.customers.remove());
	    }
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
	if(this.isEmpty()){
	    for(int spot = 0; spot<this.tellerList.length; spot++){
		if(this.tellerList[spot] != null){
		    this.time += this.tellerList[spot].serviceTime;
		}
	    }
	    return true;
	}
	//initial filling
	if(this.time == 0){
	    for(int teller = 0; teller< this.tellerList.length; teller++){
		this.tellerList[teller] = this.superMarket.elementAt(teller).remove();		   
		--this.numCustomers;
	    }
	}
	if(!this.isEmpty()){
	    for(int line =0; line <this.tellerList.length; line++){
		if(this.tellerList[line] != null){
		    this.tellerList[line].serviceTime -= 1;
		//if my customer is finished at my teller and there are more customers
		    if(this.tellerList[line].serviceTime == 0 && (!this.superMarket.elementAt(line).isEmpty())){
		    this.tellerList[line] = this.superMarket.elementAt(line).remove();
		}
		    else if(this.tellerList[line].serviceTime == 0 && (this.superMarket.elementAt(line).isEmpty())){
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
	if(this.isEmpty()){
	    for(int spot = 0; spot<this.tellerList.length; spot++){
		if(this.tellerList[spot] != null){
		    this.time += this.tellerList[spot].serviceTime;
		}
	    }
	    return true;
	}
	//initial filling
	if(this.time == 0){
	    for(int teller = 0; teller< this.tellerList.length; teller++){
		this.tellerList[teller] = this.superMarket.elementAt(teller).remove();		   
		--this.numCustomers;
	    }
	}
	if(!this.isEmpty()){
	    for(int line =0; line <this.tellerList.length; line++){
		if(this.tellerList[line] != null){
		    this.tellerList[line].serviceTime -= timeSteps;
		//if my customer is finished at my teller and there are more customers
		    if(this.tellerList[line].serviceTime == 0 && (!this.superMarket.elementAt(line).isEmpty())){
		    this.tellerList[line] = this.superMarket.elementAt(line).remove();
		}
		    else if(this.tellerList[line].serviceTime == 0 && (this.superMarket.elementAt(line).isEmpty())){
			this.tellerList[line] = null;
		    }
		}	
	    }
	    this.time += timeSteps;	    
	}
	return false;
    }

    /**
     *
     * checks to see if our lines are all empty
     * @return true if all of our lines are empty, false otherwise
     **/
    public boolean isEmpty(){
	boolean truthVal = false;
	int count = 0;
	for(int line=0; line<this.tellerList.length; line++){
	    if(this.superMarket.elementAt(line).isEmpty()){
		++count;
	    }
	}
	if(count == this.tellerList.length){
	    truthVal = true;
	}
	return truthVal;
    }
    
    public String toString(){
	String finLines = "";
	for(int teller = 0; teller<this.tellerList.length; teller++){
	    finLines += "Customers at Teller: " + this.tellerList[teller] + "\n" + "In Corresponding Line: ";
	    finLines += this.superMarket.elementAt(teller).toString();
	    finLines += "\n -------------------------------- \n";
	}
	return finLines;
    }
}
