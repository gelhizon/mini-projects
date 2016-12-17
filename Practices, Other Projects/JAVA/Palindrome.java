import java.util.Scanner;

public class Palindrome{
	public static void main(String[] args){
		String aString = "";
		boolean palindrome = true;
		Scanner input = new Scanner(System.in);

		System.out.println("Enter a String: ");
		aString = input.nextLine();
		for(int i = 0; i < aString.length() / 2; i++){
			if(aString.charAt(i) != aString.charAt(aString.length() - i - 1)){
				palindrome = false;
			}
		}
		if(palindrome)
			System.out.println("Palindrome");
		else
			System.out.println("Not Palindrome");
	}
}