import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;


public class MyFrame implements ActionListener  {
	JFrame frame;
	JButton button;
	JTextField textfield;
	JLabel lbl1;
	public MyFrame(){
		frame = new JFrame("my Frame");
		button = new JButton("Clicl");
		textfield = new JTextField(10);
		lbl1= new JLabel("hello");
		
		frame.setLayout(new FlowLayout());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(400, 300);
		frame.setVisible(true);
		
		frame.add(textfield);
		frame.add(button);
		frame.add(lbl1);
		
		button.addActionListener(this);
	}
	
	public void actionPerformed(ActionEvent e){
		Object source= e.getSource();
		
		if (source== button){
			
			lbl1.setText("Hello" + textfield.getText());
		}
	}
	
	public static void main(String[]arg){
		new MyFrame();
	}
}
