import java.io.Serializable;

public class Book implements Serializable {
	private String title;
	private String author;
	private String bookNumber;

	public Book(String title, String author, String bookNumber) {
		super();
		this.title = title;
		this.author = author;
		this.bookNumber = bookNumber;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getBookNumber() {
		return bookNumber;
	}

	public void setBookNumber(String bookNumber) {
		this.bookNumber = bookNumber;
	}

}
