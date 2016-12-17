import javax.swing.JOptionPane;

public class MyProgram2{
	public static void main(String[] args){
		int a = 0;
		int b = 0;
		int result = 0;

		a = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter First Number."));
		b = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter Second Number."));

		result = a + b;
		
		JOptionPane.showMessageDialog(null, "The sum is " + result);
	}
}