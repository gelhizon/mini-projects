import javax.swing.*;
import java.awt.*;
public class JPoliticalFrame extends JFrame{
	JButton left, right, center;
	public JPoliticalFrame(){
		super("Pork Barrel Politicians");
		left = new JButton("Enrile");
		right = new JButton("Revilla");
		center = new JButton("Napoles");
		setLayout(new BorderLayout());
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(400,100);
		add(left, BorderLayout.WEST);
		add(right, BorderLayout.EAST);
		add(center, BorderLayout.CENTER);
	}
	public static void main(String[] args){
		JPoliticalFrame f = new JPoliticalFrame();
		f.setVisible(true);
	}
}