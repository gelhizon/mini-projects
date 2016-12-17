import java.io.FileNotFoundException;
import java.io.IOException;

public class StudentFileManager extends FileManager {

	public StudentFileManager()  {
		super();
		setFileName("students.dat");
		try {
			load();
		} catch (FileNotFoundException e) {
			System.out.println("File Not Found");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void addStudent(Student value) {
		String key = value.getStudentNumber();
		add(key, value);
		save();
	}

}
