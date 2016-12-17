public class DemoStackTrace{
	public static void main(String[] args){
	methodA();
	}
	public static void methodA(){
	System.out.println("In methodA()");
		methodB();
	}
	public static void methodB(){
		System.out.println("In methodB()");
		methodC();
	}
	public static void methodC(){
		System.out.println("In methodC()");
		int [] array = {0, 1, 2};
		System.out.println(array[3]);
	}
}
