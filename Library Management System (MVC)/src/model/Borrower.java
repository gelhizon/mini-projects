package model;

public class Borrower {
	private Student student;
	private Book book;

	public Borrower() {
		student = new Student();
		book = new Book();
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
