import javax.swing.*;

public class TestFrame1 {
	public static void main(String[] args){
		JFrame frame = new JFrame("Test Frame 1");
		JButton button = new JButton("Click Me!");
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(button);
	}
}
