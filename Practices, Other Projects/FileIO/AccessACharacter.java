import java.io.*;
public class AccessACharacter{
	public static void main(String[] args) throws IOException{
		OutputStream ostream;
		int c;
		RandomAccessFile inFile = new RandomAccessFile("AQuote.txt","r");
		ostream = System.out;
		int pos = 34;
		try{
			inFile.seek(pos);
			c = inFile.read();
			System.out.print("The character in position " + pos + " is ");
			ostream.write(c);
		}catch(IOException e){
			System.out.println("Error: " + e.getMessage());
		}finally{
			System.out.println();
			inFile.close();
			ostream.close();
		}
	}
}
