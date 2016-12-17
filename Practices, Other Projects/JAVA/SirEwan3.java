import java.util.Scanner;
public class SirEwan3{
	public static void main(String[]args){
		String name, newname;

		Scanner input = new Scanner(System.in);
		name = input.nextLine();

		newname = Character.toUpperCase(name.charAt(0)) + name.substring(1, name.length());
		System.out.println(newname);
	}
}