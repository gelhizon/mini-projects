import java.util.Scanner;

public class Reverse{
	public static void main(String[] args){

		String name = "";
		String newname = "";
		Scanner input = new Scanner(System.in);

		System.out.println("Enter your name: ");
		name = input.nextLine();

		for(int i = name.length() - 1; i >= 0; i--){
			newname = newname + name.charAt(i);
		}

		System.out.println(newname);	
	}
}