import java.io. *;
import java.util.*;
public class ReadEmployeeFile2 {
	public static void main(String[] args) throws IOException {
		DataInputStream istream;
		final int EOF= -1;
		int id;
		String lastName;
		double pay;
		Scanner input = new Scanner(System.in);
		String fileName = "";
		try {
			System.out.print("Enter file name to use >>");
			fileName = input.nextLine();
			istream = new DataInputStream(new FileInputStream(fileName));
			while((id = istream.readInt()) != EOF){
				lastName = istream.readUTF();
				pay = istream.readDouble();
				System.out.println("Employee #" + id + ": " + lastName + "Pay rate $" + pay);
			}
		}catch(EOFException e) {
			System.out.println("Processing complete");
		}
		catch(FileNotFoundException e) {
			System.out.println(e.getMessage());
		}
	}
}