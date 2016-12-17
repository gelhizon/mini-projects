import javax.swing.JOptionPane;

public class MyProgram{
	public static void main(String[] args){
		int a = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter First Number."));
		int b = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter Second Number."));
		int result = a + b;
		JOptionPane.showMessageDialog(null, "The sum is " + result);
	}
}