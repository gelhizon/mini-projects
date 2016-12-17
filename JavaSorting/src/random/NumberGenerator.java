package random;

import java.util.Random;

public class NumberGenerator {

	public static void generateNumbers(int[] numbers, int min, int max) {
		Random random = new Random();
		for (int i = 0; i < numbers.length; i++) {
			numbers[i] = min + random.nextInt(max - min);
		}
	}
}
