public class BookFileManager extends FileManager {
	
	public BookFileManager() {
		super();
		setFileName("books.dat");
	}
	
	public void addBook(String key, Object value) {
		add(key, value);
	}
}
