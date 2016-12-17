import java.util.Scanner;

public class MyMain {
	public static void main(String[] args){
		
		Scanner input = new Scanner(System.in);
		int number;
		
		number = input.nextInt();
		
		Decimalto d = new Decimalto(number);//pass value you want to convert
		
		System.out.println("The Binary Equivalent is :" + d.getBinaryEquivalent());
		System.out.println("The Octal Equivalent is :" + d.getOctalEquivalent());
		System.out.println("The Hexadecimal Equivalent is :" + d.getHexEquivalent());
	}
}