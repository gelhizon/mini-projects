import java.util.Scanner;

public class CompareStrings{
	public static void main(String[] args){
		String name = "ruth berlie";
		String aName;
		Scanner input = new Scanner(System.in);
		System.out.println("Enter Your Name.");
		aName = input.nextLine();
		aName.toLowerCase();
		if(name.equals(aName)){
			System.out.println("Equal");
		}else{
			System.out.println("Not Equal");
		}
	}
}