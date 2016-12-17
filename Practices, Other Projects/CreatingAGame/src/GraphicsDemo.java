import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JFrame;

public class GraphicsDemo extends JFrame {
	public GraphicsDemo() {
		setTitle("Graphics demo with Kilobolt.");
		setSize(800, 480);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	public static void main(String[] args) {

		GraphicsDemo demo = new GraphicsDemo();
	}

	public void paint(Graphics g) {
		g.setColor(Color.RED);
		g.fillRect(0, 0, 800, 480);
		g.setColor(Color.BLUE);
		g.drawRect(300, 120, 200, 50);
		g.setColor(Color.GREEN);
		g.drawString("Hello ", 600, 400);
	}
}
