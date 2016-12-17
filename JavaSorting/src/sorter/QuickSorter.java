package sorter;

import gui.SortFormatter;

import java.util.Random;

public class QuickSorter extends Sorter {
	private Random rand = new Random();

	@Override
	public void sort() {
		list.append("Quick Sort\n\n");
		quickSort(0, numbers.length - 1);
	}

	public void quickSort(int first, int last) {
		if (last - first > 0) {
			int pivot = numbers[first + rand.nextInt(last - first)];
			int left = first;
			int right = last;

			while (left <= right) {
				while (numbers[left] < pivot)
					left++;
				while (numbers[right] > pivot)
					right--;
				if (left <= right) {
					// swap
					int temp = numbers[left];
					numbers[left] = numbers[right];
					numbers[right] = temp;
					left++;
					right--;

				}
			}
			
			
			list.append("Pivot: " + pivot + "\n" + format.formatArray(numbers) + "\n\n");

			quickSort(first, right);
			quickSort(left, last);
		}
	}

}
