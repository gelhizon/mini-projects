import java.util.Scanner;
public class Jungay{
	
	public static void main(String[]args){
		int[] num = new int[20];
		int prime=0;
		Scanner input= new Scanner(System.in);
		for(int i=0; i<5; i++){
			num[i]= input.nextInt();
			for(int x=2; x<=num[i];x++ ){
				prime= num[i]/x;

				if (prime==1 || prime==num[i])
					System.out.print("prime");
			}
		}

	}


}