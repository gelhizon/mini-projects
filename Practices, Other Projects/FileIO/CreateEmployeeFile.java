import java.io.*;
import java.util.*;
public class CreateEmployeeFile{
	public static void main(String[] args){
		DataOutputStream ostream;
		Scanner input = new Scanner(System.in);
		final int DONE = 999;
		int id;
		String lastName;
		double pay;
		try{
			ostream = new DataOutputStream(new FileOutputStream("EmpData.dat"));
			System.out.print("Enter ID number or " + DONE + " to quit >> ");
			id = input.nextInt();
			while(id != DONE){
				input.nextLine();
				System.out.print("Enter  last name >> ");
				lastName = input.nextLine();
				System.out.print("Enter  hourly pay rate >> ");
				pay = input.nextDouble();
				ostream.writeInt(id);
				ostream.writeUTF(lastName);
				ostream.writeDouble(pay);
				System.out.print("Enter ID number or " + DONE + " to quit >> ");
				id = input.nextInt();
			}
			ostream.close();
		}catch(IOException e){
			System.err.println("Error opening file");
		}catch(InputMismatchException e){
			System.err.println("lnvalid data entry");
		}
	}
}

