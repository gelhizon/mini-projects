import javax.swing.*;
import java.io.*;
public class ReadRandomStudentFile2{
	public static void main(String[] args) throws IOException{
		int pos;
		RandomAccessFile stuFile = new RandomAccessFile("StudentData.dat","rw");
		String inputString;
		int id;
		double gpa;
		final int RECORDSIZE= 12;
		final int STOP = 999;
		inputString = JOptionPane.showInputDialog(null, "Enter student 10 number or " + STOP + " to quit");
		id = Integer.parseInt(inputString);
		try	{
			while(id != STOP){
				pos = id - 1;
				stuFile.seek(pos * RECORDSIZE);
				id = stuFile.readInt();
				gpa = stuFile.readDouble();
				JOptionPane.showMessageDialog(null, "For 10 # " + id + " GPA is " + gpa);
				inputString = JOptionPane.showInputDialog(null, "Enter student 10 number or " + STOP + " to quit");
				id = Integer.parseInt(inputString);
			}
		}
		catch(IOException e){
			System.out.println("Error: " + e.getMessage());
		}
		finally	{
			stuFile.close();
		}
	}
}