import java.util.Scanner;

public class SirEwan2{
	public static void main(String[] args){
		String name;
		int count = 0;
		Scanner input = new Scanner(System.in);
		System.out.println("Enter a String");
		name = input.nextLine();

		for(int i = 0; i < name.length(); i++){
			if(Character.isDigit(name.charAt(i))){
				count++;
			}
		}

		System.out.println("Found " + count + " digit(s).");
	}
}