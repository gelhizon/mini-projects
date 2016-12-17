import java.util.Scanner;

public class SirEwan{
	public static void main(String[] args){
		String name;
		char c;
		int count = 0;
		Scanner input = new Scanner(System.in);
		System.out.println("Enter a String");
		name = input.nextLine();
		System.out.println("Enter A Char");
		c = (input.nextLine()).charAt(0);

		for(int i = 0; i < name.length(); i++){
			if(name.charAt(i) == c){
				count++;
			}
		}

		System.out.println("Found " + count + " " + c);
	}
}