import java.util.Random;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class JColorFrame extends JFrame implements ActionListener{
	JButton b = new JButton("Click!");
	JLabel l = new JLabel(" ");
	Random r = new Random();
	public JColorFrame(){
		setLayout(new BorderLayout());
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		l.setOpaque(true);
		add(b, BorderLayout.CENTER);
		add(l, BorderLayout.NORTH);
		b.addActionListener(this);
		pack();
	}
	public void actionPerformed(ActionEvent e){
		l.setBackground(new Color(r.nextInt(256), r.nextInt(256), r.nextInt(256)));
	}
	public static void main(String[]args){
		JColorFrame a = new JColorFrame();
		a.setVisible(true);
	}
}