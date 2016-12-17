import java.io.*;
public class ReadEmployeeObjectFile{
	public static void main(String[] args)throws IOException, ClassNotFoundException{
		ObjectInputStream in = new ObjectInputStream(new FileInputStream("Employees.dat"));
		Employee emp;
		try{
			while(true){
				emp = (Employee)in.readObject();
				emp.display();
			}
		}catch(EOFException e){
			in.close();
		}
	}
}
