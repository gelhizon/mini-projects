import java.nio.file.*;
import java.nio.file.attribute.*;
public class FileStatistics{
	public static void main(String[]args){
		Path path = Paths.get("sample2.txt");
		path = path.toAbsolutePath();
	
		System.out.println("Name: " + path.getFileName());
		System.out.println("Folder: " + path.getParent().getFileName());
		System.out.println("Size: " + path.toFile().length());	
		System.out.println("Time of Last Modified: " + FileTime.fromMillis(path.toFile().lastModified()));

	}
}