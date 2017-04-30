import structure5.*;

public class Customer implements Comparable<Customer> {

    // TODO: fill this class in, adding member variables and
    // methods as needed
    int eventTime;
    int serviceTime;
    /**
     * Creates a Customer that arrives at time step @eventTime and
     * requires @serviceTime time steps to be satisfied after
     * arriving at a service point.
     */
    public Customer(int evTime, int servTime) {
	eventTime = evTime;
	serviceTime = servTime;
    }

    /**
     * Compares Customers by arrival time
     */
    public int compareTo(Customer other) {
	//if this difference is <0 then we know this.eventTime is less than other.eventTime
	return (this.eventTime - other.eventTime);
    }

    public String toString() {
	return ("{Arrival Time: " + Integer.toString(this.eventTime) + " Required Service Time: " + Integer.toString(this.serviceTime) + " } ");
    }

    public static void main(String[] args){
	Customer c1 = new Customer(3,6);
	Customer c2 = new Customer(69, 420);
	System.out.println(c1.compareTo(c2));
	System.out.println(c2.compareTo(c1));
	System.out.println(c1.toString());
	System.out.println(c2.toString());
	
    }
}
