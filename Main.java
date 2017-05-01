public class Main{

    public static void main(String[] args){
	SingleLine single = new SingleLine(27,3,10,5);
	MultipleLine mult = new MultipleLine(27,3,10,5);
	while(!single.step()){	   
	}
	while(!mult.step()){
	}
	System.out.println(mult);
	System.out.println(single);
	System.out.println("Multiple Line Time: " + mult.time + " Single Line Time: " + single.time);
    }
}
