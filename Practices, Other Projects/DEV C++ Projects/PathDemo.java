import java.nio.file.*;
public class PathDemo
{
	public static void main(String[] args)
	{
		Path filePath =
		Paths.get("C:\\sample\\sample.txt");
		int count = filePath.getNameCount();
		System.out.println("Path is " + filePath.toString());
		System.out.println("File name is " + filePath.getName(count - 1));
		System.out.println("There are " + count +
		" elements in the file path");
		
	}
}
