package sorter;

import gui.SortFormatter;
import java.util.Arrays;

public abstract class Sorter {
	protected int[] numbers;
	protected StringBuilder list;
	public SortFormatter format;

	// CONSTRUCTORS
	public Sorter() {
		list = new StringBuilder();
		numbers = null;
		this.format = new SortFormatter();
	}

	// METHODS
	protected abstract void sort();

	public void setNumbers(int[] numbers) {
		this.numbers = Arrays.copyOf(numbers, numbers.length);
		sort();
		list.append("END");
	}

	public String getList() {
		return list.toString();
	}

}