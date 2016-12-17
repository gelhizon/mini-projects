public class GoTooFar{
	public static void main(String[]args){
		int[] numbers = {5, 10, 15, 20, 25};

		try{
			for(int i = 0; true; i++){
				System.out.println(numbers[i]);
			}
		}catch(ArrayIndexOutOfBoundsException e){
			System.out.println("Now you've gone too far.");
		}
	}
}