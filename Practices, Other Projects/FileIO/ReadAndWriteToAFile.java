import java.io.*;
public class ReadAndWriteToAFile{
	public static void main(String[] args){
		InputStream istream;
		OutputStream ostream;
		File outputFile = new File("MyData.dat");
		int c;
		final int EOF= -1;
		istream = System.in;
		try{
			ostream = new FileOutputStream(outputFile);
			System.out.println("Type some characters");
			try{
				while((c = istream.read()) != EOF)
				ostream.write(c);
			}catch(IOException e){
				System.out.println("Error: " + e.getMessage());
			}finally{
				try{
					istream.close();
					ostream.close();
				}catch(IOException e){
					System.out.println("File did not close");
				}
			}
		}catch(FileNotFoundException e){
			System.exit(1);
		}
	}
}

