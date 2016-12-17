package sorter;

import gui.SortFormatter;

public class InsertionSorter extends Sorter {

	@Override
	public void sort() {
		list.append("Insertion Sort\n\n");

		for (int i = 0; i < numbers.length - 1; i++) {
			int j;
			for (j = i + 1; j > 0; j--) {
				if (numbers[j] < numbers[j - 1]) {
					int temp = numbers[j - 1];
					numbers[j - 1] = numbers[j];
					numbers[j] = temp;
				} else {
					break;
				}
			}

			list.append("Insert: " + numbers[j] + "\n" + format.formatArray(numbers) + "\n\n");

		}
	}
}
//i = 2
//j = 3
//6 8 4 2 7
//6 8 4 2 7
//6 4 8 2 7
//4 6 8 2 7
//4 6 2 8 7


