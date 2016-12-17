import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
public class JDemoResistance extends JFrame implements ActionListener{
	JLabel[] labels = new JLabel[5];
	int number = 4;
	JButton button = new JButton("Click Me!");
	public JDemoResistance(){
		super("JDemoResistance");
		labels[0] = new JLabel("1. Too Expensive");
		labels[1] = new JLabel("2. Poor Quality");
		labels[2] = new JLabel("3. Dull");
		labels[3] = new JLabel("4. Unattractive");
		labels[4] = new JLabel("5. Others are Better");
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(new FlowLayout());
		button.addActionListener(this);
		add(button);
		for(int i = 0 ; i < labels.length; i++)
			add(labels[i]);
		pack();
	}

	public void actionPerformed(ActionEvent e){
		if(number >= 0){
			remove(labels[number]);
			number--;
			revalidate();
			repaint();
			pack();
			if(number == -1)
				button.setEnabled(false);
		}
	}

	public static void main(String[] args){
		JDemoResistance myFrame = new JDemoResistance();
	}
}