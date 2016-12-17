import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class JMovingFrame extends JFrame implements ActionListener{
	JPanel[] panels = new JPanel[10];
	int counter = 0;
	JButton b = new JButton("Move!");
	JLabel l = new JLabel("JLabel");
	public JMovingFrame(){
		for(int i = 0; i < panels.length; i++)
			panels[i] = new JPanel();
		setLayout(new FlowLayout());
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		b.addActionListener(this);
		add(b);
		panels[0].add(l);
		for(int i = 0; i < panels.length; i++)
			add(panels[i]);
		pack();
	}
	public void actionPerformed(ActionEvent e){
		panels[counter].remove(l);
		counter++;
		counter = counter % 10;
		panels[counter].add(l);
		revalidate();
		repaint();
		pack();
	}
	public static void main(String[]args){
		JMovingFrame a = new JMovingFrame();
		a.setVisible(true);
	}
}