import java.io.Serializable;

public class Student extends Person implements Serializable {
	private String studentNumber;

	public Student(String firstName, String lastName, String middleName, String gender, String dataOfBirth, String address, String contactNumber, String studentNumber) {
		super(firstName, lastName, middleName, gender, dataOfBirth, address, contactNumber);
		this.studentNumber = studentNumber;
	}

	public String getStudentNumber() {
		return studentNumber;
	}

	public void setStudentNumber(String studentNumber) {
		this.studentNumber = studentNumber;
	}

	
}
