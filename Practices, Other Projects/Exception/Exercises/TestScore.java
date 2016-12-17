import java.util.Scanner;
public class TestScore{
	public static void main(String[]args){
		int[] stuID = {1, 2, 3, 4, 5};
		int[] score = new int[5];
		Scanner input = new Scanner(System.in);
		for(int i = 0; i < stuID.length; i++){
			System.out.println("Enter Student ID: " + stuID[i]);
			System.out.print("Enter Score: ");
			score[i] = input.nextInt();
			try{
				if(score[i] > 100)
					throw(new ScoreException(score[i]));
			}catch(ScoreException e){
				System.out.println(e.getMessage());
				score[i] = 0;
			}
		}

		System.out.println("---------------");
		for(int i = 0; i < stuID.length; i++){
			System.out.print("Student ID: " + stuID[i] + " Score: " + score[i] + "\n");
		}
	}
}