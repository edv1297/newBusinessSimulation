import java.util.Random;
import structure5.*;
import java.util.Arrays;

public class MultipleLine extends BusinessSimulation{
    
    //all the customers
    Customer[] tellerList;
    int depth;
    Customer[][] superMarket; 
    PriorityQueue<Customer> customers;
    //    Vector<PriorityQueue<Customer>> superMarket = new Vector<PriorityQueue<Customer>>();
    
    //stores the number of tellers for conveneince    
    public MultipleLine(int numCustomers, int numServicePoints,
			int maxEventStart, int seed){
	super(numCustomers, numServicePoints, maxEventStart, seed );
	this.tellerList = new Customer[numServicePoints];
        this.depth = (int)Math.ceil(numCustomers/(double)(tellerList.length));
	this.customers = generateCustomerSequence(numCustomers, maxEventStart, seed);
	System.out.println(this.customers);
	this.superMarket = makeLines();
    }

    //we want to create a array of vectors where the indices correspond to the tellers
    //the thing is we do not want to destroy our customerSequence here
    protected Customer [][] makeLines(){
	int temp = this.numCustomers;
	Customer[][] lines = new Customer[this.tellerList.length][this.depth];       
	for(int teller = 0; teller < this.tellerList.length; teller++){
	    for(int cust = 0; cust < this.depth; cust++){
		if(temp>0){
		    System.out.println(customers.getFirst());
		    lines[teller][cust] = this.customers.remove();
		    --temp;
		}
		else
		    {
		return lines;
		    }
	    }
	}
	return lines;
    }

    @Override
    public boolean step(){
	
	return true;
    }

    public boolean step(int timeStep){
	return true;
    }

    private boolean isEmpty(){
	boolean empty= true;
	for(Customer[]temp: superMarket){
	    for(Customer cust:temp){
		if(cust!=null){
		    empty = false;
		}
	    }
	}return empty;
    }
    @Override
    public String toString(){
	String finLines = "";
	int temp = this.numCustomers;

	String line1="Customers at Line 1:\n" ;
	String line2="Customers at Line 2:\n";
	//finLines += "Customers at Teller: " + this.tellerList[teller] + " In Corresponding Line: ";
	for(int teller = 0; teller<this.tellerList.length; teller++){
	    for(int cust =0; cust< this.depth; cust++){
		if(cust%2==0){
		    line1 += this.superMarket[teller][cust]+"\n";
		}else {
		    line2 += this.superMarket[teller][cust] + "\n";
		}
	    }
	}
	return line1+ "\n" +line2;
    }

    
    public static void main (String[] args){
	MultipleLine market = new MultipleLine(9, 2, 10, 5);
	MultipleLine emptyMarket = new MultipleLine(0,2,4,1);
	System.out.println(market);
	
	System.out.println("emptyMarket, should be true " + emptyMarket.isEmpty());
	System.out.println("market should be false" + market.isEmpty());
    }
}
