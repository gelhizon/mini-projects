import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class JLeftOrRight extends JFrame implements MouseListener{
	JLabel label = new JLabel("Hello, World");
	public JLeftOrRight(){
		setSize(400,300);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		add(label);
		addMouseListener(this);
		setVisible(true);
	}
	public void mouseClicked(MouseEvent e){
		if(e.getButton() == MouseEvent.BUTTON1)
			label.setText("Left Button Clicked!");
		if(e.getButton() == MouseEvent.BUTTON2)
			label.setText("Middle Button Clicked!");
		if(e.getButton() == MouseEvent.BUTTON3)
			label.setText("Right Button Clicked!");
	}
	public void mouseEntered(MouseEvent e){}
	public void mouseExited(MouseEvent e){}
	public void mousePressed(MouseEvent e){}
	public void mouseReleased(MouseEvent e){}
	public static void main(String[]args){
		JLeftOrRight frame = new JLeftOrRight();
	}
}