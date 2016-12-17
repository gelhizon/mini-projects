import java.io.FileNotFoundException;
import java.io.IOException;

public class Model {
	private Borrower borrower;
	private BookFileManager BookFM;
	private StudentFileManager studentFM;

	public Model() {
		BookFM = new BookFileManager();
		studentFM = new StudentFileManager();
	}

	public Borrower getBorrower() {
		return borrower;
	}

	public void setBorrower(Borrower borrower) {
		this.borrower = borrower;
	}

	public BookFileManager getBookFM() {
		return BookFM;
	}

	public void setBookFM(BookFileManager bookFM) {
		BookFM = bookFM;
	}

	public StudentFileManager getStudentFM() {
		return studentFM;
	}

	public void setStudentFM(StudentFileManager studentFM) {
		this.studentFM = studentFM;
	}

}
