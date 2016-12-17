import java.util.Scanner;
public class TryToParseString{
	public static void main(String[]args){
		int number;
		Scanner input = new Scanner(System.in);
		try{
			System.out.println("Enter a String");
			number = Integer.parseInt(input.nextLine());
		}catch(NumberFormatException e){
			System.out.println(e.getMessage());
		}
	}
}