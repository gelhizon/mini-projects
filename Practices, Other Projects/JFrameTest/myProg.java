import java.util.Random;
import java.util.Scanner;
public class MyProg{
	int[] num;
	Random rand = new Random();
	Scanner input = new Scanner(System.in);	

	public MyProg(){
		try{
			int size = input.nextInt();
			num = new int[size];
		}catch(Exception e){
		}

		for(int i = 0; i < num.length; i++){
			num[i] = randomGenerator(1, 1000);
		}

		//code goes here
	}

	public static void main(String[]args){
		new MyProg();
	}

	public int randomGenerator(int min, int max){
		return min + rand.nextInt(max - min);
	}
}