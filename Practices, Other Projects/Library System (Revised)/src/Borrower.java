public class Borrower {
	private Student student;
	private Book book;

	public Borrower(Student student, Book book) {
		super();
		this.student = student;
		this.book = book;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

}
