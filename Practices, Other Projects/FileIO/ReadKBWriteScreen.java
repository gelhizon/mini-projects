import java.io.*;
public class ReadKBWriteScreen{
	public static void main(String[] args) throws IOException{
		InputStream istream;
		OutputStream ostream;
		int c;
		final int EOF= -1;
		istream = System.in;
		ostream = System.out;
		System.out.println("Please enter some characters.");
		System.out.println("Press Enter after each group of characters");
		System.out.println("to  see your input echoed to the screen.");
		System.out.println("Press Ctrl + Z when you are done.");
		try{
			while((c = istream.read())  != EOF)
				ostream.write(c);
		}
		catch(IOException e){
			System.out.println("Error: " + e.getMessage());
		}finally{
			istream.close();
			ostream.close();
		}
	}
}
