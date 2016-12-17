import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class JVideo extends JFrame implements ActionListener{
	String[] choice = {"The Hurt Locker", "The Dark Knight", "The Avengers", "Inception", "The Departed", "The Prestige", "Up", "The Kings Speech", "The Artist", "Insidious"};
	JComboBox<String> combo = new JComboBox<String>(choice);
	JLabel label = new JLabel("Price: $1");
	public JVideo(){
		setLayout(new FlowLayout());
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		combo.setSelectedIndex(0);
		combo.addActionListener(this);
		add(combo);
		add(label);
		pack();
		setVisible(true);
	}
	public void actionPerformed(ActionEvent e){
		if(combo.getSelectedIndex() == 0)
			label.setText("Price: $1");
		if(combo.getSelectedIndex() == 1)
			label.setText("Price: $3");
		if(combo.getSelectedIndex() == 2)
			label.setText("Price: $2");
		if(combo.getSelectedIndex() == 3)
			label.setText("Price: $1");
		if(combo.getSelectedIndex() == 4)
			label.setText("Price: $1");
		if(combo.getSelectedIndex() == 5)
			label.setText("Price: $3");
		if(combo.getSelectedIndex() == 6)
			label.setText("Price: $3");
		if(combo.getSelectedIndex() == 7)
			label.setText("Price: $2");
		if(combo.getSelectedIndex() == 8)
			label.setText("Price: $2");
		if(combo.getSelectedIndex() == 9)
			label.setText("Price: $2");
	}
	public static void main(String[]args){
		JVideo v = new JVideo();
	}
}	