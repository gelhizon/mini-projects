import java.util.*;
import java.io.*;
public class WriteRandomStudentFile{
	public static void main(String[] args) throws IOException{
		int pos;
		RandomAccessFile stuFile = new RandomAccessFile("StudentData.dat", "rw");
		Scanner input = new Scanner(System.in);
		int id;
		double gpa;
		final int RECORDSIZE = 12;
		final int NUMRECORDS = 1000;
		final int STOP = 999;
		try {
			for(int  x = 0;  x < NUMRECORDS; ++x) {
				stuFile.writeInt(0);
				stuFile.writeDouble(0.0);
			}
		}catch(IOException e){
			System.out.println("Error: " + e.getMessage());
		}finally{
			stuFile.close();
		}
		stuFile = new RandomAccessFile("StudentData.dat", "rw");
		try{
			System.out.print("Enter student 10 number or " + STOP + " to quit: ");
			id = input.nextInt();
			while(id != STOP){
				System.out.print("Enter grade point average: ");
				gpa = input.nextDouble();
				pos = id - 1;
				stuFile.seek(pos * RECORDSIZE);
				stuFile.writeInt(id);
				stuFile.writeDouble(gpa);
				System.out.print("Enter student 10 number or " + STOP + " to quit: ");
				id = input.nextInt();
			}
		}catch(IOException e){
			System.out.println("Error: " + e.getMessage());
		}finally{
			stuFile.close();
		}
	}
}