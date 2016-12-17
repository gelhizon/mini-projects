import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Hashtable;

public class FileManager {
	private Hashtable<String, Object> table;
	private String fileName;

	public FileManager() {
		table = new Hashtable<String, Object>();
		this.fileName = "default.dat";
	}

	public void add(String key, Object value) {
		table.put(key, value);
	}

	public void save() {
		try {
			ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream(fileName));
			output.writeObject(table);
			output.close();
			this.fileName = fileName;
			System.out.println("SAVE");
		} catch (Exception e) {

		}
	}

	public void load() throws FileNotFoundException, IOException, ClassNotFoundException {
		ObjectInputStream input = new ObjectInputStream(new FileInputStream(fileName));
		table.clear();
		table.putAll((Hashtable<String, Object>) input.readObject());
		input.close();
		this.fileName = fileName;
	}

	public Hashtable<String, Object> getTable() {
		return table;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

}
