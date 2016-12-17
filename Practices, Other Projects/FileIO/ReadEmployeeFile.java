import java.io.*;
import java.util.*;
public class ReadEmployeeFile{
	public static void main(String[] args) throws IOException {
		DataInputStream istream;
		final int EOF = -1;
		int id;
		String lastName;
		double pay;
		try {
			istream = new DataInputStream(new FileInputStream("EmpData.dat"));
			while((id = istream.readInt()) != EOF){
				lastName = istream.readUTF();
				pay = istream.readDouble();
				System.out.println("Employee #" + id + ": " + lastName + "Pay rate $" + pay);
			}
		} catch(EOFException e) {
			System.out.println("Processing complete");
		}
	}
}