import javax.swing.JOptionPane;
public class NegativeArray{
	public static void main(String[]args){
		String inputString = JOptionPane.showInputDialog(null, "Enter Size:");
		int size = Integer.parseInt(inputString);
		int[] number;

		try{
			number = new int[size];
			JOptionPane.showMessageDialog(null, "The array was created.");
		}catch(NegativeArraySizeException e){
			JOptionPane.showMessageDialog(null, "The array was not created.");
		}catch(NumberFormatException e){
			JOptionPane.showMessageDialog(null, "The array was not created.");
		}
	}
}