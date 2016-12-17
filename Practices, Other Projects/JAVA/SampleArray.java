import java.util.Scanner;

public class SampleArray{
	public static void main(String[]args){
		int[] a = new int[4];
		int[] b = new int[2];

		Scanner input = new Scanner(System.in);
		for(int i = 0; i < a.length; i++)
			a[i] = input.nextInt();

		b = a;

		for(int n: b)
			System.out.println(n);

	}
}