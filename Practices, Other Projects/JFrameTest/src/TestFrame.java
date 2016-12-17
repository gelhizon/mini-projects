import javax.swing.JFrame;
import javax.swing.JTextField;
import java.awt.FlowLayout;
public class TestFrame {
	public static void main(String[] args){
		JFrame frame = new JFrame("Test Frame!");
		JTextField tf = new JTextField(15);
		
		frame.add(tf);
		frame.setLayout(new FlowLayout());
		frame.pack();
		frame.setVisible(true);
	}
}
