import java.util.Scanner;
public class A{
	public static void main(String[]args){

	String name;
	char newname;
	String palindrome="";

	Scanner input= new Scanner(System.in);

	name= input.nextLine();

	for(int i= name.length() - 1; i>=0;i- -){
	newname= name.charAt(i);
	palindrome = palindrome + newname;
	
	}
	System.out.println(palindrome);
	if (palindrome.equals(name) )
		System.out.println("Palindrome");

	}


}