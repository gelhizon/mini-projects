public class GradeException extends Exception{
	public static char[] grade = {'A', 'B', 'C', 'D', 'F', 'I'};

	public GradeException(String msg){
		super("GradeException: " + msg);
	}
}