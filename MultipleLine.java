import java.util.Random;
import structure5.*;
import java.util.Arrays;

public class MultipleLine extends BusinessSimulation{

    //all the customers
    Customer[] tellerList;
    int depth;
    Vector<VectorHeap<Customer>> superMarket;
    PriorityQueue<Customer> customers;
    //    Vector<PriorityQueue<Customer>> superMarket = new Vector<PriorityQueue<Customer>>();

    //stores the number of tellers for conveneince
    public MultipleLine(int numCustomers, int numServicePoints,
			int maxEventStart, int seed){
	super(numCustomers, numServicePoints, maxEventStart, seed );
	this.tellerList = new Customer[numServicePoints];
	this.depth = (int)Math.ceil(numCustomers/(double)(tellerList.length));
	this.customers = generateCustomerSequence(numCustomers, maxEventStart, seed);
	this.superMarket = makeLines();
    }

    //we want to create a array of vectors where the indices correspond to the tellers
    //the thing is we do not want to destroy our customerSequence here
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
    //here I want to decrement all the tellers at a time and check
    public boolean step(){
	for(int line = 0; line< this.tellerList.length; line++){
	    if(this.superMarket.elementAt(line).isEmpty()){
		return true;
	    }
	}
	//initial filling
	if(this.time == 0){
	    for(int teller = 0; teller< this.tellerList.length; teller++){
		this.tellerList[teller] = this.superMarket.elementAt(teller).remove();		   
		--this.numCustomers;
	    }
	}
	for(int teller = 0; teller < tellerList.length; teller++){
	    this.tellerList[teller].serviceTime -= 1;
	    if(this.tellerList[teller].serviceTime == 0){
		//this is tricky I need to make sure that when there is only one customer left that I am only
		if(!this.superMarket.elementAt(teller).isEmpty()){
		    this.tellerList[teller] = this.superMarket.elementAt(teller).remove();
		}
		else{
		    this.tellerList[teller] = null;
		}
	    }
	}
	++this.time;
	return false;
    }

    public boolean isEmpty(){
	boolean truthVal = false;
	int count = 0;
	for(int line=0; line<this.tellerList.length; line++){
	    if(this.superMarket.elementAt(line).isEmpty()){
		++count;
	    }
	}
	if(count == this.tellerList.length -1){
	    truthVal = true;
	}
	return truthVal;
    }
    
    public boolean step(int timeStep){
	return true;
    }

        @Override
	//I do not want the toString to destory my superMarket
	public String toString(){
	    String finLines = "";
	    for(int teller = 0; teller<this.tellerList.length; teller++){
		System.out.println(this.tellerList[teller]);
		finLines += "Customers at Teller: " + this.tellerList[teller] + "\n" + "In Corresponding Line: ";
		finLines += this.superMarket.elementAt(teller).toString();
		finLines += "\n -------------------------------- \n";
	    }
	    return finLines;
	}



    public static void main (String[] args){
	MultipleLine market = new MultipleLine(9, 2, 10, 5);
	System.out.println(market);
	while(!market.isEmpty()){
	    market.step();
	    System.out.println(market);
	}
    }
}
