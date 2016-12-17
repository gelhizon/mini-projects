package sorter;

import gui.SortFormatter;

public class SelectionSorter extends Sorter {

	@Override
	public void sort() {
		list.append("Selection Sort\n\n");
		
		for (int i = 0; i < numbers.length - 1; i++) {

			// find the smallest number
			int smallestIndex = i;
			for (int j = i + 1; j < numbers.length; j++) {
				if (numbers[j] < numbers[smallestIndex]) {
					smallestIndex = j;
				}
			}

			// swap
			int temp = numbers[smallestIndex];
			numbers[smallestIndex] = numbers[i];
			numbers[i] = temp;
			
			list.append("Found:" + temp + "\n" + format.formatArray(numbers) + "\n\n");
		}
	}
}
//i = 0
//j = 1
//6 8 4 2 7
//2 8 4 6 7
//2 4 8 6 7
//2 4 6 8 7
//2 4 6 7 8

