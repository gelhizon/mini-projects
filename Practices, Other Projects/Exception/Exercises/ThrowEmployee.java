public class ThrowEmployee{
	public static void main(String[]args){
		Employee emp1, emp2, emp3;

		try{
			emp1 = new Employee(1, 5.0);
			System.out.println("Employee emp1 is successfully created.");
		}catch(EmployeeException e){
			System.out.println("Employee emp1 is not successfully created.");
		}

		try{
			emp2 = new Employee(2, 60.0);
			System.out.println("Employee emp2 is successfully created.");
		}catch(EmployeeException e){
			System.out.println("Employee emp2 is not successfully created.");
		}

		try{
			emp1 = new Employee(3, 30.0);
			System.out.println("Employee emp3 is successfully created.");
		}catch(EmployeeException e){
			System.out.println("Employee emp3 is not successfully created.");
		}


	}
}