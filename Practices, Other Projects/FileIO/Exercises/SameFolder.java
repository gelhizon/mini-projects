import java.nio.file.*;
public class SameFolder{
	public static void main(String[] args){
		Path path1, path2;

		try{
			path1 = Paths.get("sample.txt");
			path2 = Paths.get("sample2.txt");
			System.out.println("They are in the same directory.");
		}catch(InvalidPathException e){
			System.out.println("They are not in the same directory.");
		}
	}
}