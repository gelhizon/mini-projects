import javax.swing.*;
public class Quilala{
	public static void main(String[]args){
		String hello = "";
		for(int x = 0; x <= 2; x++){
			for(int y = 0; y <= 2; y++){
				hello += "Hello\n";
			}
		}
		JOptionPane.showMessageDialog(null, hello, "Quilala", JOptionPane.QUESTION_MESSAGE);
	}
}