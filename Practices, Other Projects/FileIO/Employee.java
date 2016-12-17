import java.io.*;
public class Employee implements Serializable{
	private int idNum;
	String name;
	private double payRate;

	public Employee(int num, String name, double rate){
		idNum = num;
		this.name = name;
		payRate = rate;
	}

	public void display(){
		System.out.println("ID#"   + idNum + " " +
		name + " Pay rate: $" + payRate);
	}
}
