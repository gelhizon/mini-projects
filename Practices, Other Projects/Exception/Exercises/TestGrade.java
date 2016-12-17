import java.util.Scanner;
public class TestGrade{
	public static void main(String[]args){
		int[] stuID = {1, 2 ,3, 4, 5, 6, 7, 8, 9, 10};
		char[] grade = new char[10];
		Scanner input = new Scanner(System.in);
		boolean isValid;

		for(int i = 0; i < grade.length; i++){
			isValid = false;
			System.out.print("Student ID: " + stuID[i] + " Grade: ");

			String inputString = input.nextLine();
			grade[i] = inputString.charAt(0);
			for(int j = 0; j < GradeException.grade.length; j++){
				if(grade[i] == GradeException.grade[j]){
					isValid = true;
					break;
				}
			}

			if(!isValid){
				try{
					throw(new GradeException(inputString));
				}catch(GradeException e){
					System.out.println(e.getMessage());
					grade[i] = 'I';
				}
			}
		}

		System.out.println("\n\nStudents:");
		for(int i = 0; i < grade.length; i++){
			System.out.println("Student ID: " + stuID[i] + " Grade: " + grade[i]);
		}

	}
}