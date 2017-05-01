//Konnor A. Herbst (HackerMan)
//Eddy D. Varela (Deputy)

public class Main{

    public static void main(String[] args){
	SingleLine single = new SingleLine(27,3,10,5);
	MultipleLine mult = new MultipleLine(27,3,10,5);
	while(!single.step(3)){
	}
	while(!mult.step(3)){
	}
	System.out.println("<Multiple Lines>\n" + mult);
	System.out.println("<Single Teller> \n"+single);
	System.out.println("\nMultiple Line Time: " + mult.time + "\nSingle Line Time: " + single.time);
    }
}
