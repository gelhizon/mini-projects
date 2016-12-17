import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class Model {
	public static final int NUMBER_OF_ROOMS = 20;
	public static final int[] NUMBER_OF_PERSON_PER_ROOM = { 2, 4, 2, 4, 2, 4, 2, 4, 2, 4, 2, 4, 2, 4, 2, 4, 2, 4, 2, 4 };
	public static final int[] PRICES = { 250, 500, 250, 500, 250, 500, 250, 500, 250, 500, 250, 500, 250, 500, 250, 500, 250, 500, 250, 500, };
	public static final String[] ROOM_DESCRIPTION = { "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec dolor justo, eleifend a sagittis a, vehicula a enim. Nullam scelerisque dignissim nulla a condimentum.", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec dolor justo, eleifend a sagittis a, vehicula a enim. Nullam scelerisque dignissim nulla a condimentum.", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec dolor justo, eleifend a sagittis a, vehicula a enim. Nullam scelerisque dignissim nulla a condimentum.", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec dolor justo, eleifend a sagittis a, vehicula a enim. Nullam scelerisque dignissim nulla a condimentum.", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec dolor justo, eleifend a sagittis a, vehicula a enim. Nullam scelerisque dignissim nulla a condimentum.",
			"Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec dolor justo, eleifend a sagittis a, vehicula a enim. Nullam scelerisque dignissim nulla a condimentum.", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec dolor justo, eleifend a sagittis a, vehicula a enim. Nullam scelerisque dignissim nulla a condimentum.", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec dolor justo, eleifend a sagittis a, vehicula a enim. Nullam scelerisque dignissim nulla a condimentum.", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec dolor justo, eleifend a sagittis a, vehicula a enim. Nullam scelerisque dignissim nulla a condimentum.", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec dolor justo, eleifend a sagittis a, vehicula a enim. Nullam scelerisque dignissim nulla a condimentum.",
			"Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec dolor justo, eleifend a sagittis a, vehicula a enim. Nullam scelerisque dignissim nulla a condimentum.", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec dolor justo, eleifend a sagittis a, vehicula a enim. Nullam scelerisque dignissim nulla a condimentum.", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec dolor justo, eleifend a sagittis a, vehicula a enim. Nullam scelerisque dignissim nulla a condimentum.", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec dolor justo, eleifend a sagittis a, vehicula a enim. Nullam scelerisque dignissim nulla a condimentum.", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec dolor justo, eleifend a sagittis a, vehicula a enim. Nullam scelerisque dignissim nulla a condimentum.",
			"Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec dolor justo, eleifend a sagittis a, vehicula a enim. Nullam scelerisque dignissim nulla a condimentum.", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec dolor justo, eleifend a sagittis a, vehicula a enim. Nullam scelerisque dignissim nulla a condimentum.", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec dolor justo, eleifend a sagittis a, vehicula a enim. Nullam scelerisque dignissim nulla a condimentum.", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec dolor justo, eleifend a sagittis a, vehicula a enim. Nullam scelerisque dignissim nulla a condimentum.", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec dolor justo, eleifend a sagittis a, vehicula a enim. Nullam scelerisque dignissim nulla a condimentum." };
	private static final String FILENAME = "rooms.dat";

	private ArrayList<Client> list;

	public Model() {
		list = new ArrayList<Client>();

		try {
			ObjectInputStream input = new ObjectInputStream(new FileInputStream(FILENAME));
			list.addAll((ArrayList<Client>) input.readObject());
			input.close();
		} catch (Exception e) {

		}

		// populate
		if (list.isEmpty()) {
			for (int i = 0; i < NUMBER_OF_ROOMS; i++)
				list.add(null);
		}

	}

	public void add(int i, Client client) {
		list.set(i, client);
		save();
	}

	public void remove(int i) {
		list.set(i, null);
		save();
	}

	public Client get(int i) {
		return list.get(i);
	}

	private void save() {
		try {
			ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream(FILENAME));
			output.writeObject(list);
			output.close();
		} catch (Exception e) {

		}
	}

}
