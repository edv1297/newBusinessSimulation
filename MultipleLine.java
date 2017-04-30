import java.util.Random;
import structure5.*;

public class MultipleLine extends BusinessSimulation{
    
    //all the customers
    PriorityQueue<Customer> customers = new VectorHeap<Customers>();
    Vector<PriorityQueue> superMarket = new Vector<PriorityQueue>();
    
    //stores the number of tellers for conveneince 
    int numServicePoints;
    
        public MultipleLine(int numCustomers, int numServicePoints,
			    int maxEventStart, int seed){
	    super(numCusomters, intNumServicePoints, maxEventStart, seed );
	    
	    this.numServicePoints = numServicePoints;
	    
	    while(numServicePoints>0){
		superMarket.add(makeLines);
		numServicePoints--;
	    }
	    
	}

    //this is wrong need to fix asap
    protected PriorityQueue<Customer> makeLines(){
	this.customers = generateCustomer(this.numCustomers, this.maxEventStart, this.seed);
	return this.customers;
    }

    protected boolean step(){
	Iterator<PriorityQueue<Customer>> it = new Iterator(superMarket);
	while(it.hasNext()){
	    PriorityQueue<Customer> temp = new VectorHeap()<>
	    if(--it.next().getFirst().serviceTime==0){
		
	    }
	}
	if(this.superMarket.isEmpty()){
	    return true;
	}
	++this.time;
	for (int teller= 0; teller< multipleLines; ++teller){
	    multipleLines[teller]
	    if(multiplelines[teller].step())
	}
    }
}
