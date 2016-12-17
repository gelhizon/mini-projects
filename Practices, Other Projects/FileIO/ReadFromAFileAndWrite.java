import java.io.*;
public class ReadFromAFileAndWrite{
	public static void main(String[] args){
		InputStream istream;
		OutputStream ostream;
		File inputFile = new File("MyData.dat");
		int c;
		final int EOF = -1;
		ostream = System.out;
		try	{
			istream = new FileInputStream(inputFile);
			try	{
				while((c = istream.read()) != EOF)
					ostream.write(c);
			}catch(IOException e) {
				System.out.println("Error: " + e.getMessage());
			} finally {
				try {
					istream.close();
					ostream.close();
				}catch(IOException e) {
					System.out.println("File did not close");
				}
			}
		}catch(FileNotFoundException e) {
			System.exit(1);
		}
	}
}
