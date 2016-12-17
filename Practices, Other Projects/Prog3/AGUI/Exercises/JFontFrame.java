import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class JFontFrame extends JFrame implements ActionListener{
	JButton b1 = new JButton("Geogia");
	JButton b2 = new JButton("Arial");
	JButton b3 = new JButton("Impact");
	JButton b4 = new JButton("Courier New");
	JLabel label = new JLabel("Text");
	public JFontFrame(){
		setTitle("JFontFrame");
		setLayout(new FlowLayout());
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		b1.addActionListener(this);
		b2.addActionListener(this);
		b3.addActionListener(this);
		b4.addActionListener(this);
		add(b1);
		add(b2);
		add(b3);
		add(b4);
		add(label);
		pack();
	}
	public void actionPerformed(ActionEvent e){
		JButton source = (JButton) e.getSource();
		label.setFont(new Font(source.getText(), Font.PLAIN, 14));
		label.setText(source.getText());
		revalidate();
		repaint();
		pack();
	}
	public static void main(String[]args){
		JFontFrame a = new JFontFrame();
		a.setVisible(true);
	}
}