import javax.swing.JOptionPane;
public class SqrtException{
	public static void main(String[]args){
		String inputString = JOptionPane.showInputDialog(null, "Enter a number: ");
		double number = Double.parseDouble(inputString);
		double sqrt;
		sqrt = Math.sqrt(number);
		if(Double.isNaN(sqrt))
			throw(new ArithmeticException());
		else
			JOptionPane.showMessageDialog(null, "The square root is " + sqrt);
	}
}