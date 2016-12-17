import java.io.*;
import java.util.*;
public class CreateEmployeeObjectFile{
	public static void main(String[] args) throws IOException{
		ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream("Employees.dat"));
		Employee emp;
		int num = 0;
		final int QUIT = 999;
		String name;
		double rate;
		Scanner in = new Scanner(System.in);
		System.out.print("Enter employee number or " + QUIT + " to quit >> ");
		num = in.nextInt();
		while(num != QUIT){
			System.out.print("Enter name: ");
			name = in.next();
			System.out.print("Enter pay rate: ");
			rate = in.nextDouble();
			emp = new Employee(num, name, rate);
			output.writeObject(emp);
			System.out.print("Enter employee number or " + QUIT + " to quit >> ");
			num = in.nextInt();
		}
		output.close();
	}
}
