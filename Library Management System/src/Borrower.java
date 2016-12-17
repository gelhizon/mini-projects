import java.io.Serializable;

public class Borrower implements Serializable {
	private Book book;
	private Student student;

	public Borrower(Book book, Student student) {
		super();
		this.book = book;
		this.student = student;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

}
