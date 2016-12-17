public class Employee{
	int empID;
	double hourlyWage;

	public Employee(int empID, double hourlyWage)throws EmployeeException{
		if(hourlyWage < 6.0 || hourlyWage > 50.0)
			throw(new EmployeeException(empID + " " + hourlyWage));

		this.empID = empID;
		this.hourlyWage = hourlyWage;
	}
}