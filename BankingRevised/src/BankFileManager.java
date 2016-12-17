import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class BankFileManager {
	private ArrayList list;
	private String fileName;
	
	public BankFileManager(String fileName) {
		super();
		list = new ArrayList();
		this.fileName = fileName;
		
		try {
			// Try to reading file if exists then add them
			ObjectInputStream input = new ObjectInputStream(new FileInputStream(fileName));
			list.addAll((ArrayList) input.readObject());
			input.close();
		} catch (Exception e) {
		}
	}

	public void add(Object o) {
		list.add(o);
		save();
	}

	public ArrayList getList() {
		return list;
	}
	
	public String getfileName() {
		return fileName;
	}

	public void remove(int selectedRow) {
		list.remove(selectedRow);
		save();
	}
	
	public void save() {
		try {
			ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream(getfileName()));
			output.writeObject(list);
			output.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
