import javax.swing.*;
import java.awt.event.*;
public class JFrameDisableButton extends JFrame{
	JButton button = new JButton("Click Me");
	public JFrameDisableButton(){
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		button.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				button.setEnabled(false);
			}
		});
		add(button);
		pack();
	}
	public static void main(String[] args){
		JFrameDisableButton a = new JFrameDisableButton();
	}
}