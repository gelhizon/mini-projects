package myprog;

public class MyProgram {
	public static void main(String[]args){
		People student = new People();
		
		student.setName("Ako");
		student.setAge(80);
		
		System.out.println("Hi, my name is " + student.getName());
		System.out.println("and i'm " + student.getAge() + " years old");
	}
}
