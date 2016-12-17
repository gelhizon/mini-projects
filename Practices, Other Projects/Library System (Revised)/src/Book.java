public class Book {
	String booknumber, title, author;

	public Book(String booknumber, String title, String author) {
		this.booknumber = booknumber;
		this.title = title;
		this.author = author;
	}

	public String getBooknumber() {
		return booknumber;
	}

	public void setBooknumber(String booknumber) {
		this.booknumber = booknumber;
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
}
