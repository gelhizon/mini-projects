package sorter;

import gui.SortFormatter;

public class BubbleSorter extends Sorter {

	@Override
	public void sort() {
		list.append("Bubble Sort\n\n");

		for (int i = 0; i < numbers.length - 1; i++) {

			// move the largest number on end of array
			for (int j = 0; j < numbers.length - 1; j++) {
				if (numbers[j] > numbers[j + 1]) {
					// swap
					int temp = numbers[j];
					numbers[j] = numbers[j + 1];
					numbers[j + 1] = temp;
					list.append("Swapped: " + numbers[j] + " and " + numbers[j + 1] + "\n");
				} else
					list.append("Cannot swapped: " + numbers[j] + " and " + numbers[j + 1] + "\n");

				list.append(format.formatArray(numbers) + "\n");
				
				list.append("\n");
			}
		}
	}
}