import java.util.Scanner;

public class NumberConversion{
	public static void main(String[]rags){
		int remainder, number;
		String binary = "";
		Scanner input = new Scanner (System.in);
		number = input.nextInt();
		while(number != 0){
			remainder = number % 2;
			number = number / 2;
			binary = remainder + binary;
		}
		System.out.println(binary);
	}
}