import java.util.Scanner;
public class GetIDAndAge{
	public static void main(String[]args){
		int ID, age;
		Scanner input = new Scanner(System.in);

		do{
			System.out.print("Enter ID: ");
			ID = input.nextInt();
			if(ID < 0 || ID > 999)
				try{
					throw(new DataEntryException("ID: " + ID));
				}catch(DataEntryException e){
					System.out.println(e.getMessage());
				}
			System.out.print("Enter Age: ");
			age = input.nextInt();
			if(age < 0 || age > 119){
				try{
					throw(new DataEntryException("Age: " + age));
				}catch(DataEntryException e){
					System.out.println(e.getMessage());
				}
			}
		}while(ID != 0 && age != 0);
	}
}