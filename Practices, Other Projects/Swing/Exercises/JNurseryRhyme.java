import javax.swing.*;
public class JNurseryRhyme{
	public static void main(String[]args){
		final int FRAME_WIDTH = 250;
		final int FRAME_HEIGHT = 100;
		JFrame frame = new JFrame("Twinkle Twinkle Little Star!");
		frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JLabel rhyme = new JLabel("Twinkle Twinkle Little Star!");
		frame.add(rhyme);
	}
}