import java.util.Scanner;
public class De{
	public static void main(String[]args){		

		String binary = "";
		int decimal = 0;
		Scanner input = new Scanner(System.in);
		binary = input.nextLine();
		for(int i = 0; i < binary.length(); i++){
			if(binary.charAt(binary.length() - 1 - i) == '1'){
				decimal = decimal + (int)Math.pow(2, i);
			}
		}

		System.out.println(decimal);

	}
}