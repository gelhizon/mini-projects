import javax.swing.JOptionPane;

public class Multiplication{
	public static void main(String[] args){
		int a = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter First Number."));
		int b = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter Second Number."));
		int result = a * b;
		JOptionPane.showMessageDialog(null, "The product is " + result);
	}
}