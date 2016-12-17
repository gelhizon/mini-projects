import java.io.*;
public class ReadRandomStudentFile{
	public static void main(String[] args) throws IOException{
		RandomAccessFile stuFile = new RandomAccessFile("StudentData.dat","rw");
		int id;
		double gpa;
		final int NUMRECORDS = 1000;
		try{
			for(int x = 0; x < NUMRECORDS ; ++x){
				id = stuFile.readInt();
				gpa = stuFile.readDouble();
				if(id != 0)
					System.out.println("ID# " + id + " GPA: " + gpa);
			}
		}
		catch(IOException e){
			System.out.println("Error: " + e.getMessage());
		}finally{
			stuFile.close();
		}
	}
}

